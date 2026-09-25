package employee;

import exceptions.EmployeeManagementException;
import exceptions.EmployeeNotFoundException;
import exceptions.InsufficientLeaveException;
import exceptions.InvalidSalaryException;

public class ExceptionHandlingDemo {

    public static void main(String[] args) {

        System.out.println("════════════════════════════════════════════════");
        System.out.println(" SETTING UP — CUSTOM EXCEPTIONS ON CREATION");
        System.out.println("════════════════════════════════════════════════\n");

        EmployeeRepository repo = new EmployeeRepository(10);
        PayrollService payroll  = new PayrollService(repo);

        // Valid employees
        repo.add(new Employee(1001, "Priya Sharma", 75000));
        repo.add(new Employee(1002, "Rahul Verma", 65000));
        repo.add(new Employee(1003, "Ananya Reddy", 120000));
        repo.add(new Employee(1004, "Kiran Patel", 55000));

        System.out.println("4 employees created successfully.\n");

        // Invalid creation attempts — caught and handled
        System.out.println("--- Attempting invalid employee creation ---");

        try {
            new Employee(-1, "Bad ID", 50000);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ " + e.getMessage());
        }

        try {
            new Employee(1005, "", 50000);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ " + e.getMessage());
        }

        try {
            new Employee(1005, "Below Minimum", 5000);
        } catch (InvalidSalaryException e) {
            System.out.println("✗ " + e.getMessage() +
                    " (attempted: ₹" + e.getAttempted() + ")");
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" LEAVE MANAGEMENT — try-catch-finally");
        System.out.println("════════════════════════════════════════════════\n");

        Employee emp1 = repo.findById(1001);

        System.out.println("--- Valid leave requests ---");
        try {
            emp1.applyLeave(5);
            emp1.applyLeave(3);
        } catch (EmployeeManagementException e) {
            System.out.println("✗ Leave error: " + e.getMessage());
        } finally {
            System.out.println("  Leave processing complete. Balance: " +
                    emp1.getLeaveBalance() + " days");
        }

        System.out.println("\n--- Insufficient leave ---");
        try {
            emp1.applyLeave(20); // Only 16 remaining
        } catch (InsufficientLeaveException e) {
            System.out.println("✗ " + e.getMessage());
            System.out.println("  Shortfall: " + e.getShortfall() + " days");
        } finally {
            System.out.println("  Leave balance unchanged: " + emp1.getLeaveBalance() + " days");
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" SALARY OPERATIONS — throw in action");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("--- Valid raises ---");
        try {
            emp1.applyRaise(0.10);
            repo.findById(1002).applyRaise(0.15);
        } catch (EmployeeManagementException e) {
            System.out.println("✗ " + e.getMessage());
        }

        System.out.println("\n--- Invalid raises ---");
        try { emp1.applyRaise(-0.05); }
        catch (IllegalArgumentException e) { System.out.println("✗ " + e.getMessage()); }

        try { emp1.applyRaise(0.75); }
        catch (IllegalArgumentException e) { System.out.println("✗ " + e.getMessage()); }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" EMPLOYEE NOT FOUND — EmployeeNotFoundException");
        System.out.println("════════════════════════════════════════════════\n");

        int[] idsToLookup = {1001, 9999, 1003, 8888};

        for (int id : idsToLookup) {
            try {
                Employee emp = repo.findById(id);
                System.out.println("Found: " + emp);
            } catch (EmployeeNotFoundException e) {
                System.out.println("✗ " + e.getMessage() +
                        " (code: " + e.getErrorCode() + ")");
                //
            }
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" MONTHLY PAYROLL — Exception Translation");
        System.out.println("════════════════════════════════════════════════");

        // Deactivate one employee to trigger IllegalStateException
        repo.findById(1004).deactivate();

        // Add invalid ID to trigger EmployeeNotFoundException → PayrollException
        // (would happen in real system if data is corrupted)

        payroll.processMonthlyPayroll("August 2026");


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" MULTI-CATCH & EXCEPTION HIERARCHY");
        System.out.println("════════════════════════════════════════════════\n");

        int[] testIds = {1001, 1002, 7777};

        for (int id : testIds) {
            try {
                Employee emp = repo.findById(id);
                emp.applyRaise(0.20);
                System.out.println("  Raise applied for: " + emp.getName());

            } catch (EmployeeNotFoundException | IllegalStateException e) {
                // Multi-catch — same handling for both
                System.out.println("  ✗ Cannot process ID " + id + ": " + e.getMessage());

            } catch (EmployeeManagementException e) {
                // Base exception — catches anything else in our hierarchy
                System.out.println("  ✗ Business rule violation [" +
                        e.getErrorCode() + "]: " + e.getMessage());
            }
        }


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" EXCEPTION ANTI-PATTERNS — What NOT to do");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("1. EMPTY CATCH — never do this:");
        System.out.println("   try { findById(9999); }");
        System.out.println("   catch (Exception e) { } // Exception swallowed — dangerous");

        System.out.println("\n2. CATCHING TOO BROADLY — avoid:");
        System.out.println("   catch (Exception e) { // Catches NullPointerException too");
        System.out.println("       // Hides programming bugs as business errors");
        System.out.println("   }");

        System.out.println("\n3. LOSING THE CAUSE — always chain:");
        System.out.println("   throw new PayrollException(id, month, originalException);");
        System.out.println("   // NOT: throw new PayrollException(id, month); // Cause lost!");

        System.out.println("\n4. EXCEPTION FOR FLOW CONTROL — use checks instead:");
        System.out.println("   if (index >= 0) { process(arr[index]); } // Check first");
        System.out.println("   // NOT: try { process(arr[index]); }");
        System.out.println("   //      catch (ArrayIndexOutOfBoundsException e) { skip; }");


        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" EXCEPTION HIERARCHY SUMMARY");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("Throwable");
        System.out.println("├── Error (don't catch — JVM problems)");
        System.out.println("│     └── OutOfMemoryError, StackOverflowError");
        System.out.println("└── Exception");
        System.out.println("      ├── RuntimeException (unchecked — programming errors)");
        System.out.println("      │     ├── NullPointerException");
        System.out.println("      │     ├── IllegalArgumentException");
        System.out.println("      │     └── IllegalStateException");
        System.out.println("      └── EmployeeManagementException (our custom base)");
        System.out.println("            ├── EmployeeNotFoundException");
        System.out.println("            ├── InvalidSalaryException");
        System.out.println("            ├── InsufficientLeaveException");
        System.out.println("            └── PayrollException");
    }
}
