package com.umr.agilmentecore.Class.Params;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "number_of_rows")
public class NumberOfRows implements IParam{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "number_of_rows")
	private int numberOfRows;
	private static int MAX_VALUE = 6;
	private static int MIN_VALUE = 3;
	
	@Override
	public void setValue(String value) throws Exception {
		int parsed  = Integer.parseInt(value);
		
		if (this.checkIfValid(parsed)) {
			this.numberOfRows = parsed;
		} else {
			throw new Exception("NumberOfRows parameter can't be greater than " + MAX_VALUE + " or lesser than "+ MIN_VALUE +".");
		}
	}

	@Override
	public String getValue() {
		return Integer.toString(this.numberOfRows);
	}

	@Override
	public String getName() {
		return "numberOfRows";
	}

	@Override
	public String getSpanishName() {
		return "Número de filas";
	}

	@Override
	public String getUnit() {
		return "Filas";
	}

	@Override
	public String getContextualHelp() {
		return "Cantidad de filas presentes en la grilla del juego";
	}
	
	/**
	 * Verifica si el valor ingresado es válido.
	 * @param value Cantidad de filas.
	 * @return Verdadero si cumple con los valores máximos y mínimos establecidos.
	 */
	private boolean checkIfValid(int value) {
		return (value <= MAX_VALUE && value >= MIN_VALUE);
	}

}
