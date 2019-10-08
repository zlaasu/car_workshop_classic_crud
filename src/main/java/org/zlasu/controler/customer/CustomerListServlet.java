package org.zlasu.controler.customer;

import org.zlasu.controler.MainListServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.customer.CustomerDao;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/customer/list")
public class CustomerListServlet extends MainListServlet {

    private CustomerDao customerDao = new CustomerDao();

    @Override
    protected List<MainModel> getObjectList() {
        return (List<MainModel>) (List<?>) customerDao.findAll();
    }

}
