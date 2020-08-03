package view;

import control.ProvincesController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author caroline
 */
@WebServlet(name = "Provincias", urlPatterns = {"/Provincias"})
public class Provincias extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProvincesController pc = new ProvincesController();
        JSONObject provinces = pc.getProvinces();
        PrintWriter out = response.getWriter();
        out.println(provinces);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProvincesController pc = new ProvincesController();
        JSONObject provinces = pc.getProvincesByName("bat");
        PrintWriter out = response.getWriter();
        out.println(provinces);
    }
}
