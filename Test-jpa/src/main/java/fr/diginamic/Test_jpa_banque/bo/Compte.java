package fr.diginamic.Test_jpa_banque.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity qui represente un compte
 * @author Joseph
 *
 */
@Entity
@Table(name = "compte")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte implements Serializable{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String numero;
	private double solde;
	
	@ManyToMany(mappedBy = "comptes")
	private Set<Client> propietaires;
	
	@OneToMany(mappedBy = "compte")
	private Set<Operation> operations;
	
	public Compte() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Getter
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * Setter
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * Getter
	 * @return the propietaires
	 */
	public Set<Client> getPropietaires() {
		return propietaires;
	}

	/**
	 * Setter
	 * @param propietaires the propietaires to set
	 */
	public void setPropietaires(Set<Client> propietaires) {
		this.propietaires = propietaires;
	}

	/**
	 * Getter
	 * @return the operations
	 */
	public Set<Operation> getOperations() {
		return operations;
	}

	/**
	 * Setter
	 * @param operations the operations to set
	 */
	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compte [id=");
		builder.append(id);
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", solde=");
		builder.append(solde);
		builder.append("]");
		return builder.toString();
	}

}
