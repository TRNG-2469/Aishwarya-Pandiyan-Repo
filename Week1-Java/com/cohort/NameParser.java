package com.cohort;

public class NameParser {

    public static String getInitials(String name) {
        if (name == null || name.strip().isEmpty()) {
            return "";
        }

        String[] parts = name.strip().split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                initials.append(Character.toUpperCase(part.charAt(0)));
            }
        }

        return initials.toString();
    }

    public static void main(String[] args) {
        System.out.println("Standard check: " + getInitials("John Doe"));
        System.out.println("Middle name check: " + getInitials("John Fitzgerald Kennedy"));
        System.out.println("Multiple spaces: " + getInitials("  Alice   Smith  "));
        System.out.println("Single name: " + getInitials("Zendaya"));
        System.out.println("Null check: '" + getInitials(null) + "'");
    }
}
