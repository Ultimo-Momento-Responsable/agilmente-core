package com.umr.agilmentecore.Class.IntermediateClasses;

public class ProfessionalData {
	private String firstName;
	private String lastName;
	private String token;
	
	public ProfessionalData(String firstName, String lastName, String token) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
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
