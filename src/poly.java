public class poly {
    public static void main(String[] args) {
        String name="kalki";
        String sb=new StringBuilder(name).reverse().toString();
        if(name.equals(sb))
        {
            System.out.println("polyndrome");
        }
        else {
            System.out.println( "not polyndrome");;
        }
    }
}
