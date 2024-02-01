import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // Create a list of strings
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Access elements by index
        System.out.println("First Fruit: " + fruits.get(0));

        // Modify an element
        fruits.set(1, "Grapes");
        System.out.println("Updated List: " + fruits);

        // Remove an element
        fruits.remove("Orange");
        System.out.println("List after removal: " + fruits);
    }
}
