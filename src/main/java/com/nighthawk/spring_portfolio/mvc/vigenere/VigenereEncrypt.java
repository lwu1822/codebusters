package com.nighthawk.spring_portfolio.mvc.vigenere;

public class VigenereEncrypt{
    public static String encrypt(String text, final String key)
    {
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
        return "{ \"result\": \"" + encrypt(null, null) + "\" }";
    }
 
    public static void main(String[] args)
    {
        System.out.println("Encrypted Text: " + VigenereEncrypt.encrypt(null, null));
    }
}