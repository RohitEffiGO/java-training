// Import necessary classes for using HashMap and Map
import java.util.HashMap;
import java.util.Map;

// Define a class named Person to represent individuals
class Person {
    String name;
    int age;

    // Constructor to initialize Person with name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override the equals method to compare Person objects based on both name and age
    public boolean equals(Object obj) {
        // Check if the compared object is the same reference
        if (this == obj) return true;
        
        // Check if the compared object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast the compared object to Person type
        Person person = (Person) obj;

        // Compare both name and age for equality
        return age == person.age && name.equals(person.name);
    }

    // Override the hashCode method to generate a unique hash code based on name and age
    public int hashCode() {
        // Generate a hash code using the name's hash code and a combination with age
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}

// Main class to demonstrate HashCode and Equals concepts
public class HashCodeEquals {
    public static void main(String[] args) {
        // Create a HashMap to store Person objects and their roles
        Map<Person, String> personMap = new HashMap<>();

        // Create two Person objects with different names and ages
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);

        // Add Person objects to the map with their roles
        personMap.put(person1, "Employee");
        personMap.put(person2, "Manager");

        // Create a searchPerson with the same name and age as person1
        Person searchPerson = new Person("John", 25);

        // Retrieve the role associated with the searchPerson from the map
        String role = personMap.get(searchPerson);

        // Print the role associated with the searchPerson
        System.out.println("Role: " + role);
    }
}
