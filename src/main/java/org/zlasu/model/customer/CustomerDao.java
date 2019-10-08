package org.zlasu.model.customer;

import org.zlasu.model.MainDao;
import org.zlasu.model.MainModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao extends MainDao {

    private final String READ_BY_ID_QUERY = "SELECT id, name, lastname, date_of_birth FROM customer WHERE id = ?";
    private final String DELETE_QUERY = "DELETE FROM customer WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO customer(id, name, lastname, date_of_birth) VALUES (?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE customer SET name = ?, lastname = ?, date_of_birth = ? WHERE id = ?";
    private final String FIND_ALL_QUERY = "SELECT id, name, lastname, date_of_birth FROM customer";

    @Override
    protected MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setLastname(resultSet.getString("lastname"));
        System.out.println();
        customer.setDate_of_birth(resultSet.getDate("date_of_birth"));
        return customer;
    }

    @Override
    public ArrayList<String> getObjectParams(MainModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Customer customer = (Customer) item;
        params.add(String.valueOf(customer.getId()));
        params.add(customer.getName());
        params.add(customer.getLastname());
        params.add(dateFormat.format(customer.getDate_of_birth()) + "");
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