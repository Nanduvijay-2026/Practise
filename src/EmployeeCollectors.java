import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCollectors {
    String name;
    String Department;
    double Salary;
    public EmployeeCollectors(String name, String Department, double Salary)
    {
        this.name=name;
        this.Department=Department;
        this.Salary=Salary;
    }
    public String toString()
    {
        return name+" "+Department+" "+Salary;
    }

    public static void main(String[] args) {
        ArrayList<EmployeeCollectors> emp=new ArrayList<>();
        emp. add(new EmployeeCollectors("nandhini", "IT", 90000));
        emp. add(new EmployeeCollectors("kani", "HR",60000));
        emp. add(new EmployeeCollectors("loni","IT",60000));
        emp. add(new EmployeeCollectors("lizi","HR",85000));
        emp.forEach(System.out::println);
       Map<String, Double> avgSalary=emp.stream()
               .collect(Collectors.groupingBy(e->e.Department,
                        Collectors.averagingDouble(e->e.Salary)));
        System.out.println("\nAverage Salary by Department:");
        avgSalary.forEach((dept, avg) ->
                System.out.println(dept + " : " + avg)
        );
    }
}
