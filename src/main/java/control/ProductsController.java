package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Products;
import org.json.JSONObject;

public class ProductsController {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ProductsController() {
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

    public JSONObject getProducts() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Products> typedQuery
                    = this.entityManager.createNamedQuery("Products.findAll", Products.class);
            List<Products> products = typedQuery.getResultList();

            products.forEach((p) -> {
                System.out.println(p.getId() + " " + p.getDescription());
            });

            result.put("error", false);
            result.put("result", products);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }
        public JSONObject getProductDetails(int productId) {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Products> typedQuery
                    = this.entityManager.createNamedQuery("Products.findById", Products.class);
            
            typedQuery.setParameter("id", productId);
            List<Products> products = typedQuery.getResultList();
            
            

            products.forEach((p) -> {
                System.out.println(p.getId() + " " + p.getDescription());
            });

            result.put("error", false);
            result.put("result", products);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }
}
