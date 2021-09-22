package com.umr.agilmentecore.Class;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.umr.agilmentecore.Class.IntermediateClasses.GameParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@ManyToMany
	private List<CognitiveDomain> cognitiveDomain;
	@OneToMany
	private List<GameParam> gameParams;

	public List<Param> getParams() {
		List<Param> paramList = null;
		for (GameParam gp : gameParams) {
			paramList.add(gp.getParam());
		}
		return paramList;
	}
	
	
}