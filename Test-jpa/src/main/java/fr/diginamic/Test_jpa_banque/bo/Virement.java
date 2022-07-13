package fr.diginamic.Test_jpa_banque.bo;

import javax.persistence.Entity;

@Entity
public class Virement extends Operation {

	private String beneficiaire;
	
	public Virement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * @return the beneficiaire
	 */
	public String getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * Setter
	 * @param beneficiaire the beneficiaire to set
	 */
	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Virement [beneficiaire=");
		builder.append(beneficiaire);
		builder.append("]");
		return builder.toString();
	}

}
