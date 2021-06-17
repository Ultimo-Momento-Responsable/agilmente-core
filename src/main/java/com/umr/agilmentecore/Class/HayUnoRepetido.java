package com.umr.agilmentecore.Class;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hay_uno_repetido")
public class HayUnoRepetido {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "mistakes")
	private int mistakes;
	@Column(name = "successes")
	private int successes;
	@Column(name = "total_time")
	private float totalTime;
	@Column(name = "time_between_successes")
	private float[] timeBetweenSuccesses;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "date_time")
	private Date dateTime;
	@Column(name = "canceled")
	private boolean canceled;
	
}
