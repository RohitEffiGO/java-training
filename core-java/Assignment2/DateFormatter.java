import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DataObject {
    private LocalDateTime timestamp;

    public DataObject(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

public class DateFormatter {
    public static void main(String[] args) {
        String userInput = "29/01/2024 01:00";

        // Define the format of the input string
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Parse the user input to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(userInput, inputFormatter);

        // List of DataObjects with timestamps
        List<DataObject> dataObjects = new ArrayList<>();
        dataObjects.add(new DataObject(localDateTime));

        // Add one day
        LocalDateTime afterOneDay = localDateTime.plusDays(1);
        dataObjects.add(new DataObject(afterOneDay));

        // Subtract 10 days
        LocalDateTime beforeTenDays = localDateTime.minusDays(10);
        dataObjects.add(new DataObject(beforeTenDays));

        // Add 1 hour
        LocalDateTime afterOneHour = localDateTime.plusHours(1);
        dataObjects.add(new DataObject(afterOneHour));

        // Subtract 5 hours, 30 mins
        LocalDateTime beforeFiveHoursThirtyMins = localDateTime.minusHours(5).minusMinutes(30);
        dataObjects.add(new DataObject(beforeFiveHoursThirtyMins));

        // Sort objects using Timestamp/Date
        Collections.sort(dataObjects, (obj1, obj2) ->
                obj1.getTimestamp().compareTo(obj2.getTimestamp()));

        // Display the sorted timestamps
        System.out.println("Sorted DataObjects:");
        for (DataObject dataObject : dataObjects) {
            System.out.println(dataObject.getTimestamp().format(inputFormatter));
        }
    }
}
