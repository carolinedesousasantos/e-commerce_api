package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Comunidades;
import org.json.JSONObject;

public class ComunidadesController {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ComunidadesController() {
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

    public JSONObject getComunidades() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Comunidades> query
                    = this.entityManager.createNamedQuery("Comunidades.findAll", Comunidades.class);
            List<Comunidades> comunidades = query.getResultList();

            comunidades.forEach((c) -> {
                System.out.println(c.getId() + " " + c.getComunidad());
            });

            result.put("error", false);
            result.put("result", comunidades);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }

}
