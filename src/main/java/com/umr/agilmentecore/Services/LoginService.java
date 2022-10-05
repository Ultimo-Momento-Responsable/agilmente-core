package com.umr.agilmentecore.Services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.IntermediateClasses.LoginData;
import com.umr.agilmentecore.Class.IntermediateClasses.ProfessionalData;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class LoginService {
	@Autowired
	private ProfessionalRepository repository;
	
	/**
	 * Función que se encarga de chequear el login y setear el tiempo de expiración del mismo.
	 * @param user Objeto con usuario y contraseña
	 * @return Devuelve el token de inicio de sesión o nulo si el usuario no existe
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public ProfessionalData login(LoginData user) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		Professional professional = repository.findByUserName(user.getUserName());
		
		if (Objects.nonNull(professional)) {
			if (passwordEncoder.matches(user.getPassword(), professional.getPassword())) {
				String token = getJWTToken(professional.getUserName());
				professional.setToken(token);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 3);
				professional.setTokenExpiration(cal.getTime());
				repository.save(professional);
				ProfessionalData pd = new ProfessionalData(professional.getId(), professional.getFirstName(),professional.getLastName(),professional.getToken());
				return pd;
			}
		}
		return null;
	}
	
	/**
	 * Genera un token
	 * @return devuelve el token
	 */
	public static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * Chequea si el token es válido y si no se ha expirado
	 * @param token el token que viene del front
	 * @return devuelve true o false si es válido o no el token
	 */
	public Boolean checkIfLogged(String token) {
		Professional professional = repository.findByToken(token);
		if (Objects.nonNull(professional)) {
			Date today = new Date();
			if (professional.getTokenExpiration().after(today)) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 3);
				professional.setTokenExpiration(cal.getTime());
				repository.save(professional);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Crea un token JWT.
	 * @param username nombre de usuario
	 * @return JWT
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	private String getJWTToken(String username) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String secretKey = "C81CBD833EA526BFAF6F4CBAEECCD5A8141C38A1DFF26B2134DDFA75A1";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256,
						secretKey.getBytes()).compact();
		return "Bearer " + token;
	}
	
}
