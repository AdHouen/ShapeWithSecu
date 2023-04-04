package com.shape.ShapeAppSpring.resource;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shape.ShapeAppSpring.domain.HttpResponse;
import com.shape.ShapeAppSpring.domain.User;
import com.shape.ShapeAppSpring.domain.UserPrincipal;
import com.shape.ShapeAppSpring.exception.domain.EmailExistException;
import com.shape.ShapeAppSpring.exception.domain.EmailNotFoundException;
import com.shape.ShapeAppSpring.exception.domain.ExceptionHandling;
import com.shape.ShapeAppSpring.exception.domain.NotAnImageFileException;
import com.shape.ShapeAppSpring.exception.domain.UserNotFoundException;
import com.shape.ShapeAppSpring.exception.domain.UsernameExistException;
import com.shape.ShapeAppSpring.service.UserService;
import com.shape.ShapeAppSpring.utility.JWTTokenProvider;

import jakarta.mail.MessagingException;

import static com.shape.ShapeAppSpring.constant.FileConstant.*;
import static com.shape.ShapeAppSpring.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;



@RestController
@RequestMapping(path = { "/", "/user" })
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")

public class UserResource extends ExceptionHandling {
	
	private static final String IMAGE_JPEG_VALUE2 = IMAGE_JPEG_VALUE;
	private static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
// Des que vous voyez la notation Rest c est un Web Service pour obtenir du JSON
	private static final String EMAIL_SENT = "An email with a new password,was sent to : you";
	private static final HttpStatus NO_CONTENT = null;
	private static final HttpStatus TEMP_PROFILE_IMAGE_BASE_URL = null;

	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	public UserResource(UserService userService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		super();
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@GetMapping("/home")
	public String showUser() throws UserNotFoundException {
		// return "Application works !"
		// throw new EmailExistException("This email address is already taken");
		throw new UserNotFoundException("User Not Found");
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) throws UserNotFoundException {
		authenticate(user.getUsername(), user.getPassword());
		User loginUser = userService.findUserByUsername(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException {
		User newUser = userService.register(user.getFirstname(), user.getLastname(), user.getUsername(),
				user.getEmail(),user.getPassword());
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	


	
	  @PostMapping("/add") public ResponseEntity<User> addNewUser(
			  @RequestParam("firstname") String firstname,
			  @RequestParam("lastname") String lastname, 
			  @RequestParam("username") String username,
			  @RequestParam("email") String email, 
			  @RequestParam("password") String password, 
			  @RequestParam("role") String role,
			  @RequestParam("isActive") String isActive, 
			  @RequestParam("isNonLocked") String isNonLocked,
			  @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) 
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException { 
		  
		  User newUser = userService.addNewUser(firstname, lastname, username, email,password, role, Boolean.parseBoolean(isNonLocked), 
				  		Boolean.parseBoolean(isActive), profileImage); 
		  
		  return new ResponseEntity<>(newUser, HttpStatus.OK); }
	 

	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestParam("currentUsername") String currentUsername,
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("role") String role, @RequestParam("isActive") String isActive,
			@RequestParam("isNonLocked") String isNonLocked,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImage)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {

		User updateUser = userService.updateUser(currentUsername,firstname, lastname, username, email, role,
				Boolean.parseBoolean(isNonLocked),  Boolean.parseBoolean(isActive), profileImage);
		System.out.println("Here Update : " + updateUser);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}


	@GetMapping("/find/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		User user = userService.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> user = userService.getUsers();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/*@GetMapping("/list2")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getUsers();
		System.out.println("Here is the result ... or not ..."+users);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}*/

	@GetMapping("/resetPassword/{email}")
	public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email)
			throws EmailNotFoundException, MessagingException {
		userService.resetPassword(email);

		return response(HttpStatus.OK, EMAIL_SENT + email);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('user:delete')")
	public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") long id)
			throws EmailNotFoundException, MessagingException {
		userService.deleteUser(id);
		return response(HttpStatus.OK, USER_DELETED_SUCCESSFULLY+ id);
	}
	
	@PostMapping("/updateProfileImage")
	public ResponseEntity<User> updateProfileImage(
		
			@RequestParam("username") String username, 
			@RequestParam(value = "profileImage") MultipartFile profileImage)
			throws UserNotFoundException, UsernameExistException, IOException, NotAnImageFileException, EmailExistException {
				User user = userService.updateProfileImage(username, profileImage);
				return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(path="/image/{profile}/{username}",produces = IMAGE_JPEG_VALUE)
	public byte[] gProfileImage(@RequestParam("username")String username, @RequestParam("fileName")String fileName)
	throws IOException {
		return Files.readAllBytes(Paths.get(USER_FOLDER+username+FORWARD_SLASH+fileName));
											//"user.home"+"/xprod/user/EgonAdmin/EgonAdmin.jpg"
	}
 
	
	@GetMapping(path="/image/{username}/{fileName}",produces = IMAGE_JPEG_VALUE)
	public byte[] getTempProfileImage(@RequestParam("username")String username, 
	@RequestParam("fileName")String fileName)
		throws IOException {
			URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL+username);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try(InputStream inputStream = url.openStream()){
				int bytesRead;
				byte[] chunk = new byte[1024];
				while((bytesRead=inputStream.read(chunk))>0) {
					byteArrayOutputStream.write(chunk,0, bytesRead); //0-1024 bytes
				}
		}
		
			return byteArrayOutputStream.toByteArray();
			//"user.home"+"xprod/user/paul/paul.jpeg		}
	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		
		HttpResponse body = new HttpResponse(httpStatus.value(),httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());
		
		return new ResponseEntity<>(body,httpStatus);
	}

	private HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJWTToken(user));

		return headers;
	}

	private void authenticate(String username, String password) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); // donner une
																											// instance
																											// de
																											// UsernamAUthentication
	}


}

