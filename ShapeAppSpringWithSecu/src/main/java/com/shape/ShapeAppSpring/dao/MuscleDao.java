package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.Muscle;
import com.shape.ShapeAppSpring.repository.IMuscleRepository;



@Service
public class MuscleDao {
	
	@Autowired
	IMuscleRepository muscleRepository;
	
	// Liste des Muscles
	
	public List<Muscle> getMuscles() {
		return muscleRepository.findAll();
		
	}
	
	//Save un Muscle 
	
	public Muscle saveMuscle(Muscle muscle) {
		return muscleRepository.save(muscle);
		
	}
	
	// Get un Muscle by ID
	
	public Muscle getMuscleByID(Long muscleId) {
		return muscleRepository.findById(muscleId).get();
	}
	
	
	// Delete un Muscle 
	
	public void deleteMuscle(Muscle muscle) {
		muscleRepository.delete(muscle);
	
	}
	
	// Update un Muscle
	
	public Muscle updateMuscle(Muscle muscle) {
		return muscleRepository.save(muscle);
		
	}

}
