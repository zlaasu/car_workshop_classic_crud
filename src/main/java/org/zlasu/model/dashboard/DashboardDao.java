package org.zlasu.model.dashboard;

import org.zlasu.util.db.DbConnector;

import java.sql.*;

public class DashboardDao {

    private final String READ_TOTAL_ORDERS_QUERY = "SELECT count(id) as result FROM customer_order;";
    private final String READ_TOTAL_INCOME_QUERY = "SELECT sum(cost_repair) as result FROM customer_order;";
    private final String READ_TOTAL_COSTS_QUERY = "SELECT sum(cost_parts) as result FROM customer_order;";

    public int getOrdersCount () {
        return Integer.parseInt(execute(READ_TOTAL_ORDERS_QUERY));
    }

    public Double getTotalIncome () {
        return Double.parseDouble(execute(READ_TOTAL_INCOME_QUERY));
    }

    public Double getTotalCosts () {
        return Double.parseDouble(execute(READ_TOTAL_COSTS_QUERY));
    }

    private String execute(String query) {
        try (Connection connection = DbConnector.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString("result");
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
