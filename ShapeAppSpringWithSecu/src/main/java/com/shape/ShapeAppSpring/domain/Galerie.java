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
@Table(name="GALERIE")
public class Galerie implements Serializable {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GALERIEID")
	private Long galerieId;
	
	@Column(name = "GALERIEFILENAME")
	private String galeriefilename;
	@Column(name = "DATE")
	private LocalDate date; 
	@Column(name = "UID")
	private Long uid;
	
	// GETTER
	
	public Long getGalerieId() {
		return galerieId;
	}

	public String getGaleriefilename() {
		return galeriefilename;
	}
	public LocalDate getDate() {
		return date;
	}
	public Long getUid() {
		return uid;
	}
	
	// SETTER
	
	public void setGalerieId(Long galerieId) {
		this.galerieId = galerieId;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public void setGaleriefilename(String galeriefilename) {
		this.galeriefilename = galeriefilename;
	}
	
	// CONSTRUCTOR
	
	public Galerie() {
		super();
	}

	public Galerie(Long galerieId, String galeriefilename, LocalDate date, Long uid) {
		super();
		this.galerieId = galerieId;
		this.galeriefilename = galeriefilename;
		this.date = date;
		this.uid = uid;
	}









	
	

}
