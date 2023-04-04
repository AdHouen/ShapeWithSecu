package com.shape.ShapeAppSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shape.ShapeAppSpring.domain.Entrainement;

public interface IEntrainementRepository extends JpaRepository<Entrainement, Long>{

}
