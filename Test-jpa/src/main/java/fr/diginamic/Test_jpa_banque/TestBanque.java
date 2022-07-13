package fr.diginamic.Test_jpa_banque;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.Test_jpa_banque.bo.Adresse;
import fr.diginamic.Test_jpa_banque.bo.Banque;
import fr.diginamic.Test_jpa_banque.bo.Client;
import fr.diginamic.Test_jpa_banque.bo.Compte;

public class TestBanque {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque_db");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		System.out.println(entityManager);
		
		Compte compte1 = new Compte();
		compte1.setNumero("testestnumero");
		compte1.setSolde(3000);
		
		Client client = new Client();
		client.setNom("toto");
		client.setPrenom("titi");
		client.setComptes(Stream.of(compte1).collect(Collectors.toSet()));
		client.setAdresse(new Adresse());
		compte1.setPropietaires(Stream.of(client).collect(Collectors.toSet()));
		
		Banque banque = new Banque();
		banque.setNom("banque1");
		banque.setClients(Stream.of(client).collect(Collectors.toSet()));
		client.setBanque(banque);
		
		entityManager.getTransaction().begin();
		entityManager.persist(banque);
		entityManager.persist(compte1);
		entityManager.persist(client);
		entityManager.getTransaction().commit();
	
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
