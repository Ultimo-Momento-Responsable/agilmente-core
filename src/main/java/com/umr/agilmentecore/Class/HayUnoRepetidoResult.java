package com.umr.agilmentecore.Class;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umr.agilmentecore.Interfaces.IResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
@Table(name = "hay_uno_repetido_result")
public class HayUnoRepetidoResult implements IResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "complete_datetime")
	private Date completeDatetime;
	@Column(name = "canceled")
	private boolean canceled;
	@Column(name = "mistakes")
	private int mistakes;
	@Column(name = "successes")
	private int successes;
	@Column(name = "time_between_successes")
	private float[] timeBetweenSuccesses;
	@Column(name = "total_time")
	private float totalTime;
	@Column (name = "score")
	private int score;
	@Column (name = "mgp")
	private int mgp;
}
