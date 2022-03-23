package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.MemorillaResult;
import com.umr.agilmentecore.Class.MemorillaSession;
import com.umr.agilmentecore.Interfaces.IGameSession;

public class MemorillaSessionBuilder implements IGameSessionBuilder {
	private MemorillaSession gameSession;
	
	/**
	 * Construye los diferentes params para el MemorillaSession.
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
	 * Construye la instancia de MemorillaSession.
	 * @param game Tipo de juego.
	 * @param creationDatetime Fecha y hora de creación.
	 */
	@Override
	public void buildProduct(Game game) {
		this.gameSession = new MemorillaSession();
		this.gameSession.setResults(new ArrayList<MemorillaResult>());
		this.gameSession.setGame(game);
		this.gameSession.setResults(new ArrayList<MemorillaResult>());
	}

	@Override
	public IGameSession getGameSession() {
		return this.gameSession;
	}

	@Override
	public void setGameSession(IGameSession gameSession) {
		this.gameSession = (MemorillaSession) gameSession;		
	}
}
