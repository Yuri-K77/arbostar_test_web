package com.arbostar.automation.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T jsonFileToObject(String filePath, Class<T> cls) {
        InputStream inputStream = ValueUtils.class.getResourceAsStream(filePath);
        try {
            return objectMapper.readValue(inputStream, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jsonFileToString(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String lineWithText;
//            bufferedReader.lines();
            while ((lineWithText = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineWithText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //isTextFormatCorrect("90.45%", "\\d+\\.\\d+%")
    public static boolean isTextFormatCorrect(String text, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.matches();
    }
}