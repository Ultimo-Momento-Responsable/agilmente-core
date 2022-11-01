package com.umr.agilmentecore.Class.IntermediateClasses;

public class ChangePassword {
	private Long professionalId;
	private String oldPassword;
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public Long getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Long professionalId) {
		this.professionalId = professionalId;
	}
}
