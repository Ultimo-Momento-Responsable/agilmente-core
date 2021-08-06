package com.umr.agilmentecore.Class;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "description")
	private String description;
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "born_date")
	private Date bornDate;
	@Column(name = "city")
	private String city;
	@Column(name = "login_code")
	private String loginCode;
	@Column(name = "is_logged")
	private boolean isLogged = false;
}
