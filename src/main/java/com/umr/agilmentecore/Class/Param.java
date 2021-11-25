package com.umr.agilmentecore.Class;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "param")
public class Param {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "class_name")
	private String className;
	@Column(name = "type")
	private Integer type;
	@Column(name = "unit")
	private String unit;
	@Column(name = "contextual_help")
	private String contextualHelp;
}