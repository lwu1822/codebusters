package com.nighthawk.spring_portfolio.mvc.vigenere;

public class VigenereEncrypt {
    private final String key;
    private final String text;

    public VigenereEncrypt(String text, String key) {
        this.key = key.toUpperCase();
        this.text = text.toUpperCase();
    }

    public String encrypt() {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') {
                result.append(c);
                continue;
            }
            int keyChar = key.charAt(j) - 'A';
            result.append((char) ((c + keyChar) % 26 + 'A'));
            j = (j + 1) % key.length();
        }
        return result.toString();
    }

    public String toStringJson() {
        return "{ \"result\": \"" + encrypt() + "\" }";
    }

    // Tester method
    public static void main(String[] args) {
        // Random set of test cases
        VigenereEncrypt encryptor = new VigenereEncrypt("KEY", "TEXT");
        System.out.println("Encrypted Text: " + encryptor.encrypt());
    }
}
