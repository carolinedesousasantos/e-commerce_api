package view;

import control.ResetPasswordTokensController;
import control.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import utils.Password;
import utils.RelatedJson;
import utils.SendEmailSMTP;

@WebServlet(name = "SendEmailChangePassword", urlPatterns = {"/SendEmailChangePassword"})
public class SendEmailChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("content-type", "application/json; charset=UTF-8");

        String JsonData = RelatedJson.getBody(request);

        JSONObject json = new JSONObject(JsonData);
        JSONObject result = new JSONObject();

        UserController userController = new UserController();

        String email = json.getString("email");

        boolean emailExist = userController.checkIfEmailExists(email);

        if (emailExist) {

            Integer userId = userController.getUserId(email);
            String token = Password.hashEmail(email);
            System.out.println("token "+ token);

            ResetPasswordTokensController resetPasswordtokensController = new ResetPasswordTokensController();

            boolean savedToken = resetPasswordtokensController.saveToken(userId, token, LocalDateTime.now());

            if (savedToken) {

                boolean emailSent = SendEmailSMTP.sendEmail(email, token);

                if (emailSent) {
                    result.put("error", false);
                    result.put("msg", "Email sent.");

                } else {
                    result.put("error", true);
                    result.put("msg", "Error sending email.");
                }
            } else {
                result.put("error", true);
                result.put("msg", "Error saving token.");
            }

        } else {
            result.put("error", true);
            result.put("msg", "Email not registered");
        }

        System.out.println(result);

        PrintWriter out = response.getWriter();

        out.print(result);

    }
}
