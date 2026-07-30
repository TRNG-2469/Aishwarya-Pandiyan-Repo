import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployee {
    private static final String URL = "jdbc:postgresql://localhost:5432/week2_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("PG_PASSWORD");

    public static void main(String[] args) {
        String sql = "DELETE FROM employees WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Jane Doe");

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}