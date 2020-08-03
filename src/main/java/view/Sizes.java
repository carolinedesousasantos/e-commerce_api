package view;

import control.CategoriesController;
import control.SizesController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "Sizes", urlPatterns = {"/Sizes"})
public class Sizes extends HttpServlet {

        
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      SizesController sizes = new SizesController();
        JSONObject json = sizes.getSizes();
        System.out.println("sizes "+ sizes);

        PrintWriter out = response.getWriter();

        out.print(json);

    
       
    }

}
