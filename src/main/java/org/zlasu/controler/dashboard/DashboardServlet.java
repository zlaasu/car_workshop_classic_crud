package org.zlasu.controler.dashboard;

import com.google.gson.JsonObject;
import org.zlasu.model.dashboard.Dashboard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();
        Dashboard dashboard = new Dashboard();

        json.addProperty("total_orders", dashboard.getTotalOrders());
        json.addProperty("total_income", dashboard.getTotalIncome());
        json.addProperty("total_costs", dashboard.getTotalCosts());

        out.print(json.toString());
        out.flush();
    }

}
