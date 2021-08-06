package com.umr.agilmentecore.Class;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "class_name")
	private String className;
	@Column(name = "type")
	private Integer type;
	@Column(name = "max_value")
	private Integer maxValue;
	@Column(name = "min_value")
	private Integer minValue;
}