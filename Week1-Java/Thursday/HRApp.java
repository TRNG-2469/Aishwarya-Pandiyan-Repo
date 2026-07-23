package Thursday;

public class HRApp {
    public static void main(String[] args) {
        EmployeeProfile employee = new EmployeeProfile("E001", "Jordan Lee", 5000.0);
        System.out.println("Employee Name: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Salary: " + employee.getMonthlySalary());

        employee.setMonthlySalary(-5500.00);
        System.out.println("Salary after invalid update attempt: " + employee.getMonthlySalary());

        employee.setName(null);
        System.out.println("Name after invalid update attempt: " + employee.getName());

        employee.setMonthlySalary(5500.00);
        System.out.println("Salary after valid update: " + employee.getMonthlySalary());

    }
}
