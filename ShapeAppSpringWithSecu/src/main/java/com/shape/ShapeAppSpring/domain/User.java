package com.shape.ShapeAppSpring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long uid;
	
	private String userId; // id de l utilisateur
	

	@Column(name="userEmail")
	private String email;
	
	@Column(name="userPassword")
	private String password;

	@Column(name="username")
	private String username;

	@Column(name="authorization")
	private String authorization;

	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;

	
	@Column(name="profileImg")
	private String profileImageURL;
	
	private Date lastLoginDate;
	private Date lastLoginDateDisplay;
	private Date joinDate;
	private String role; // ROLE_USER(read,edit), ROLE_ADMIN(delete)
	private String[] authorities; // [] = tableau de String // Authorities = permissions (read, edit, delete)
	private boolean isActive;// Pour activer les rôles
	private boolean isNotLocked;
	
	// ASSOCIATION
		//Avec Gallerie
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "uid")
		private List<Galerie> listGallerie= new ArrayList<>();

		//Avec Muscle
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "uid")
		private List<Muscle> listMuscle= new ArrayList<>();

		//Avec Entrainement
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "uid")
		private List<Entrainement> listEntrainement= new ArrayList<>();
		
	// GETTER

	public Long getUid() {
		return uid;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getAuthorization() {
		return authorization;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public Date getLastLoginDateDisplay() {
		return lastLoginDateDisplay;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public String getRole() {
		return role;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public List<Galerie> getListGallerie() {
		return listGallerie;
	}

	public List<Muscle> getListMuscle() {
		return listMuscle;
	}
	public List<Entrainement> getListEntrainement() {
		return listEntrainement;
	}
	
	// SETTER
	
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
		this.lastLoginDateDisplay = lastLoginDateDisplay;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public void setListGallerie(List<Galerie> listGallerie) {
		this.listGallerie = listGallerie;
	}

	public void setListMuscle(List<Muscle> listMuscle) {
		this.listMuscle = listMuscle;
	}
	public void setListEntrainement(List<Entrainement> listEntrainement) {
		this.listEntrainement = listEntrainement;
	}
	
	// CONSTRUCTOR
	
	public User() {
		super();
	}

	public User(Long uid, String userId, String email, String password, String username, String authorization,
			String firstname, String lastname, String profileImageURL, Date lastLoginDate, Date lastLoginDateDisplay,
			Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked,
			List<Galerie> listGallerie, List<Muscle> listMuscle, List<Entrainement> listEntrainement) {
		super();
		this.uid = uid;
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.username = username;
		this.authorization = authorization;
		this.firstname = firstname;
		this.lastname = lastname;
		this.profileImageURL = profileImageURL;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDateDisplay = lastLoginDateDisplay;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
		this.listGallerie = listGallerie;
		this.listMuscle = listMuscle;
		this.listEntrainement = listEntrainement;
	}







	
	
	

	

}
