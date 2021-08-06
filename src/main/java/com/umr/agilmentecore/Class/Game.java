package com.umr.agilmentecore.Class;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany
	private List<CognitiveDomain> cognitiveDomain;
	@ManyToMany
	private List<Param> param;
	@ManyToMany
	private List<Param> stopConditions;
}
