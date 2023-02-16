package com.nighthawk.spring_portfolio.mvc.decryptor;

public class LetterDecryptor {
    private final String encryptedLetterText;
    private final int shift;

    public LetterDecryptor(String encryptedLetterText) {
        this.encryptedLetterText = encryptedLetterText;
        this.shift = 3;
    }

    public String decrypt() {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedLetterText.length(); i++) {
            char c = encryptedLetterText.charAt(i);
            if (Character.isLetter(c)) {
                int shiftValue = Character.isUpperCase(c) ? 'A' : 'a';
                char decryptedChar = (char) (((c + shift) - shiftValue) % 26 + shiftValue);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    public String toStringJson() {
        return "{ \"result\": \"" + decrypt() + "\" }";
    }

    // Tester method
    public static void main(String[] args) {
        // Random set of test cases
        Decryptor decryptor = new Decryptor("abc");
        System.out.println("Decrypted Text: " + decryptor.decrypt());
    }
}