package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.Entrainement;
import com.shape.ShapeAppSpring.repository.IEntrainementRepository;




@Service
public class EntrainementDao {
	
	@Autowired
	IEntrainementRepository entrainementRepository;
	
	
	
	// Liste des Entrainements
	public List<Entrainement> getEntrainements() {
		return entrainementRepository.findAll();
		
	}
	
	//Save un Entrainement 
	public Entrainement saveEntrainement(Entrainement entrainement) {
		return entrainementRepository.save(entrainement);
		
	}
	
	// get un Entrainement by ID
	public Entrainement getEntrainementByID(Long entrainementId) {
		return entrainementRepository.findById(entrainementId).get();
	}
	
	
	// Delete un Entrainement 
	
	public void deleteEntrainement(Entrainement entrainement) {
		entrainementRepository.delete(entrainement);
	
	}
	
	// Update un Entrainement 
	
	public Entrainement updateEntrainement(Entrainement entrainement) {
		return entrainementRepository.save(entrainement);
		
	}

}
