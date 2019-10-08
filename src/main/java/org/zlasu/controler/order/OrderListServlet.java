package org.zlasu.controler.order;

import org.zlasu.controler.MainListServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.order.OrderDao;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/order/list")
public class OrderListServlet extends MainListServlet {

    private OrderDao orderDao = new OrderDao();

    @Override
    protected List<MainModel> getObjectList() {
        return (List<MainModel>) (List<?>) orderDao.findAll();
    }

}
