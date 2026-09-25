package exceptions;

public class InsufficientLeaveException extends EmployeeManagementException {
    private final int requested, available;
    public InsufficientLeaveException(String name, int req, int avail) {
        super(name + " requested " + req + " days but has only " +
                avail + " available", "INSUFFICIENT_LEAVE");
        this.requested = req; this.available = avail;
    }
    public int getShortfall() { return requested - available; }
}
