package fr.diginamic.Test_jpa_banque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.Test_jpa_banque.bo.Adresse;
import fr.diginamic.Test_jpa_banque.bo.AssuranceVie;
import fr.diginamic.Test_jpa_banque.bo.Banque;
import fr.diginamic.Test_jpa_banque.bo.Client;
import fr.diginamic.Test_jpa_banque.bo.Compte;
import fr.diginamic.Test_jpa_banque.bo.LivretA;
import fr.diginamic.Test_jpa_banque.bo.Operation;
import fr.diginamic.Test_jpa_banque.bo.Virement;

public class TestJPQL {

	public static void main(String[] args) {

		// initialisation

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
		banque.setClients(Stream.of(client1, client2).collect(Collectors.toSet()));
		client1.setBanque(banque);
		client2.setBanque(banque);

		Operation operation = new Operation();
		operation.setDate(LocalDateTime.now());
		operation.setMontant(200);
		operation.setMotif("testtesttest");
		operation.setCompte(compte1);

		Virement virement = new Virement();
		virement.setDate(LocalDateTime.now());
		virement.setMontant(1000);
		virement.setMotif("testtesttest");
		virement.setCompte(compte1);
		virement.setBeneficiaire("chanceux");

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
		entityManager.getTransaction().commit();

		// Requetes
		TypedQuery<Compte> rechercheCompteParClient = entityManager.createQuery(
				"SELECT compte FROM fr.diginamic.Test_jpa_banque.bo.Compte compte JOIN compte.proprietaires p WHERE p.id = :clientId",
				Compte.class);
		rechercheCompteParClient.setParameter("clientId", 1);

		List<Compte> comptesClient1 = rechercheCompteParClient.getResultList();
		/*
		 * for (Compte compte : comptesClient1) { System.out.println(compte); }
		 */

		TypedQuery<Compte> rechercheComptesParBanque = entityManager.createQuery(
				"SELECT DISTINCT compte " + "FROM fr.diginamic.Test_jpa_banque.bo.Banque banque "
						+ "JOIN banque.clients clients " + "JOIN clients.comptes compte " + "WHERE banque.nom = :nom",
				Compte.class);
		rechercheComptesParBanque.setParameter("nom", "banque1");

		List<Compte> comptesBanque1 = rechercheComptesParBanque.getResultList();

		/*
		 * for (Compte compte : comptesBanque1) { System.out.println(compte); }
		 */

		TypedQuery<Compte> rechercheComptesOperationPlusdeMille = entityManager
				.createQuery(
						"SELECT compte " + "FROM fr.diginamic.Test_jpa_banque.bo.Compte compte "
								+ "JOIN compte.operations operation " + "WHERE operation.montant >= 1000",
						Compte.class);
		List<Compte> comptesOperationPlusMille = rechercheComptesOperationPlusdeMille.getResultList();

		/*
		 * for (Compte compte : comptesOperationPlusMille) { System.out.println(compte);
		 * }
		 */

		TypedQuery<Compte> recherchecompteAyantDesOperations = entityManager.createQuery("SELECT DISTINCT compte "
				+ "FROM fr.diginamic.Test_jpa_banque.bo.Operation operation " + "JOIN operation.compte compte",
				Compte.class);
		List<Compte> comptesAyantDesOperations = recherchecompteAyantDesOperations.getResultList();

		for (Compte compte : comptesAyantDesOperations) {
			System.out.println(compte);
		}
		entityManager.close();
		entityManagerFactory.close();

	}

}
