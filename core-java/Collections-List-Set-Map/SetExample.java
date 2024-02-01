import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        // Create a set of integers
        Set<Integer> numbersSet = new HashSet<>();
        numbersSet.add(5);
        numbersSet.add(2);
        numbersSet.add(8);
        numbersSet.add(1);

        // Add duplicate element (won't be added in a set)
        numbersSet.add(2);

        System.out.println("Set: " + numbersSet);

        // Check if an element is present
        System.out.println("Is 5 present? " + numbersSet.contains(5));

        // Remove an element
        numbersSet.remove(2);
        System.out.println("Set after removal: " + numbersSet);
    }
}
