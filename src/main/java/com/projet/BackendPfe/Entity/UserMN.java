package com.projet.BackendPfe.Entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="UserMN")
public class UserMN extends User {
	protected long telephone ;
	protected Date date_naissance ;
	/**  Association entre Admin et user **/
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "adminMedical_id")
	  private AdminMedicalManager adminMedical;
	public UserMN(int cin, String nom, String prenom, String gender, String username, String email, String password,
			String reservePassword, String image, Date date_inscription, String role,AdminMedicalManager adminMedical, long telephone,
			Date date_naissance) {
		super(cin, nom, prenom, gender, username, email, password, reservePassword, image, date_inscription, role);
		this.telephone = telephone;
		this.date_naissance = date_naissance;
		this.adminMedical=adminMedical;
	}
	
	public UserMN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	  

}
