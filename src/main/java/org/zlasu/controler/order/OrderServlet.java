package org.zlasu.controler.order;

import com.google.gson.Gson;
import org.zlasu.controler.MainServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.order.Order;
import org.zlasu.model.order.OrderDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/order/id/*")
public class OrderServlet extends MainServlet {

    private OrderDao orderDao = new OrderDao();

    @Override
    protected MainModel gsonCast(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Order.class);
    }

    @Override
    protected MainModel getModelById(int id) {
        return (MainModel) orderDao.readById(id);
    }

    @Override
    protected boolean deleteById(int id) {
        return orderDao.delete(orderDao.readById(id));
    }

    @Override
    protected void create(MainModel mainModel) {
        Order order = (Order) mainModel;
        order.setId(0);
        orderDao.create(order);
    }

    @Override
    protected boolean createValidator(Validator validator, MainModel mainModel) {
        Order order = (Order) mainModel;

        return  validator.isNotPositiveId(order.getCustomerId() + "", "customer_id")
                | validator.isNotPositiveId(order.getVehicleId() + "", "vehicle_id")
                | validator.isNotPositiveId(order.getStatusId() + "", "status_id")
                | validator.isEmpty(order.getDateOrderAccepted() + "", "date_order_accepted")
                | validator.isEmpty(order.getDateRepairStart() + "", "date_repair_start")
                | validator.isEmpty(order.getProblemDescription(), "problem_description")
                | validator.isEmpty(order.getRepairDescription(), "repair_description")
                | validator.isNotDouble(order.getCostRepair() + "", "cost_repair")
                | validator.isNotDouble(order.getCostParts() + "", "cost_parts")
                | validator.isNotDouble(order.getCostPerHour() + "", "cost_per_hour")
                | validator.isNotPositiveId(order.getNumberOfManHours() + "", "number_of_man_hours");
    }

    @Override
    protected void update(MainModel mainModel) {
        Order order = (Order) mainModel;
        orderDao.update(order);
    }

    @Override
    protected boolean updateValidator(Validator validator, MainModel mainModel) {
        Order order = (Order) mainModel;
        return validator.isNotPositiveId(order.getId() + "", "id") | createValidator(validator, mainModel);
    }

}