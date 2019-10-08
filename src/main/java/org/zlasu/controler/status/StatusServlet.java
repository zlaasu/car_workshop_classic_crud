package org.zlasu.controler.status;

import com.google.gson.Gson;
import org.zlasu.controler.MainServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.status.Status;
import org.zlasu.model.status.StatusDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/status/id/*")
public class StatusServlet extends MainServlet {

    private StatusDao statusDao = new StatusDao();

    @Override
    protected MainModel gsonCast(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Status.class);
    }

    @Override
    protected MainModel getModelById(int id) {
        return (MainModel) statusDao.readById(id);
    }

    @Override
    protected boolean deleteById(int id) {
        return statusDao.delete(statusDao.readById(id));
    }

    @Override
    protected void create(MainModel mainModel) {
        Status status = (Status) mainModel;
        status.setId(0);
        statusDao.create(status);
    }

    @Override
    protected boolean createValidator(Validator validator, MainModel mainModel) {
        Status status = (Status) mainModel;
        return validator.isEmpty(status.getName(), "name");
    }

    @Override
    protected void update(MainModel mainModel) {
        Status status = (Status) mainModel;
        statusDao.update(status);
    }

    @Override
    protected boolean updateValidator(Validator validator, MainModel mainModel) {
        Status status = (Status) mainModel;
        return validator.isNotPositiveId(status.getId() + "", "id") | createValidator(validator, mainModel);
    }

}