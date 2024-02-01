import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkingWithFiles {
    public static void main(String[] args) {
        // Define the paths for input and output files
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Reading from a file using FileInputStream
        try (FileInputStream fileInputStream = new FileInputStream(inputFile)) {
            // Create a byte array to store read data
            byte[] data = new byte[fileInputStream.available()];

            // Read data from the file into the byte array
            fileInputStream.read(data);

            // Print the contents read from the file
            System.out.println("Contents of " + inputFile + ":");
            System.out.println(new String(data));

        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }

        // Writing to a new file using FileOutputStream
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            // Sample data to write to the file
            String outputData = "Hello, this is written to the output file!";

            // Convert the string to bytes and write to the file
            fileOutputStream.write(outputData.getBytes());

            System.out.println("\nData written to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
