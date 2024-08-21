package org.ifarmr.utils;


public class FullNameFormatter {

    public static String formatFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return fullName;
        }

        // Split the full name by spaces
        String[] nameParts = fullName.trim().split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : nameParts) {
            if (part.length() > 0) {
                // Capitalize the first letter of each part and make the rest lowercase
                formattedName.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return formattedName.toString().trim();
    }
}
