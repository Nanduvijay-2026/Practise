package exceptions;

public class PayrollException extends EmployeeManagementException {
    private final int employeeId;
    public PayrollException(int id, String month, Throwable cause) {
        super("Payroll failed for employee " + id +
                " — " + month, "PAYROLL_FAILED", cause);
        this.employeeId = id;
    }
    public int getEmployeeId() { return employeeId; }
}
