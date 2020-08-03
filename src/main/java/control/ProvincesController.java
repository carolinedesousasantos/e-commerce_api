package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Provincias;
import org.json.JSONObject;

public class ProvincesController {

    public EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;

    public ProvincesController() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ECommerce-PU");
        entityManager = this.entityManagerFactory.createEntityManager();
    }

    public JSONObject getProvinces() {
        JSONObject result = new JSONObject();

        try {
            TypedQuery<Provincias> query
                    = this.entityManager.createNamedQuery("Provincias.findAll", Provincias.class);
            List<Provincias> provincias = query.getResultList();

            provincias.forEach((p) -> {
                System.out.println(p.getId() + " " + p.getProvincia());
            });

            result.put("error", false);
            result.put("result", provincias);
        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }

        return result;
    }

    public JSONObject getProvincesById(int provincia_id) {
        JSONObject result = new JSONObject();

        try {
            TypedQuery<Provincias> query
                    = this.entityManager.createNamedQuery("Provincias.findById", Provincias.class);
            query.setParameter("id", provincia_id);

            Provincias provincia = query.getSingleResult();
            System.out.println(provincia.getId() + " " + provincia.getProvincia());

            result.put("error", false);
            result.put("result", provincia);
        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;
    }

    public JSONObject getProvincesByName(String provincia) {
        JSONObject result = new JSONObject();
        try {
            TypedQuery<Provincias> query
                    = this.entityManager.createNamedQuery("Provincias.findByProvincia", Provincias.class);
            query.setParameter("provincia", provincia + "%");

            List<Provincias> provincias = query.getResultList();

            if (provincias.size() > 0) {
                provincias.forEach((p) -> {
                    System.out.println(p.getId() + " " + p.getProvincia());
                    result.put("error", false);
                    result.put("result", provincias);
                });
            } else {
                System.out.println("No results found.");
                result.put("error", false);
                result.put("result", "No results found.");
            }

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;
    }
}
