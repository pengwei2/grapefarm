package com.pw.grapefarm.util;

import java.util.Random;

public class CommonUtil {

    public static String genRandomStr(){
        return genRandomStr(6);
    }

    public static String genRandomStr(int randomLength){
        String code = "";

        char[] chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();
        int length = chars.length;
        Random random = new Random();

        for (int i = 0; i < randomLength ; i++) {
            code += chars[random.nextInt(length-1)];
        }

        return code;
    }
}
