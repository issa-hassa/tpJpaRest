package Services;

import DAOs.UserDAO;
import domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class Connexion {
    EntityManager em;
    UserDAO userDAO ;

    public Connexion(EntityManager em) {
        Objects.requireNonNull(em);
        this.em = em;
        userDAO = new UserDAO(em);
    }
    public boolean checkPassWord(String login, String passWord){
        return userDAO.getAuthentification(login).equals(passWord);
    }
    public boolean checkLogin(String login){
        List<User> users= this.em.createQuery("SELECT  e FROM User e WHERE e.login = :login", User.class)
                .setParameter("login",login)
                .getResultList();
        return users.size() == 0;
    }
}
