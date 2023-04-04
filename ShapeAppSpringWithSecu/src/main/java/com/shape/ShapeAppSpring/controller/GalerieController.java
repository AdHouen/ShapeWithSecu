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


import com.shape.ShapeAppSpring.dao.GalerieDao;
import com.shape.ShapeAppSpring.domain.Galerie;
import com.shape.ShapeAppSpring.domain.Mensuration;



@RestController
@RequestMapping
@CrossOrigin("*")
public class GalerieController {
	
	@Autowired
	GalerieDao galerieDao; 
	
	@GetMapping("/galeries")
	public List<Galerie> getAllGaleries(@Validated @RequestBody(required = false) Galerie galerie) {
		return galerieDao.getGaleries();
		
	}
	
	@PostMapping("/galeries")
	public Galerie createGalerie(@Validated @RequestBody(required = false) Galerie galerie) {
		return galerieDao.saveGalerie(galerie);
		
	}
	
	@GetMapping("/galeries/{galerieId}")
	public ResponseEntity findGalerieById(@PathVariable(name = "galerieId")Long galerieId){
		if (galerieId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas la galerie avec son ID");
		}
		
		Galerie galerie = galerieDao.getGalerieByID(galerieId);
		
		if (galerie == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(galerie); 
		
	}
	
	@PutMapping("/galeries/{galerieId}")
	public ResponseEntity<Galerie> updateGalerie (@Validated @PathVariable(name = "galerieId")Long galerieId, @RequestBody(required = false) Galerie galerie) {
		if (galerie == null) {
			return ResponseEntity.notFound().build();
			
		}
		galerie.setGalerieId(galerieId);
		galerieDao.updateGalerie(galerie);
		return ResponseEntity.ok().body(galerie);
	}
	
	@DeleteMapping("/galeries/{galerieId}")
	public ResponseEntity<Galerie> deleteGalerie (@Validated @PathVariable(name = "galerieId")Long galerieId) {
		
		Galerie galerie = galerieDao.getGalerieByID(galerieId);
		
		if (galerie == null) {
			return ResponseEntity.notFound().build();
		
	}
		galerieDao.deleteGalerie(galerie);
		return ResponseEntity.ok().body(galerie); 
	
	}
	
	@GetMapping("/galeries/date")
	public List<Galerie> getAllPhotosByDate(@Validated @RequestBody(required = false) Galerie galerie) {
		return galerieDao.getPhotosByDate();
		
	}
	


	
}


