package org.zlasu.controler;

import com.google.gson.Gson;
import org.zlasu.model.MainModel;
import org.zlasu.util.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class MainServlet extends HttpServlet {

    protected Gson gson = new Gson();

    protected abstract MainModel gsonCast(HttpServletRequest request) throws IOException;

    protected abstract MainModel getModelById(int id);

    protected abstract boolean deleteById(int id);

    protected abstract void create(MainModel mainModel);

    protected abstract boolean createValidator(Validator validator, MainModel mainModel);

    protected abstract void update(MainModel mainModel);

    protected abstract boolean updateValidator(Validator validator, MainModel mainModel);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";
        MainModel mainModel = null;

        String idStr = request.getPathInfo().split("/")[1];

        if (validator.isEmpty(idStr, "id")
                | validator.isNotInt(idStr, "id")) {
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            mainModel = getModelById(Integer.parseInt(idStr));
            if (mainModel != null) {
                jsonString = gson.toJson(mainModel);
            }
        }

        out.print(jsonString);
        out.flush();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //TODO nullPointer when ID not exist in table
        Validator validator = new Validator();
        String idStr = request.getPathInfo().split("/")[1];

        if (validator.isEmpty(idStr, "id")
                | validator.isNotInt(idStr, "id")) {
            response.setStatus(400);
        } else if (deleteById(Integer.parseInt(idStr))) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";

        //TODO when body missing
        MainModel mainModel = gsonCast(request);

        if (createValidator(validator, mainModel)) {
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            create(mainModel);
            response.setStatus(201);
        }

        out.print(jsonString);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO update when ID notexists
        Validator validator = new Validator();
        PrintWriter out = response.getWriter();
        String jsonString = "[]";

        //TODO when body missing
        MainModel mainModel = gsonCast(request);

        if (updateValidator(validator, mainModel)) {
            jsonString = gson.toJson(validator);
            response.setStatus(400);
        } else {
            update(mainModel);
        }

        out.print(jsonString);
        out.flush();
    }

}
