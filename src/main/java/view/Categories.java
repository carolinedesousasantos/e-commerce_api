package view;

import control.CategoriesController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@WebServlet(name = "Categories", urlPatterns = {"/Categories"})
public class Categories extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      CategoriesController categories = new CategoriesController();
        JSONObject json = categories.getCategories();
        System.out.println("categories "+ categories);

        PrintWriter out = response.getWriter();

        out.print(json);

    }
    }




