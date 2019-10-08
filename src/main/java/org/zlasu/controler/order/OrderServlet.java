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

        return  validator.isNotPositiveId(order.getCustomer_id() + "", "customer_id")
                | validator.isNotPositiveId(order.getVehicle_id() + "", "vehicle_id")
                | validator.isNotPositiveId(order.getStatus_id() + "", "status_id")
                | validator.isEmpty(order.getDate_order_accepted() + "", "date_order_accepted")
                | validator.isEmpty(order.getDate_repair_start() + "", "date_repair_start")
                | validator.isEmpty(order.getProblem_description(), "problem_description")
                | validator.isEmpty(order.getRepair_description(), "repair_description")
                | validator.isNotDouble(order.getCost_repair() + "", "cost_repair")
                | validator.isNotDouble(order.getCost_parts() + "", "cost_parts")
                | validator.isNotDouble(order.getCost_per_hour() + "", "cost_per_hour")
                | validator.isNotPositiveId(order.getNumber_of_man_hours() + "", "number_of_man_hours");
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