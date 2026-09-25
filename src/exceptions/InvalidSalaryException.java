package exceptions;

public class InvalidSalaryException extends EmployeeManagementException {
    private final double attempted;
    public InvalidSalaryException(double salary, double min, double max) {
        super(String.format("Salary ₹%.0f invalid. Range: ₹%.0f–₹%.0f",
                salary, min, max), "INVALID_SALARY");
        this.attempted = salary;
    }
    public double getAttempted() { return attempted; }
}
