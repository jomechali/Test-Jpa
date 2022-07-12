package fr.diginamic.Test_jpa.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIVRE")
public class Livre implements Serializable {

	@Id
	private int id;
	
	@Column(name = "TITRE", length = 255, nullable = false)
	private String titre;
	
	@Column(name = "AUTEUR", length = 50, nullable = false)
	private String auteur;
	
	public Livre() {
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
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Setter
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Getter
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * Setter
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Livre [id=");
		builder.append(id);
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", auteur=");
		builder.append(auteur);
		builder.append("]");
		return builder.toString();
	}

}
