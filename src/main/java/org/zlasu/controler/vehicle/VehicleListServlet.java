package org.zlasu.controler.vehicle;

import org.zlasu.controler.MainListServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.vehicle.VehicleDao;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/vehicle/list")
public class VehicleListServlet extends MainListServlet {

    private VehicleDao vehicleDao = new VehicleDao();

    @Override
    protected List<MainModel> getObjectList() {
        return (List<MainModel>) (List<?>) vehicleDao.findAll();
    }

}
