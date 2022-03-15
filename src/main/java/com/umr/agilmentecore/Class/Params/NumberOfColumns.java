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
@Table(name = "number_of_columns")
public class NumberOfColumns implements IParam{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "number_of_columns")
	private int numberOfColumns;
	private static int MAX_VALUE = 8;
	private static int MIN_VALUE = 3;
	
	@Override
	public void setValue(String value) throws Exception {
		int parsed  = Integer.parseInt(value);
		
		if (this.checkIfValid(parsed)) {
			this.numberOfColumns = parsed;
		} else {
			throw new Exception("NumberOfColumns parameter can't be greater than " + MAX_VALUE + " or lesser than "+ MIN_VALUE +".");
		}
	}

	@Override
	public String getValue() {
		return Integer.toString(this.numberOfColumns);
	}

	@Override
	public String getName() {
		return "numberOfColumns";
	}

	@Override
	public String getSpanishName() {
		return "Número de columnas";
	}

	@Override
	public String getUnit() {
		return "Columnas";
	}

	@Override
	public String getContextualHelp() {
		return "Cantidad de columnas presentes en la grilla del juego";
	}
	
	/**
	 * Verifica si el valor ingresado es válido.
	 * @param value Cantidad de columnas.
	 * @return Verdadero si cumple con los valores máximos y mínimos establecidos.
	 */
	private boolean checkIfValid(int value) {
		return (value <= MAX_VALUE && value >= MIN_VALUE);
	}

}
