package com.umr.agilmentecore.Interfaces;

public interface IGameSession {
	/**
	 * Añadir param a una sesión de juego.
	 * @param type Tipo de parámetro.
	 * @param value Valor del parámetro.
	 * @throws Exception Si el parámetro no es válido.
	 */
	public void addParam(String type, String value) throws Exception;
}
