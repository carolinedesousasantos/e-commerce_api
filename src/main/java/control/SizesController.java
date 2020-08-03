package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Categories;
import model.Sizes;
import org.json.JSONObject;

public class SizesController {
            private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public SizesController() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ECommerce-PU");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
       public JSONObject getSizes() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Sizes> query
                    = this.entityManager.createNamedQuery("Sizes.findAll", Sizes.class);
            List<Sizes> sizes = query.getResultList();

            sizes.forEach((s) -> {
                System.out.println(s.getId() + " " + s.getDescription());
            });         
            

            result.put("error", false);
            result.put("result", sizes);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }
}
