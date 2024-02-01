import java.util.Base64;

public class EncodeDecode {
    public static void main(String[] args) {
        // Original string to be encoded
        String originalString = "Hello, Base64 encoding and decoding!";
        System.out.println("Original String: "+ originalString);

        // Encoding using Base64
        String encodedString = encodeBase64(originalString);
        System.out.println("Encoded String: " + encodedString);

        // Decoding using Base64
        String decodedString = decodeBase64(encodedString);
        System.out.println("Decoded String: " + decodedString);
    }

    // Method to encode a string using Base64
    private static String encodeBase64(String originalString) {
        byte[] encodedBytes = Base64.getEncoder().encode(originalString.getBytes());
        return new String(encodedBytes);
    }

    // Method to decode a Base64 encoded string
    private static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
        return new String(decodedBytes);
    }
}
