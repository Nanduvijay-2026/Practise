package employee;

import exceptions.InsufficientLeaveException;
import exceptions.InvalidSalaryException;

class Employee {
    private final int    id;
    private       String name;
    private       double salary;
    private       int    leaveBalance;
    private       boolean isActive;

    private static final double MIN_SALARY = 15000.0;
    private static final double MAX_SALARY = 500000.0;



    public Employee(int id, String name, double salary) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive: " + id);
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        if (salary < MIN_SALARY || salary > MAX_SALARY)
            throw new InvalidSalaryException(salary, MIN_SALARY, MAX_SALARY);

        this.id           = id;
        this.name         = name;
        this.salary       = salary;
        this.leaveBalance = 24;
        this.isActive     = true;
    }

    public void applyLeave(int days) {
        if (!isActive)
            throw new IllegalStateException(name + " is inactive");
        if (days <= 0)
            throw new IllegalArgumentException("Leave days must be positive");
        if (days > leaveBalance)
            throw new InsufficientLeaveException(name, days, leaveBalance);
        leaveBalance -= days;
        System.out.printf("  %s: %d days leave approved. Balance: %d days%n",
                name, days, leaveBalance);
    }

    public void applyRaise(double percent) {
        if (!isActive)
            throw new IllegalStateException(name + " is inactive — cannot raise");
        if (percent <= 0 || percent > 0.5)
            throw new IllegalArgumentException("Raise must be 0–50%: " + percent);
        double old = salary;
        double raised = salary * (1 + percent);
        if (raised > MAX_SALARY)
            throw new InvalidSalaryException(raised, MIN_SALARY, MAX_SALARY);
        salary = raised;
        System.out.printf("  %s: ₹%.0f → ₹%.0f (+%.0f%%)%n",
                name, old, salary, percent * 100);
    }

    public int    getId()           { return id; }
    public String getName()         { return name; }
    public double getSalary()       { return salary; }
    public int    getLeaveBalance() { return leaveBalance; }
    public boolean isActive()       { return isActive; }
    public void deactivate()        { this.isActive = false; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=₹%.0f, " +
                "leave=%d, active=%b}", id, name, salary, leaveBalance, isActive);
    }
}
