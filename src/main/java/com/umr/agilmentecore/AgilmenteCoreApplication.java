package com.umr.agilmentecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.umr.agilmentecore.Security.JWTAuthorizationFilter;

@SpringBootApplication
public class AgilmenteCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgilmenteCoreApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers(HttpMethod.GET, "/patient/{id:\\d+}").permitAll()
				.antMatchers(HttpMethod.GET, "/patient/loginCode/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/patient/{id:\\d+}").permitAll()
				.antMatchers(HttpMethod.GET, "/planning/mobile_patient/**").permitAll()
				.antMatchers(HttpMethod.GET, "/result/ranking**").permitAll()
				.antMatchers(HttpMethod.POST, "/result/encuentra-al-nuevo").permitAll()
				.antMatchers(HttpMethod.POST, "/result/encuentra-al-repetido").permitAll()
				.antMatchers(HttpMethod.POST, "/result/memorilla").permitAll()
				.antMatchers(HttpMethod.POST, "/login/loginCaptcha").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated();
		}
	}
}
