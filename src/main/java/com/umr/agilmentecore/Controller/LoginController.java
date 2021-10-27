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
import com.umr.agilmentecore.Services.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@PostMapping	
    public String login(@RequestBody LoginData user) {
        return service.login(user);
    }
	
	@GetMapping(value = "/token/{token}")	
    public Boolean checkIfLogged(@PathVariable(name = "token") String token) {
        return service.checkIfLogged(token);
    }
}
