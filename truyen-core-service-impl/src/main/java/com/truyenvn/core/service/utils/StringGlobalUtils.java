package com.truyenvn.core.service.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringGlobalUtils {
    public static String covertToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String removeSpaces(String value) {
        String str = value.trim();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') stb.append(str.charAt(i));
            else if (str.charAt(i) == ' ') {
            }
        }
        return str;
    }
}
