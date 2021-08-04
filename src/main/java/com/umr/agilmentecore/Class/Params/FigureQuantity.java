package com.umr.agilmentecore.Class.Params;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	public void setValue(String value) throws Exception {
		int parsed  = Integer.parseInt(value);
		
		if (this.checkIfValid(parsed)) {
			this.figureQuantity = parsed;
		} else {
			throw new Exception("FigureQuantity parameter can't be greater than 20.");
		}
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
	
	/**
	 * Verifica si el valor ingresado es válido.
	 * @param value Cantidad de figuras.
	 * @return Verdadero si es menor o igual a 20.
	 */
	private boolean checkIfValid(int value) {
		return value <= 20;
	}
}
