import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmployee {
    private static final String URL = "jdbc:postgresql://localhost:5432/week2_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("PG_PASSWORD");

    public static void main(String[] args) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Jane Doe");
            stmt.setString(2, "Engineering");
            stmt.setBigDecimal(3, new java.math.BigDecimal("85000.00"));

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}