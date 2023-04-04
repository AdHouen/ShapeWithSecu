package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.Exercice;
import com.shape.ShapeAppSpring.repository.IExerciceRepository;


@Service
public class ExerciceDao {
	
	@Autowired
	IExerciceRepository exerciceRepository;
	
	// Liste des Exercices
	public List<Exercice> getExercices() {
		return exerciceRepository.findAll();
		
	}
	
	//Save un Exercice 
	public Exercice saveExercice(Exercice exercice) {
		return exerciceRepository.save(exercice);
		
	}
	
	// get un Exercice by ID
	public Exercice getExerciceByID(Long exerciceId) {
		return exerciceRepository.findById(exerciceId).get();
	}
	
	
	// Delete un Exercice 
	
	public void deleteExercice(Exercice exercice) {
		exerciceRepository.delete(exercice);
	
	}
	
	// Update un Exercice 
	
	public Exercice updateExercice(Exercice exercice) {
		return exerciceRepository.save(exercice);
		
	}

}
