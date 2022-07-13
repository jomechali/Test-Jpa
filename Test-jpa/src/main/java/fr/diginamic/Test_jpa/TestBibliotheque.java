package fr.diginamic.Test_jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.Test_jpa.bo.Client;
import fr.diginamic.Test_jpa.bo.Emprunt;
import fr.diginamic.Test_jpa.bo.Livre;

public class TestBibliotheque {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Emprunt emprunt = entityManager.find(Emprunt.class, 1);
		for (Livre livre : emprunt.getLivres()) {
			System.out.println(livre);
		}
		System.out.println(emprunt.getDelai());


		Client client = entityManager.find(Client.class, 1);
		for (Emprunt empruntTemp : client.getEmprunts()) {
			System.out.println(empruntTemp);
		}
		entityManager.close();
		entityManagerFactory.close();
	}

}
