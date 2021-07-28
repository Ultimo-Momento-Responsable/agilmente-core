package com.umr.agilmentecore.Class.Params;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Result;
import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "figure_quantity")
public class FigureQuantity implements IParam {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "figure_quantity")
	private int figureQuantity;
	
	@Override
	public String getValue() {
		return Integer.toString(this.figureQuantity);
	}
	
	@Override
	public void setValue(String value) {
		this.figureQuantity = Integer.parseInt(value);
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}
