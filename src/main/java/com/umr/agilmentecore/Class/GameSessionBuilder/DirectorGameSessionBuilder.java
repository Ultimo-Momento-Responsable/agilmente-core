package com.umr.agilmentecore.Class.GameSessionBuilder;

import java.util.Date;
import java.util.Dictionary;

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
	
	public void build(Game game, Date creationDatetime, Dictionary<String, String> params) {
		this.builder.buildProduct(game, creationDatetime);
		this.builder.buildParams(params);
	}
}
