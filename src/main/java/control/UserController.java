package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Resetpasswordtokens;
import model.User;
import org.json.JSONObject;
import utils.Password;
import utils.SendEmailSMTP;

public class UserController {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserController() {
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

    public JSONObject login(String email, String password) {

        JSONObject result = new JSONObject();

        try {
            String jpql = "select u from User u where u.email = :email";
            TypedQuery<User> typedQuery = this.entityManager.createQuery(jpql, User.class);
            typedQuery.setParameter("email", email);
            User user = typedQuery.getSingleResult();

            String passwordModificada = Password.hashPassword(password);

            if (user.getEmail().equals(email)) {
                if (user.getEmail().equals(email) && user.getHashPass().equals(passwordModificada)) {
                    System.out.println("Usuario logado.");
                    result.put("error", false);
                    result.put("msg", "Usuario logado.");
                } else {
                    System.out.println("Email e/ou senha incorrecto(s).");
                    result.put("error", true);
                    result.put("msg", "Email e/ou senha incorrecto(s).");
                }
            }

        } catch (NoResultException noResultException) {
            System.out.println("Email não cadastrado: " + noResultException.getMessage());
            result.put("error", true);
            result.put("msg", "Email não cadastrado");
        } catch (PersistenceException persistenceException) {
            System.out.println("Erro no login: " + persistenceException.getMessage());
        }

        return result;

    }

    public JSONObject createUser(String name, String surnames, String email, String password,
            LocalDate birthDate, String gender, LocalDateTime creationDate, Boolean policieAccepted, Boolean active) {

        JSONObject result = new JSONObject();

        User user = new User();
        UserController userController = new UserController();

        boolean emailExists = userController.checkIfEmailExists(email);
        if (emailExists) {
            System.out.println("Email já cadastrado");
            result.put("error", true);
            result.put("msg", "Email já cadastrado");
        } else {
            user.setName(name);
            user.setSurnames(surnames);
            user.setEmail(email);

            String passwordModificada = Password.hashPassword(password);

            user.setHashPass(passwordModificada);
            user.setBirthDate(birthDate);
            user.setGender(gender);
            user.setCreationDate(creationDate);
            user.setPolicieAccepted(policieAccepted);
            user.setActive(active);

            entityManager.getTransaction().begin();

            try {
                entityManager.persist(user);
                entityManager.getTransaction().commit();
                System.out.println("Usuario inserido com sucesso.");
                result.put("error", false);
                result.put("msg", "Usuario inserido com sucesso.");
            } catch (PersistenceException persistenceException) {
                entityManager.getTransaction().rollback();
                System.out.println("Erro ao inserir usuario: " + persistenceException.getMessage());
                result.put("error", true);
                result.put("msg", "Usuario inserido com sucesso.");
            }
        }

        return result;
    }

    public static void updateUserById(EntityManager entityManager, Integer idUser, String name, String surnames, String email, LocalDate birthDate
    ) {
        User userToUpdate = entityManager.find(User.class, idUser);

        if (!name.equals("") && !name.equals(userToUpdate.getName())) {
            userToUpdate.setName(name);
        }

        if (!surnames.equals("") && !surnames.equals(userToUpdate.getSurnames())) {
            userToUpdate.setSurnames(surnames);
        }

        if (!email.equals("") && !email.equals(userToUpdate.getEmail())) {
            userToUpdate.setEmail(email);
        }

        if (!birthDate.equals(userToUpdate.getBirthDate())) {
            userToUpdate.setBirthDate(birthDate);
        }

        userToUpdate.setActive(true);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userToUpdate);
            entityManager.getTransaction().commit();
            System.out.println("Usuario atualizado  com sucesso.");
        } catch (PersistenceException persistenceException) {
            System.out.println("Erro ao atualizar usuario: " + persistenceException.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    public static void deleteUser(EntityManager entityManager, Integer userId) {
        User user = entityManager.find(User.class, userId);

        entityManager.getTransaction().begin();

        try {
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            System.out.println("Usuario removido  com sucesso.");
        } catch (PersistenceException persistenceException) {
            System.out.println("Erro ao remover usuario: " + persistenceException.getMessage());
        }
    }

    public boolean checkIfEmailExists(String email) {
        boolean emailExists = false;

        try {
            String jpql = "select u from User u where u.email= :email ";
            TypedQuery<User> typedQuery = this.entityManager.createQuery(jpql, User.class);
            System.out.println("Email ja existe");
            typedQuery.setParameter("email", email);
            User user = typedQuery.getSingleResult();

            emailExists = user.getEmail().equals(email);

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
        }

        return emailExists;

    }

    public Integer getUserId(String email) {
        Integer userId = 0;

        try {
            String jpql = "select u from User u where u.email= :email ";
            TypedQuery<User> typedQuery = this.entityManager.createQuery(jpql, User.class);
            System.out.println("Email ja existe");
            typedQuery.setParameter("email", email);
            User user = typedQuery.getSingleResult();

            userId = user.getId();

        } catch (PersistenceException persistenceException) {
            System.out.println("Erro: " + persistenceException.getMessage());
        }

        return userId;

    }

    public JSONObject saveNewPassword(String token, String password) {

        JSONObject result = new JSONObject();

        ResetPasswordTokensController resetPasswordtokensController = new ResetPasswordTokensController();

        Resetpasswordtokens infoByToken = resetPasswordtokensController.getInfoByToken(token);

        User userToUpdate = entityManager.find(User.class, infoByToken.getUserId());
        System.out.println(userToUpdate.getEmail());

        String hashPassword = Password.hashPassword(password);

        userToUpdate.setHashPass(hashPassword);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userToUpdate);
            entityManager.getTransaction().commit();
            System.out.println("Password updated");
            result.put("error", false);
            result.put("msg", "Password updated.");

        } catch (PersistenceException persistenceException) {
            System.out.println("Error updating password : " + persistenceException.getMessage());
            result.put("error", false);
            result.put("msg", "Error updating password");
            entityManager.getTransaction().rollback();
        }
        return result;
    }

}
