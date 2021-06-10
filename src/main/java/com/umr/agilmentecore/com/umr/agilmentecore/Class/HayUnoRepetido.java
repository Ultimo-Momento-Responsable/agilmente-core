package com.umr.agilmentecore.com.umr.agilmentecore.Class;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HayUnoRepetido {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private int mistakes;
	private int successes;
	private float totalTime;
	private float[] timeBetweenSuccesses;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date dateTime;
	private boolean canceled;
	
}
