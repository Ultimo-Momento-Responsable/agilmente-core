package com.umr.agilmentecore.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.IntermediateClasses.LoginData;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

@Service
public class LoginService {
	@Autowired
	private ProfessionalRepository repository;
	
	public String login(LoginData user) {
		try {
			Professional professional = repository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
			String token = generateString();
			professional.setToken(token);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			professional.setTokenExpiration(cal.getTime());
			repository.save(professional);
			return token;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
	}

	public Boolean checkIfLogged(String token) {
		try {
			Professional professional = repository.findByToken(token);
			Date today = new Date();
			if (professional.getTokenExpiration().after(today)) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 3);
				professional.setTokenExpiration(cal.getTime());
				repository.save(professional);
				return true;
			}
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
}
