package com.del.keeper.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class StringUtil {

    public static Integer string2Integer(String no) {
        return isAvaliable(no) ? new BigDecimal(no).intValue() : null;
    }

    public static Double string2Double(String no) {
        return isAvaliable(no) ? Double.parseDouble(no) : null;
    }

    /**
     * 生成一个指定位数的随机数，并将其转换为字符串作为函数的返回值。
     *
     * @param numberLength
     *            随机数的位数。
     * @return String 注意随机数可能以0开头。
     */
    public static String randomNumber(int numberLength) {
        // 记录生成的每一位随机数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberLength; i++) {
            // 每次生成一位,随机生成一个0-10之间的随机数,不含10。
            Double ranDouble = Math.floor(Math.random() * 10);
            sb.append(ranDouble.intValue());
        }
        return sb.toString();
    }

    public static Object bytes2Object(byte[] bs) {
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bs);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static byte[] object2Bytes(Object obj) {
        byte[] bs = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            bs = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bs;
    }

    public static String firstCharLowCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String addPrefix(int num, String prefix, int length) {
        return String.format("%04d", num);
    }

    public static boolean isAvaliable(String string) {
        return string != null && !"".equals(string.trim());
    }

    public static String FILL(String s, char c, int n, char f) {
        int iByteLen = StringToBytes(s).length;
        if (iByteLen >= n) {
            return s;
        } else {
            byte[] fillChars = new byte[n - iByteLen];
            for (int i = 0; i < fillChars.length; i++)
                fillChars[i] = (byte) c;

            if (f == 'L')
                return new String(fillChars) + s;
            else
                return s + new String(fillChars);
        }
    }

    public static byte[] StringToBytes(String str) {
        try {
            if (str == null || str.length() <= 0)
                return new byte[0];
            else
                return str.getBytes("GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ByteToString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "GBK");
    }

    public static String getMsgFieldValue(String sMsg, String sKey, char cSeparatorField, char cSeparatorValue) {
        if (sMsg == null || sMsg.length() <= 0)
            return "";
        if (sKey == null || sKey.length() <= 0)
            return "";
        int iMsgLen = sMsg.length();
        int iKeyLen = sKey.length();
        if (sMsg != null && iMsgLen > 0 && iKeyLen > 0) {
            int iStartIndex = 0;
            while (true) {
                iStartIndex = sMsg.indexOf(sKey, iStartIndex);
                if (iStartIndex <= -1)
                    break;
                int iSeparatorFieldIndex = iStartIndex + iKeyLen;
                if ((iStartIndex == 0 && iMsgLen > iSeparatorFieldIndex
                        && sMsg.charAt(iSeparatorFieldIndex) == cSeparatorValue)
                        || (iStartIndex > 0 && iMsgLen > iSeparatorFieldIndex
                                && sMsg.charAt(iStartIndex - 1) == cSeparatorField
                                && sMsg.charAt(iSeparatorFieldIndex) == cSeparatorValue)) {
                    iStartIndex += iKeyLen + 1;
                    int iEndIndex = sMsg.indexOf(cSeparatorField, iStartIndex);
                    iEndIndex = iEndIndex == -1 ? iMsgLen : iEndIndex;
                    return sMsg.substring(iStartIndex, iEndIndex);
                } else {
                    iStartIndex += iKeyLen;
                }
            }
        }
        return "";
    }
}
