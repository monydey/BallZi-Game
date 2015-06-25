package com.ec.ballzi.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Highscore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Highscoreentry> highscoreList = new Vector<Highscoreentry>();
	
	
	public void addHighscore(String name,int score){
		
		if (isHighscore(score)||highscoreList.size()<10){
			if (highscoreList.size()>=10){
			int worst=2000;
				for (Highscoreentry entry:highscoreList){
					if (entry.getScore()<worst){
						worst=entry.getScore();
					}
				}
				Highscoreentry worstentry=null;
				for (Highscoreentry entry:highscoreList){
					if (entry.getScore()==worst){
						worstentry=entry;
					}
				}
				System.err.println("remove:" +worstentry.getName() );
				highscoreList.remove(worstentry);
			}
			Highscoreentry newentry = new Highscoreentry(name,score);
			highscoreList.add(newentry);
			
			
			boolean isTen=true;
			while (isTen){
				if (highscoreList.size()>10){
					highscoreList.removeElementAt(10);
				}else{
					isTen=false;
				}
			}
			for (Highscoreentry entry:highscoreList){
				System.out.print("|"+entry.getName()+":"+entry.getScore());
			}
			System.err.println("");
			highscoreList= sortHighscoreList(highscoreList);
			if (highscoreList.size()>10)
				highscoreList.remove(0);
			
			for (Highscoreentry entry:highscoreList){
				System.err.print("|"+entry.getName()+":"+entry.getScore());
			}
			System.err.println("");
		}
		
		
	}
	
	private Vector<Highscoreentry> sortHighscoreList(Vector<Highscoreentry> list){
		Collections.sort(list);
		return list;
		
//		int[] tosortvalues = new int[list.size()];
//		int a=0;
//		for(Highscoreentry entry:list){
//			tosortvalues[a]=entry.getScore();
//			a++;
//		}
//		//ArrayUtils.reverse(tosortvalues);
//		Arrays.sort(tosortvalues);
//		
//		for(int i = 0; i < tosortvalues.length; i++) {
//			
//		}
//		
//
//
//		Vector<Highscoreentry> scoreList = new Vector<Highscoreentry>();
//		Highscoreentry removeentry=null;
//		for (int g : tosortvalues) {
//			for (Highscoreentry entry : list) {
//				if (entry.getScore()==g){
//					scoreList.add(entry);
//					removeentry=entry;
//				}
//			}
//			list.remove(removeentry);
//			
//		}
//		list=scoreList;
//		return list;
	}
	
	
	public boolean isHighscore(int score){
		boolean ishscore = false;
		for (Highscoreentry entry:highscoreList){
			if (entry.getScore()<score||highscoreList.size()<10)
				ishscore=true;
		}
		if (highscoreList.size()<10){
			ishscore=true;
		}
		return ishscore;
	}

	public Vector<Highscoreentry> getHighscoreList() {
		return highscoreList;
	}
	

}