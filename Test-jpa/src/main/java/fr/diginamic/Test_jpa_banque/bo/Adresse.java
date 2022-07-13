package fr.diginamic.Test_jpa_banque.bo;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Complement d inforamations pour une personne
 * @author Joseph
 *
 */
@Embeddable
public class Adresse implements Serializable {

	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
	/**
	 * Constructor
	 */
	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Getter
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Setter
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * Getter
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * Setter
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * Getter
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	/**
	 * Setter
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * Getter
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * Setter
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adresse [numero=");
		builder.append(numero);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}
	
}
