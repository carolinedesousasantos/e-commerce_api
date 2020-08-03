package view;

import control.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import utils.RelatedJson;

@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("content-type", "application/json; charset=UTF-8");

        String JsonData = RelatedJson.getBody(request);

        JSONObject json = new JSONObject(JsonData);

        UserController userController = new UserController();

        String name = json.getString("name");
        String surname = json.getString("surname");
        String birthDate = json.getString("birthDate");
        String gender = json.getString("gender");
        boolean policyAccepted = json.getBoolean("policyAccepted");
        String email = json.getString("email");
        String password = json.getString("password");

        JSONObject result = new JSONObject();

        if (!name.equals("") && !surname.equals("") && !birthDate.equals("")
                && !gender.equals("") && !email.equals("") && !password.equals("")) {
            
            result = userController.createUser(name, surname,
                    email, password, LocalDate.parse(birthDate),
                    gender, LocalDateTime.now(), policyAccepted, true);
        } else {
            result.put("error", true);
            result.put("msg", "All fields are required.");
        }

        System.out.println(result);

        PrintWriter out = response.getWriter();

        out.print(result);
    }

}
