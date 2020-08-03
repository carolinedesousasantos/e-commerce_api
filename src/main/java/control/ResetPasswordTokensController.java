package control;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import model.Resetpasswordtokens;

public class ResetPasswordTokensController {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ResetPasswordTokensController() {
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

    public boolean saveToken(int userId, String token, LocalDateTime creationDate) {

        boolean savedToken;

        Resetpasswordtokens resetpasswordtokens = new Resetpasswordtokens();

        resetpasswordtokens.setUserId(userId);
        resetpasswordtokens.setToken(token);
        resetpasswordtokens.setDate(creationDate);

        entityManager.getTransaction().begin();

        try {
            entityManager.persist(resetpasswordtokens);
            entityManager.getTransaction().commit();
            System.out.println("Info inseridos com sucesso.");
            savedToken = true;
        } catch (PersistenceException persistenceException) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao inserir info: " + persistenceException.getMessage());
            savedToken = false;
        }

        return savedToken;
    }

    public int deleteTokenIfExist(int userId) {

        int rowsDeleted = 0;
        String jpql = "DELETE FROM Resetpasswordtokens r where r.userId= :userId ";
        TypedQuery<Resetpasswordtokens> typedQuery = entityManager.createQuery(jpql, Resetpasswordtokens.class);
        typedQuery.setParameter("userId", userId);
        try {

            entityManager.getTransaction().begin();
            rowsDeleted = typedQuery.executeUpdate();
            System.out.println("entities deleted: " + rowsDeleted);

            entityManager.getTransaction().commit();
            System.out.println("Apagar com sucesso.");

        } catch (PersistenceException persistenceException) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao apagar info: " + persistenceException.getMessage());
            rowsDeleted = -1;
        }

        return rowsDeleted;
    }

    public Resetpasswordtokens getInfoByToken(String token) {

        Resetpasswordtokens tokenInfo = null;

        try {

            TypedQuery<Resetpasswordtokens> typedQuery
                    = this.entityManager.createNamedQuery("Resetpasswordtokens.findByToken", Resetpasswordtokens.class);
            typedQuery.setParameter("token", token);
            tokenInfo = typedQuery.getSingleResult();

            System.out.println("tokenInfo " + tokenInfo);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
        }

        return tokenInfo;

    }
}
