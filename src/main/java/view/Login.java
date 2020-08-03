package view;

import control.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import utils.RelatedJson;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("content-type", "application/json; charset=UTF-8;");

        String JsonData = RelatedJson.getBody(request);

        JSONObject json = new JSONObject(JsonData);

        String email = json.getString("email");
        String password = json.getString("password");
        
        System.out.println(email);
        UserController userController = new UserController();

        JSONObject result =  new JSONObject();
        if (!email.equals("") && !password.equals("")) {
            result = userController.login(email, password);
        } else {
            result.put("error", true);
            result.put("msg", "Email and password are required.");
        }

        System.out.println(result);

        PrintWriter out = response.getWriter();

        out.print(result);
    }

}
