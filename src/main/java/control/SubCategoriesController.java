package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Categories;
import model.SubCategories;
import org.json.JSONObject;

public class SubCategoriesController {
        private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public SubCategoriesController() {
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
       public JSONObject getSubCategories() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<SubCategories> query
                    = this.entityManager.createNamedQuery("SubCategories.findAll", SubCategories.class);
            List<SubCategories> subCategories = query.getResultList();

            
            System.out.println("query" + query);
            subCategories.forEach((sc) -> {
                System.out.println(sc.getId() + " " + sc.getDescription());
            });
            
            

            result.put("error", false);
            result.put("result", subCategories);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }
}
