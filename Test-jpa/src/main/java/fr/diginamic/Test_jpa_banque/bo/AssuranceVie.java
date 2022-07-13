package fr.diginamic.Test_jpa_banque.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity qui represente une assurance vie
 * @author Joseph
 *
 */
@Entity
@Table(name = "assurance_vie")
public class AssuranceVie extends Compte {

	private LocalDate dateFin;
	private double taux;
	
	public AssuranceVie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Getter
	 * @return the taux
	 */
	public double getTaux() {
		return taux;
	}

	/**
	 * Setter
	 * @param taux the taux to set
	 */
	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssuranceVie [dateFin=");
		builder.append(dateFin);
		builder.append(", taux=");
		builder.append(taux);
		builder.append("]");
		return builder.toString();
	}

}
