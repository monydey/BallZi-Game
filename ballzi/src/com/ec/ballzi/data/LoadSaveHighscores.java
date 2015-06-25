package com.ec.ballzi.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadSaveHighscores {
	
	public void save(Highscores highscores) {

		try {
			FileOutputStream fileOut = new FileOutputStream(
					"bin/config/highscores.sco");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(highscores);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public Highscores load(){
		Highscores highscores= new Highscores();
			try
		      {
		         FileInputStream fileIn = new FileInputStream("bin/config/highscores.sco");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         highscores = (Highscores) in.readObject();
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		    	  SaveLevels save = new SaveLevels();
		    	  save.save(new Levels());
		      }catch(ClassNotFoundException c)
		      {
		      }
		
		return highscores;
	}
}
