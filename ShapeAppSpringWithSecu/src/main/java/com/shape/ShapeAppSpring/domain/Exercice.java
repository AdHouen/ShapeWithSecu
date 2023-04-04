package com.shape.ShapeAppSpring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="EXERCICE")
public class Exercice implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXERCICEID")
	private Long exerciceId;
	
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PHOTO")
	private String photo;
	@Column(name = "POSITION")
	private String position;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "MUSCLEID")
	private Long muscleId;
	

			
			
	// GETTER
			

	public Long getExerciceId() {
		return exerciceId;
	}
	public String getNom() {
		return nom;
	}

	public String getPhoto() {
		return photo;
	}

	public String getPosition() {
		return position;
	}

	public String getDescription() {
		return description;
	}
	public Long getMuscleId() {
		return muscleId;
	}

	
	// SETTER


	public void setExerciceId(Long exerciceId) {
		this.exerciceId = exerciceId;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public void setMuscleId(Long muscleId) {
		this.muscleId = muscleId;
	}

	
	// CONSTRUCTOR

	public Exercice() {
		super();
	}
	public Exercice(Long exerciceId, String nom, String photo, String position, String description, Long muscleId) {
		super();
		this.exerciceId = exerciceId;
		this.nom = nom;
		this.photo = photo;
		this.position = position;
		this.description = description;
		this.muscleId = muscleId;
	}

	










	
	
	
		 

}
