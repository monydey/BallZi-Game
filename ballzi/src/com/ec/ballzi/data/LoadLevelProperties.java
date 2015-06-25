package com.ec.ballzi.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

public class LoadLevelProperties {

	HashMap<String,Integer> props = new HashMap<String,Integer>();
	int starscount = 0;
	
    public LoadLevelProperties( )
    {
    	Properties prop = new Properties();
 
    	try {
               //load a properties file
    		prop.load(new FileInputStream("bin/config/config.properties"));
    		Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();
    		while (it.hasNext()) {
			  Entry<Object, Object> entry = it.next();
			  props.put((String)entry.getKey(),Integer.parseInt((String)entry.getValue()));
			  starscount +=Integer.parseInt((String)entry.getValue());
			  // Remove entry if key is null or equals 0.
			  if (entry.getKey() == null || entry.getKey() == "") {
			    it.remove();
			  }
			}
    		
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
 
    }
    public int getStarscount() {
		return starscount;
	}
	public HashMap<String,Integer> getConfig(){
    	return props;
    	
    }
}
