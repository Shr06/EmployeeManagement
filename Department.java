package models;
import java.util.*;

public class Department {
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) { this.name = name; }

    public String getName() { return name; }
    public void addEmployee(Employee employee) { employees.add(employee); }
    public List<Employee> getEmployees() { return employees; }

    public double getTotalSalaryExpense() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public void showEmployees() {
        System.out.println("Employees in " + name + " Department:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
