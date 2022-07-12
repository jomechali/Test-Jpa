package fr.diginamic.Test_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.Test_jpa.bo.Livre;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_essai");
    	EntityManager em = emf.createEntityManager();
    	System.out.println(em);
    	
    	
    	//System.out.println(em.find(Class.forName("fr.diginamic.Test_jpa.bo.Livre"), 3));
    	
    	//em.getTransaction().begin();
    	/*
    	Livre livre = new Livre();
    	livre.setAuteur("David Gemmell");
    	livre.setTitre("Jon Shannow");
    	livre.setId(10);
		em.persist(livre );
    	*/
    	/*
    	Livre aModifier = em.find(Livre.class, 5);
    	aModifier.setTitre("Du plaisir dans la cuisine");
    	*/
    	/*
    	em.remove(em.find(Livre.class, 10));
    	*/
    	//em.getTransaction().commit();
    	
    	TypedQuery<Livre> findAllQuery = em.createQuery("from Livre", Livre.class);
    	List<Livre> livres = findAllQuery.getResultList();
    	
    	for (Livre livre : livres) {
			System.out.println(livre);
		}

    	TypedQuery<Livre> findByTitle = em.createQuery("select l from Livre l where l.titre = :titre", Livre.class);
    	TypedQuery<Livre> findByAuteur = em.createQuery("select l from Livre l where l.auteur = :auteur", Livre.class);
    	System.out.println(findByAuteur.setParameter("auteur", "Emile Zola").getSingleResult());
    	System.out.println(findByTitle.setParameter("titre", "Germinal").getSingleResult());
    	em.close();
    	emf.close();
    }
}
