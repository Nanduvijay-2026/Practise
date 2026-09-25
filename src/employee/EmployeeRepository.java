package employee;

import exceptions.EmployeeNotFoundException;

class EmployeeRepository {

    private final Employee[] employees;
    private int count = 0;

    public EmployeeRepository(int capacity) {
        employees = new Employee[capacity];
    }

    public void add(Employee emp) {
        if (count >= employees.length)
            throw new IllegalStateException("Repository at capacity");
        employees[count++] = emp;
    }

    public Employee findById(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getId() == id) return employees[i];
        }
        throw new EmployeeNotFoundException(id);
    }

    public Employee[] findAll() {
        Employee[] all = new Employee[count];
        System.arraycopy(employees, 0, all, 0, count);
        return all;
    }
}
