package com.ec.ballzi.elements;

public class LevelTimer {

	private String timestring;
	private float seconds;
	private int minutes;
	private long start;
	private long end;

	public LevelTimer() {
		start = System.currentTimeMillis();
	}

	public String getTime() {
		end = System.currentTimeMillis();
		seconds = (end - start) / 1000;
		minutes = (int) seconds / 60;
		seconds = (int) seconds % 60;
		if ((int)seconds<10){
			timestring = minutes + ":0" + (int)seconds;
		}else{
			timestring = minutes + ":" + (int)seconds;
		}
		

		return timestring;
	}
	public int getSeconds() {
		end = System.currentTimeMillis();
		int secs = (int)((end - start) / 1000);
		return secs;
	}

	public void resetTimer() {
		start = System.currentTimeMillis();
	}
}
