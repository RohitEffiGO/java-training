import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsExample {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);

        // Shuffle the list
        Collections.shuffle(numbers);
        System.out.println("Shuffled List: " + numbers);

        // Sort the list
        Collections.sort(numbers);
        System.out.println("Sorted List: " + numbers);

        // Reverse the list
        Collections.reverse(numbers);
        System.out.println("Reversed List: " + numbers);
    }
}
