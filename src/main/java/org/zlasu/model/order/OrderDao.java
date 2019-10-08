package org.zlasu.model.order;

import org.zlasu.model.customer.Customer;
import org.zlasu.model.customer.CustomerDao;
import org.zlasu.model.status.Status;
import org.zlasu.model.status.StatusDao;
import org.zlasu.model.vehicle.Vehicle;
import org.zlasu.model.vehicle.VehicleDao;
import org.zlasu.util.crud.DaoMain;
import org.zlasu.util.crud.ModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao extends DaoMain {

    private final String READ_BY_ID_QUERY = "SELECT id, customer_id, vehicle_id, status_id, date_order_accepted," +
            "       date_repair_start, problem_description, cost_repair," +
            "       cost_parts, cost_per_hour, number_of_man_hours FROM customer_order WHERE id = ?;";
    private final String DELETE_QUERY = "DELETE FROM customer_order WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO customer_order(id, customer_id, vehicle_id, status_id, date_order_accepted, date_repair_start," +
            "                           problem_description, repair_description, cost_repair, cost_parts, cost_per_hour," +
            "                           number_of_man_hours) " +
            "                           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE_QUERY = "UPDATE customer_order SET customer_id = ?, vehicle_id = ?, status_id = ?," +
            "    date_order_accepted = ? date_repair_start = ?, problem_description = ?, cost_repair = ?," +
            "    cost_parts = ?, cost_per_hour = ?, number_of_man_hours = ? WHERE id = ?;";
    private final String FIND_ALL_QUERY = "SELECT id, customer_id, vehicle_id, status_id, date_order_accepted," +
            "                  date_repair_start, problem_description, cost_repair," +
            "                  cost_parts, cost_per_hour, number_of_man_hours FROM customer_order";

    @Override
    protected ModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCustomer((Customer) new CustomerDao().readById(resultSet.getInt("customer_id")));
        order.setVehicle((Vehicle) new VehicleDao().readById(resultSet.getInt("vehicle_id")));
        order.setStatus((Status) new StatusDao().readById(resultSet.getInt("status_id")));
        order.setDate_order_accepted(resultSet.getDate("date_order_accepted"));
        order.setDate_repair_start(resultSet.getDate("date_repair_start"));
        order.setProblem_description(resultSet.getString("problem_description"));
        order.setCost_repair(resultSet.getDouble("cost_repair"));
        order.setCost_parts(resultSet.getDouble("cost_parts"));
        order.setCost_per_hour(resultSet.getDouble("cost_per_hour"));
        order.setNumber_of_man_hours(resultSet.getInt("number_of_man_hours"));
        return order;
    }

    @Override
    public ArrayList<String> getObjectParams(ModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Order order = (Order) item;
        params.add(order.getId() + "");
        params.add(order.getCustomer().getId() + "");
        params.add(order.getVehicle().getId() + "");
        params.add(order.getStatus().getId() + "");
        params.add(order.getDate_order_accepted() + "");
        params.add(order.getDate_repair_start() + "");
        params.add(order.getProblem_description());
        params.add(order.getCost_repair() + "");
        params.add(order.getCost_parts() + "");
        params.add(order.getCost_per_hour() + "");
        params.add(order.getNumber_of_man_hours() + "");
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
