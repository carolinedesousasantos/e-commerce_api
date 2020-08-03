
package view;


import control.SubCategoriesController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "SubCategories", urlPatterns = {"/SubCategories"})
public class SubCategories extends HttpServlet {
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        SubCategoriesController subCategories = new SubCategoriesController();
        JSONObject json = subCategories.getSubCategories();

        System.out.println(json);
        PrintWriter out = response.getWriter();

       System.out.println("Caroline");
        out.print(json);

    }

   
}
