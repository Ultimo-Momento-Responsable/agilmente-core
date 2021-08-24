package com.umr.agilmentecore.Class;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umr.agilmentecore.Persistence.PlanningStateRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "planning")
public class Planning {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Professional professional;
	@ManyToOne
	private Patient patient;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "creation_datetime")
	private Date creationDatetime;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "start_date")
	private Date startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "due_date")
	private Date dueDate;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planning_id")
	private List<PlanningDetail> detail;
	@ManyToOne
	private PlanningState state;
	
}
