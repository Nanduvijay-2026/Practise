//import java.util.*;



public class array {
    /* public static void main(String[] args) {
         String str = "programming";
         Set<Character> set = new HashSet<>();

         for(char c : str.toCharArray()) {
             if(!set.add(c)) {
                 System.out.println("Duplicate: " + c);
             }
         }
     }*/
/*public static void main(String[] args)
{
    int[] num={30,40,20,15};
    int min=num[0], max=num[0];
   Arrays.sort(num);
    System.out.println(num[num.length-2]);
    System.out.println(num[num.length-1]);
}*/
    public static void main(String[] args) {
        int[] arr = {10, 40, 5, 8, 30};
        int slarge = Integer.MIN_VALUE;
        int large = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > large) {
                slarge = large;
                large = num;
            } else if (num > slarge && num != large) {
                slarge = num;
            }
        }
        System.out.println("Second largeest number is:" + slarge);
        System.out.println("Largeest number is:" + large);
    }
}

