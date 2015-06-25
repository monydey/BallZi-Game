package com.ec.ballzi.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

public class Setup {
	
	
    public void save( HashMap<String, String> props )
    {
    	Properties prop = new Properties();
    		
    	try {
    		//set the properties value
    		Iterator<Entry<String, String>> it = props.entrySet().iterator();

			while (it.hasNext()) {
			  Entry<String, String> entry = it.next();
			  prop.setProperty(entry.getKey(), entry.getValue()+"");
			  
			  // Remove entry if key is null or equals 0.
			  if (entry.getKey() == null || entry.getKey() == "") {
			    it.remove();
			  }
			}
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream("bin/config/setup.pro"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    }
    
    public HashMap<String, String> load() throws Exception
    {
    	Properties prop = new Properties();
    	HashMap<String,String> props = new HashMap<String,String>();
    	
    	
               //load a properties file
    		prop.load(new FileInputStream("bin/config/setup.pro"));
    		Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();
    		while (it.hasNext()) {
			  Entry<Object, Object> entry = it.next();
			  props.put((String)entry.getKey(),(String)entry.getValue());
			  // Remove entry if key is null or equals 0.
			  if (entry.getKey() == null || entry.getKey() == "") {
			    it.remove();
			  }
			}
    		
 
    	
    	
    	return props;
 
    }

}
