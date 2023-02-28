package com.nighthawk.spring_portfolio.mvc.affine;

public class AffineEncrypt {
    private static String keyA;
    private static String keyB;
    private static String text;

    public AffineEncrypt(String text, String keyA, String keyB) {
        AffineEncrypt.text = text.toUpperCase();
        AffineEncrypt.keyA = keyA.toUpperCase();
        AffineEncrypt.keyB = keyB.toUpperCase();
    }

    public String encrypt() {
        int a = Integer.parseInt(keyA);
        int b = Integer.parseInt(keyB);
        String CTxt = "";
        for (int i = 0; i < text.length(); i++)
        {
            CTxt = CTxt + (char) ((((a * text.charAt(i)) + b) % 26) + 65);
        }
        return CTxt.toString();
    }

    public String toStringJson() {
        return "{ \"result\": \"" + encrypt() + "\" }";
    }

    // Tester method
    public static void main(String[] args) {
        // Random set of test cases
        AffineEncrypt encryptor = new AffineEncrypt("codecodecode", "3", "7");
        System.out.println("Encrypted Text: " + encryptor.encrypt());
    }
}
