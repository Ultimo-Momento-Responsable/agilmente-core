package com.umr.agilmentecore.Class;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.umr.agilmentecore.Interfaces.IGameSession;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "planning_detail")
public class PlanningDetail {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private HayUnoRepetidoSession hayUnoRepetidoSession;
	@Column(name = "max_number_of_sessions")
	private int maxNumberOfSessions;
	
	public PlanningDetail(IGameSession gameSession, Integer maxNumberOfSessions) {
		this.setMaxNumberOfSessions(maxNumberOfSessions);
		this.setGameSession(gameSession);
	}
	
	public void setGameSession(IGameSession gameSession) {
		if(gameSession instanceof HayUnoRepetidoSession) {
			this.setHayUnoRepetidoSession((HayUnoRepetidoSession) gameSession);
		}
	}
}
