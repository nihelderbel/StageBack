package com.projet.BackendPfe.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "PCDA")
public class PCDA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@JsonProperty("Designation")
	private String Designation;
	
	@NotNull
	@JsonProperty("Titre")
	private String Titre;

 @NotNull(message = "Sujet_de_publication ne peut pas être nul")
	@JsonProperty("Sujet_de_publication")
	private String Sujet_de_publication;

	@NotNull
	@JsonProperty("Processus")
	private String Processus;

	@NotNull
	@JsonProperty("Priorite")
	private long Priorite;
	@NotNull
	@JsonProperty("Action")
	private String Action;
	
	@NotNull
	@JsonProperty("O_N")
	private String O_N;

	
	@JsonProperty("Delaideaction")
	private String Delaideaction;
	
	@NotNull
	@JsonProperty("Responsable")
	private String Responsable;
	
	@NotNull
	@JsonProperty("Delai")
	private String Delai;

	@NotNull
	@JsonProperty("Statut")
	private String Statut;
	
	@NotNull
	@JsonProperty("Commentaire")
	private String Commentaire;
	
	public PCDA() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PCDA(long id, String designation, String titre, String sujet_de_publication, String processus, long priorite,
			String action, String o_N, String delaideaction, String responsable, String delai, String statut,
			String commentaire) {
		super();
		this.id = id;
		Designation = designation;
		Titre = titre;
		Sujet_de_publication = sujet_de_publication;
		Processus = processus;
		Priorite = priorite;
		Action = action;
		O_N = o_N;
		Delaideaction = delaideaction;
		Responsable = responsable;
		Delai = delai;
		Statut = statut;
		Commentaire = commentaire;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String désignation) {
		Designation = désignation;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getSujet_de_publication() {
		return Sujet_de_publication;
	}
	public void setSujet_de_publication(String sujet_de_publication) {
		Sujet_de_publication = sujet_de_publication;
	}
	public String getProcessus() {
		return Processus;
	}
	public void setProcessus(String processus) {
		Processus = processus;
	}
	public long getPriorite() {
		return Priorite;
	}
	public void setPriorite(long priorite) {
		Priorite = priorite;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getO_N() {
		return O_N;
	}
	public void setO_N(String o_N) {
		O_N = o_N;
	}
	public String getDelaideaction() {
		return Delaideaction;
	}
	public void setDelaideaction(String delaideaction) {
		Delaideaction = delaideaction;
	}
	public String getResponsable() {
		return Responsable;
	}
	public void setResponsable(String responsable) {
		Responsable = responsable;
	}
	public String getDelai() {
		return Delai;
	}
	public void setDelai(String delai) {
		Delai = delai;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	@Override
	public String toString() {
		return "PCDA [id=" + id + ", Designation=" + Designation + ", Titre=" + Titre + ", Sujet_de_publication="
				+ Sujet_de_publication + ", Processus=" + Processus + ", Priorite=" + Priorite + ", Action=" + Action
				+ ", O_N=" + O_N + ", Delaideaction=" + Delaideaction + ", Responsable=" + Responsable + ", Delai="
				+ Delai + ", Statut=" + Statut + ", Commentaire=" + Commentaire + "]";
	}
	
	

}
