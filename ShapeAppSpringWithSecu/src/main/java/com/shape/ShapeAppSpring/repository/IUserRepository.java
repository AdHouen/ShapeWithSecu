package com.shape.ShapeAppSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shape.ShapeAppSpring.domain.User;

public interface IUserRepository extends JpaRepository<User,Long>{
	
	User findUserByUsername(String username);
	
	User findUserByEmail(String email);

}