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
@Table(name = "max_level")
public class MaxLevel implements IParam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "max_level")
	private int maxLevel;
	private static int MAX_VALUE = 20;
	private static int MIN_VALUE = 3;
	
	@Override
	public String getValue() {
		return Integer.toString(this.maxLevel);
	}
	
	@Override
	public void setValue(String value) throws Exception {
		int parsed  = Integer.parseInt(value);
		
		if (this.checkIfValid(parsed)) {
			this.maxLevel = parsed;
		} else {
			throw new Exception("MaxLevel parameter can't be greater than " + MAX_VALUE + " or lesser than "+ MIN_VALUE +".");
		}
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
	
	@Override
	public String getName() {
		return "maxLevel";
	}
	
	@Override
	public String getSpanishName() {
		return "Nivel Máximo";
	}
	
	@Override
	public String getUnit() {
		return "Niveles";
	}
	
	/**
	 * Verifica si el valor ingresado es válido.
	 * @param value Cantidad de figuras.
	 * @return Verdadero si es menor o igual a 20 y si es mayor o igual a 1.
	 */
	private boolean checkIfValid(int value) {
		return (value <= MAX_VALUE && value >= MIN_VALUE);
	}
}
