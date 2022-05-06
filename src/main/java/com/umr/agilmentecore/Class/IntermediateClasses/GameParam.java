package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.umr.agilmentecore.Class.Param;
import com.umr.agilmentecore.Class.ParamType2Content;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game_param")
public class GameParam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	Param param;
	@Column(name = "max_value")
	private Integer maxValue;
	@Column(name = "min_value")
	private Integer minValue;
	@OneToMany
	private List<ParamType2Content> paramType2Content;
	
	public List<String> getParamType2Content() {
		List<String> paramList = new ArrayList<String>();
		for (ParamType2Content gp : paramType2Content) {
			paramList.add(gp.getName());
		}
		return paramList;
	}
}