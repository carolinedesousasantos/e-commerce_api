package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Categories;
import org.json.JSONObject;

public class CategoriesController {
        private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CategoriesController() {
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
       public JSONObject getCategories() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Categories> query
                    = this.entityManager.createNamedQuery("Categories.findAll", Categories.class);
            List<Categories> categories = query.getResultList();

            categories.forEach((c) -> {
                System.out.println(c.getId() + " " + c.getDescription());
            });
            
            

            result.put("error", false);
            result.put("result", categories);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }
}
