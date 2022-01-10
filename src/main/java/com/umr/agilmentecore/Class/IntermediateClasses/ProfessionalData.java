package com.umr.agilmentecore.Class.IntermediateClasses;

public class ProfessionalData {
	private Long id;
	private String firstName;
	private String lastName;
	private String token;
	
	public ProfessionalData(Long id, String firstName, String lastName, String token) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
	}
	
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getToken() {
		return token;
	}
}
