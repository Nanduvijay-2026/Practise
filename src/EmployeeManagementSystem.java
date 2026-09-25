
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        int[] EmployeeId = {1001, 1002, 1003, 1004, 1005, 1006};
        String[] name = {"Nandha kumar", "Vinoth ragu", "Raj Kumar", "Ammu Hari", "Kunal kapoor", "sanjay manoj"};
        String[] Department = {"Engineering", "Marketing", "Sales", "HRD", "Engineering", "Engineering"};
        double[] Salary = {115900.5, 29500.9, 50490.7, 84390.6, 108354.4, 60050.4};
        int[]  YearsofService= {10, 2, 5, 7, 12, 6};
        char[] PerformanceGrade = {'B', 'C', 'A', 'D', 'C', 'C'};
        boolean[] IsActive = {true, true, true, true, false, true};


        String[] empCode =new String[10];
        String first3="",highExpName="";
        double[] GrossPay =new double[10];
        long[] NetPay =new long[10];
        long totalSalary = 0, totalnetpay = 0;
        double highSalary = 0, lowestSalary = Integer.MAX_VALUE;
        String highestName="",lowestName="";
        int ecount = 0, mcount = 0, scount = 0, hcount = 0,highExp=0, count = 0;
        double esalary = 0, msalary = 0, ssalary = 0, hsalary = 0;
        double[] Tax=new double[10];
        double HRA, Bonus;


        for (int i = 0; i < EmployeeId.length; i++) {
            GrossPay[i] = Salary[i] * 12;
            if (GrossPay[i] > 1200000) {
                Tax[i] = GrossPay[i] * 30 / 100;
            } else if (GrossPay[i] > 600000 && GrossPay[i] < 1200000) {
                Tax[i] = GrossPay[i] * 20 / 100;
            } else if (GrossPay[i] > 250000 && GrossPay[i] < 600000) {
                Tax[i] = GrossPay[i] * 10 / 100;
            } else {
                Tax[i] = 0;
            }
            if (IsActive[i]) {
                switch (PerformanceGrade[i]) {
                    case 'A':
                        HRA = (Salary[i] * 50) / 100;
                        Bonus = Salary[i] * 20 / 100;
                        NetPay[i] = Math.round(Salary[i] + HRA + Bonus - Tax[i] / 12);
                        //NetPay=NetPay/12;
                        break;
                    case 'B':
                        HRA = (Salary[i] * 50) / 100;
                        Bonus = Salary[i] * 15 / 100;
                        NetPay[i] = Math.round(Salary[i] + HRA + Bonus - Tax[i] / 12);
                        // NetPay=NetPay/12;
                        break;
                    case 'C':
                        HRA = (Salary[i] * 40) / 100;
                        Bonus = Salary[i] * 10 / 100;
                        NetPay[i] = Math.round(Salary[i] + HRA + Bonus - Tax[i] / 12);
                        // NetPay=NetPay/12;
                        break;
                    case 'D':
                        HRA = (Salary[i] * 30) / 100;
                        Bonus = 0;
                        NetPay[i] = Math.round(Salary[i] + HRA + Bonus - Tax[i] / 12);
                        //NetPay=NetPay/12;
                        break;
                }
                totalnetpay = totalnetpay + NetPay[i];
            }
            else{
                System.out.println("This employee is not Active");
            }
            if (Salary[i] > highSalary&& IsActive[i]) {
                highSalary = Salary[i];
                highestName=name[i];
            }

            if (Salary[i] < lowestSalary  && IsActive[i]) {
                lowestSalary = Salary[i];
                lowestName = name[i];
            }
            totalSalary = (long) (totalSalary + Salary[i]);
            if (IsActive[i]) {
                count++;
                if (Department[i].equals("Engineering")) {
                    ecount++;
                    esalary = esalary + Salary[i];
                    if (YearsofService[i] > highExp) {
                        highExp = YearsofService[i];
                        highExpName=name[i];
                    }
                } else if (Department[i].equals("Marketing")) {
                    mcount++;
                    msalary = msalary + Salary[i];
                } else if (Department[i].equals("Sales")) {
                    scount++;
                    ssalary = ssalary + Salary[i];
                } else if (Department[i].equals("HRD")) {
                    hcount++;
                    hsalary = hsalary + Salary[i];
                }
            }
        }long avgSalary=totalSalary/count;

        System.out.println("==============================================");
        System.out.println("        TechCorp Solutions — Payroll Report");
        System.out.println("                 August 2026");
        System.out.println("==============================================");
        System.out.println("      Name             Department       Grade           GrossPay         Tax                      Net Pay       EmployeeCode ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < EmployeeId.length; i++) {
            String Status = IsActive[i] ? "Yes" : "No";
            first3 = Department[i].toUpperCase().substring(0, 3);
            empCode[i] = "TC-" + first3 + "-" + EmployeeId[i] + "-2026";

            if (Status.equals("Yes")) {
                String row = String.format("%-20s, %-20s ,%-5c, %-15f, %-15.2f, %20d, %s",
                        name[i],
                        Department[i],
                        PerformanceGrade[i],
                        GrossPay[i],
                        Tax[i],
                        NetPay[i],
                        empCode[i]);
                System.out.println(row);


            }

        }
        System.out.println("==============================================");
        System.out.println("         PayRoll Calculation                  ");
        System.out.println("==============================================");
        for (int i = 0; i < EmployeeId.length; i++) {
            System.out.println(name[i] + "'s netpay is  " + NetPay[i]);
        }
        long AverageSalary = totalSalary / (EmployeeId.length);

        System.out.println("==============================================");
        System.out.println("          Department Analytics                ");
        System.out.println("==============================================");
        System.out.println("Total payroll Cost for active employees:"+totalSalary);
        System.out.println("Average Salary of all employees:        "+avgSalary);
        System.out.println(highestName + " is getting Highest Salary- " + highSalary);
        System.out.println(lowestName + " is getting Lowest Salary-  " + lowestSalary);
        System.out.println("Active Employees in Engineering Department  "+ecount+"   and total salary"+esalary);
        System.out.println("Active Employees in Marketing Department    "+mcount+"   and total salary"+msalary);
        System.out.println("Active Employees in Sales Department        "+scount+"   and total salary"+ssalary);
        System.out.println("Active Employees in HR Department           "+hcount+"   and total salary"+hsalary);

        System.out.println("==============================================");
        System.out.println("          Search & Validation               ");
        System.out.println("==============================================");
        System.out.println("  Employee id      Name          Department     Salary        YearsofSerice    Grade    IsActive  ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (int i = 0; i < EmployeeId.length; i++) {
            if (Department[i].equals("Engineering")) {
                ecount++;
                esalary = esalary + Salary[i];
                String row = String.format("%-5d  %20s  %-20s  %-10f  %-5d  %5c   %5b",
                        EmployeeId[i],
                        name[i],
                        Department[i],
                        Salary[i],
                        YearsofService[i],
                        PerformanceGrade[i],
                        IsActive[i]);
                System.out.println(row);
            } else if (Department[i].equals("Marketing")) {
                mcount++;
                msalary = msalary + Salary[i];
            } else if (Department[i].equals("Sales")) {
                scount++;
                ssalary = ssalary + Salary[i];
            } else if (Department[i].equals("HRD")) {
                hcount++;
                hsalary = hsalary + Salary[i];
            }
            boolean isNameValid =
                    !name[i].isBlank() &&
                            name[i].contains(" ") &&
                            name[i].length() >= 5 &&
                            name[i].length() <= 50;
            if (Salary[i] < 15000) {
                System.out.println("WARNING:" + name[i] + " salary below minimum wage");
            } else if (Salary[i] > 200000) {
                System.out.println("ALERT:" + name[i] + "salary requires CFO approval");
            } else if (PerformanceGrade[i] == 'A' && Salary[i] < 50000) {
                System.out.println("REVIEW: Grade A employee " + name[i] + " appears under paid");
            }

        }

        System.out.println("==============================================");
        System.out.println("              SUMMARY                         ");
        System.out.println("==============================================");

        System.out.println("The Total Number of the employee is: " + EmployeeId.length);
        System.out.println("The Total Number of the Active employee is: " + count);
        System.out.println("The Total Payroll of the employee is: " + totalSalary);
        System.out.println("The Average Salary of all employee is: " + AverageSalary);
        System.out.println("The Average NetPAy of the Active employee is: " + totalnetpay);
        System.out.println("             ********   CONFIDENTIAL  **********            ");

        System.out.println("==============================================");
        System.out.println("              BONUS                           ");
        System.out.println("==============================================");

        System.out.println("highest Experience person is    "+highExpName+"and total years:"+highExp);
        for (int i = 0; i < EmployeeId.length; i++) {
            for (int j = i + 1; j < EmployeeId.length; j++) {

                if (Department[i].equals(Department[j]) &&
                        PerformanceGrade[i]==(PerformanceGrade[j])) {

                    System.out.println("Flag: " + name[i] + " and " + name[j] +
                            " have same Department( " + Department[i] +
                            " )and same Grade (" + PerformanceGrade[i]+")");
                }
            }
        }
    }
}