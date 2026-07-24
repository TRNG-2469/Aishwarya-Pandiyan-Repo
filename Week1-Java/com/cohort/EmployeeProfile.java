package com.cohort;

public class EmployeeProfile {
    private String employeeId;
    private String name;
    private double monthlySalary;

    public EmployeeProfile(String employeeId, String name, double salary) {
        this.employeeId = employeeId;
        // Route through the setter so construction respects the same guards
        setName(name);
        setMonthlySalary(salary);
    }

    // Read-only: no setEmployeeId()
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Error: name cannot be null or empty. Update rejected.");
            return;
        }
        this.name = name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary < 0.0) {
            System.out.println("Error: salary cannot be negative. Update rejected.");
            return;
        }
        this.monthlySalary = monthlySalary;
    }
}
