package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployee {
    private static final String URL = "jdbc:postgresql://localhost:5432/week2_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("PG_PASSWORD");

    public static void main(String[] args) {
        String sql = "SELECT id, name, department, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                var salary = rs.getBigDecimal("salary");
                System.out.println(id + " | " + name + " | " + department + " | " + salary);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}