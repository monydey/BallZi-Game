package com.ec.ballzi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ec.ballzi.BallZi;
import com.ec.ballzi.data.Highscore;
import com.ec.ballzi.data.Level;
import com.ec.ballzi.data.Highscoreentry;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myLabel;
import com.ec.ballzi.uiitems.myTextButton;

public class HighscoreScreen {
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private SpriteBatch batch = new SpriteBatch();
	private Stage stage = new Stage();
	private Sprite spritebackground;
	private Texture texture;
	private static float itemsize_small = screenwidth/40;
	private int buttonfontsize = (int)(itemsize_small/1.3f);
	private int labelfontsize = 60;
	private Table table;
	public BallZi ballzi;
	private TextButton restart;
	public Level level;
	public boolean showbackbutton;
	public MySounds sounds;
	private Highscore highscore;
	
	public HighscoreScreen(BallZi ballzi,Highscore highscore,Level level,boolean showbackbutton,MySounds sounds){
		this.ballzi=ballzi;
		this.level=level;
		this.showbackbutton =showbackbutton;
		this.sounds=sounds;
		this.highscore=highscore;
		create();
		
	}
	
	private void create(){
		texture = new Texture(Gdx.files.internal("textures/hsc-screen.png"));
		spritebackground = new Sprite(texture);
		spritebackground.setSize(screenwidth, screenheight);
		table = new Table(new Skin());
		table.setFillParent(true);
		table.center();
		
		for (Highscoreentry v:highscore.getHighscoreList()){
			Label scorelabel = new myLabel(""+v.getScore(), labelfontsize/4,Color.BLACK).getMyLabel();
			Label namelabel = new myLabel(v.getName(), labelfontsize/4,Color.BLACK).getMyLabel();
			table.add(namelabel).left().padRight(100f);
			table.add(scorelabel).right().row();
		}
		
		
		TextureAtlas buttonAtlas = new TextureAtlas("buttons/button2.pack");
		
		restart = new myTextButton(buttonAtlas, buttonfontsize,Color.WHITE).getButton();
		restart.setText("close");
		restart.setPosition((screenwidth/2)-(restart.getWidth()/2), screenheight-(34*(screenheight/40)));
		
		stage.addActor(table);
		stage.addActor(restart);
		
		
		restart.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	System.out.println("Button click");
            	ballzi.setScreen(new LevelEditorScreen(ballzi,level,showbackbutton,sounds));
            	}});
	}
	
	public Stage getStage() {
		return stage;
	}



	public void update(float delta){
		batch.begin();
		spritebackground.draw(batch);
		batch.end();
		//System.out.println("updated");
	}
}
