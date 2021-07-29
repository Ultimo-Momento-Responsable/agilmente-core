package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Date;
import java.util.Dictionary;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Class.HayUnoRepetidoSession;
import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

public class HayUnoMasBuilder implements IGameSessionBuilder{
	private FigureQuantity figureQuantity;
	private MaximumTime maximumTime;
	private HayUnoRepetidoSession gameSession;
	
	@Override
	public void buildParams(Dictionary<String, String> params) {
		while(params.keys().hasMoreElements()){
			String key = params.keys().nextElement();
			String value = params.elements().nextElement();
			
			this.gameSession.addParam(key, value);
		}
	}

	@Override
	public void buildProduct(Game game, Date creationDatetime) {
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
