package jpa;

import DAOs.UserDAO;
import Services.Connexion;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();
        JpaTest test = new JpaTest(manager);
       // App app = new App(manager);
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        try {
           // app.start();
            test.createProfessionnel();
            test.createUtilisateur();
            test.createRDV();

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }




}

