package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultListHistory {
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date completeDatetime;
	public Date getCompleteDatetime() {
		return completeDatetime;
	}
	public void setCompleteDatetime(Date completeDatetime) {
		this.completeDatetime = completeDatetime;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private String game;
	private int score;
}
