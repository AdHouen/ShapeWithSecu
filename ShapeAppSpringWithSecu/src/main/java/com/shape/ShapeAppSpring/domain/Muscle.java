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
@Table(name="MUSCLE")
public class Muscle implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MUSCLEID")
	private Long muscleId;
	
	@Column(name = "NOM")
	private String nom;
	@Column(name = "UID")
	private Long uid;
	
	// ASSOCIATION
		//Avec Mensuration
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "muscleId")
		private List<Mensuration> listMensuration= new ArrayList<>();
	
		//Avec Exercice
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "muscleId")
		private List<Exercice> listExercice= new ArrayList<>();
	
		
	// GETTER 
	

	public Long getMuscleId() {
		return muscleId;
	}
	public String getNom() {
		return nom;
	}
	public Long getUid() {
		return uid;
	}
	public List<Mensuration> getListMensuration() {
		return listMensuration;
	}

	public List<Exercice> getListExercice() {
		return listExercice;
	}
	
	// SETTER


	public void setMuscleId(Long muscleId) {
		this.muscleId = muscleId;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public void setListMensuration(List<Mensuration> listMensuration) {
		this.listMensuration = listMensuration;
	}

	public void setListExercice(List<Exercice> listExercice) {
		this.listExercice = listExercice;
	}
	
	// CONSTRUCTOR

	public Muscle() {
		super();
	}
	public Muscle(Long muscleId, String nom, Long uid, List<Mensuration> listMensuration,
			List<Exercice> listExercice) {
		super();
		this.muscleId = muscleId;
		this.nom = nom;
		this.uid = uid;
		this.listMensuration = listMensuration;
		this.listExercice = listExercice;
	}







	










	
	

}
