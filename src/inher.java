
class animal{
    void noise(){
        System.out.println("animals has different sounds");}
}
class dog extends animal{
    void sound()
    {
        System.out.println("dog sounds bow bow");
    }
}
public class inher {
    public static void main(String[] args)
    {
       dog d=new dog();
       d.sound();
       d.noise();
    }
}
