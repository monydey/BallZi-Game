package com.ec.ballzi.screens;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ec.ballzi.BallZi;
import com.ec.ballzi.data.Setup;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myLabel;
import com.ec.ballzi.uiitems.myTextButton;

public class SetupScreen implements Screen{
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private int textsize = (int)screenwidth/50;
	private int buttontextsize = (int)screenwidth/70;
	private Skin skin;
	private Slider resolutionslider;
	private Label resolutionLabel;
	private Stage stage;
	private Table table;
	private SpriteBatch batch;
	private Texture texture;
	private Setup setup;
	private String resolution = "1280x720"; //DEFAULT
	private HashMap<String, String> loadedsetup;
	private Label resvalueLabel;
	private TextureAtlas buttonAtlas;
	private TextButton saveexitbutton;
	private boolean restartfullneeded = false;
	private boolean restartresneeded = false;
	private String unchangedres;
	private String unchangedfullsc;
	private Label fullscreenLabel;
	private TextButton fullscreenOnbutton;
	private TextButton fullscreenOffbutton;
	private String isfullscreen="off";
	private Slider volumeslider;
	private Label volumeLabel;
	private Label soundonoffLabel;
	private TextButton soundOnbutton;
	private TextButton soundOffbutton;
	private Label multisampleLabel;
	private Slider multisampleslider;
	private Label multisamplevalueLabel;
	private Label volumevalueLabel;
	private String volume="100";
	private String multisample="4";
	private String isSound;
	private boolean restartmultineeded;
	private int unchaingedmulti;
	private Slider musicvolumeslider;
	private Label musicvolumevalueLabel;
	private Label musicvolumeLabel;
	private String musicvolume="100";
	private TextButton musicOnbutton;
	private TextButton musicOffbutton;
	private String isMusic;
	private Label musiconoffLabel;
	
	public SetupScreen(final BallZi ballzi, final MySounds sounds){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin();
		batch = new SpriteBatch();
		setup = new Setup();
		try{
			sounds.stopMenuMusicLoop();
			}catch(Exception e){}
		try{
		sounds.stopGameMusicLoop();
		}catch(Exception e){}
		sounds.playMenuMusicLoop();
		
		texture = new Texture(Gdx.files.internal("textures/level-select-screen.png"));
		skin.add("knob", new Texture(Gdx.files.internal("slider/sliderknobside.png")));
		skin.add("bgs", new Texture(Gdx.files.internal("slider/sliderbgside.png")));
		Slider.SliderStyle sliderstyle = new Slider.SliderStyle();
		sliderstyle.background = skin.getDrawable("bgs");
		sliderstyle.knob = skin.getDrawable("knob");
		buttonAtlas = new TextureAtlas("buttons/button2checked.pack");
		

		fullscreenOnbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		fullscreenOffbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		fullscreenOnbutton.setText("on");
		fullscreenOffbutton.setText("off");
		soundOnbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		soundOffbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		soundOnbutton.setText("on");
		soundOffbutton.setText("off");
		musicOnbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		musicOffbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		musicOnbutton.setText("on");
		musicOffbutton.setText("off");
		saveexitbutton = new myTextButton(buttonAtlas, buttontextsize,Color.WHITE).getButton();
		saveexitbutton.setText("save and exit");
		saveexitbutton.setPosition((screenwidth/2)-(saveexitbutton.getWidth()/2), screenheight/6);
		
		try{		
			loadedsetup = setup.load();
			resolution=loadedsetup.get("resolution:");
			volume=loadedsetup.get("volume:");
			musicvolume=loadedsetup.get("musicvolume:");
			multisample=loadedsetup.get("multisample:");
			unchaingedmulti=Integer.parseInt(multisample);
			String fullscreen = loadedsetup.get("fullscreen:");
			String sound = loadedsetup.get("sound:");
			String music = loadedsetup.get("music:");
			if (fullscreen.equals("on")){
				fullscreenOnbutton.setChecked(true);
				fullscreenOffbutton.setChecked(false);
				isfullscreen="on";
				
			}else{
				fullscreenOnbutton.setChecked(false);
				fullscreenOffbutton.setChecked(true);
				isfullscreen="off";
			}
			if (sound.equals("on")){
				soundOnbutton.setChecked(true);
				soundOffbutton.setChecked(false);
				isSound="on";
				
			}else{
				soundOnbutton.setChecked(false);
				soundOffbutton.setChecked(true);
				isSound="off";
			}
			if (music.equals("on")){
				musicOnbutton.setChecked(true);
				musicOffbutton.setChecked(false);
				isMusic="on";
				
			}else{
				musicOnbutton.setChecked(false);
				musicOffbutton.setChecked(true);
				isMusic="off";
			}
				
		}catch(Exception e){
			loadedsetup = new HashMap<String, String>();
		}
		
		
		resvalueLabel = new myLabel(resolution,textsize,Color.BLACK).getMyLabel();
		soundonoffLabel = new myLabel("          Sound:",textsize,Color.BLACK).getMyLabel();
		musiconoffLabel = new myLabel("          Music:",textsize,Color.BLACK).getMyLabel();
		volumevalueLabel = new myLabel(volume+"%",textsize,Color.BLACK).getMyLabel();
		volumeLabel = new myLabel("   SoundVolume:",textsize,Color.BLACK).getMyLabel();
		musicvolumevalueLabel = new myLabel(musicvolume+"%",textsize,Color.BLACK).getMyLabel();
		musicvolumeLabel = new myLabel("   MusicVolume:",textsize,Color.BLACK).getMyLabel();
		multisampleLabel = new myLabel("Multisample:",textsize,Color.BLACK).getMyLabel();
		multisamplevalueLabel = new myLabel(multisample,textsize,Color.BLACK).getMyLabel();
		resolutionLabel = new myLabel("   Resolution:",textsize,Color.BLACK).getMyLabel();
		fullscreenLabel = new myLabel("   Fullscreen:",textsize,Color.BLACK).getMyLabel();
		
		
		
		resolutionslider = new Slider(2, 4, 1, false, sliderstyle);
		resolutionslider.setVisible(true);
		volumeslider = new Slider(1, 100, 1, false, sliderstyle);
		volumeslider.setVisible(true);
		volumeslider.setValue(Integer.parseInt(volume));
		
		musicvolumeslider = new Slider(1, 100, 1, false, sliderstyle);
		musicvolumeslider.setVisible(true);
		musicvolumeslider.setValue(Integer.parseInt(musicvolume));
		
		multisampleslider = new Slider(0, 4, 1, false, sliderstyle);
		multisampleslider.setVisible(true);
		multisampleslider.setValue(Integer.parseInt(multisample)/4);
		
		
		ButtonGroup b = new ButtonGroup();
		b.add(fullscreenOnbutton);
		b.add(fullscreenOffbutton);
		b.setMinCheckCount(1);
		b.setMaxCheckCount(1);
		
		ButtonGroup b2 = new ButtonGroup();
		b2.add(soundOnbutton);
		b2.add(soundOffbutton);
		b2.setMinCheckCount(1);
		b2.setMaxCheckCount(1);
		
		ButtonGroup b3 = new ButtonGroup();
		b3.add(musicOnbutton);
		b3.add(musicOffbutton);
		b3.setMinCheckCount(1);
		b3.setMaxCheckCount(1);
		
		
		unchangedfullsc=isfullscreen;
		unchangedres=resolution;
		
		if (resolution.equals("800x600")){
			resolutionslider.setValue(1);
		}else if(resolution.equals("1024x768")){
			resolutionslider.setValue(2);
		}else if(resolution.equals("1280x720")){
			resolutionslider.setValue(3);
		}else if(resolution.equals("1280x800")){
			resolutionslider.setValue(4);
		}else if(resolution.equals("1920x1080")){
			resolutionslider.setValue(5);
		}else if(resolution.equals("1920x1200")){
			resolutionslider.setValue(6);
		}
		
		table = new Table(new Skin());
		table.setFillParent(true);
		table.center();
		table.add(fullscreenLabel).pad(10);
		table.add(fullscreenOnbutton).pad(10);
		table.add(fullscreenOffbutton).pad(10).row();
		table.add(resolutionLabel).pad(10);
		table.add(resolutionslider).pad(10);
		table.add(resvalueLabel).pad(10).row();
		table.add(soundonoffLabel).pad(10);
		table.add(soundOnbutton).pad(10);
		table.add(soundOffbutton).pad(10).row();
		table.add(volumeLabel).pad(10);
		table.add(volumeslider).pad(10);
		table.add(volumevalueLabel).pad(10).row();
		table.add(musiconoffLabel).pad(10);
		table.add(musicOnbutton).pad(10);
		table.add(musicOffbutton).pad(10).row();
		table.add(musicvolumeLabel).pad(10);
		table.add(musicvolumeslider).pad(10);
		table.add(musicvolumevalueLabel).pad(10).row();
		table.add(multisampleLabel).pad(10);
		table.add(multisampleslider).pad(10);
		table.add(multisamplevalueLabel).pad(10).row();
		
		stage.addActor(saveexitbutton);
		stage.addActor(table);
		
		saveexitbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				loadedsetup.put("resolution:", resolution);
				loadedsetup.put("fullscreen:", isfullscreen);
				loadedsetup.put("volume:", (int)volumeslider.getValue()+"");
				loadedsetup.put("musicvolume:", (int)musicvolumeslider.getValue()+"");
				loadedsetup.put("multisample:", (int)multisampleslider.getValue()*4+"");
				loadedsetup.put("sound:",isSound);
				loadedsetup.put("music:",isMusic);
				sounds.soundOn(isSound);
				sounds.musicOn(isMusic);
				sounds.changeSoundVolume((int)volumeslider.getValue());
				sounds.changeMusicVolume((int)musicvolumeslider.getValue());
				saveSetup();
				if (restartresneeded||restartfullneeded||restartmultineeded){
					try {
					Runtime runTime = Runtime.getRuntime();
					@SuppressWarnings("unused")
					Process process = runTime.exec("java -jar BallZiE.jar");
					} catch (IOException e) {
						e.printStackTrace();
					}
					Gdx.app.exit();
				}else{
					ballzi.setScreen(new StartScreen(ballzi));
				}
				
				
			}
		});
		fullscreenOnbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isfullscreen="on";
				if (isfullscreen.equals(unchangedfullsc)){
					restartfullneeded=false;
				}else{
					restartfullneeded=true;
				}
				
			}
		});
		fullscreenOffbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isfullscreen="off";
				if (isfullscreen.equals(unchangedfullsc)){
					restartfullneeded=false;
				}else{
					restartfullneeded=true;
				}
				
			}
		});
		soundOnbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isSound="on";
				sounds.soundOn(isSound);
				
			}
		});
		soundOffbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isSound="off";
				sounds.soundOn(isSound);
				
			}
		});
		musicOnbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isMusic="on";
				sounds.musicOn(isMusic);
			}
		});
		musicOffbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				isMusic="off";
				sounds.musicOn(isMusic);
				
			}
		});
		resolutionslider.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (resolution.equals(unchangedres)){
					restartresneeded=false;
				}else{
					restartresneeded=true;
				}
				switch ((int)resolutionslider.getValue()){
				case 1:
					resolution="800x600";
					break;
				case 2:
					resolution="1024x768";
					break;
				case 3:
					resolution="1280x720";
					break;
				case 4:
					resolution="1280x800";
					break;
				case 5:
					resolution="1920x1080";
					break;
				case 6:
					resolution="1920x1200";
					break;
				}
				
			}
		});
		volumeslider.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				volumevalueLabel.setText((int)volumeslider.getValue()+"%");
				sounds.changeSoundVolume((int)volumeslider.getValue());
			}
		});
		musicvolumeslider.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				musicvolumevalueLabel.setText((int)musicvolumeslider.getValue()+"%");
				sounds.changeMusicVolume((int)musicvolumeslider.getValue());
			}
		});
		multisampleslider.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				multisamplevalueLabel.setText(""+(int)multisampleslider.getValue()*4);
				if ((int)multisampleslider.getValue()*4==unchaingedmulti){
					restartmultineeded=false;
				}else{
					restartmultineeded=true;
				}
			}
		});
	}
	
	public void saveSetup(){
		setup.save(loadedsetup);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, 0, 0, screenwidth,
				screenheight);
		batch.end();
		resvalueLabel.setText(resolution);
		
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
