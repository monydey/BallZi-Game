package com.ec.ballzi.data;


import java.io.Serializable;
import java.util.Vector;

public class Levels implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Level> levels;
	
	public Levels (){
		levels = new Vector<Level>();
	}
	
	public void addLevel(Level level){
		levels.add(level);
	}

	public Level getByName(String name){
		Level l = null;
		for(Level lev:levels){
			if (lev.getName().equals(name)){
				l=lev;
			}
		}
		return l;
	}
	
	public Level getByIndex(int index){
		return levels.get(index);
	}

	public Vector<Level> getLevels() {
		return levels;
	}

	public void setLevels(Vector<Level> levels) {
		this.levels = levels;
	}

	
}
