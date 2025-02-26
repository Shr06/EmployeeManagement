package main;
import models.*;
import java.util.*;

public class EmployeeManagementApp {
    private static List<Employee> employees = new ArrayList<>();
    private static Map<String, Department> departments = new HashMap<>();
    private static List<PerformanceReview> reviews = new ArrayList<>();
    private static int employeeIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        seedData();

        while (true) {
            System.out.println("\n1. Add Employee | 2. Remove Employee | 3. Search | 4. Performance Review | 5. Promote | 6. Department Summary | 7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1 -> addEmployee(scanner);
                case 2 -> removeEmployee(scanner);
                case 3 -> searchEmployee(scanner);
                case 4 -> addPerformanceReview(scanner);
                case 5 -> promoteEmployee(scanner);
                case 6 -> departmentSummary();
                case 7 -> { scanner.close(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String departmentName = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee newEmployee = new Employee(employeeIdCounter++, name, departmentName, salary);
        employees.add(newEmployee);
        departments.putIfAbsent(departmentName, new Department(departmentName));
        departments.get(departmentName).addEmployee(newEmployee);

        System.out.println("Employee added: " + newEmployee);
    }

    private static void removeEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();
        employees.removeIf(e -> e.getId() == id);
        System.out.println("Employee removed!");
    }

    private static void searchEmployee(Scanner scanner) {
        System.out.println("Search by: 1. ID | 2. Name | 3. Department");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                System.out.print("Enter Employee ID: ");
                int id = scanner.nextInt();
                employees.stream().filter(e -> e.getId() == id).forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();
                employees.stream().filter(e -> e.getName().equalsIgnoreCase(name)).forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();
                if (departments.containsKey(dept)) {
                    departments.get(dept).showEmployees();
                } else {
                    System.out.println("Department not found!");
                }
            }
        }
    }

    private static void addPerformanceReview(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Review: ");
        String review = scanner.nextLine();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();

        reviews.add(new PerformanceReview(id, review, rating));
        System.out.println("Review added!");
    }

    private static void promoteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to promote: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new salary: ");
        double newSalary = scanner.nextDouble();

        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setSalary(newSalary);
                System.out.println("Employee promoted!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void departmentSummary() {
        departments.values().forEach(dept -> {
            System.out.println("\nDepartment: " + dept.getName());
            dept.showEmployees();
            System.out.println("Total Salary Expense: ₹" + dept.getTotalSalaryExpense());
        });
    }

    private static void seedData() {
        employees.add(new Employee(employeeIdCounter++, "Alice", "IT", 50000));
        employees.add(new Employee(employeeIdCounter++, "Bob", "HR", 45000));
        departments.put("IT", new Department("IT"));
        departments.put("HR", new Department("HR"));
        departments.get("IT").addEmployee(employees.get(0));
        departments.get("HR").addEmployee(employees.get(1));
    }
}
