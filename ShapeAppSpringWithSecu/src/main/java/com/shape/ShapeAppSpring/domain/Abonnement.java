package com.shape.ShapeAppSpring.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ABONNEMENT")
public class Abonnement implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ABONNEMENTID")
	private Long abonnementId;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "dateSouscription")
	private LocalDate dateSouscription;
	@Column(name = "DUREE")
	private Integer duree;
	@Column(name = "UID")
	private Long uid;
	
	// GETTER 
	
	public Long getAbonnementId() {
		return abonnementId;
	}
	public String getType() {
		return type;
	}
	public LocalDate getDateSouscription() {
		return dateSouscription;
	}
	public Integer getDuree() {
		return duree;
	}
	public Long getUid() {
		return uid;
	}

	
	// SETTER
	public void setAbonnementId(Long abonnementId) {
		this.abonnementId = abonnementId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDateSouscription(LocalDate dateSouscription) {
		this.dateSouscription = dateSouscription;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	
	// CONSTRUCTOR
	
	public Abonnement() {
		super();
	}
	public Abonnement(Long abonnementId, String type, LocalDate dateSouscription, Integer duree, Long uid) {
		super();
		this.abonnementId = abonnementId;
		this.type = type;
		this.dateSouscription = dateSouscription;
		this.duree = duree;
		this.uid = uid;
	}
	




	
	

	
	
	

}
