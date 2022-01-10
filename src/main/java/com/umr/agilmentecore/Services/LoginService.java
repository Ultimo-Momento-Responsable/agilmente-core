package com.umr.agilmentecore.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.IntermediateClasses.LoginData;
import com.umr.agilmentecore.Class.IntermediateClasses.ProfessionalData;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

@Service
public class LoginService {
	@Autowired
	private ProfessionalRepository repository;
	
	/**
	 * Función que se encarga de chequear el login y setear el tiempo de expiración del mismo.
	 * @param user Objeto con usuario y contraseña
	 * @return Devuelve el token de inicio de sesión o nulo si el usuario no existe
	 */
	public ProfessionalData login(LoginData user) {
		Professional professional = repository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (Objects.nonNull(professional)) {
			String token = generateString();
			professional.setToken(token);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			professional.setTokenExpiration(cal.getTime());
			repository.save(professional);
			ProfessionalData pd = new ProfessionalData(professional.getId(), professional.getFirstName(),professional.getLastName(),professional.getToken());
			return pd;
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
}
