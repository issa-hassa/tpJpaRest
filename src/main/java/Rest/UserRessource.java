package Rest;

import DAOs.UserDAO;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jpa.EntityManagerHelper;
import jpa.JpaTest;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.swing.text.html.parser.Entity;

@Path("user")
@Produces({"application/json", "application/xml"})
public class UserRessource {
    UserDAO userDAO;
    EntityManager em;

    @GET
    @Path("/{UserName}")
    public User getUserByName(@PathParam("UserName") String name){
        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        userDAO = new UserDAO<>(manager);

        tx.begin();
        User u = userDAO.getUserByName(name);
        tx.commit();

        return u;
    }
    @GET
    @Path("/id/{UserId}")
    public User getUserById(@PathParam("UserId") Long id){
        EntityManager manager = EntityManagerHelper.getEntityManager();
        userDAO = new UserDAO<>(manager);

        EntityTransaction tx = manager.getTransaction();
        User u;
        tx.begin();
        u = manager.find(User.class,id);
        tx.commit();
        System.out.println(u.getNom());
        return u;
    }

}
