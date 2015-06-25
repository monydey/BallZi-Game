package com.ec.ballzi.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import com.ec.ballzi.data.ItemData;
import com.ec.ballzi.data.Level;
import com.ec.ballzi.data.LoadLevels;
import com.ec.ballzi.data.SaveLevels;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myLabel;
import com.ec.ballzi.uiitems.myTextButton;

public class StartScreen implements Screen {
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private Stage stage = new Stage();
	private Table table = new Table();
	private TextureAtlas buttonAtlas;
	private TextButton editorbutton, playbutton, exitbutton, setupButton;
	private Label headerLabel;
	private Texture texture;
	private SpriteBatch batch = new SpriteBatch();
	private BallZi game;
	private Table table1;
	private LoadLevels ll = null;
	private Level level;
	private Table table2;
	private boolean load = false;
	private String levelname;
	private SaveLevels save = new SaveLevels();
	private TextButton loadlevelbutton;
	private TextureAtlas settingbuttonAtlas;
	private static float itemsize_small = screenwidth/40;
	private static float itemsize_large = screenwidth/6;
	private static MySounds sounds = new MySounds();
	public StartScreen(BallZi game) {
		super();
		Gdx.input.setInputProcessor(stage);
		this.game = game;
		try{
			sounds.stopMenuMusicLoop();
			}catch(Exception e){}
		try{
		sounds.stopGameMusicLoop();
		}catch(Exception e){}
		sounds.playMenuMusicLoop();
		
		Vector<ItemData> stars = new Vector<ItemData>();
		stars.add(new ItemData(-100, -100, 0));
		stars.add(new ItemData(390, 0, 0));

		level = new Level();
		level.setBoxes_fixed(new Vector<ItemData>());
		level.setCrossboxes_fixed(new Vector<ItemData>());
		level.setForceboxes_fixed(new Vector<ItemData>());
		level.setMagnetboxes_fixed(new Vector<ItemData>());
		level.setAbyssboxes_fixed(new Vector<ItemData>());
		level.setBalls_fixed(new Vector<ItemData>());
		level.setPushboxes_fixed(new Vector<ItemData>());
		level.setStars(new Vector<ItemData>());
		level.setStars(stars);

		try {
			ll = new LoadLevels();
			level = ll.load("bin/levels/").getByIndex(0);
		} catch (Exception e) {
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		batch.begin();
		batch.draw(texture, 0, 0, screenwidth,
				screenheight);
		batch.end();
		stage.act(delta);
		stage.draw();
		// Table.drawDebug(stage);
		if (load) {
			game.setScreen(new LevelEditorScreen(game, ll.load("bin/levels/").getByName(
					levelname),true,sounds));
			load = false;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}


	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		texture = new Texture(Gdx.files.internal("textures/start-page.png"));
		
		
		
		// create header label
		int ab = 0;
		if (screenwidth == 1920)
			ab = 90;
		if (screenwidth == 1280)
			ab = 70;
		if (screenwidth == 1024)
			ab = 60;
		if (screenwidth == 800)
			ab = 50;
		headerLabel = new myLabel("", ab,Color.BLACK).getMyLabel();

		// create buttons
		buttonAtlas = new TextureAtlas("buttons/button2.pack");
		settingbuttonAtlas = new TextureAtlas("buttons/setupbutton.pack");
		setupButton = new myTextButton(settingbuttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		setupButton.setSize(itemsize_small*2.3f, itemsize_small*2.3f);
		setupButton.setPosition(screenwidth-(screenwidth/16), screenheight-(screenwidth/16));
		editorbutton = new myTextButton(buttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		editorbutton.setScale(0.5f);
		editorbutton.padBottom((int)(itemsize_small/4));
		playbutton = new myTextButton(buttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		playbutton.padBottom((int)(itemsize_small/4));
		exitbutton = new myTextButton(buttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		exitbutton.padBottom((int)(itemsize_small/4));
		loadlevelbutton = new myTextButton(buttonAtlas, (int)(itemsize_small/1.5f),Color.WHITE).getButton();
		loadlevelbutton.padBottom((int)(itemsize_small/4));
		
		
		// Create a table that fills the screen. Everything else will go inside
		// this table.

		table1 = new Table(new Skin());
		table1.setFillParent(true);
		table1.center().top();
		table1.add(headerLabel);
		table1.debug();

		editorbutton.setText("level editor");
		playbutton.setText("play");
		loadlevelbutton.setText("load level");
		exitbutton.setText("exit");


		table = new Table(new Skin());
		table.setFillParent(true);
		table.padTop(screenheight / 4f);
		table.add(editorbutton).minWidth(itemsize_large/6).minHeight((int)(itemsize_small/6)).pad((int)(itemsize_small/12));
		table.add(playbutton).minWidth(itemsize_large/6).minHeight((int)(itemsize_small/6)).pad((int)(itemsize_small/12));
		table.add(exitbutton).minWidth(itemsize_large/6).minHeight((int)(itemsize_small/6)).pad((int)(itemsize_small/12));
		table.add(loadlevelbutton).minWidth(itemsize_large/6).minHeight((int)(itemsize_small/6)).pad((int)(itemsize_small/12));
		table2 = new Table(new Skin());
		table2.setFillParent(true);


		// add table to stage
		stage.addActor(setupButton);
		stage.addActor(table);
		stage.addActor(table1);
		stage.addActor(table2);
		// button listener
		editorbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LevelEditorScreen(game,sounds));
				sounds.playPushButtonSound();
				try{
					sounds.stopMenuMusicLoop();
					}catch(Exception e){}
				try{
				sounds.stopGameMusicLoop();
				}catch(Exception e){}
				
			}
		});
		playbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				//game.setScreen(new LevelSelectScreen(game,sounds));	
				game.setScreen(new LoadLevelScreen2(game,sounds,"gamelevels/"));
			}
		});
		exitbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				Gdx.app.exit();
			}
		});
		loadlevelbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LoadLevelScreen2(game,sounds,"levels/"));
			}
		});
		setupButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new SetupScreen(game,sounds));
				
			}
		});
		

	}


	public void clearlevels() {
		save.clear();
	}

	public void loadlevel(String levelname) {
		this.levelname = levelname;
		load = true;
	}

	@Override
	public void hide() {
		dispose();

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
		stage.dispose();
		texture.dispose();
		batch.dispose();

	}

}