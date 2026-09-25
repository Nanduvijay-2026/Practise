package inheritance;

public class example3 extends example2{
    public example3() {
        super();   // calls example2 → example1 constructor
    }

    public void childinheritance() {
        System.out.println(number1);
        System.out.println(number2);
        System.out.println(number3);
        System.out.println(number4);
    }
    public static void main(String[] args) {
        example3 obj = new example3();
        obj.superinheritance();
        obj.superinheritance1();
        obj.childinheritance();
    }
}
