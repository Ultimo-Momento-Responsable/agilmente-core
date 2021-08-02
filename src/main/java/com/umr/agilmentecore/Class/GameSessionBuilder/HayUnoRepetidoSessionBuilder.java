package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

public class HayUnoRepetidoSessionBuilder implements IGameSessionBuilder{
	private FigureQuantity figureQuantity;
	private MaximumTime maximumTime;
	private HayUnoRepetidoSession gameSession;
	
	/**
	 * Construye los diferentes params para el HayUnoRepetidoSession.
	 * @param params Diccionario con los parámetros.
	 */
	@Override
	public void buildParams(Map<String, String> params) {		
		for (Map.Entry<String, String> param : params.entrySet()) {
		    String key = param.getKey();
		    String value = param.getValue();
		    
		    try {
				this.gameSession.addParam(key, value);
			} catch (Exception e) {
				System.out.println(e);
			}
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
