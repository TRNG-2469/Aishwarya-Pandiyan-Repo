package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureAuthGateway {

    public boolean authenticateUser(String emailInput, String passwordInput, Connection conn) throws SQLException {
        // SECURE: static query template with placeholders, no string concatenation
        String query = "SELECT * FROM members WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, emailInput);
            stmt.setString(2, passwordInput);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}