package com.umr.agilmentecore.Class;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private HayUnoRepetidoSession hayUnoRepetidoSession;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private EncuentraAlNuevoSession encuentraAlNuevoSession;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MemorillaSession memorillaSession;
	@Column(name = "max_number_of_sessions")
	private int maxNumberOfSessions;
	@Column(name = "number_of_sessions")
	private int numberOfSessions;
	@Column (name = "difficulty")
	private String difficulty;
	
	public PlanningDetail(IGameSession gameSession, Integer maxNumberOfSessions, String difficulty) {
		this.setMaxNumberOfSessions(maxNumberOfSessions);
		this.setDifficulty(difficulty);
		this.setGameSession(gameSession);
		this.numberOfSessions = maxNumberOfSessions;
	}
	
	public void setGameSession(IGameSession gameSession) {
		if(gameSession instanceof HayUnoRepetidoSession) {
			this.setHayUnoRepetidoSession((HayUnoRepetidoSession) gameSession);
		}
		if(gameSession instanceof EncuentraAlNuevoSession) {
			this.setEncuentraAlNuevoSession((EncuentraAlNuevoSession) gameSession);
		}
		if(gameSession instanceof MemorillaSession) {
			this.setMemorillaSession((MemorillaSession) gameSession);
		}
	}
	
	public IGameSession getGameSession() {
        if (this.hayUnoRepetidoSession!=null) {
            return this.hayUnoRepetidoSession;
        }
        if (this.encuentraAlNuevoSession!=null) {
            return this.encuentraAlNuevoSession;
        }
        if (this.memorillaSession!=null) {
            return this.memorillaSession;
        }
        return null;
    }
  
}
