package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Date;
import java.util.Dictionary;

import com.umr.agilmentecore.Class.Game;
import com.umr.agilmentecore.Interfaces.IGameSession;

public interface IGameSessionBuilder {
	public void buildParams(Dictionary<String, String> params);
	public void buildProduct(Game game, Date creationDatetime);
	public IGameSession getGameSession();
	public void setGameSession(IGameSession gameSession);
}
