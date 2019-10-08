package org.zlasu.controler.status;

import org.zlasu.controler.MainListServlet;
import org.zlasu.model.MainModel;
import org.zlasu.model.status.StatusDao;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/status/list")
public class StatusListServlet extends MainListServlet {

    private StatusDao statusDao = new StatusDao();

    @Override
    protected List<MainModel> getObjectList() {
        return (List<MainModel>) (List<?>) statusDao.findAll();
    }

}
