public class Fullpyramid {

    public static void main(String[] args) {
        int rows = 7;
        for (int i = 1; i <= rows; i++) {
            // print spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // print stars
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
   /* public static void main(String[] args){
        for(int i=1;i<=5;i++)
        {
            for(int j=1;j<=i;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }*/
}

