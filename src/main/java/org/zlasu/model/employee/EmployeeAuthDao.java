package org.zlasu.model.employee;

import org.mindrot.jbcrypt.BCrypt;
import org.zlasu.util.db.DbConnector;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class EmployeeAuthDao {

    private final String READ_BY_ID_QUERY = "SELECT id, email, password, token FROM employee WHERE id = ?;";
    private final String READ_BY_EMAIL_QUERY = "SELECT id, email, password, token FROM employee WHERE email = ?;";
    private final String UPDATE_BY_EMAIL_QUERY = "UPDATE employee SET email = ?, password = ?, token = ? WHERE id = ?;";

    public EmployeeAuth readById(int id) {
        return readByString(String.valueOf(id), READ_BY_ID_QUERY);
    }

    public EmployeeAuth readByEmail(String email) {
        return readByString(email, READ_BY_EMAIL_QUERY);
    }

    private EmployeeAuth readByString(String string, String query) {
        try (Connection connection = DbConnector.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, string);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    EmployeeAuth employeeAuth = new EmployeeAuth();
                    employeeAuth.setId(resultSet.getInt("id"));
                    employeeAuth.setEmail(resultSet.getString("email"));
                    employeeAuth.setPassword(resultSet.getString("password"));
                    employeeAuth.setToken(resultSet.getString("token"));
                    return employeeAuth;
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeAuth update(EmployeeAuth employeeAuth) {
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_EMAIL_QUERY);

            preparedStatement.setString(1,employeeAuth.getEmail());
            preparedStatement.setString(2,employeeAuth.getPassword());
            preparedStatement.setString(3,employeeAuth.getToken());
            preparedStatement.setInt(4,employeeAuth.getId());

            preparedStatement.executeUpdate();

            return readById(employeeAuth.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static String generateNewToken() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[72];
        secureRandom.nextBytes(randomBytes);
        return "$" + base64Encoder.encodeToString(randomBytes);
    }
}
