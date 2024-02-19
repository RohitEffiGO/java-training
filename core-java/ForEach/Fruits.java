import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class Fruits{
    public static void main(String[] args) {
        Map<String, String> myMap = new HashMap<>(Map.of("a", "apple", "p", "peach", "b", "banana"));
        myMap.entrySet().removeIf(entry -> entry.getValue().equals("b"));
        System.out.println(myMap);
        
        Map<String, String> filteredMap = myMap.entrySet().stream()
        .filter(entry -> entry.getValue().startsWith("a"))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(filteredMap);

    }
}