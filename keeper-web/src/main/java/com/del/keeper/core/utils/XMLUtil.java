package com.del.keeper.core.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtil {

    /**
     * 将xml字符串转换为java对象
     *
     * @param xml
     *            字符串
     * @param cla
     *            对象
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> cla) {
        try {
            JAXBContext context = JAXBContext.newInstance(cla);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T t = (T) unmarshaller.unmarshal(new StringReader(xml));
            return t;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 将java对象转换为xml字符串
     *
     * @param obj
     *            对象
     * @param charsetName
     *            编码
     * @return 字符串
     */
    public static <T> String beanToXml(T t, Class<T> cla, String charsetName) {
        try {
            JAXBContext context = JAXBContext.newInstance(cla);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charsetName);
            StringWriter sw = new StringWriter();
            marshaller.marshal(t, sw);
            return sw.toString().replace(" standalone=\"yes\"", "");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }

}
