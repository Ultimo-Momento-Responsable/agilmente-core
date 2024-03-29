package com.umr.agilmentecore.Controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.IntermediateClasses.ChangePassword;
import com.umr.agilmentecore.Class.IntermediateClasses.LoginData;
import com.umr.agilmentecore.Class.IntermediateClasses.ProfessionalData;
import com.umr.agilmentecore.Services.CaptchaService;
import com.umr.agilmentecore.Services.LoginService;
import com.umr.agilmentecore.Services.ProfessionalService;

import io.jsonwebtoken.security.InvalidKeyException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private CaptchaService captchaService;
	
	@Autowired
	private ProfessionalService professionalService;
	
	/**
	 * Función que se encarga de chequear el login y setear el tiempo de expiración del mismo.
	 * @param user Objeto con usuario y contraseña
	 * @return Devuelve el token de inicio de sesión o nulo si el usuario no existe
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	@PostMapping	
    public ProfessionalData login(@RequestBody LoginData user) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return service.login(user);
    }
	
	/**
	 * Endpoint que se encarga de cambiar la contraseña del profesional
	 * @param user contiene una contraseña vieja, una nueva y el id del profesional
	 * @return true o false si se puede cambiar o no.
	 */
	@PostMapping(value = "/changePassword")
	public Boolean changePassword(@RequestBody ChangePassword user) {
		return service.changePassword(user);
	}
	
	/**
	 * Chequea si el token es válido y si no se ha expirado
	 * @param token el token que viene del front
	 * @return devuelve true o false si es válido o no el token
	 */
	@GetMapping(value = "/token/{token}")	
    public Boolean checkIfLogged(@PathVariable(name = "token") String token) {
        return service.checkIfLogged(token);
    }
	
	/**
	 * Verifica con el servicio de Google si el usuario es un potencial bot. 
	 * @param token el token que viene del front
	 * @return devuelve true o false si es válido o no el token
	 */
	@PostMapping("/loginCaptcha")
	public Boolean captchaLoginAccount(@RequestBody String token) {
		return captchaService.processResponse(token, CaptchaService.REGISTER_ACTION);
	}
	
	/**
	 * Método para reiniciar la contraseña del profesional.
	 * @param email Correo del profesional.
	 * @return true o false si se pudo hacer o no.
	 */
	@GetMapping("/resetPassword/{email}")
	public Boolean resetPassword(@PathVariable(name = "email") String email) {
		return professionalService.resetPassword(email);
	}
}
