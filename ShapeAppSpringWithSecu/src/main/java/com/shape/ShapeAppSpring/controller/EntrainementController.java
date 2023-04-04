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

import com.shape.ShapeAppSpring.dao.EntrainementDao;
import com.shape.ShapeAppSpring.domain.Entrainement;




@RestController
@RequestMapping
@CrossOrigin("*")
public class EntrainementController {
	
	@Autowired
	EntrainementDao entrainementDao; 
	
	@GetMapping("/entrainements")
	public List<Entrainement> getAllEntrainements(@Validated @RequestBody(required = false) Entrainement entrainement) {
		return entrainementDao.getEntrainements();
		
	}
	
	@PostMapping("/entrainements")
	public Entrainement createEntrainement(@Validated @RequestBody(required = false) Entrainement entrainement) {
		return entrainementDao.saveEntrainement(entrainement);
		
	}
	
	@GetMapping("/entrainements/{entrainementId}")
	public ResponseEntity findEntrainementById(@PathVariable(name = "entrainementId")Long entrainementId){
		if (entrainementId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas l'entrainement avec son ID");
		}
		
		Entrainement entrainement = entrainementDao.getEntrainementByID(entrainementId);
		
		if (entrainement == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(entrainement); 
		
	}
	
	@PutMapping("/entrainements/{entrainementId}")
	public ResponseEntity<Entrainement> updateEntrainement (@Validated @PathVariable(name = "entrainementId")Long entrainementId, @RequestBody(required = false) Entrainement entrainement) {
		if (entrainement == null) {
			return ResponseEntity.notFound().build();
			
		}
		entrainement.setEntrainementId(entrainementId);
		entrainementDao.updateEntrainement(entrainement);
		return ResponseEntity.ok().body(entrainement);
	}
	
	@DeleteMapping("/entrainements/{entrainementId}")
	public ResponseEntity<Entrainement> deleteEntrainement (@Validated @PathVariable(name = "entrainementId")Long entrainementId) {
		
		Entrainement entrainement = entrainementDao.getEntrainementByID(entrainementId);
		
		if (entrainement == null) {
			return ResponseEntity.notFound().build();
		
	}
		entrainementDao.deleteEntrainement(entrainement);
		return ResponseEntity.ok().body(entrainement); 
	
	}
}
	
