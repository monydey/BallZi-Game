package com.ec.ballzi.data;

import java.io.Serializable;

public class Highscoreentry implements Serializable, Comparable<Highscoreentry> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public Highscoreentry(String name,int score){
		this.name=name;
		this.score=score;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Highscoreentry arg0) {
		if(this.score > arg0.score) return -1;
		else if(this.score < arg0.score) return 1;
		return this.name.compareTo(arg0.name);
	}
	
}
