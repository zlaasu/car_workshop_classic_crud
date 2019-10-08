package org.zlasu.controler.vehicle;

import com.google.gson.Gson;
import org.zlasu.controler.MainServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.vehicle.Vehicle;
import org.zlasu.model.vehicle.VehicleDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/vehicle/id/*")
public class VechicleServlet extends MainServlet {

    private VehicleDao vehicleDao = new VehicleDao();

    @Override
    protected MainModel gsonCast(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Vehicle.class);
    }

    @Override
    protected MainModel getModelById(int id) {
        return (MainModel) vehicleDao.readById(id);
    }

    @Override
    protected boolean deleteById(int id) {
        return vehicleDao.delete(vehicleDao.readById(id));
    }

    @Override
    protected void create(MainModel mainModel) {
        Vehicle vehicle = (Vehicle) mainModel;
        vehicle.setId(0);
        vehicleDao.create(vehicle);
    }

    @Override
    protected boolean createValidator(Validator validator, MainModel mainModel) {
        Vehicle vehicle = (Vehicle) mainModel;

        return  validator.isNotPositiveId(vehicle.getCustomer_id() + "", "customer_id")
                | validator.isEmpty(vehicle.getModel(), "model")
                | validator.isEmpty(vehicle.getBrand(), "brand")
                | validator.isEmpty(vehicle.getYear_of_production() + "", "year_of_production")
                | validator.isEmpty(vehicle.getRegistration_number(), "registration_number")
                | validator.isEmpty(vehicle.getNext_technical_inspection() + "", "next_technical_inspection");
    }

    @Override
    protected void update(MainModel mainModel) {
        Vehicle vehicle = (Vehicle) mainModel;
        vehicleDao.update(vehicle);
    }

    @Override
    protected boolean updateValidator(Validator validator, MainModel mainModel) {
        Vehicle vehicle = (Vehicle) mainModel;
        return validator.isNotPositiveId(vehicle.getId() + "", "id") | createValidator(validator, mainModel);
    }

}