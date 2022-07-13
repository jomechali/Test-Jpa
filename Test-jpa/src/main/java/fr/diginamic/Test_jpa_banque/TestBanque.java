package fr.diginamic.Test_jpa_banque;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.Test_jpa_banque.bo.Adresse;
import fr.diginamic.Test_jpa_banque.bo.Banque;
import fr.diginamic.Test_jpa_banque.bo.Client;
import fr.diginamic.Test_jpa_banque.bo.Compte;
import fr.diginamic.Test_jpa_banque.bo.Operation;
import fr.diginamic.Test_jpa_banque.bo.Virement;

public class TestBanque {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque_db");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println(entityManager);

		Compte compte1 = new Compte();
		compte1.setNumero("numeroCompte1");
		compte1.setSolde(3000);
		
		Compte compte2 = new Compte();
		compte2.setNumero("numerocommte2");
		compte2.setSolde(600);

		Client client1 = new Client();
		client1.setNom("toto");
		client1.setPrenom("titi");
		client1.setComptes(Stream.of(compte1, compte2).collect(Collectors.toSet()));
		client1.setAdresse(new Adresse());
		compte1.setPropietaires(Stream.of(client1).collect(Collectors.toSet()));

		Client client2 = new Client();
		client2.setNom("toto");
		client2.setPrenom("titi");
		client2.setComptes(Stream.of(compte2).collect(Collectors.toSet()));
		client2.setAdresse(new Adresse());
		compte2.setPropietaires(Stream.of(client1, client2).collect(Collectors.toSet()));
		
		Banque banque = new Banque();
		banque.setNom("banque1");
		banque.setClients(Stream.of(client1).collect(Collectors.toSet()));
		client1.setBanque(banque);

		Operation operation = new Operation();
		operation.setDate(LocalDateTime.now());
		operation.setMontant(200);
		operation.setMotif("testtesttest");
		operation.setCompte(compte1);

		Virement virement = new Virement();
		virement.setDate(LocalDateTime.now());
		virement.setMontant(200);
		virement.setMotif("testtesttest");
		virement.setCompte(compte1);

		compte1.setOperations(Stream.of(operation, virement).collect(Collectors.toSet()));

		entityManager.getTransaction().begin();
		entityManager.persist(banque);
		entityManager.persist(compte1);
		entityManager.persist(client1);
		entityManager.persist(operation);
		entityManager.persist(virement);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}
