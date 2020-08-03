package view;

import control.ProductsController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import utils.RelatedJson;

@WebServlet(name = "ProductDetails", urlPatterns = {"/ProductDetails"})
public class ProductDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("content-type", "application/json; charset=UTF-8;");

        String JsonData = RelatedJson.getBody(request);

        JSONObject json = new JSONObject(JsonData);

        int productId = json.getInt("productId");

        ProductsController products = new ProductsController();

        JSONObject result = products.getProductDetails(productId);

        PrintWriter out = response.getWriter();

        out.print(result);

    }

}
