import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployee {
    private static final String URL = "jdbc:postgresql://localhost:5432/week2_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("PG_PASSWORD");

    public static void main(String[] args) {
        String sql = "UPDATE employees SET salary = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, new java.math.BigDecimal("92000.00"));
            stmt.setString(2, "Jane Doe");

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}