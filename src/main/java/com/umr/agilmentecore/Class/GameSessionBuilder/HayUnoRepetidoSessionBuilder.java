package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Map;
import java.util.Map.Entry;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;

public class HayUnoRepetidoSessionBuilder implements IGameSessionBuilder{
	private FigureQuantity figureQuantity;
	private MaximumTime maximumTime;
	private HayUnoRepetidoSession gameSession;
	
	/**
	 * Construye los diferentes params para el HayUnoRepetidoSession.
	 * @param params Diccionario con los parámetros.
	 * @throws Exception Si uno de los parámetros es inválido. 
	 */
	@Override
	public void buildParams(Map<String, String> params) throws Exception {
		for (Entry<String, String> param : params.entrySet()) {
		    String key = param.getKey();
		    String value = param.getValue();
			this.gameSession.addParam(key, value);
		}
	}

	/**
	 * Construye la instancia de HayUnoRepetidoSession.
	 * @param game Tipo de juego.
	 * @param creationDatetime Fecha y hora de creación.
	 */
	@Override
	public void buildProduct(Game game) {
		this.gameSession = new HayUnoRepetidoSession();
		this.gameSession.setGame(game);
	}

	@Override
	public IGameSession getGameSession() {
		return this.gameSession;
	}

	@Override
	public void setGameSession(IGameSession gameSession) {
		this.gameSession = (HayUnoRepetidoSession) gameSession;		
	}
}
