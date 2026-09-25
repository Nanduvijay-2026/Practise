import java.util.*;

public class Sample {
    public static void main(String[] args)
    {
   List<Integer> num=Arrays.asList(5,6,2,4,8,3,1);
   List<Integer> sortedlist=num.stream().sorted().toList();
   System.out.println(sortedlist);
   String name="Nandhini";
   name=name+" Vijayakumar";
   System.out.print(name);
    }

}
