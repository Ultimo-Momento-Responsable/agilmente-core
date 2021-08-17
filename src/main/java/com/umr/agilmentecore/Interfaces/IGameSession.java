package com.umr.agilmentecore.Interfaces;

import java.util.List;


import com.umr.agilmentecore.Class.IntermediateClasses.ResultsData;

import com.umr.agilmentecore.Class.Param;


public interface IGameSession {
	/**
	 * Añadir param a una sesión de juego.
	 * @param type Tipo de parámetro.
	 * @param value Valor del parámetro.
	 * @throws Exception Si el parámetro no es válido.
	 */
	public void addParam(String type, String value) throws Exception;
	
	// Método general para obtener resultados para los juegos
	public List<ResultsData> getResults();
	
	// Método general para obtener el nombre del juego
	public String getName();
	
	public List<Param> getParams();
	
	public List<IParam> getSettedParams();

}
