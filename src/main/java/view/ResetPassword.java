package view;

import control.ResetPasswordTokensController;
import control.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Resetpasswordtokens;
import org.json.JSONObject;
import utils.RelatedJson;

@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {

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

        String password = json.getString("newPassword");
        String token = json.getString("rst");

        PrintWriter out = response.getWriter();

        ResetPasswordTokensController resetPasswordtokensController = new ResetPasswordTokensController();

        Resetpasswordtokens infoByToken = resetPasswordtokensController.getInfoByToken(token);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenCreationDate = infoByToken.getDate();
        Duration duration = Duration.between(tokenCreationDate, now);
        long diff = Math.abs(duration.toHours());

        if (diff > 0) {
            result.put("error", true);
            result.put("msg", "Link to change password is expired.");

        } else {

            if (password != null && !password.equals("")) {
                result = userController.saveNewPassword(token, password);

            } else {
                result.put("error", true);
                result.put("msg", "Password can't be empty.");
            }

        }
        System.out.println("Result: "+ result);

        out.print(result);
    }

}
