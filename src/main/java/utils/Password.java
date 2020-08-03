package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    private static String get_SHA_512_Hash(String stringToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return generatedPassword;
    }

    public static String hashPassword(String passwordToHash) {
        byte[] salt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        String securePassword = get_SHA_512_Hash(passwordToHash, salt);
        System.out.println(securePassword);

        return securePassword;
    }
        public static String hashEmail(String email) {
        byte[] salt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        String token = get_SHA_512_Hash(email, salt);
        System.out.println(token);

        return token;
    }
}
