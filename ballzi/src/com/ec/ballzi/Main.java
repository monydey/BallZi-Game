package com.ec.ballzi;

import java.util.HashMap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ec.ballzi.data.Setup;

public class Main {
	private static HashMap<String, String> loadedsetup;
	private static Setup setup;
	private static String resolution;
	private static String fullscreen;
	private static int multisample;
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		setup = new Setup();
		try {
			loadedsetup = setup.load();
		} catch (Exception e1) {
		}
		try{		
			resolution=loadedsetup.get("resolution:");
		}catch(Exception e){}
		try{		
			fullscreen=loadedsetup.get("fullscreen:");
		}catch(Exception e){}
		try{
			if (fullscreen.equals("on")){
				cfg.fullscreen=true;
				cfg.resizable=true;
			}else{
				cfg.fullscreen=false;
				cfg.resizable=true;
			}
		}catch(Exception e){}
		try{		
			multisample=Integer.parseInt(loadedsetup.get("multisample:"));
			cfg.samples=multisample;
		}catch(Exception e){}
		
		if (resolution.equals("800x600")){
			cfg.width = 800;
			cfg.height = 600;
		}else if(resolution.equals("1024x768")){
			cfg.width = 1024;
			cfg.height = 768;
		}else if(resolution.equals("1280x720")){
			cfg.width = 1280;
			cfg.height = 720;
		}else if(resolution.equals("1280x800")){
			cfg.width = 1280;
			cfg.height = 800;
		}else if(resolution.equals("1920x1080")){
			cfg.width = 1920;
			cfg.height = 1080;
		}else if(resolution.equals("1920x1200")){
			cfg.width = 1920;
			cfg.height = 1200;
		}
		
		
		cfg.title = "BallZi";
		cfg.useGL20 = false;
		
		
		new LwjglApplication(new BallZi(), cfg);
	}
}
