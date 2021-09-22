package com.umr.agilmentecore.Class.IntermediateClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.umr.agilmentecore.Class.Param;

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
}