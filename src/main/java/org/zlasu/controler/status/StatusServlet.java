package org.zlasu.controler.status;

import com.google.gson.Gson;
import org.zlasu.model.status.Status;
import org.zlasu.model.status.StatusDao;
import org.zlasu.util.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/status/id/*")
public class StatusServlet extends HttpServlet {

    private Gson gson = new Gson();
    private StatusDao statusDao = new StatusDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";

        Status status = new Gson().fromJson(request.getReader(), Status.class);

        if (validator.isNotPositiveId(status.getId() + "", "id")
                | validator.isEmpty(status.getName(), "name")) {
            System.out.println("TU");
            System.out.println(status.getName());
            System.out.println(status.getId());
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            statusDao.update(status);
        }

        out.print(jsonString);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";
        Status status = null;

        String idStr = request.getPathInfo().split("/")[1];

        if (validator.isEmpty(idStr, "id")
                | validator.isNotInt(idStr, "id")) {
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            status = (Status) statusDao.readById(Integer.parseInt(idStr));
            if (status != null) {
                jsonString = gson.toJson(status);
            }
        }

        out.print(jsonString);
        out.flush();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";

        Status status = new Gson().fromJson(request.getReader(), Status.class);
        status.setId(0);

        if (validator.isEmpty(status.getName(), "name")) {
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            statusDao.create(status);
            response.setStatus(201);
        }

        out.print(jsonString);
        out.flush();
    }

}
