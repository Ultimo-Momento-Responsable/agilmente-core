package com.umr.agilmentecore.Services;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umr.agilmentecore.Class.GoogleResponse;

@Service
public class CaptchaService {

	@Autowired
    private RestTemplate restTemplate;
	public static final String REGISTER_ACTION = "register";

	@Value("${google.recaptcha.key.threshold}")
	private float threshold;
	
	@Value("${google.recaptcha.key.secret}")
	private String secret;
	
	@Autowired
    protected HttpServletRequest request;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder.build();
	}
	
	protected static final String RECAPTCHA_URL_TEMPLATE = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
	
    public boolean processResponse(String response, String action) {
    	final URI verifyUri = URI.create(String.format(RECAPTCHA_URL_TEMPLATE, secret, response));
        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);        
        if(googleResponse.getScore() < threshold) {
            return false;
        }
        return true;
    }

}
