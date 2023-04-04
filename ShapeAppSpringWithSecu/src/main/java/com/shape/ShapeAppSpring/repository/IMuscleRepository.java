package com.shape.ShapeAppSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shape.ShapeAppSpring.domain.Muscle;

public interface IMuscleRepository  extends JpaRepository<Muscle, Long>{

}
