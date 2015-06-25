package com.ec.ballzi.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveLevels {

	public void save(Levels levels){
		for(Level lvl:levels.getLevels()){
			
			try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("bin/levels/"+lvl.getName()+".lvl");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(lvl);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		}
		
		
	}
	public void clear(){
		try
	      {
			 Levels levels = new Levels();
	         FileOutputStream fileOut =
	         new FileOutputStream("levels.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(levels);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
}
