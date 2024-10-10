package com.projet.BackendPfe.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Audit")
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroChangement;
	
	@NotNull
	@JsonProperty("typedocument")
	private String typedocument;
	
	@NotNull
	@JsonProperty("numeroinstruction")
	private String numeroinstruction;
	
	@NotNull
	@JsonProperty("titredocument")
	private String titredocument;
	
	@NotNull
	@JsonProperty("raison")
	private String raison;
	
	@NotNull
	@JsonProperty("contenu")
	private String contenu;
	

	@JsonProperty("etatdeinstruction")
	private String etatdeinstruction;
	
	@NotNull
	@JsonProperty("effectif")
	private String effectif;
	
	@NotNull
	@JsonProperty("application")
	private String application;
	
	@NotNull
	@JsonProperty("miseajour")
	private String miseajour;
	
	@NotNull
	@JsonProperty("deviation")
	private String deviation;
	@NotNull
	@JsonProperty("action")
	private String action;
	@NotNull
	@JsonProperty("responsable")
	private String responsable;
	@NotNull
	@JsonProperty("delai")
	private String delai;
	@NotNull
	@JsonProperty("controlesuccess")
	private String controlesuccess;
	
	
	
	
	
	
	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Audit(long numeroChangement, @NotNull String typedocument, @NotNull String numeroinstruction,
			@NotNull String titredocument, @NotNull String raison, @NotNull String contenu,
			@NotNull String etatdeinstruction, @NotNull String effectif, @NotNull String application,
			@NotNull String miseajour, @NotNull String deviation, @NotNull String action, @NotNull String responsable,
			@NotNull String delai, @NotNull String controlesuccess) {
		super();
		this.numeroChangement = numeroChangement;
		this.typedocument = typedocument;
		this.numeroinstruction = numeroinstruction;
		this.titredocument = titredocument;
		this.raison = raison;
		this.contenu = contenu;
		this.etatdeinstruction = etatdeinstruction;
		this.effectif = effectif;
		this.application = application;
		this.miseajour = miseajour;
		this.deviation = deviation;
		this.action = action;
		this.responsable = responsable;
		this.delai = delai;
		this.controlesuccess = controlesuccess;
	}
	public long getNumeroChangement() {
		return numeroChangement;
	}
	public void setNumeroChangement(long numeroChangement) {
		this.numeroChangement = numeroChangement;
	}
	public String getTypedocument() {
		return typedocument;
	}
	public void setTypedocument(String typedocument) {
		this.typedocument = typedocument;
	}
	public String getNumeroinstruction() {
		return numeroinstruction;
	}
	public void setNumeroinstruction(String numeroinstruction) {
		this.numeroinstruction = numeroinstruction;
	}
	public String getTitredocument() {
		return titredocument;
	}
	public void setTitredocument(String titredocument) {
		this.titredocument = titredocument;
	}
	public String getRaison() {
		return raison;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getEtatdeinstruction() {
		return etatdeinstruction;
	}
	public void setEtatdeinstruction(String etatdeinstruction) {
		this.etatdeinstruction = etatdeinstruction;
	}
	public String getEffectif() {
		return effectif;
	}
	public void setEffectif(String effectif) {
		this.effectif = effectif;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getMiseajour() {
		return miseajour;
	}
	public void setMiseajour(String miseajour) {
		this.miseajour = miseajour;
	}
	public String getDeviation() {
		return deviation;
	}
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getDelai() {
		return delai;
	}
	public void setDelai(String delai) {
		this.delai = delai;
	}
	public String getControlesuccess() {
		return controlesuccess;
	}
	public void setControlesuccess(String controlesuccess) {
		this.controlesuccess = controlesuccess;
	}
	@Override
	public String toString() {
		return "Audit [numeroChangement=" + numeroChangement + ", typedocument=" + typedocument + ", numeroinstruction="
				+ numeroinstruction + ", titredocument=" + titredocument + ", raison=" + raison + ", contenu=" + contenu
				+ ", etatdeinstruction=" + etatdeinstruction + ", effectif=" + effectif + ", application=" + application
				+ ", miseajour=" + miseajour + ", deviation=" + deviation + ", action=" + action + ", responsable="
				+ responsable + ", delai=" + delai + ", controlesuccess=" + controlesuccess + "]";
	}
	
	

}
