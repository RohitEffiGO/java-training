import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // Create a map of student IDs and their names
        Map<Integer, String> studentMap = new HashMap<>();
        studentMap.put(1, "Alice");
        studentMap.put(2, "Bob");
        studentMap.put(3, "Charlie");

        // Access value by key
        System.out.println("Name of student with ID 2: " + studentMap.get(2));

        // Modify a value
        studentMap.put(1, "Alicia");
        System.out.println("Updated Map: " + studentMap);

        // Remove a key-value pair
        studentMap.remove(3);
        System.out.println("Map after removal: " + studentMap);
    }
}
