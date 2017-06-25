package com.del.keeper.plugin;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.*;

import java.util.*;

/**
 * 创建mybatis version lock
 */
public class OptimisticLockVersionXmlPlugin extends PluginAdapter {

    private Map<FullyQualifiedTable, List<XmlElement>> elementsToAdd;
    private Map<FullyQualifiedTable, List<XmlElement>> elementsToRemove;

    public OptimisticLockVersionXmlPlugin() {
        elementsToAdd = new HashMap<FullyQualifiedTable, List<XmlElement>>();
        elementsToRemove = new HashMap<FullyQualifiedTable, List<XmlElement>>();
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 从原xml获取XmlElement
     */
    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            copyAndSaveElement(element, introspectedTable.getFullyQualifiedTable());
        }
        return true;
    }

    /**
     * 从原xml获取XmlElement
     */
    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            copyAndSaveElement(element, introspectedTable.getFullyQualifiedTable());
        }
        return true;
    }

    /**
     * 从原xml获取XmlElement
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            copyAndSaveElement(element, introspectedTable.getFullyQualifiedTable());
        }
        return true;
    }

    /**
     * 从原xml获取XmlElement
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            copyAndSaveElement(element, introspectedTable.getFullyQualifiedTable());
        }
        return true;
    }

    /**
     * 从原xml获取XmlElement
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
            copyAndSaveElement(element, introspectedTable.getFullyQualifiedTable());
        }
        return true;
    }

    /**
     * 把修改后的XmlElement返回给generator
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        List<XmlElement> elementsRemove = elementsToRemove.get(introspectedTable.getFullyQualifiedTable());
        if (elementsRemove != null) {
            for (XmlElement elementRemove : elementsRemove) {
                List<Element> elementsRoot = document.getRootElement().getElements();
                if (elementsRoot != null) {
                    for (Iterator<Element> iterator = elementsRoot.iterator(); iterator.hasNext();) {
                        Element element2 = (Element) iterator.next();
                        if (elementRemove == element2) {
                            iterator.remove();
                        }
                    }
                }

            }
        }
        List<XmlElement> elementsAdd = elementsToAdd.get(introspectedTable.getFullyQualifiedTable());
        if (elementsAdd != null) {
            for (XmlElement elementAdd : elementsAdd) {
                document.getRootElement().addElement(elementAdd);
            }
        }
        return true;
    }

    /**
     * 修改XmlElement
     *
     * @param element
     * @param fqt
     * @author xp
     */
    private void copyAndSaveElement(XmlElement element, FullyQualifiedTable fqt) {
        XmlElement myElement = null;
        String judgeName = null;
        try {
            if (element.getAttributes().size() > 0) {
                for (Iterator<Attribute> iterator = element.getAttributes().iterator(); iterator.hasNext();) {
                    Attribute attribute = iterator.next();
                    if ("id".equals(attribute.getName())) {
                        judgeName = attribute.getValue();
                        System.out.println(judgeName);
                    }
                    break;
                }
            }
            myElement = (XmlElement) recursionElement(element, judgeName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // save the new element locally. We'll add it to the document later
        List<XmlElement> elements = elementsToAdd.get(fqt);
        if (elements == null) {
            elements = new ArrayList<XmlElement>();
            elementsToAdd.put(fqt, elements);
        }
        elements.add(myElement);

        List<XmlElement> elements2 = elementsToRemove.get(fqt);
        if (elements2 == null) {
            elements2 = new ArrayList<XmlElement>();
            elementsToRemove.put(fqt, elements2);
        }
        elements2.add(element);
    }

    private XmlElement recursionElement(XmlElement oldElement, String judgeName) {
        XmlElement newElement = new XmlElement(oldElement.getName());
        if (oldElement.getAttributes().size() > 0) {
            for (Iterator<Attribute> iterator = oldElement.getAttributes().iterator(); iterator.hasNext();) {
                Attribute attribute = iterator.next();
                Attribute newAttribute = null;
                if (judgeName.contains("insert") && "test".equals(attribute.getName())
                        && "version != null".equals(attribute.getValue())) {
                    newAttribute = new Attribute(attribute.getName(), "version == null");
                } else {
                    newAttribute = new Attribute(attribute.getName(), attribute.getValue());
                }
                newElement.addAttribute(newAttribute);
            }
        }

        List<Element> elements = oldElement.getElements();
        if (elements != null && elements.size() > 0) {
            for (Iterator<Element> iterator = elements.iterator(); iterator.hasNext();) {
                Element element = iterator.next();
                Class<? extends Element> clazz = element.getClass();
                if (clazz.isAssignableFrom(XmlElement.class)) {
                    newElement.addElement(recursionElement((XmlElement) element, judgeName));
                } else if (clazz.isAssignableFrom(TextElement.class)) {
                    String content = ((TextElement) element).getContent();
                    if (content.contains("#{version,jdbcType=INTEGER}")
                            && ("insert".equals(judgeName) || "insertSelective".equals(judgeName))) {
                        content = replace(content, "#{version,jdbcType=INTEGER}", "1");
                        TextElement textElement = new TextElement(content);
                        newElement.addElement(textElement);
                    } else if (content.contains("version = #{version,jdbcType=INTEGER},")
                            && ("updateByPrimaryKeySelective".equals(judgeName)
                                    || "updateByPrimaryKey".equals(judgeName))) {
                        content = replace(content, "version = #{version,jdbcType=INTEGER},",
                                "version = #{version+1,jdbcType=INTEGER},");
                        TextElement textElement = new TextElement(content);
                        newElement.addElement(textElement);
                    } else if (content.contains("where id = #{id,jdbcType=BIGINT}")
                            && ("updateByPrimaryKeySelective".equals(judgeName)
                                    || "updateByPrimaryKey".equals(judgeName))) {
                        content = replace(content, "where id = #{id,jdbcType=BIGINT}",
                                "where id = #{id,jdbcType=BIGINT} and version = #{version,jdbcType=INTEGER}");
                        TextElement textElement = new TextElement(content);
                        newElement.addElement(textElement);
                    } else if (content.contains("where id = #{id,jdbcType=INTEGER}")
                            && ("updateByPrimaryKeySelective".equals(judgeName)
                                    || "updateByPrimaryKey".equals(judgeName))) {
                        content = replace(content, "where id = #{id,jdbcType=INTEGER}",
                                "where id = #{id,jdbcType=INTEGER} and version = #{version,jdbcType=INTEGER}");
                        TextElement textElement = new TextElement(content);
                        newElement.addElement(textElement);
                    } else {
                        TextElement textElement = new TextElement(content);
                        newElement.addElement(textElement);
                    }
                }
            }
        }
        return newElement;
    }

    private static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null) {
            return null;
        }
        int i = 0;
        if ((i = strSource.indexOf(strFrom, i)) >= 0) {
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strTo.toCharArray();
            int len = strFrom.length();
            StringBuffer buf = new StringBuffer(cSrc.length);
            buf.append(cSrc, 0, i).append(cTo);
            i += len;
            int j = i;
            while ((i = strSource.indexOf(strFrom, i)) > 0) {
                buf.append(cSrc, j, i - j).append(cTo);
                i += len;
                j = i;
            }
            buf.append(cSrc, j, cSrc.length - j);
            return buf.toString();
        }
        return strSource;
    }

}
