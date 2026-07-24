package com.cohort;

public class HRApp {
    public static void main(String[] args) {
        EmployeeProfile emp = new EmployeeProfile("E1001", "Jordan Blake", 5200.00);

        System.out.println("Initial state:");
        System.out.println("ID: " + emp.getEmployeeId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Salary: " + emp.getMonthlySalary());

        System.out.println("\nAttempting invalid salary (-5000.00):");
        emp.setMonthlySalary(-5000.00);
        System.out.println("Salary after attempt: " + emp.getMonthlySalary());

        System.out.println("\nAttempting invalid name (null):");
        emp.setName(null);
        System.out.println("Name after attempt: " + emp.getName());

        System.out.println("\nApplying a valid update:");
        emp.setName("Jordan B. Blake");
        emp.setMonthlySalary(5500.00);
        System.out.println("Name: " + emp.getName());
        System.out.println("Salary: " + emp.getMonthlySalary());
    }
}