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
@Table(name = "maximum_time")
public class MaximumTime implements IParam {
	
	private static int MIN_VALUE = 1;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "maximum_time")
	private float maximumTime;

	@Override
	public String getValue() {
		return Float.toString(this.maximumTime);
	}

	@Override
	public String getName() {
		return "maximumTime";
	}
	
	@Override
	public String getSpanishName() {
		return "Tiempo Máximo";
	}
	
	@Override
	public void setValue(String value) throws Exception {
		int parsed  = Integer.parseInt(value);
		
		if (this.checkIfValid(parsed)) {
			this.maximumTime = parsed;
		} else {
			throw new Exception("MaximumTime parameter can't be less than " + MIN_VALUE + ".");
		}
		this.maximumTime = Float.parseFloat(value);
	}

	@Override
	public String toString() {
		return this.getValue();
	}
	
	/**
	 * Verifica si el valor ingresado es válido.
	 * @param value Tiempo.
	 * @return Verdadero si es mayor o igual a 1.
	 */
	private boolean checkIfValid(int value) {
		return (value >= MIN_VALUE);
	}
}
