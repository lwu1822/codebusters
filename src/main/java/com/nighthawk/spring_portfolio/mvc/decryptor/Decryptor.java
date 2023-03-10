package com.nighthawk.spring_portfolio.mvc.decryptor;




    public class Decryptor {
        private final String encryptedText;
        private final int shift;
        
        public Decryptor(String encryptedText) {
            this.encryptedText = encryptedText;
            this.shift = 3;
        }
        
        public String decrypt() {
            StringBuilder decryptedText = new StringBuilder();
            for (int i = 0; i < encryptedText.length(); i++) {
                char c = encryptedText.charAt(i);
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