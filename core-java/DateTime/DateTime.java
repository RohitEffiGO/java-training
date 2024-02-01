import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTime {
    public static void main(String[] args) {
        // Traditional Date to String
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        System.out.println("Formatted Date (Traditional): " + formattedDate);

        // String to Traditional Date
        String dateString = "2022-01-31 15:30:00";
        try {
            Date parsedDate = dateFormat.parse(dateString);
            System.out.println("Parsed Date (Traditional): " + parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Using java.time for better clarity and functionality

        // Current date in java.time.LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("Current Date (java.time): " + today);

        // String to java.time.LocalDate
        String dateStr = "2022-01-31";
        LocalDate parsedLocalDate = LocalDate.parse(dateStr);
        System.out.println("Parsed Date (java.time): " + parsedLocalDate);

        // java.time.LocalDate to String
        String formattedLocalDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Formatted Date (java.time): " + formattedLocalDate);
    }
}
