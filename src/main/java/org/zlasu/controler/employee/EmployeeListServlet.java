package org.zlasu.controler.employee;

import org.zlasu.controler.MainListServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.employee.EmployeeDao;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/employee/list")
public class EmployeeListServlet extends MainListServlet {

    private EmployeeDao employeeDao = new EmployeeDao();

    @Override
    protected List<MainModel> getObjectList() {
        return (List<MainModel>) (List<?>) employeeDao.findAll();
    }

}
