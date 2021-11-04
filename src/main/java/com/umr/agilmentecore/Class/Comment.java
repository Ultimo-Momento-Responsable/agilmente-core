package com.umr.agilmentecore.Class;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "datetime")
	private Date datetime;
	@Column(name = "comment")
	private String comment;
	@Column(name = "edited")
	private boolean edited;
	@ManyToOne()
	private Professional author;
}
