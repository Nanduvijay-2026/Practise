import java.util.*;

public class input {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder("Hello");
        sb.append("World");
        System.out.println(sb);
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the first number");
        int i= s.nextInt();
        System.out.println(" Enter the second number");
        int j= s.nextInt();
        int z= i+ j ;
        System.out.println("the added value is:" +z);
    }
}
