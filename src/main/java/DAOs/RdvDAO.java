package DAOs;

import domain.RDV;
import domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class RdvDAO {

    EntityManager em ;

    public RdvDAO(EntityManager em) {
        Objects.requireNonNull(em);
        this.em = em;
    }
    public List<RDV> getAllRDVofPro(User user){
        Objects.requireNonNull(user);
        return em.createQuery("SELECT a FROM RDV a WHERE a.professionnel.id = :userid",RDV.class)
                        .setParameter("userid",user.getId())
                        .getResultList();
    }
    public List<RDV> getAllRDVofPro(String userName){
        Objects.requireNonNull(userName);
        return em.createQuery("SELECT a FROM RDV a WHERE a.professionnel.nom = :userName",RDV.class)
                .setParameter("userName",userName)
                .getResultList();
    }

    public List<RDV> getAllRDVofUser(User user){
        Objects.requireNonNull(user);
        return em.createQuery("SELECT a FROM RDV a WHERE a.utilisateur.id = :userid",RDV.class)
                .setParameter("userid",user.getId())
                .getResultList();
    }
    public List<RDV> getAllRDVofUser(String userName){
        Objects.requireNonNull(userName);
        return em.createQuery("SELECT a FROM RDV a WHERE a.utilisateur.nom = :userName",RDV.class)
                .setParameter("userName",userName)
                .getResultList();
    }
}
