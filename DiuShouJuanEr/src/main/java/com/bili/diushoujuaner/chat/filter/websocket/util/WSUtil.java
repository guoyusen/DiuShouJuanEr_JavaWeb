package com.bili.diushoujuaner.chat.filter.websocket.util;

public class WSUtil {
    public static String[] trimStringArray(String[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].trim().equals("")) {
                String[] trimmedArray = new String[i + 1];
                System.arraycopy(array, 0, trimmedArray, 0, i + 1);
                return trimmedArray;
            }
        }
        return null;
    }

    private static ThreadLocal<Utf8Validator> utf8ValidatorThreadLocal = new ThreadLocal<Utf8Validator>() {
        @Override
        protected Utf8Validator initialValue() {
            return new Utf8Validator();
        }
    };

    public static boolean validateUTF8(byte[] msg) {
        Utf8Validator utf8Validator = utf8ValidatorThreadLocal.get();
        utf8Validator.reset();
        return utf8Validator.validate(msg);
    }
}
