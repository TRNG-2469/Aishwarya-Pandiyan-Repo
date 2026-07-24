package com.cohort;

public class StringProcessor {

    public static String reverse(String input) {
        if (input == null) {
            return "";
        }
        int len = input.length();
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = input.charAt(len - 1 - i);
        }
        return new String(result);
    }

    public static int countVowels(String input) {
        if (input == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I'
                    || c == 'o' || c == 'O' || c == 'u' || c == 'U') {
                count++;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }

        char[] cleaned = new char[input.length()];
        int cleanedLen = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ') {
                if (c >= 'A' && c <= 'Z') {
                    c = (char) (c + 32);
                }
                cleaned[cleanedLen] = c;
                cleanedLen++;
            }
        }

        int left = 0;
        int right = cleanedLen - 1;
        while (left < right) {
            if (cleaned[left] != cleaned[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(reverse("hello"));
        System.out.println("[" + reverse(null) + "]");
        System.out.println(countVowels("Hello World"));
        System.out.println(countVowels(null));
        System.out.println(isPalindrome("A man a plan a canal Panama"));
        System.out.println(isPalindrome(null));
        System.out.println(isPalindrome(""));
    }
}
