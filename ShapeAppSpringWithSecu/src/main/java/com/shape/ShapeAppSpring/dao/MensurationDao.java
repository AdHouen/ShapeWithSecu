package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.Mensuration;
import com.shape.ShapeAppSpring.repository.IMensurationRepository;




@Service
public class MensurationDao {
	
	@Autowired
	IMensurationRepository mensurationRepository;
	
	// Liste des Mensurations
	
	public List<Mensuration> getMensurations() {
		return mensurationRepository.findAll();
		
	}
	
	//Save une Mensuration 
	
	public Mensuration saveMensuration(Mensuration mensuration) {
		return mensurationRepository.save(mensuration);
		
	}
	
	// Get une Mensuration by ID
	
	public Mensuration getMensurationByID(Long mensurationId) {
		return mensurationRepository.findById(mensurationId).get();
	}
	
	
	// Delete une Mensuration 
	
	public void deleteMensuration(Mensuration mensuration) {
		mensurationRepository.delete(mensuration);
	
	}
	
	// Update une Mensuration 
	
	public Mensuration updateMensuration(Mensuration mensuration) {
		return mensurationRepository.save(mensuration);
		
	}
	
	// Liste des Mensurations by Date
	
	public List<Mensuration> getMensurationsByDate() {
		return mensurationRepository.findAllByOrderByDateDesc();
			
	}

}
