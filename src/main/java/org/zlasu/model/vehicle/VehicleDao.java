package org.zlasu.model.vehicle;

import org.zlasu.model.MainDao;
import org.zlasu.model.MainModelInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDao  extends MainDao {

    private final String READ_BY_ID_QUERY = "SELECT id, customer_id, model, brand, year_of_production," +
            "       registration_number, next_technical_inspection FROM vehicle WHERE id = ?;";
    private final String DELETE_QUERY = "DELETE FROM vehicle WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO vehicle (id, customer_id, model, brand, year_of_production, " +
            "       registration_number, next_technical_inspection) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE vehicle SET customer_id = ?, model = ?, brand = ?, year_of_production = ?, " +
            "       registration_number = ?,  next_technical_inspection = ? WHERE id = ?";
    private final String FIND_ALL_QUERY = "SELECT id, customer_id, model, brand, year_of_production," +
            "       registration_number, next_technical_inspection FROM vehicle";

    @Override
    protected MainModelInterface newObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getInt("id"));
        //vehicle.setCustomer((Customer) new CustomerDao().readById(resultSet.getInt("customer_id")));
        vehicle.setCustomerId(resultSet.getInt("customer_id"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setBrand(resultSet.getString("brand"));
        vehicle.setYearOfProduction(resultSet.getInt("year_of_production"));
        vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
        vehicle.setNextTechnicalInspection(resultSet.getDate("next_technical_inspection"));
        return vehicle;
    }

    @Override
    public ArrayList<String> getObjectParams(MainModelInterface item) {
        ArrayList<String> params = new ArrayList();
        Vehicle vehicle = (Vehicle) item;
        params.add(vehicle.getId() + "");
        //params.add(vehicle.getCustomer().getId() + "");
        params.add(vehicle.getCustomerId() + "");
        params.add(vehicle.getModel());
        params.add(vehicle.getBrand());
        params.add(vehicle.getYearOfProduction() + "");
        params.add(vehicle.getRegistrationNumber());
        params.add(dateFormat.format(vehicle.getNextTechnicalInspection()) + "");
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
