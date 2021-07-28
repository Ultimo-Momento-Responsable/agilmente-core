package com.umr.agilmentecore.Class;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "result")
public class Result {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "complete_datetime")
	private Date completeDatetime;
	@Column(name = "canceled")
	private boolean canceled;
}
