package org.zlasu.controler.customer;

import com.google.gson.Gson;
import org.zlasu.controler.MainServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.customer.Customer;
import org.zlasu.model.customer.CustomerDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/customer/id/*")
public class CustomerServlet extends MainServlet {

    private CustomerDao customerDao = new CustomerDao();

    @Override
    protected MainModel gsonCast(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Customer.class);
    }

    @Override
    protected MainModel getModelById(int id) {
        return (MainModel) customerDao.readById(id);
    }

    @Override
    protected boolean deleteById(int id) {
        return customerDao.delete(customerDao.readById(id));
    }

    @Override
    protected void create(MainModel mainModel) {
        Customer customer = (Customer) mainModel;
        customer.setId(0);
        customerDao.create(customer);
    }

    @Override
    protected boolean createValidator(Validator validator, MainModel mainModel) {
        Customer customer = (Customer) mainModel;

        return validator.isEmpty(customer.getName(), "name")
                | validator.isEmpty(customer.getLastName(), "lastName")
                | validator.isEmpty(customer.getDateOfBirth() + "", "dateOfBirth");
    }

    @Override
    protected void update(MainModel mainModel) {
        Customer customer = (Customer) mainModel;
        customerDao.update(customer);
    }

    @Override
    protected boolean updateValidator(Validator validator, MainModel mainModel) {
        Customer customer = (Customer) mainModel;
        return validator.isNotPositiveId(customer.getId() + "", "id") | createValidator(validator, mainModel);
    }

}