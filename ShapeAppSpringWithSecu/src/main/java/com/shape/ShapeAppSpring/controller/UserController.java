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

import com.shape.ShapeAppSpring.dao.UserDao;
import com.shape.ShapeAppSpring.domain.User;





@RestController
@RequestMapping
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserDao userDao; 
	
	@GetMapping("/users")
	public List<User> getAllUsers(@Validated @RequestBody(required = false) User user) {
		return userDao.getUsers();
		
	}
	
	@PostMapping("/users")
	public User createUser(@Validated @RequestBody(required = false) User user) {
		return userDao.saveUser(user);
		
	}
	
	@GetMapping("/users/{uId}")
	public ResponseEntity findUserById(@PathVariable(name = "uId")Long uId){
		if (uId == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas le user avec son ID");
		}
		
		User user = userDao.getUserByID(uId);
		
		if (user == null) {
			return ResponseEntity.notFound().build(); 
		}
		
		return ResponseEntity.ok().body(user); 
		
	}
	
	@PutMapping("/users/{uId}")
	public ResponseEntity<User> updateUser (@Validated @PathVariable(name = "uId")Long uId, @RequestBody(required = false) User user) {
		if (user == null) {
			return ResponseEntity.notFound().build();
			
		}
		user.setUid(uId);
		userDao.updateUser(user);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("/users/{uId}")
	public ResponseEntity<User> deleteUser (@Validated @PathVariable(name = "uId")Long uId) {
		
		User user = userDao.getUserByID(uId);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		
	}
		userDao.deleteUser(user);
		return ResponseEntity.ok().body(user); 
	
	}
	

}


