package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import DAOs.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.EntityManagerHelper;

@WebServlet(name="userinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {
    UserDAO userDAO;
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Logger.getGlobal().info("Adding a user");

        PrintWriter out = response.getWriter();
        String nom, prenom,login,motDePasse,type;
        nom = request.getParameter("Nom");
        prenom = request.getParameter("Prenom");
        login = request.getParameter("Login");
        type = request.getParameter("Type");
        motDePasse = request.getParameter("MotDePasse");


        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif</H1>\n" +
                "<UL>\n" +
                " <LI>Type: "
                + type + "\n" +
                " <LI>Nom: "
                + nom + "\n" +
                " <LI>Prenom: "
                + prenom + "\n" +
                " <LI>Login: "
                + login + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
        out.flush();


        EntityManager manager = EntityManagerHelper.getEntityManager();
        userDAO = new UserDAO(manager);
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        try {
            if(type.equals("Compte professionnel")){
                userDAO.createProUser(nom,prenom,login,motDePasse);
            }
            else{
                userDAO.createClientUser(nom,prenom,login,motDePasse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }
}
