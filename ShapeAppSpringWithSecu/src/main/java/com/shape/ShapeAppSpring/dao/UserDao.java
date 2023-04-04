package com.shape.ShapeAppSpring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.ShapeAppSpring.domain.User;
import com.shape.ShapeAppSpring.repository.IUserRepository;


@Service
public class UserDao {
	
	@Autowired
	IUserRepository userRepository;
	
	// Liste de Users
	public List<User> getUsers() {
		return userRepository.findAll();
		
	}
	
	//Save un User 
	
	public User saveUser(User user) {
		return userRepository.save(user);
		
	}
	
	// get un User by ID
	
	public User getUserByID(Long uid) {
		return userRepository.findById(uid).get();
	}
	
	
	// Delete un User 
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	
	}
	
	// Update un User 
	
	public User updateUser(User user) {
		return userRepository.save(user);
		
	}

}
