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
@Table(name="MENSURATION")
public class Mensuration implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENSURATIONID")
	private Long mensurationId;
	@Column (name ="DATE")
	private LocalDate date;
	@Column(name = "VALEUR") 
	private Integer valeur;
	@Column(name = "MUSCLEID")
	private Long muscleId;
	
	// GETTER 
	
	public Long getMensurationId() {
		return mensurationId;
	}
	public LocalDate getDate() {
		return date;
	}
	public Integer getValeur() {
		return valeur;
	}
	public Long getMuscleId() {
		return muscleId;
	}
	
	// SETTER 
	
	public void setMensurationId(Long mensurationId) {
		this.mensurationId = mensurationId;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	public void setMuscleId(Long muscleId) {
		this.muscleId = muscleId;
	}
	
	// CONSTRUCTOR
	
	public Mensuration() {
		super();
	}
	public Mensuration(Long mensurationId, LocalDate date, Integer valeur, Long muscleId) {
		super();
		this.mensurationId = mensurationId;
		this.date = date;
		this.valeur = valeur;
		this.muscleId = muscleId;
	}




	
	
	
	
	
	

}
