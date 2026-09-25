import java.util.ArrayList;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {

        // Create a list of names
        List<String> names = new ArrayList<>();
        names.add("Nandhini");
        names.add("Arun");
        names.add("Priya");
        names.add("Kumar");
        names.add("Anitha");

        // 1. Print all names (lambda)
        System.out.println("All names:");
        names.forEach(System.out::println);

        // 2. Print names starting with 'A' (stream + lambda)
        System.out.println("\nNames starting with A:");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // 3. Convert all names to uppercase (stream + method reference)
        System.out.println("\nUppercase names:");
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // 4. Sort names alphabetically (stream + lambda)
        System.out.println("\nSorted names:");
        names.stream()
                .sorted((a, b)-> a.compareTo(b))
                .forEach(System.out::println);

        List<Integer> nums= List.of(1,2,3,4,5,6);

        int sum=nums.stream()
              .filter(n->n%2==0)
              .map(n->n*10)
              .reduce(0,Integer::sum);
      System.out.println(sum);
    }
}
