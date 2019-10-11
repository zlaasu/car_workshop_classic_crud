package org.zlasu.controler.auth;

import com.google.gson.JsonObject;
import org.mindrot.jbcrypt.BCrypt;
import org.zlasu.model.employee.EmployeeAuth;
import org.zlasu.model.employee.EmployeeAuthDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        EmployeeAuthDao employeeAuthDao = new EmployeeAuthDao();
        EmployeeAuth employeeAuth = employeeAuthDao.readByEmail(email);

        if (employeeAuth != null && BCrypt.checkpw(password, employeeAuth.getPassword())) {
            PrintWriter out = response.getWriter();
            JsonObject json = new JsonObject();
            HttpSession session = request.getSession();

            String token = EmployeeAuthDao.generateNewToken();
            employeeAuth.setToken(token);
            employeeAuthDao.update(employeeAuth);

            setUserSession(session, employeeAuth);

            json.addProperty("token", employeeAuth.getToken());
            json.addProperty("email", employeeAuth.getEmail());

            out.print(json.toString());
            out.flush();
        } else {
            response.setStatus(401);
        }
    }

    public static void setUserSession(HttpSession session, EmployeeAuth employeeAuth) {
        session.setAttribute("userID", employeeAuth.getId());
        session.setAttribute("token", employeeAuth.getToken());
    }

}
