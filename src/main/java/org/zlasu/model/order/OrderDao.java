package org.zlasu.model.order;

import org.zlasu.model.MainDao;
import org.zlasu.model.MainModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao extends MainDao {

    private final String READ_BY_ID_QUERY = "SELECT id, customer_id, vehicle_id, status_id, date_order_accepted," +
            "       date_repair_start, problem_description, repair_description, cost_repair," +
            "       cost_parts, cost_per_hour, number_of_man_hours FROM customer_order WHERE id = ?;";
    private final String DELETE_QUERY = "DELETE FROM customer_order WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO customer_order(id, customer_id, vehicle_id, status_id, date_order_accepted, date_repair_start," +
            "                           problem_description, repair_description, cost_repair, cost_parts, cost_per_hour," +
            "                           number_of_man_hours) " +
            "                           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE_QUERY = "UPDATE customer_order SET customer_id = ?, vehicle_id = ?, status_id = ?," +
            "      date_order_accepted = ?, date_repair_start = ?, problem_description = ?, repair_description = ?, " +
            "      cost_repair = ?, cost_parts = ?, cost_per_hour = ?, number_of_man_hours = ? WHERE id = ?;";
    private final String FIND_ALL_QUERY = "SELECT id, customer_id, vehicle_id, status_id, date_order_accepted," +
            "                  date_repair_start, problem_description, repair_description, cost_repair," +
            "                  cost_parts, cost_per_hour, number_of_man_hours FROM customer_order";

    @Override
    protected MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
//        order.setCustomer((Customer) new CustomerDao().readById(resultSet.getInt("customer_id")));
//        order.setVehicle((Vehicle) new VehicleDao().readById(resultSet.getInt("vehicle_id")));
//        order.setStatus((Status) new StatusDao().readById(resultSet.getInt("status_id")));
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setVehicleId(resultSet.getInt("vehicle_id"));
        order.setStatusId(resultSet.getInt("status_id"));
        order.setDateOrderAccepted(resultSet.getDate("date_order_accepted"));
        order.setDateRepairStart(resultSet.getDate("date_repair_start"));
        order.setProblemDescription(resultSet.getString("problem_description"));
        order.setRepairDescription(resultSet.getString("problem_description"));
        order.setCostRepair(resultSet.getDouble("cost_repair"));
        order.setCostParts(resultSet.getDouble("cost_parts"));
        order.setCostPerHour(resultSet.getDouble("cost_per_hour"));
        order.setNumberOfManHours(resultSet.getInt("number_of_man_hours"));
        return order;
    }

    @Override
    public ArrayList<String> getObjectParams(MainModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Order order = (Order) item;
        params.add(order.getId() + "");
        params.add(order.getCustomerId() + "");
        params.add(order.getVehicleId() + "");
        params.add(order.getStatusId() + "");
        params.add(dateFormat.format(order.getDateOrderAccepted()) + "");
        params.add(dateFormat.format(order.getDateRepairStart()) + "");
        params.add(order.getProblemDescription());
        params.add(order.getRepairDescription());
        params.add(order.getCostRepair() + "");
        params.add(order.getCostParts() + "");
        params.add(order.getCostPerHour() + "");
        params.add(order.getNumberOfManHours() + "");
        return params;
    }

    @Override
    protected String getReadByIdQuery() {
        return READ_BY_ID_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }

}
