package com.ec.ballzi.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Save {
	private Properties prop;
	
	public void saveProp(Properties prop,String path){
		this.prop=prop;
		 
    	try {
    		this.prop.store(new FileOutputStream(path), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}

}
