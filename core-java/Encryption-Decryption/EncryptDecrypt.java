import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptDecrypt {
    public static void main(String[] args) {
        try {
            // Generate a secret key for AES encryption
            SecretKey secretKey = generateSecretKey();

            // Original string to be encrypted
            String originalString = "Hello, AES encryption and decryption!";

            // Encryption using AES
            String encryptedString = encryptAES(originalString, secretKey);
            System.out.println("Encrypted String: " + encryptedString);

            // Decryption using AES
            String decryptedString = decryptAES(encryptedString, secretKey);
            System.out.println("Decrypted String: " + decryptedString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate a secret key for AES encryption
    private static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    // Method to encrypt a string using AES
    private static String encryptAES(String originalString, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(originalString.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt a string using AES
    private static String decryptAES(String encryptedString, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        return new String(decryptedBytes);
    }
}
