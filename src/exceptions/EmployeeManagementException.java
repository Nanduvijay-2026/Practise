package exceptions;

public class EmployeeManagementException extends RuntimeException {
    private final String errorCode;
    public EmployeeManagementException(String msg, String code) {
        super(msg); this.errorCode = code;
    }
    public EmployeeManagementException(String msg, String code, Throwable cause) {
        super(msg, cause); this.errorCode = code;
    }
    public String getErrorCode() { return errorCode; }
}
