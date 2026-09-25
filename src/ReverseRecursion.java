public class ReverseRecursion {


    public static void main(String[] args) {
        String input = "Hello World";
       // StringBuilder sb=new StringBuilder(input);
        StringBuilder sb= new StringBuilder(input);
        System.out.println("Reversed String: " +sb.reverse());
           }
 /* public static void main(String[] args) {
      String str = "Hello World";
      char[] arr = str.toCharArray();
      String reversed = "";

      for (int i = arr.length - 1; i >= 0; i--) {
          reversed += arr[i];
      }

      System.out.println("Reversed String: " + reversed);
  }*/
}
