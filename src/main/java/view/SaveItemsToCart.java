/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author caroline
 */
@WebServlet(name = "SaveItemsToCart", urlPatterns = {"/SaveItemsToCart"})
public class SaveItemsToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setHeader("content-type", "application/json; charset=UTF-8;");
//
//        String JsonData = RelatedJson.getBody(request);
//
//        JSONObject json = new JSONObject(JsonData);
////        int user_id = json.getInt();
//        String products = json.getString("products");
      
//
//       
//        UserController userController = new UserController();
//
//        JSONObject result = new JSONObject();
//        if (!email.equals("") && !password.equals("")) {
//            result = userController.login(email, password);
//        } else {
//            result.put("error", true);
//            result.put("msg", "");
//        }
//
//        System.out.println(result);
//
//        PrintWriter out = response.getWriter();
//
//        out.print(result);
//    }

}}
