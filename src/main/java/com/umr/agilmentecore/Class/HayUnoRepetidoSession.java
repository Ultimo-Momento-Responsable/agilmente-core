package com.umr.agilmentecore.Class;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hay_uno_repetido_session")
public class HayUnoRepetidoSession implements IGameSession {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@OneToOne
	private FigureQuantity figureQuantity;
	@OneToOne
	private MaximumTime maximumTime;
	@Column(name = "results")
	@OneToMany
	private List<Result> results;
	@ManyToOne
	private Game game;
	
	@Override
	public String toString() {
		return "";
	}
}
