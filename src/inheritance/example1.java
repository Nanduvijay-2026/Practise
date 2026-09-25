package inheritance;

public class example1 {

    int id;
    String name;
    int empno=98789;
    String empname="Nandhini";
    int number1 = 10;
    int number2=20;
    int number3=30;
    public example1()
    {
        this(294,"sathya");
        System.out.println("employee id is:" + this.empno);
        System.out.println("employee name is:"+this.empname);
}
    public example1(int id,String name)
    {
        this.id=id;
this.name=name;

        String name1 = this.name;
       // System.out.println(this.id "  ,  " this.name);
    }
public void superinheritance()
{

    if(empno>10000)
    {
        System.out.println("the employee is active");
    }
    else {
        System.out.println("the employee is inactive");
    }
}

}