package com.ec.ballzi.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class LoadImage {
	final JFileChooser fc = new JFileChooser();
	private File file;
	private String imagepath;
	public LoadImage(){
		int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
          
        }
        
        FileOutputStream fop = null;
		Path path = Paths.get(file.getAbsolutePath());
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		imagepath = "bin/backgrounds/" + file.getName();
		try {
 
			fop = new FileOutputStream(imagepath);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(data);
			fop.flush();
			fop.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        
	}
	public String getImagepath() {
		return imagepath;
	}
}
