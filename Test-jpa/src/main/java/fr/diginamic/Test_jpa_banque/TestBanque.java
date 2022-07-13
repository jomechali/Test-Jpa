package fr.diginamic.Test_jpa_banque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.Test_jpa_banque.bo.Adresse;
import fr.diginamic.Test_jpa_banque.bo.AssuranceVie;
import fr.diginamic.Test_jpa_banque.bo.Banque;
import fr.diginamic.Test_jpa_banque.bo.Client;
import fr.diginamic.Test_jpa_banque.bo.Compte;
import fr.diginamic.Test_jpa_banque.bo.LivretA;
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
		
		AssuranceVie compte2 = new AssuranceVie();
		compte2.setNumero("numerocommte2");
		compte2.setSolde(600);
		compte2.setDateFin(LocalDate.of(2042, 1, 1));
		compte2.setTaux(0.2);
		
		LivretA compte3 = new LivretA();
		compte3.setNumero("numerocommte3");
		compte3.setSolde(600);
		compte3.setTaux(3);

		Client client1 = new Client();
		client1.setNom("toto");
		client1.setPrenom("titi");
		client1.setComptes(Stream.of(compte1, compte2).collect(Collectors.toSet()));
		client1.setAdresse(new Adresse());
		compte1.setPropietaires(Stream.of(client1).collect(Collectors.toSet()));

		Client client2 = new Client();
		client2.setNom("tata");
		client2.setPrenom("tutu");
		client2.setComptes(Stream.of(compte2, compte3).collect(Collectors.toSet()));
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
		entityManager.persist(client1);
		entityManager.persist(client2);
		entityManager.persist(compte2);
		entityManager.persist(compte1);
		entityManager.persist(compte3);
		entityManager.persist(operation);
		entityManager.persist(virement);
		entityManager.persist(virement);
		entityManager.getTransaction().commit();
		/*entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();*/

		entityManager.close();
		entityManagerFactory.close();
	}

}
