package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Map;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Interfaces.IGameSession;

public interface IGameSessionBuilder {
	/**
	 * Construye los parámetros de la sesión de juego.
	 * @param params Diccionario con los params.
	 * @throws Exception Si los parámetros son inválidos.
	 */
	public void buildParams(Map<String, String> params) throws Exception;
	
	/**
	 * Construye la sesión de juego.
	 * @param game Tipo de juego.
	 * @param creationDatetime Fecha y hora de creación.
	 */
	public void buildProduct(Game game);
	
	/**
	 * Getter de la sesión de juego.
	 * @return Sesión de juego.
	 */
	public IGameSession getGameSession();
	
	/**
	 * Setter de la sesión de juego.
	 * @param gameSession Sesión de juego.
	 */
	public void setGameSession(IGameSession gameSession);
}
