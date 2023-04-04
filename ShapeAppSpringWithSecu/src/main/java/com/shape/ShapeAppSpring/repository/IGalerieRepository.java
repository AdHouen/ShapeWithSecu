package com.shape.ShapeAppSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shape.ShapeAppSpring.domain.Galerie;
import com.shape.ShapeAppSpring.domain.Mensuration;

public interface IGalerieRepository extends JpaRepository<Galerie, Long>{
	public List<Galerie> findAllByOrderByDateDesc();

}
