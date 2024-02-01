import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConversion {
    public static void main(String[] args) {
        String userInput = "29/01/2024 01:00";

        // Define the format of the input string
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Parse the user input to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(userInput, inputFormatter);

        // Convert local timestamp (IST) to Unix Timestamp
        long unixTimestamp = convertToUnixTimestamp(localDateTime);
        System.out.println("Unix Timestamp: " + unixTimestamp);

        // Convert local timestamp (IST) to UTC
        ZonedDateTime utcDateTime = convertToUTC(localDateTime);
        System.out.println("UTC Timestamp: " + utcDateTime);

        // Convert UTC to local timestamp (IST)
        LocalDateTime localDateTimeFromUTC = convertToIST(utcDateTime);
        System.out.println("Local Timestamp (IST) from UTC: " + localDateTimeFromUTC.format(inputFormatter));
    }

    // Method to convert local timestamp (IST) to Unix Timestamp
    private static long convertToUnixTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // Method to convert local timestamp (IST) to UTC
    private static ZonedDateTime convertToUTC(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
    }

    // Method to convert UTC to local timestamp (IST)
    private static LocalDateTime convertToIST(ZonedDateTime utcDateTime) {
        return utcDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }
}
