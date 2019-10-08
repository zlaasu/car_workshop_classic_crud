package org.zlasu.controler;

import com.google.gson.Gson;
import org.zlasu.model.MainModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class MainListServlet extends HttpServlet {

    private Gson gson = new Gson();
    protected abstract List<MainModel> getObjectList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        List<MainModel> mainModels = getObjectList();
        String jsonString = this.gson.toJson(mainModels);

        out.print(jsonString);
        out.flush();
    }

}
