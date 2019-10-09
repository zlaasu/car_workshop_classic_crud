package org.zlasu.model.employee;

import org.zlasu.model.MainDao;
import org.zlasu.model.MainModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao extends MainDao {

    private final String READ_BY_ID_QUERY = "SELECT id, name, lastname, address, phone_number, note, cost_per_hour " +
            "FROM employee where id = ?";
    private final String DELETE_QUERY = "DELETE FROM employee WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO employee(id, name, lastname, address, phone_number, note, " +
            "cost_per_hour) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE employee SET name = ?, lastname = ?, address = ?, phone_number = ?," +
            " note = ?, cost_per_hour = ? where id = ?";
    private final String FIND_ALL_QUERY = "SELECT id, name, lastname, address, phone_number, note, cost_per_hour FROM employee";

    @Override
    protected MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setLastName(resultSet.getString("lastname"));
        employee.setAddress(resultSet.getString("address"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setNote(resultSet.getString("note"));
        employee.setCostPerHour(resultSet.getDouble("cost_per_hour"));
        return employee;
    }

    @Override
    public ArrayList<String> getObjectParams(MainModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Employee status = (Employee) item;
        params.add(String.valueOf(status.getId()));
        params.add(status.getName());
        params.add(status.getLastName());
        params.add(status.getAddress());
        params.add(status.getPhoneNumber());
        params.add(status.getNote());
        params.add(status.getCostPerHour() + "");
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
