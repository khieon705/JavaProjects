public class InputSanitizer {
    public static String formatOutputString(String string) {
        if (string.isEmpty()) {
            return string;
        }

        StringBuilder sb = new StringBuilder();
        String[] parts = string.split(" ");

        for (String part : parts) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1)).append(" ");
        }

        return sb.toString().trim();
    }

    public static String formatStoredString(String string) {
        if (string.isEmpty()) {
            return string;
        }

        return string.toLowerCase().trim();
    }
}
