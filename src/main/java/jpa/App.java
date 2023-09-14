package jpa;

import DAOs.UserDAO;
import Services.Connexion;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

class App {
    UserDAO userDAO;
    Connexion cx;

    EntityManager em;

    public App(EntityManager em) {
        this.em = em;
        cx = new Connexion(em);
        this.userDAO = new UserDAO(em);
    }

    public void start(){

        Boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while(!exit){
            System.out.println(">1 Connexion");
            System.out.println(">2 Créer un compte professionnel");
            System.out.println(">3 Créer un compte client");

            int choice = sc.nextInt();
            switch (choice){
                case 1 :  break;
                case 2 :
                    this.creatAccountClient(true);
                    System.out.println("Compte créer avec succes");
                    break;
                case 3 : break;
            }

        }



    }

    public void creatAccountClient( Boolean pro){

        Scanner sc = new Scanner(System.in);

        String nom, prenom, login, motDePasse;
        System.out.println("Entrez le nom : ");
        nom  = sc.nextLine();
        System.out.println("Entrez le prenom : ");
        prenom  = sc.nextLine();
        System.out.println("Entrez le login : ");
        login = sc.nextLine();
        boolean isLoginUnique = cx.checkLogin(login);
        while(!isLoginUnique){
            System.out.println("Ce login existe déjà dans la base de donnée");
            System.out.println("Entrez un autre login : ");
            login = sc.nextLine();
            isLoginUnique = cx.checkLogin(login);
        }
        System.out.println("Entrez le mot de passe : ");
        motDePasse = sc.nextLine();

        while (motDePasse.length() < 8){
            System.out.println("Le mot de passe doit contenir au moins 8 caractères:");
            motDePasse = sc.nextLine();
        }
        if(!pro){
            this.userDAO.createClientUser(nom,prenom,login,motDePasse);
        }
        else {
            this.userDAO.createProUser(nom,prenom,login,motDePasse);
        }
    }
    public void connexion(){
        Scanner sc = new Scanner(System.in);
        String login,motDePasse;
        System.out.println("Entrez le login : ");
        login = sc.nextLine();
        System.out.println("Entrez le mot de passe : ");
        motDePasse = sc.nextLine();
        if(cx.checkPassWord(login,motDePasse)){
            boolean exit = false;
            int choice;
            while (!exit){
                System.out.println("1 : liste des rendez-vous");
                System.out.println("2 : Créer un rendez-vous rendez-vous");
                System.out.println("3 : Exit");
                choice = sc.nextInt();
                if(choice == 1){

                }

            }
        }

    }
}
