package com.ec.ballzi.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Highscores implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,Highscore> highscores = new HashMap<String,Highscore>();
	
	public void put(Highscore highscore,String levelname){
		highscores.put(levelname, highscore);
		System.out.println("HIGHSCORE FILE ADDED");
	}
	
	public Highscore get(String levelname){
		return highscores.get(levelname);
	}
	
}
