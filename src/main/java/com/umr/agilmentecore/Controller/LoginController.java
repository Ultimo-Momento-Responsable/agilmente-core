package com.umr.agilmentecore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.IntermediateClasses.LoginData;
import com.umr.agilmentecore.Class.IntermediateClasses.ProfessionalData;
import com.umr.agilmentecore.Services.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	/**
	 * Función que se encarga de chequear el login y setear el tiempo de expiración del mismo.
	 * @param user Objeto con usuario y contraseña
	 * @return Devuelve el token de inicio de sesión o nulo si el usuario no existe
	 */
	@PostMapping	
    public ProfessionalData login(@RequestBody LoginData user) {
        return service.login(user);
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
}
