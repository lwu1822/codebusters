package com.nighthawk.spring_portfolio.mvc.vigenere;

public class VigenereEncrypt{
    private final String key;

    public VigenereEncrypt(String key) {
        this.key = key.toUpperCase();
    }

    public String encrypt(String text) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res.toString();
    }

    public String toStringJson() {
        return "{ \"result\": \"" + encrypt(null) + "\" }";
    }
 
    public static void main(String[] args)
    {
        VigenereEncrypt encryptor = new VigenereEncrypt("KEY");
        System.out.println("Encrypted Text: " + encryptor.encrypt("TEXT"));
    }
}
