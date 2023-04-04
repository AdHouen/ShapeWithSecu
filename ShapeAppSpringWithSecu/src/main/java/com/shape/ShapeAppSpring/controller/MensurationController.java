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

import com.shape.ShapeAppSpring.dao.MensurationDao;
import com.shape.ShapeAppSpring.domain.Mensuration;

@RestController
@RequestMapping
@CrossOrigin("*")
public class MensurationController {
	
	@Autowired
	MensurationDao mensurationDao; 
	
	@GetMapping("/mensurations")
	public List<Mensuration> getAllMensurations(@Validated @RequestBody(required = false) Mensuration mensuration) {
		return mensurationDao.getMensurations();
		
	}
	
	@PostMapping("/mensurations")
	public Mensuration createMensuration(@Validated @RequestBody(required = false) Mensuration mensuration) {
		return mensurationDao.saveMensuration(mensuration);
		
	}
	
	@GetMapping("/mensurations/{mensurationId}")
	public ResponseEntity findMensurationById(@PathVariable(name = "mensurationId")Long mensurationId){
		if (mensurationId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas la mensuration avec son ID");
		}
		
		Mensuration mensuration = mensurationDao.getMensurationByID(mensurationId);
		
		if (mensuration == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(mensuration); 
		
	}
	
	@PutMapping("/mensurations/{mensurationId}")
	public ResponseEntity<Mensuration> updateMensuration (@Validated @PathVariable(name = "mensurationId")Long mensurationId, @RequestBody(required = false) Mensuration mensuration) {
		if (mensuration == null) {
			return ResponseEntity.notFound().build();
			
		}
		mensuration.setMensurationId(mensurationId);
		mensurationDao.updateMensuration(mensuration);
		return ResponseEntity.ok().body(mensuration);
	}
	
	@DeleteMapping("/mensurations/{mensurationId}")
	public ResponseEntity<Mensuration> deleteMensuration (@Validated @PathVariable(name = "mensurationId")Long mensurationId) {
		
		Mensuration mensuration = mensurationDao.getMensurationByID(mensurationId);
		
		if (mensuration == null) {
			return ResponseEntity.notFound().build();
		
	}
		mensurationDao.deleteMensuration(mensuration);
		return ResponseEntity.ok().body(mensuration); 
	
	}
	
	@GetMapping("/mensurations/date")
	public List<Mensuration> getAllMensurationsByDate(@Validated @RequestBody(required = false) Mensuration mensuration) {
		return mensurationDao.getMensurationsByDate();
		
	}

}

