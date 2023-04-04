package com.shape.ShapeAppSpring.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shape.ShapeAppSpring.domain.Exercice;

public interface IExerciceRepository extends JpaRepository<Exercice, Long>{

}
