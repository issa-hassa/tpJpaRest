package jpa;
/*
* imports nécessaires pour utiliser les classes et interfaces JPA*/

import domain.Professionnel;
import domain.RDV;
import domain.Client;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JpaTest {

	EntityManager manager;
	private final int nbUsers = 10;
	List<Client> clientList = new ArrayList<>();
	List<Professionnel>  professionnelList = new ArrayList<>();


	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	public void createUtilisateur(){

			int numOfEmploye = this.manager.createQuery("Select a from Client a ", Client.class)
					.getResultList().
					 size();

			if(numOfEmploye == 0){

				for (int i = 0; i <nbUsers ; i++) {
					this.clientList.add(new Client("utilisateur"+i));
				}
				for(Client client : this.clientList) this.manager.persist(client);
			}


	}
	public void createProfessionnel(){

		int numOfEmploye = this.manager.createQuery("Select a from Professionnel a ", Professionnel.class)
				.getResultList().
				size();

		if(numOfEmploye == 0){

			for (int i = 0; i <nbUsers ; i++) {
				this.professionnelList.add(new Professionnel("professionnel"+i));
			}
			for(Professionnel professionnel : this.professionnelList) this.manager.persist(professionnel);
		}


	}
	public void createRDV(){
		List<Client> clients = this.manager.createQuery("Select a from Client a ", Client.class)
				.getResultList();

		List<Professionnel> professionnels = this.manager.createQuery("Select a from Professionnel a ", Professionnel.class)
				.getResultList();

		if(clients.size() == 0 || professionnels.size() == 0){
			System.out.println("There are no users please create users first");
			return;
		}
		else{
			System.out.println("SELECT A USER");
			for (int i = 0; i < clients.size(); i++) {
				System.out.println(i +" :" + clients.get(i).getNom());
			}
			System.out.print(">");
			Scanner sc  = new Scanner(System.in);
			int userIndex = sc.nextInt();
			if(userIndex <0 || userIndex >= clients.size()){
				System.out.println("please select a valid index");
				return;
			}
			System.out.println("SELECT A PROFESSIONNAL");

			for (int i = 0; i < professionnels.size(); i++) {
				System.out.println(i +" :" + professionnels.get(i).getNom());
			}

			int proIndex = sc.nextInt();
			if(userIndex <0 || userIndex >= clients.size()){
				System.out.println("please select a valid index");
				return;
			}

			System.out.println("SELECT A NAME FOR THE RDV : ");
			int i = 0;
			List<Enum> enumList = new ArrayList<>();
			for (Enum name:Professionnel.Title.values()) {
				System.out.println(i+" :" + name.name());
				enumList.add(name);
				i++;
			}
			int enumIndex = sc.nextInt();
			if(enumIndex < 0 || enumIndex >= enumList.size()){
				System.out.println("please select a valid index ");
				return;
			}
			RDV rdv = new RDV(enumList.get(enumIndex).name());
			manager.persist(rdv);
			rdv.setProfessionnel(professionnels.get(proIndex));
			rdv.setUtilisateur(clients.get(userIndex));





		}
	}
}
