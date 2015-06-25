package com.ec.ballzi.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class SaveLevelProperties {
	   
	    public void save( HashMap<String, Integer> props )
	    {
	    	Properties prop = new Properties();
	    		
	    	try {
	    		//set the properties value
	    		Iterator<Entry<String, Integer>> it = props.entrySet().iterator();

    			while (it.hasNext()) {
    			  Entry<String, Integer> entry = it.next();
    			  prop.setProperty(entry.getKey(), entry.getValue()+"");
    			  
    			  // Remove entry if key is null or equals 0.
    			  if (entry.getKey() == null || entry.getKey() == "") {
    			    it.remove();
    			  }
    			}
	 
	    		//save properties to project root folder
	    		prop.store(new FileOutputStream("bin/config/config.properties"), null);
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
	    }
}

