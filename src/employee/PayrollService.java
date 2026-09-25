package employee;

import exceptions.EmployeeNotFoundException;
import exceptions.PayrollException;

class PayrollService {

    private final EmployeeRepository repo;

    public PayrollService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public double calculateNetPay(int employeeId, String month) {
        try {
            Employee emp = repo.findById(employeeId); // May throw EmployeeNotFoundException

            if (!emp.isActive())
                throw new IllegalStateException(emp.getName() + " is inactive");

            double gross  = emp.getSalary();
            double pf     = gross * 0.12;
            double tax    = calculateTax(gross * 12) / 12;
            double net    = gross - pf - tax;

            System.out.printf("  %-20s | Gross: ₹%.0f | PF: ₹%.0f | " +
                            "Tax: ₹%.0f | Net: ₹%.0f%n",
                    emp.getName(), gross, pf, tax, net);

            return net;

        } catch (EmployeeNotFoundException e) {
            // Re-throw as PayrollException — exception translation
            throw new PayrollException(employeeId, month, e);
        }
    }

    private double calculateTax(double annualSalary) {
        if (annualSalary > 1500000) return annualSalary * 0.30;
        if (annualSalary > 1000000) return annualSalary * 0.20;
        if (annualSalary > 500000)  return annualSalary * 0.10;
        return 0;
    }

    public void processMonthlyPayroll(String month) {
        System.out.println("\n  Processing payroll for: " + month);
        System.out.println("  " + "─".repeat(75));

        Employee[] all = repo.findAll();
        double totalNet  = 0;
        int    processed = 0;
        int    skipped   = 0;

        for (Employee emp : all) {
            try {
                double net = calculateNetPay(emp.getId(), month);
                totalNet += net;
                processed++;

            } catch (PayrollException e) {
                // Log and continue — don't let one failure stop all payroll
                System.out.println("  ✗ SKIPPED [" + e.getEmployeeId() + "]: " +
                        e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("    Caused by: " + e.getCause().getMessage());
                }
                skipped++;

            } catch (IllegalStateException e) {
                System.out.println("  ✗ SKIPPED [" + emp.getName() + "]: " +
                        e.getMessage());
                skipped++;
            }
        }

        System.out.println("  " + "─".repeat(75));
        System.out.printf("  Processed: %d | Skipped: %d | " +
                "Total Payout: ₹%.0f%n", processed, skipped, totalNet);
    }
}
