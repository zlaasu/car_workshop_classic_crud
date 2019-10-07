package org.zlasu.controler.status;

import com.google.gson.Gson;
import org.zlasu.model.status.Status;
import org.zlasu.model.status.StatusDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/status/list")
public class StatusListServlet extends HttpServlet {

    private Gson gson = new Gson();
    private StatusDao statusDao = new StatusDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        List<Status> statusList = (List<Status>) (List<?>) statusDao.findAll();
        String jsonString = this.gson.toJson(statusList);

        out.print(jsonString);
        out.flush();
    }

}
