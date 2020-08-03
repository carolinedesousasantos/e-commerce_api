package control;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Municipios;
import org.json.JSONObject;

public class MunicipiosController {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MunicipiosController() {
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

    public JSONObject getMunicipios() {

        JSONObject result = new JSONObject();

        try {
            TypedQuery<Municipios> query
                    = this.entityManager.createNamedQuery("Municipios.findAll", Municipios.class);
            List<Municipios> municipios = query.getResultList();

            municipios.forEach((m) -> {
                System.out.println(m.getId() + " " + m.getMunicipio());
            });

            result.put("error", false);
            result.put("result", municipios);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
            result.put("error", true);
            result.put("result", persistenceException.getMessage());
        }
        return result;

    }

}
