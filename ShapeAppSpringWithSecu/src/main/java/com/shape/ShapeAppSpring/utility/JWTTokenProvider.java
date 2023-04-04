package com.shape.ShapeAppSpring.utility;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.shape.ShapeAppSpring.constant.SecurityConstant.*;
import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.shape.ShapeAppSpring.domain.UserPrincipal;

@Component
public class JWTTokenProvider {
//Pour recuperer notre TOKEN il nous faut un clé secrete et une cle publique

	// Je fais appel a l'annotion value
	// {$} = je récupère une valeur
	@Value("${jwt.secret}") // Il provient du fichier application.properties
	private String secret;


	public String generateJWTToken(UserPrincipal userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);

		return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION).withIssuedAt(new Date())
				.withSubject(userPrincipal.getUsername()).withArrayClaim(AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(HMAC512(secret.getBytes()));

	}

	public List<GrantedAuthority> getAuthorities(String token) {
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList()); // :: methode reference to
																								// Java 8 using
																								// Collection
	}

	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJWTVerifier();

		return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
	}

	private JWTVerifier getJWTVerifier() {

			JWTVerifier verifier;
			try {
				Algorithm algorithm = HMAC512(secret);
						verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
			}catch(JWTVerificationException exception){
				throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
			}



			return verifier;
	}

	private String[] getClaimsFromUser(UserPrincipal user) {
		List<String> authorities = new ArrayList<>();
		// Je parcours mes autorisations assoxié a mes utilisateurs
		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			// et je recupere ces auth et j'ajoute a ma liste
			authorities.add(grantedAuthority.getAuthority());

		}
		return authorities.toArray(new String[0]);
	}

	//Get authentification when we verify the token

	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken usernamePassworAuthToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
		usernamePassworAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		return usernamePassworAuthToken;


	}

	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJWTVerifier();
		return StringUtils.isNotEmpty(username)&&!isTokenExpired(verifier,token);

	}

	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		// new Date() = je créé une nouvelle instnace de date
		return expiration.before(new Date());
	}

	public String getSubject(String token) {
		JWTVerifier verifier = getJWTVerifier();

		return verifier.verify(token).getSubject();

	}
}

