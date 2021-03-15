package com.seyongkim.shortening_url_demo.util;

public class Base52 {
    private final static String[] words
            = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z"};

    public static String encode(long number) {
        StringBuilder shortenedUrl = new StringBuilder();

        while (number % 52 > 0 || "".equals(shortenedUrl.toString())) {
            shortenedUrl.append(words[(int) (number % 52L)]);
            number /= 52;
        }

        return shortenedUrl.toString();
    }
}
