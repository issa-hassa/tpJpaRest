package servlet;

import DAOs.UserDAO;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.EntityManagerHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name="allUsers",
        urlPatterns={"/AllUsers"})
public class AllUsers extends HttpServlet {
    UserDAO dao ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            resp.setContentType("text/html");
        Logger.getGlobal().info("doget All users");
        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();

        PrintWriter out = resp.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des utilisateur </H1>\n" +
                "<UL>\n" );
        tx.begin();
        List<User> userList;
        try {
            dao = new UserDAO(manager);
            userList = dao.getAllUsers();
            for(User user : userList){
                Logger.getGlobal().info(user.getNom());
                out.print(
                        "<li> "+ user.getNom() +" "+user.getPrenom() +" </li>"
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();


        out.println("</ul>");
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();




    }

}
