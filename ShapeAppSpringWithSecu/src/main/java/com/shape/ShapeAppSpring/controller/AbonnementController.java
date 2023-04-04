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

import com.shape.ShapeAppSpring.dao.AbonnementDao;
import com.shape.ShapeAppSpring.domain.Abonnement;




@RestController
@RequestMapping
@CrossOrigin("*")
public class AbonnementController {
	
	@Autowired
	AbonnementDao abonnementDao; 
	
	@GetMapping("/abonnements")
	public List<Abonnement> getAllAbonnements(@Validated @RequestBody(required = false) Abonnement abonnement) {
		return abonnementDao.getAbonnements();
		
	}
	
	@PostMapping("/abonnements")
	public Abonnement createAbonnement(@Validated @RequestBody(required = false) Abonnement abonnement) {
		return abonnementDao.saveAbonnement(abonnement);
		
	}
	
	@GetMapping("/abonnements/{abonnementId}")
	public ResponseEntity findAbonnementById(@PathVariable(name = "abonnementId")Long abonnementId){
		if (abonnementId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas l'abonnement avec son ID");
		}
		
		Abonnement abonnement = abonnementDao.getAbonnementByID(abonnementId);
		
		if (abonnement == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(abonnement); 
		
	}
	
	@PutMapping("/abonnements/{abonnementId}")
	public ResponseEntity<Abonnement> updateAbonnement (@Validated @PathVariable(name = "abonnementId")Long abonnementId, @RequestBody(required = false) Abonnement abonnement) {
		if (abonnement == null) {
			return ResponseEntity.notFound().build();
			
		}
		abonnement.setAbonnementId(abonnementId);
		abonnementDao.updateAbonnement(abonnement);
		return ResponseEntity.ok().body(abonnement);
	}
	
	@DeleteMapping("/abonnements/{abonnementId}")
	public ResponseEntity<Abonnement> deleteAbonnement (@Validated @PathVariable(name = "abonnementId")Long abonnementId) {
		
		Abonnement abonnement = abonnementDao.getAbonnementByID(abonnementId);
		
		if (abonnement == null) {
			return ResponseEntity.notFound().build();
		
	}
		abonnementDao.deleteAbonnement(abonnement);
		return ResponseEntity.ok().body(abonnement); 
	
	}
}
