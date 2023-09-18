package DAOs;

import com.mysql.cj.log.Log;
import domain.Client;
import domain.Professionnel;
import domain.RDV;
import domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

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
        return em.createQuery("SELECT a FROM RDV a WHERE a.client.id = :userid",RDV.class)
                .setParameter("userid",user.getId())
                .getResultList();
    }
    public List<RDV> getAllRDVofUser(String userName){
        Objects.requireNonNull(userName);
        return em.createQuery("SELECT a FROM RDV a WHERE a.client.nom = :userName",RDV.class)
                .setParameter("userName",userName)
                .getResultList();
    }

    public void createRDV(Client c, Professionnel p, Date d){
        if(d.after(new Date())) {
            Logger.getGlobal().info("La date choisi est depassée");
            return;
        }
        for(RDV r : p.getListRDV()){
            if(r.getDate().equals(d)||r.getDate().equals(d.getTime()+360000)){
                Logger.getGlobal().info(p.getNom()+" a déjà un rdv à l'heure choisie");
                return;
            }
        }
        for(RDV r : c.getListRdv()){
            if(r.getDate().equals(d)||r.getDate().equals(d.getTime()+360000)){
                Logger.getGlobal().info(c.getNom()+" a déjà un rdv à l'heure choisie");
                return;
            }
        }
        RDV rdv = new RDV();
        rdv.setDate(d);
        rdv.setClient(c);
        rdv.setProfessionnel(p);
        this.em.persist(rdv);





    }
}
