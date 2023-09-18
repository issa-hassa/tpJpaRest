package DAOs;

import domain.Client;
import domain.Professionnel;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserDAO<T extends Serializable> {

    EntityManager em;

    public UserDAO(EntityManager em) {
        Objects.requireNonNull(em);
        this.em = em;
    }
    public User getUserByName(String name){
        try {
            return this.em.createQuery("SELECT u FROM User u WHERE u.nom = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (NoResultException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.warning("Aucun utilisateur avec le nom : " + name + " n'a été trouvé dans la base de données.");
            return null;
        }
    }
    public User getUserByLogin(String login){
        try {
            return this.em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        }
        catch (NoResultException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.warning("Aucun utilisateur avec le login : " + login + " n'a été trouvé dans la base de données.");
            return null;
        }
    }
    public String  getAuthentification(String login){
        User userBd = this.em.createQuery("SELECT u FROM User u WHERE u.login = :login",User.class)
                .setParameter("login",login)
                .getSingleResult();
        return userBd.getMotDePasse();
    }
    public List<User> getAllUsers(){
        return  this.em.createQuery("SELECT e FROM User e").getResultList();
    }

    public void createProUser(String nom,String prenom, String login,String passWord){
        User user = new Professionnel( nom,  prenom,  login,  passWord);
        this.em.persist(user);
    }
    public void createClientUser(String nom,String prenom, String login,String passWord){
        User user = new Client( nom,  prenom,  login,  passWord);
        this.em.persist(user);
    }



}
