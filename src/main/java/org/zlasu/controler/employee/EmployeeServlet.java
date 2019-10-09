package org.zlasu.controler.employee;

import com.google.gson.Gson;
import org.zlasu.controler.MainServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.employee.Employee;
import org.zlasu.model.employee.EmployeeDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/employee/id/*")
public class EmployeeServlet extends MainServlet {

    private EmployeeDao employeeDao = new EmployeeDao();

    @Override
    protected MainModel gsonCast(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Employee.class);
    }

    @Override
    protected MainModel getModelById(int id) {
        return (MainModel) employeeDao.readById(id);
    }

    @Override
    protected boolean deleteById(int id) {
        return employeeDao.delete(employeeDao.readById(id));
    }

    @Override
    protected void create(MainModel mainModel) {
        Employee employee = (Employee) mainModel;
        employee.setId(0);
        employeeDao.create(employee);
    }

    @Override
    protected boolean createValidator(Validator validator, MainModel mainModel) {
        Employee employee = (Employee) mainModel;

        return validator.isEmpty(employee.getName(), "name")
                | validator.isEmpty(employee.getLastName(), "lastname")
                | validator.isEmpty(employee.getAddress() + "", "address")
                | validator.isEmpty(employee.getPhoneNumber() + "", "phone_number")
                | validator.isEmpty(employee.getNote() + "", "note")
                | validator.isNotDouble(employee.getCostPerHour() + "", "cost_per_hour");
    }

    @Override
    protected void update(MainModel mainModel) {
        Employee employee = (Employee) mainModel;
        employeeDao.update(employee);
    }

    @Override
    protected boolean updateValidator(Validator validator, MainModel mainModel) {
        Employee employee = (Employee) mainModel;
        return validator.isNotPositiveId(employee.getId() + "", "id") | createValidator(validator, mainModel);
    }

}