import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Define a class named Student to represent individuals with name and age
class Student {
    String name;
    int age;

    // Constructor to initialize Student with name and age
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override the toString method to provide a meaningful string representation of Student
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// Main class to demonstrate sorting objects based on name and age
public class SortingObject {
    public static void main(String[] args) {
        // Create a List to store Student objects
        List<Student> students = new ArrayList<>();

        // Add three Student objects to the list with different names and ages
        students.add(new Student("Alice", 22));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 25));

        // Sorting the list of students by name
        Collections.sort(students, Comparator.comparing(student -> student.name));
        System.out.println("Sorted by Name: " + students);

        // Sorting the list of students by age
        Collections.sort(students, Comparator.comparingInt(student -> student.age));
        System.out.println("Sorted by Age: " + students);
    }
}
