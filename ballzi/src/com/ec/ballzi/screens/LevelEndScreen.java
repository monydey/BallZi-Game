package com.ec.ballzi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.ec.ballzi.BallZi;
import com.ec.ballzi.data.Highscore;
import com.ec.ballzi.data.Highscores;
import com.ec.ballzi.data.Level;
import com.ec.ballzi.data.LoadSaveHighscores;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myLabel;
import com.ec.ballzi.uiitems.myTextButton;

public class LevelEndScreen implements EventListener {
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private TextureAtlas buttonAtlas;
	private static float itemsize_small = screenwidth/40;
	private int buttonfontsize = (int)(itemsize_small/1.3f);
	private int labelfontsize = 60;
	private TextButton restart;
	private Label labeltop;
	private Label labelbottom;
	private Texture texture;
	private Stage stage;
	private SpriteBatch batch = new SpriteBatch();
	private Sprite spritebackground;
	private Sprite spriteforeground;
	private BallZi ballzi;
	private TextButton levelmenuButton;
	private Level level;
	boolean showbackbutton;
	private Texture texture2;
	private TextButton backtomainbutton;
	private TextureAtlas settingbuttonAtlas;
	private TextButton setupButton;
	private MySounds sounds;
	private int points;
	private Label pointslabel;
	private Label hscoreLabel;
	private TextField textfield;
	private Highscores highscores;
	private LoadSaveHighscores lsh;
	private Highscore hc;
	
	public LevelEndScreen(BallZi ballzi, Level level,boolean showbackbutton, MySounds sounds){
		this.sounds=sounds;
		this.stage = new Stage();
		this.ballzi= ballzi;
		this.level=level;
		this.showbackbutton=showbackbutton;
		createElements();
		setupElements();
		points=0;
		
	}
	public void setWon(boolean win,int stars, int time,Level level){
		points=0;
		if (win){
			if (time > 180){
				time=180;
			}
			switch (stars){
			case 1:
				points = 300 + (180 - time);
				break;
			case 2:
				points = 800 + (180 - time);
				break;
			case 3:
				points = 1500 + (180 - time);
				break;
			default:
				points = 180 - time;
				break;
			}
			
			lsh = new LoadSaveHighscores();
			try{
				//hole den highscore file für dieses level
				highscores = lsh.load();
			}catch(Exception e){
				highscores = new Highscores();
			}
			
			hc = highscores.get(level.getName());
			if(hc == null) {
				hc = new Highscore();
			}
			
			//lsh.save(highscores);
				
			try{
				sounds.stopMenuMusicLoop();
			}catch(Exception e){}
			try{
			sounds.stopGameMusicLoop();
			}catch(Exception e){}
			sounds.playWinningSound();
			labeltop.setText("congratulations");
			labelbottom.setText("you win");
			pointslabel.setText("score "+points);
			pointslabel.setSize((screenwidth/4), (screenwidth/12));
			pointslabel.setPosition((screenwidth/2)-(pointslabel.getWidth()/2), screenheight-(32*(screenheight/40)));
			
			switch (stars){
			case 1:
				texture2 = new Texture(Gdx.files.internal("textures/1-star.png"));
				break;
			case 2:
				texture2 = new Texture(Gdx.files.internal("textures/2-star.png"));
				break;
			case 3:
				texture2 = new Texture(Gdx.files.internal("textures/3-star.png"));
				break;
			default:
				texture2 = new Texture(Gdx.files.internal("textures/0-star.png"));
				break;
			}
			
			
			spriteforeground.setTexture(texture2);
			spriteforeground.setSize((screenwidth/4), (screenwidth/8));
			spriteforeground.setPosition((screenwidth/2)-(spriteforeground.getWidth()/2), (screenheight/2)-(spriteforeground.getHeight()/3));
		}else{
			try{
				sounds.stopMenuMusicLoop();
				}catch(Exception e){}
			try{
			sounds.stopGameMusicLoop();
			}catch(Exception e){}
			sounds.playLoosingSound();
			texture2 = new Texture(Gdx.files.internal("textures/sad-face.png"));
			spriteforeground.setTexture(texture2);
			spriteforeground.setSize((screenwidth/6), (screenwidth/12));
			spriteforeground.setPosition((screenwidth/2)-(spriteforeground.getWidth()/2), (screenheight/2)-(spriteforeground.getHeight()/3));
			labeltop.setSize((screenwidth/6), (screenwidth/12));
			labeltop.setPosition((screenwidth/2)-(labeltop.getWidth()/2), screenheight-(12*(screenheight/40)));
			labelbottom.setSize((screenwidth/4), (screenwidth/12));
			labelbottom.setPosition((screenwidth/2)-(labelbottom.getWidth()/2), screenheight-(28*(screenheight/40)));
			labeltop.setText("sorry....");
			labelbottom.setText("you loose");
		}
		
	}
	
	public void createElements(){
		texture = new Texture(Gdx.files.internal("textures/levelend-screen.png"));
		texture2 = new Texture(Gdx.files.internal("textures/sad-face.png"));
		
		spriteforeground = new Sprite(texture2);
		spritebackground = new Sprite(texture);
		buttonAtlas = new TextureAtlas("buttons/button2.pack");
		restart = new myTextButton(buttonAtlas, buttonfontsize,Color.WHITE).getButton();
		levelmenuButton = new myTextButton(buttonAtlas, buttonfontsize,Color.WHITE).getButton();
		
		
		Color color = new Color((float)237/255,(float)18/255,(float)96/255,1f);
		pointslabel = new myLabel("", labelfontsize/4,Color.BLACK).getMyLabel();
		hscoreLabel = new myLabel("", labelfontsize/4,Color.BLACK).getMyLabel();
		labeltop = new myLabel("congratulations", labelfontsize,color).getMyLabel();
		labelbottom = new myLabel("you win", labelfontsize,color).getMyLabel();
		
		
		
		buttonAtlas = new TextureAtlas("buttons/homebutton.pack");
		backtomainbutton = new myTextButton(buttonAtlas, buttonfontsize*3,Color.WHITE).getButton();
		backtomainbutton.setSize(itemsize_small*2.3f, itemsize_small*2.3f);
		backtomainbutton.setPosition(screenwidth-(screenwidth/16)-(screenwidth/16), screenheight-(screenwidth/16));
		
		
		
		settingbuttonAtlas = new TextureAtlas("buttons/setupbutton.pack");
		setupButton = new myTextButton(settingbuttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		setupButton.setSize(itemsize_small*2.3f, itemsize_small*2.3f);
		setupButton.setPosition(screenwidth-(screenwidth/16), screenheight-(screenwidth/16));
		
		
		
		
		
		TextureAtlas atlas = new TextureAtlas("buttons/textfield.pack");
		FileHandle fontFile = Gdx.files.internal("fonts/PTC55F.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        BitmapFont mBitmapFont = generator.generateFont(labelfontsize/3);
        generator.dispose();
		Skin textfieldskin = new Skin(atlas);
		TextFieldStyle style = new TextFieldStyle();
		style.fontColor = Color.BLACK;
		style.font = mBitmapFont;
		style.background = textfieldskin.getDrawable("textfield.1");
		style.cursor= textfieldskin.getDrawable("textfield.2");
		textfield = new TextField("",style);
		textfield .setMessageText("your name");
		textfield.setBlinkTime(0.5f);
		textfield.setWidth((screenwidth / 8));
		textfield.setHeight((screenwidth / 30));
		
		
		
		backtomainbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ballzi.setScreen(new StartScreen(ballzi));
			}
		});
		setupButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ballzi.setScreen(new SetupScreen(ballzi,sounds));
			}
		});
	}
	
	public void setupElements(){
		
		//spriteforeground.setSize(screenwidth/4, screenheight/4);
		spritebackground.setSize(screenwidth, screenheight);
		
		
		levelmenuButton.setText("select level");
		restart.setText("retry");
		
		labeltop.setPosition((screenwidth/2)-(labeltop.getWidth()/2), screenheight-(12*(screenheight/40)));
		labelbottom.setPosition((screenwidth/2)-(labelbottom.getWidth()/2), screenheight-(28*(screenheight/40)));
		restart.setPosition((screenwidth/2)-(restart.getWidth()/2)-(screenwidth/6), screenheight-(34*(screenheight/40)));
		levelmenuButton.setPosition((screenwidth/2)-(restart.getWidth()/2)+(screenwidth/6), screenheight-(34*(screenheight/40)));
		textfield.setPosition((screenwidth / 2) ,screenheight - (30 * (screenheight / 40)));
		

		
		stage.addActor(hscoreLabel);
		stage.addActor(pointslabel);
		stage.addActor(setupButton);
		stage.addActor(backtomainbutton);
		stage.addActor(labeltop);
		stage.addActor(labelbottom);
		stage.addActor(restart);
		stage.addActor(levelmenuButton);
		stage.addActor(textfield);
		
		restart.addListener(this);
		restart.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	ballzi.setScreen(new LevelEditorScreen(ballzi,level,showbackbutton,sounds));
            	}});
		levelmenuButton.addListener(this);
		levelmenuButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	ballzi.setScreen(new LoadLevelScreen2(ballzi,sounds,"gamelevels/"));
            	}});
	}
	public TextField getTF(){
		return textfield;
	}
	public void update(float delta){
		batch.begin();
		textfield.act(delta);
		spritebackground.draw(batch);
		spriteforeground.draw(batch);
		batch.end();
		//System.out.println("updated");
	}
	public Stage getStage(){
		return stage;
	}
	@Override
	public boolean handle(Event event) {		
		if (!(event instanceof ChangeEvent)) return false;
		
		sounds.playPushButtonSound();
		
		if (hc.isHighscore(points)) {
			hc.addHighscore(textfield.getText(), points);
			highscores.put(hc, level.getName());
		}
		
    	try{
        	lsh.save(highscores);
        }catch(Exception e){}
		return true;
	}
	
	
}