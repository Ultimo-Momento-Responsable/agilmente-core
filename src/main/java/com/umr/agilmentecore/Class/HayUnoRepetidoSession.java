package com.umr.agilmentecore.Class;

import java.util.List;

import javax.persistence.*;

import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;

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
	private List<HayUnoMasResult> results;
	@ManyToOne
	private Game game;
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public void addParam(String type, String value) {
		if (this.isMaximumTimeParam(type) && this.canAddMaximumTimeParam()) {
			this.maximumTime = new MaximumTime();
			this.maximumTime.setValue(value);
		} else if(this.isFigureQuantityParam(type) && this.canAddFigureQuantityParam()) {
			this.figureQuantity = new FigureQuantity();
			this.figureQuantity.setValue(value);
		}
	}
	
	private boolean isMaximumTimeParam(String type) {
		return type.equals("MaximumTime");
	}
	
	private boolean isFigureQuantityParam(String type) {
		return type.equals("FigureQuantity");
	}
	
	private boolean canAddFigureQuantityParam() {
		return this.figureQuantity == null && this.maximumTime != null;
	}
	
	private boolean canAddMaximumTimeParam() {
		return this.figureQuantity != null && this.maximumTime == null;
	}
}
