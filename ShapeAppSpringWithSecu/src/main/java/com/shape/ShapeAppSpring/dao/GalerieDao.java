package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.Galerie;
import com.shape.ShapeAppSpring.domain.Mensuration;
import com.shape.ShapeAppSpring.repository.IGalerieRepository;




@Service
public class GalerieDao {
	
	@Autowired
	IGalerieRepository galerieRepository;
	
	// Liste de Galleries
	
	public List<Galerie> getGaleries() {
		return galerieRepository.findAll();
		
	}
	
	//Save une Gallerie
	
	public Galerie saveGalerie(Galerie galerie) {
		return galerieRepository.save(galerie);
		
	}

	// Get une Galerie by ID
	
	public Galerie getGalerieByID(Long galerieId) {
		return galerieRepository.findById(galerieId).get();
	}
	
	
	// Delete une Galerie 
	
	public void deleteGalerie(Galerie galerie) {
		galerieRepository.delete(galerie);
	
	}
	
	// Update une Galerie
	
	public Galerie updateGalerie(Galerie galerie) {
		return galerieRepository.save(galerie);
		
	}
	
	// Liste des photos by Date
	
	public List<Galerie> getPhotosByDate() {
		return galerieRepository.findAllByOrderByDateDesc();
			
	}

}
