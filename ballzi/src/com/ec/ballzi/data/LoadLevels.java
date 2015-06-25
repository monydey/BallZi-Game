package com.ec.ballzi.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadLevels {

	public Levels load(String levelpath){
		Levels levls = new Levels();
		Level e = null;
		File f = new File(levelpath);
		File[] fileArray = f.listFiles();
		for (File filename:fileArray){
			try
		      {
		         FileInputStream fileIn = new FileInputStream(levelpath+filename.getName());
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         e = (Level) in.readObject();
		         in.close();
		         fileIn.close();
		         levls.addLevel(e);
		      }catch(IOException i)
		      {
		    	  SaveLevels save = new SaveLevels();
		    	  save.save(new Levels());
		      }catch(ClassNotFoundException c)
		      {
		      }
		}
		
		return levls;
	}
	
}
