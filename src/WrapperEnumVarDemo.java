import java.util.ArrayList;
import java.util.HashMap;

// ── ENUMS ─────────────────────────────────────────────────────────────────

enum Department {
    ENGINEERING("Engineering", "ENG", 150000.0),
    MARKETING  ("Marketing",   "MKT",  80000.0),
    SALES      ("Sales",       "SAL",  70000.0),
    HR         ("HR",          "HR",   60000.0);

    private final String fullName;
    private final String code;
    private final double averageSalary;

    Department(String fullName, String code, double avgSalary) {
        this.fullName = fullName;
        this.code = code;
        this.averageSalary = avgSalary;
    }

    public String getFullName()      { return fullName; }
    public String getCode()          { return code; }
    public double getAverageSalary() { return averageSalary; }

    public static Department fromCode(String code) {
        for (var d : values()) { // var in enhanced for
            if (d.code.equalsIgnoreCase(code)) return d;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}

enum EmployeeStatus {
    ACTIVE, ON_LEAVE, INACTIVE, TERMINATED;

    public boolean canReceiveSalary() {
        return this == ACTIVE || this == ON_LEAVE;
    }

    public boolean isPayrollEligible() {
        return canReceiveSalary();
    }
}

enum PerformanceGrade {
    A_PLUS(0.30, "Outstanding"),
    A     (0.20, "Exceeds Expectations"),
    B     (0.15, "Meets Expectations"),
    C     (0.10, "Needs Improvement"),
    D     (0.00, "Unsatisfactory");

    private final double bonusRate;
    private final String description;

    PerformanceGrade(double bonusRate, String description) {
        this.bonusRate   = bonusRate;
        this.description = description;
    }

    public double getBonusRate()   { return bonusRate; }
    public String getDescription() { return description; }
}


// ── EMPLOYEE ───────────────────────────────────────────────────────────────

class Employee {

    // Wrapper for nullable field — manager might not exist
    private final Integer    employeeId;
    private       String     name;
    private       Department department;
    private       Double     salary;         // Wrapper — salary can be "not yet set"
    private       EmployeeStatus  status;
    private       PerformanceGrade grade;
    private       Integer    managerId;      // Nullable — null means no manager

    public Employee(int id, String name, Department dept, double salary) {
        // Autoboxing — int and double automatically boxed to Integer and Double
        this.employeeId = id;        // autoboxed to Integer
        this.salary     = salary;    // autoboxed to Double
        this.name       = name;
        this.department = dept;
        this.status     = EmployeeStatus.ACTIVE;
        this.grade      = PerformanceGrade.B;
        this.managerId  = null;      // No manager initially
    }

    public double calculateBonus() {
        if (!status.isPayrollEligible()) return 0.0;
        // Unboxing — Double → double for arithmetic
        return salary * grade.getBonusRate(); // salary unboxed automatically
    }

    public double calculateNetPay() {
        if (!status.isPayrollEligible()) return 0.0;
        var grossPay = salary + calculateBonus(); // var — obviously double
        var pf       = salary * 0.12;             // var — obviously double
        var tax      = calculateTax(grossPay * 12) / 12;
        return grossPay - pf - tax;
    }

    private double calculateTax(double annualGross) {
        if (annualGross > 1500000) return annualGross * 0.30;
        if (annualGross > 1000000) return annualGross * 0.20;
        if (annualGross > 500000)  return annualGross * 0.10;
        return 0.0;
    }

    public boolean hasManager()   { return managerId != null; }

    // Getters
    public int             getEmployeeId() { return employeeId; } // Unboxing Integer → int
    public String          getName()       { return name; }
    public Department      getDepartment() { return department; }
    public double          getSalary()     { return salary; }     // Unboxing Double → double
    public EmployeeStatus  getStatus()     { return status; }
    public PerformanceGrade getGrade()     { return grade; }
    public Integer         getManagerId()  { return managerId; }  // Returns wrapper — nullable

    // Setters
    public void setStatus(EmployeeStatus status)       { this.status = status; }
    public void setGrade(PerformanceGrade grade)       { this.grade = grade; }
    public void setSalary(double salary)               { this.salary = salary; } // Autoboxed
    public void setManagerId(Integer managerId)        { this.managerId = managerId; }

    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, name='%s', dept=%s, salary=₹%.0f, " +
                        "status=%s, grade=%s, manager=%s}",
                employeeId, name, department.getCode(),
                salary, status, grade,
                hasManager() ? managerId : "None"
        );
    }
}


// ── DEMONSTRATION ──────────────────────────────────────────────────────────

public class WrapperEnumVarDemo {

    public static void main(String[] args) {

        System.out.println("════════════════════════════════════════════════");
        System.out.println(" ENUM — DEPARTMENT WITH FIELDS");
        System.out.println("════════════════════════════════════════════════\n");

        // var — type obvious from values()
        for (var dept : Department.values()) {
            System.out.printf("  %-5s | %-15s | Avg Salary: ₹%.0f%n",
                    dept.getCode(), dept.getFullName(), dept.getAverageSalary());
        }

        // Enum fromCode lookup
        var found = Department.fromCode("ENG");
        System.out.println("\nLookup 'ENG': " + found.getFullName());


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" CREATING EMPLOYEES — AUTOBOXING IN ACTION");
        System.out.println("════════════════════════════════════════════════\n");

        // var — types obvious from constructors
        var emp1 = new Employee(1001, "Priya Sharma", Department.ENGINEERING, 75000.0);
        var emp2 = new Employee(1002, "Rahul Verma", Department.MARKETING, 65000.0);
        var emp3 = new Employee(1003, "Ananya Reddy", Department.ENGINEERING, 120000.0);
        var emp4 = new Employee(1004, "Kiran Patel", Department.SALES, 55000.0);

        // Set grades using PerformanceGrade enum
        emp1.setGrade(PerformanceGrade.A_PLUS);
        emp2.setGrade(PerformanceGrade.B);
        emp3.setGrade(PerformanceGrade.A);
        emp4.setGrade(PerformanceGrade.C);

        // Set manager — autoboxing int → Integer
        emp1.setManagerId(3001);
        emp2.setManagerId(3001);

        // emp3, emp4 have null manager — no setManagerId called
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);
        System.out.println(emp4);


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" WRAPPER — NULL CAPABILITY FOR OPTIONAL FIELDS");
        System.out.println("════════════════════════════════════════════════\n");

        Employee[] allStaff = {emp1, emp2, emp3, emp4};

        for (var emp : allStaff) {
            // Integer managerId — nullable, checked before use
            var managerInfo = emp.hasManager()
                    ? "Reports to manager ID: " + emp.getManagerId() // Unboxing Integer → int
                    : "No manager (top-level)";
            System.out.printf("  %-20s | %s%n", emp.getName(), managerInfo);
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" ENUM STATUS — CONTROLS PAYROLL ELIGIBILITY");
        System.out.println("════════════════════════════════════════════════\n");

        // Change some statuses
        emp4.setStatus(EmployeeStatus.ON_LEAVE);

        // Simulate terminated employee
        var terminated = new Employee(1005, "Dev Sharma", Department.HR, 50000.0);
        terminated.setStatus(EmployeeStatus.TERMINATED);

        Employee[] payrollCandidates = {emp1, emp2, emp3, emp4, terminated};

        System.out.println("Payroll Eligibility Check:");
        for (var emp : payrollCandidates) {
            var eligible = emp.getStatus().isPayrollEligible();
            System.out.printf("  %-20s | Status: %-12s | Eligible: %s%n",
                    emp.getName(), emp.getStatus(), eligible);
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" PERFORMANCE GRADE ENUM — BONUS CALCULATION");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("Grade details:");
        for (var grade : PerformanceGrade.values()) {
            System.out.printf("  %-7s | %-25s | Bonus Rate: %.0f%%%n",
                    grade.name(), grade.getDescription(), grade.getBonusRate() * 100);
        }

        System.out.println("\nBonus calculation per employee:");
        for (var emp : allStaff) {
            var bonus = emp.calculateBonus();  // var — obviously double
            var netPay = emp.calculateNetPay(); // var — obviously double
            System.out.printf("  %-20s | Grade: %-7s | Bonus: ₹%-8.0f | Net Pay: ₹%.0f%n",
                    emp.getName(), emp.getGrade().name(), bonus, netPay);
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" WRAPPER UTILITY METHODS");
        System.out.println("════════════════════════════════════════════════\n");

        // Parsing — String to primitive via wrapper
        var csvRecord = "2001,Deepa Nair,MKT,72000.50,true";
        var fields = csvRecord.split(",");

        var parsedId = Integer.parseInt(fields[0]);          // "2001" → 2001
        var parsedName = fields[1];                            // "Deepa Nair"
        var parsedDept = Department.fromCode(fields[2]);       // "MKT" → Department.MARKETING
        var parsedSalary = Double.parseDouble(fields[3]);        // "72000.50" → 72000.5
        var parsedActive = Boolean.parseBoolean(fields[4]);      // "true" → true

        System.out.println("Parsed from CSV:");
        System.out.printf("  ID=%d | Name=%s | Dept=%s | Salary=₹%.2f | Active=%s%n",
                parsedId, parsedName, parsedDept.getFullName(), parsedSalary, parsedActive);

        // Constants
        System.out.println("\nInteger range: " +
                Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Max employee ID safe as int: " + Integer.MAX_VALUE);

        // Comparison
        var salary1 = Integer.valueOf(75000);
        var salary2 = Integer.valueOf(85000);
        System.out.println("\nSalary comparison:");
        System.out.println("  75000 vs 85000: " +
                (Integer.compare(salary1, salary2) < 0 ? "first is lower" : "first is higher"));

        // Autoboxing == trap
        System.out.println("\nAutoboxing == trap:");
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("  127 == 127 (cached):     " + (a == b));       // true
        System.out.println("  128 == 128 (not cached): " + (c == d));       // false!
        System.out.println("  128 equals 128:          " + c.equals(d));    // true
        System.out.println("  → Always use .equals() for wrapper comparison");


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" var — DEPARTMENT GROUPING");
        System.out.println("════════════════════════════════════════════════\n");

        // var with complex generic type — avoids repetition
        var deptGroups = new HashMap<Department, ArrayList<Employee>>();

        for (var dept : Department.values()) {
            deptGroups.put(dept, new ArrayList<>());
        }

        for (var emp : allStaff) {
            deptGroups.get(emp.getDepartment()).add(emp);
        }

        System.out.println("Employees by department:");
        for (var entry : deptGroups.entrySet()) {
            var dept = entry.getKey();
            var employees = entry.getValue();
            if (!employees.isEmpty()) {
                System.out.printf("  %s (%d employees):%n",
                        dept.getFullName(), employees.size());
                for (var emp : employees) {
                    System.out.printf("    - %-20s | ₹%.0f | %s%n",
                            emp.getName(), emp.getSalary(), emp.getGrade().getDescription());
                }
            }
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" SUMMARY");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("Wrapper Classes:");
        System.out.println("  • Bridge between primitives and object world");
        System.out.println("  • Integer, Double, Boolean, Character etc.");
        System.out.println("  • Parsing: Integer.parseInt(), Double.parseDouble()");
        System.out.println("  • Nullable: Double salary = null means 'not set'");
        System.out.println("  • Always .equals() — never == for comparison");

        System.out.println("\nAutoboxing / Unboxing:");
        System.out.println("  • Compiler converts automatically — int ↔ Integer");
        System.out.println("  • Integer cache: -128 to 127 reuse same object");
        System.out.println("  • Null unboxing → NullPointerException");
        System.out.println("  • Performance cost in tight loops");

        System.out.println("\nEnums:");
        System.out.println("  • Type-safe set of named constants");
        System.out.println("  • Can have fields, constructors, methods");
        System.out.println("  • Perfect with switch — exhaustiveness checking");
        System.out.println("  • Store name() not ordinal() to database");
        System.out.println("  • Abstract methods — per-constant behaviour");

        System.out.println("\nvar:");
        System.out.println("  • Local variables only — not fields or parameters");
        System.out.println("  • Statically typed — type fixed at compile time");
        System.out.println("  • Use when type is obvious from right side");
        System.out.println("  • Don't use when type communicates important intent");
        System.out.println("  • Requires initialiser — cannot be null or bare");
    }
}