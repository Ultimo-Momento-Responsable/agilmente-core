package com.umr.agilmentecore.Class.IntermediateClasses;

public class PatientComment {
	private Long patientId;
	private String comment;
	private String professionalFirstName;
	private String professionalLastName;
	private int commentId;
	
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setProfessionalFirstName(String professionalFirstName) {
		this.professionalFirstName = professionalFirstName;
	}

	public void setProfessionalLastName(String professionalLastName) {
		this.professionalLastName = professionalLastName;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public int getCommentId() {
		return commentId;
	}

	public Long getPatientId() {
		return patientId;
	}
	public String getComment() {
		return comment;
	}
	public String getProfessionalFirstName() {
		return professionalFirstName;
	}
	public String getProfessionalLastName() {
		return professionalLastName;
	}
}
