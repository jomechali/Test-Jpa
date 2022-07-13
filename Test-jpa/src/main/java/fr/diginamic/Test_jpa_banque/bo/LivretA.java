package fr.diginamic.Test_jpa_banque.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity qui represente un livretA
 * @author Joseph
 *
 */
@Entity
@Table(name = "livretA")
public class LivretA extends Compte {

	private int taux;
	
	public LivretA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * @return the taux
	 */
	public int getTaux() {
		return taux;
	}

	/**
	 * Setter
	 * @param taux the taux to set
	 */
	public void setTaux(int taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LivretA [taux=");
		builder.append(taux);
		builder.append("]");
		return builder.toString();
	}

}
