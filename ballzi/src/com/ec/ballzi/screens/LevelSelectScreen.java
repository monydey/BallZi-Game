package com.ec.ballzi.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ec.ballzi.BallZi;
import com.ec.ballzi.data.Level;
import com.ec.ballzi.data.Levels;
import com.ec.ballzi.data.LoadLevelProperties;
import com.ec.ballzi.data.LoadLevels;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myTextButton;

public class LevelSelectScreen implements Screen{
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private Stage stage = new Stage();
	private BallZi game;
	private LoadLevels ll;
	private SpriteBatch batch = new SpriteBatch();
	private TextButton level1, level2, level3,  level4, level5, level6, level7, level8, level9, level10;
	private int level1stars,level2stars,level3stars,level4stars,level5stars,level6stars,level7stars,level8stars,level9stars,level10stars;
	private LoadLevelProperties load;
	private HashMap<String, Integer> loadmap;
	private Texture backgroundtexture;
	private TextureAtlas buttonAtlas;
	private Table table;
	private static TextureRegion[] starsregion;
	private static Skin s2;
	private static String[] starsnames;
	private Levels levels;
	private int starscount;
	private static float itemsize_small = screenwidth/40;
	private static float itemsize_large = screenwidth/6;
	private int buttonfontsize = (int)(itemsize_small/1.5f);
	private int padbottomvalue = (int)(itemsize_small/4);
	private TextButton backtomainbutton;
	private MySounds sounds;
	
	static void setupAnimation() {
		TextureAtlas atlas = new TextureAtlas(
				"textures/stars.pack");
		starsnames = new String[atlas.getRegions().size];
		starsregion = new TextureRegion[atlas.getRegions().size];
		s2 = new Skin(atlas);
		
		for (int i = 0; i < 50; i++) {
			int a = i + 1;
			if (a<10){
				starsregion[i] = s2.getRegion("stars0.000" + a);
				starsnames[i] = "stars0.000" + a;
			}else{
				starsregion[i] = s2.getRegion("stars0.00" + a);
				starsnames[i] = "stars0.00" + a;
			}
		}
		for (int i = 0; i < 50; i++) {
			int a = i + 1;
			if (a<10){
				starsregion[i+50] = s2.getRegion("stars1.000" + a);
				starsnames[i+50] = "stars1.000" + a;
				}
			else{
				starsregion[i+50] = s2.getRegion("stars1.00" + a);
				starsnames[i+50] = "stars1.00" + a;
				}
		}
		for (int i = 0; i < 50; i++) {
			int a = i + 1;
			if (a<10){
				starsregion[i+100] = s2.getRegion("stars2.000" + a);
				starsnames[i+100] = "stars2.000" + a;
				}
			else{
				starsregion[i+100] = s2.getRegion("stars2.00" + a);
				starsnames[i+100] = "stars2.00" + a;
				}
		}
		for (int i = 0; i < 50; i++) {
			int a = i + 1;
			if (a<10){
				starsregion[i+150] = s2.getRegion("stars3.000" + a);
				starsnames[i+150] = "stars3.000" + a;
				}
			else{
				starsregion[i+150] = s2.getRegion("stars3.00" + a);
				starsnames[i+150] = "stars3.00" + a;
				}
		}
		
	}
	
	
	public LevelSelectScreen(BallZi game, MySounds sounds) {
		super();
		this.sounds=sounds;
		try{
			sounds.stopMenuMusicLoop();
		}catch(Exception e){}
		sounds.playMenuMusicLoop();
		try{
			sounds.stopGameMusicLoop();
		}catch(Exception e){}
		Gdx.input.setInputProcessor(stage);
		this.game = game;
		backgroundtexture = new Texture(Gdx.files.internal("textures/level-select-screen.png"));
		setupAnimation();

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		batch.begin();
		batch.draw(backgroundtexture, 0, 0, screenwidth,
				screenheight);
		
		batch.end();
		stage.act(delta);
		stage.draw();
		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}
	
	public TextButton makeButton(int levelstars){
		Color color = new Color((float)237/255,(float)18/255,(float)96/255,1f);
		TextureAtlas buttonatlas;
		switch (levelstars) {
		case 1:
			buttonatlas = new TextureAtlas("buttons/levelselectbuttons1stars.pack");
			break;
		case 2:
			buttonatlas = new TextureAtlas("buttons/levelselectbuttons2stars.pack");
			break;
		case 3:
			buttonatlas = new TextureAtlas("buttons/levelselectbuttons3stars.pack");
			break;
		default:
			buttonatlas = new TextureAtlas("buttons/levelselectbuttons.pack");
			break;
		}
		
		return new myTextButton(buttonatlas, buttonfontsize*3,color).getButton();
	}
	@Override
	public void show() {
		try {
			ll = new LoadLevels();
		} catch (Exception e) {
		}
		load = new LoadLevelProperties();
		loadmap = load.getConfig();	
		levels=ll.load("bin/gamelevels/");
		System.out.println(levels.getByIndex(0).getName());
		level1stars = loadmap.get(levels.getByIndex(0).getName()+"STARSCOLLECTED");
		level2stars = loadmap.get(levels.getByIndex(1).getName()+"STARSCOLLECTED");
		level3stars = loadmap.get(levels.getByIndex(2).getName()+"STARSCOLLECTED");
		level4stars = loadmap.get(levels.getByIndex(3).getName()+"STARSCOLLECTED");
		level5stars = loadmap.get(levels.getByIndex(4).getName()+"STARSCOLLECTED");
		level6stars = loadmap.get(levels.getByIndex(5).getName()+"STARSCOLLECTED");
		level7stars = loadmap.get(levels.getByIndex(6).getName()+"STARSCOLLECTED");
		level8stars = loadmap.get(levels.getByIndex(7).getName()+"STARSCOLLECTED");
		level9stars = loadmap.get(levels.getByIndex(8).getName()+"STARSCOLLECTED");
		level10stars = loadmap.get(levels.getByIndex(9).getName()+"STARSCOLLECTED");
		
		starscount = 0;
		for (Level level:levels.getLevels()){
			starscount += loadmap.get(level.getName()+"STARSCOLLECTED");
		}
		
		buttonAtlas = new TextureAtlas("buttons/homebutton.pack");
		backtomainbutton = new myTextButton(buttonAtlas, buttonfontsize*3,Color.WHITE).getButton();
		backtomainbutton.setSize(itemsize_small*2.3f, itemsize_small*2.3f);
		backtomainbutton.setPosition(screenwidth-(screenwidth/16), screenheight-(screenwidth/16));
		
		level1 = makeButton(level1stars);
		level2 = makeButton(level2stars);
		level3 = makeButton(level3stars);
		level4 = makeButton(level4stars);
		level5 = makeButton(level5stars);
		level6 = makeButton(level6stars);
		level7 = makeButton(level7stars);
		level8 = makeButton(level8stars);
		level9 = makeButton(level9stars);
		level10 = makeButton(level10stars);
		level1.padBottom(padbottomvalue*4);
		level2.padBottom(padbottomvalue*4);
		level3.padBottom(padbottomvalue*4);
		level4.padBottom(padbottomvalue*4);
		level5.padBottom(padbottomvalue*4);
		level6.padBottom(padbottomvalue*4);
		level7.padBottom(padbottomvalue*4);
		level8.padBottom(padbottomvalue*4);
		level9.padBottom(padbottomvalue*4);
		level10.padBottom(padbottomvalue*4);
		level1.setText("1");
		level2.setText("2");
		level3.setText("3");
		level4.setText("4");
		level5.setText("5");
		level6.setText("6");
		level7.setText("7");
		level8.setText("8");
		level9.setText("9");
		level10.setText("10");
		
		if (starscount<10){
			level6.setDisabled(true);
			level6.setColor(1, 1, 1, 0.3f);
			level7.setDisabled(true);
			level7.setColor(1, 1, 1, 0.3f);
			level8.setDisabled(true);
			level8.setColor(1, 1, 1, 0.3f);
			level9.setDisabled(true);
			level9.setColor(1, 1, 1, 0.3f);
			level10.setDisabled(true);
			level10.setColor(1, 1, 1, 0.3f);
			
		}
		
		
		
		table = new Table(new Skin());
		table.setFillParent(true);
		table.center();
		table.padTop(screenheight / 7f);
		table.debug();
		table.add(level1).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level2).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level3).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level4).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level5).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f).row();
		
		
		
		table.add(level6).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level7).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level8).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level9).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
		table.add(level10).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f).row();
		
		stage.addActor(backtomainbutton);
		stage.addActor(table);
		
		backtomainbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen(game));
			}
		});
		level1.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(0),false,sounds));
			}
		});
		level2.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(1),false,sounds));
			}
		});
		level3.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(2),false,sounds));
			}
		});
		level4.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(3),false,sounds));
			}
		});
		level5.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(4),false,sounds));
			}
		});
		level6.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(5),false,sounds));
			}
		});
		level7.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(6),false,sounds));
			}
		});
		level8.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(7),false,sounds));
			}
		});
		level9.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(8),false,sounds));
			}
		});
		level10.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				game.setScreen(new LevelEditorScreen(game,levels.getByIndex(9),false,sounds));
			}
		});
		
		
		
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
