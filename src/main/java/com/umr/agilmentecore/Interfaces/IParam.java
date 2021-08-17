package com.umr.agilmentecore.Interfaces;

public interface IParam {
	/**
	 * Setter del valor del parámetro.
	 * @param value Valor del parámetro.
	 * @throws Exception Si se intenta usar un valor no válido.
	 */
	public void setValue(String value) throws Exception;
	
	/**
	 * Getter del valor del parámetro.
	 * @return Valor del parámetro.
	 */
	public String getValue();
	
	public String getName();
}
