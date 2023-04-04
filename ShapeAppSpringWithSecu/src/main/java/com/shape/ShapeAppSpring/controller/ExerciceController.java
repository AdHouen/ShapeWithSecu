package com.shape.ShapeAppSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shape.ShapeAppSpring.dao.ExerciceDao;
import com.shape.ShapeAppSpring.domain.Exercice;




@RestController
@RequestMapping
@CrossOrigin("*")
public class ExerciceController {
	
	@Autowired
	ExerciceDao exerciceDao; 
	
	@GetMapping("/exercices")
	public List<Exercice> getAllExercices(@Validated @RequestBody(required = false) Exercice exercice) {
		return exerciceDao.getExercices();
		
	}
	
	@PostMapping("/exercices")
	public Exercice createExercice(@Validated @RequestBody(required = false) Exercice exercice) {
		return exerciceDao.saveExercice(exercice);
		
	}
	
	@GetMapping("/exercices/{exerciceId}")
	public ResponseEntity findExerciceById(@PathVariable(name = "exerciceId")Long exerciceId){
		if (exerciceId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas l'exercice avec son ID");
		}
		
		Exercice exercice = exerciceDao.getExerciceByID(exerciceId);
		
		if (exercice == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(exercice); 
		
	}
	
	@PutMapping("/exercices/{exerciceId}")
	public ResponseEntity<Exercice> updateExercice (@Validated @PathVariable(name = "exerciceId")Long exerciceId, @RequestBody(required = false) Exercice exercice) {
		if (exercice == null) {
			return ResponseEntity.notFound().build();
			
		}
		exercice.setExerciceId(exerciceId);
		exerciceDao.updateExercice(exercice);
		return ResponseEntity.ok().body(exercice);
	}
	
	@DeleteMapping("/exercices/{exerciceId}")
	public ResponseEntity<Exercice> deleteExercice (@Validated @PathVariable(name = "exerciceId")Long exerciceId) {
		
		Exercice exercice = exerciceDao.getExerciceByID(exerciceId);
		
		if (exercice == null) {
			return ResponseEntity.notFound().build();
		
	}
		exerciceDao.deleteExercice(exercice);
		return ResponseEntity.ok().body(exercice); 
	
	}
	

}


