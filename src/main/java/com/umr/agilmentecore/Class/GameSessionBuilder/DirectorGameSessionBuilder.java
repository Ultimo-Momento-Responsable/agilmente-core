package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Map;

import com.umr.agilmentecore.Class.Game;

public class DirectorGameSessionBuilder {
	private IGameSessionBuilder builder;
	
	public DirectorGameSessionBuilder(IGameSessionBuilder builder) {
		this.builder = builder;
	}
	
	public IGameSessionBuilder getBuilder() {
		return this.builder;
	}
	
	public void setBuilder(IGameSessionBuilder builder) {
		this.builder = builder;
	}
	
	/**
	 * Método principal que inicia la construcción de 
	 * una sesión de juegos.
	 * @param game Tipo de juego.
	 * @param creationDatetime Fecha de creación de la sesión.
	 * @param params Diccionario con los parámetros del juego.
	 * @throws Exception Si los parámetros son inválidos.
	 */
	public void build(Game game, Map<String, String> params) throws Exception {
		this.builder.buildProduct(game);
		this.builder.buildParams(params);
	}
}
