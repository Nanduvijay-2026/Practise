package exceptions;

public class EmployeeNotFoundException extends EmployeeManagementException {
    private final int employeeId;
    public EmployeeNotFoundException(int id) {
        super("No employee found with ID: " + id, "EMP_NOT_FOUND");
        this.employeeId = id;
    }
    public int getEmployeeId() { return employeeId; }
}
