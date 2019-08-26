package com.home.test.utils;

/**
 * @author malikai
 * @version 1.0
 * @date 2019-8-26 15:33
 */
public class XOR {
    public static void main(String[] args) {
        String s="243900070005B73D";
        System.out.println(yihuo(s));
    }
    public static String yihuo(String content) {
        content = change(content);
        String[] b = content.split(" ");
        int a = 0;
        for (String aB : b) {
            a = a ^ Integer.parseInt(aB, 16);
        }
        if (a < 10) {
            StringBuffer sb = new StringBuffer();
            sb.append("0");
            sb.append(a);
            return sb.toString();
        }
        return Integer.toHexString(a);
    }
    public static String change(String content) {
        String str = "";
        for (int i = 0; i < content.length(); i++) {
            if (i % 2 == 0) {
                str += " " + content.substring(i, i + 1);
            } else {
                str += content.substring(i, i + 1);
            }
        }
        return str.trim();
    }
}
