package com.ec.ballzi.screens;

import java.util.HashMap;
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

public class LoadLevelScreen2 implements Screen{
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private Stage stage = new Stage();
	private BallZi game;
	private LoadLevels ll;
	private SpriteBatch batch = new SpriteBatch();
	private LoadLevelProperties load;
	private HashMap<String, Integer> loadmap;
	private Texture backgroundtexture;
	private TextureAtlas buttonAtlas;
	private Table table;
	private Levels levels;
	private static float itemsize_small = screenwidth/40;
	private static float itemsize_large = screenwidth/6;
	private int buttonfontsize = (int)(itemsize_small/3f);
	private int padbottomvalue = (int)(itemsize_small/4);
	private TextButton backtomainbutton;
	private Vector<String> levelnameslist = new Vector<String>();
	private Vector<TextButton> buttonlist;
	private Vector<Vector<TextButton>> buttonlistlist = new Vector<Vector<TextButton>>();
	private int pageindex;
	private int index;
	private TextButton nextpagebutton;
	private TextButton lastpagebutton;
	private TextureAtlas buttonAtlasUpArrow;
	private TextureAtlas buttonAtlasDownArrow;
	private MySounds sounds;
	private String path;
	
	public LoadLevelScreen2(BallZi game, MySounds sounds,String path) {
		super();
		this.sounds=sounds;
		this.path=path;
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
		
		return new myTextButton(buttonatlas, buttonfontsize,color).getButton();
	}
	
	@Override
	public void show() {
		buttonAtlas = new TextureAtlas("buttons/homebutton.pack");
		buttonAtlasUpArrow = new TextureAtlas("buttons/playbutton.pack");
		buttonAtlasDownArrow = new TextureAtlas("buttons/downarrow.pack");
		backtomainbutton = new myTextButton(buttonAtlas, buttonfontsize*3,Color.WHITE).getButton();
		backtomainbutton.setSize(itemsize_small*2.3f, itemsize_small*2.3f);
		backtomainbutton.setPosition(screenwidth-(screenwidth/16), screenheight-(screenwidth/16));
		
		
		nextpagebutton = new myTextButton(buttonAtlasUpArrow, buttonfontsize*3,Color.WHITE).getButton();
		nextpagebutton.setSize(itemsize_small*2, itemsize_small*2);
		nextpagebutton.setPosition(screenwidth-(screenwidth/8)-(nextpagebutton.getWidth()/2), screenheight/2-(screenheight/8.8f));
		
		lastpagebutton = new myTextButton(buttonAtlasDownArrow, buttonfontsize*3,Color.WHITE).getButton();
		lastpagebutton.setSize(itemsize_small*2, itemsize_small*2);
		lastpagebutton.setPosition((screenwidth/8)-(lastpagebutton.getWidth()/2), screenheight/2-(screenheight/8.8f));
		
		
		pageindex=0;
		try {
			ll = new LoadLevels();
		} catch (Exception e) {
		}
		load = new LoadLevelProperties();
		loadmap = load.getConfig();	
		levels=ll.load("bin/"+path);
		
		buttonlist = new Vector<TextButton>();
		buttonlistlist.add(buttonlist);
		
		index =0;
		for (Level level:levels.getLevels()){
			int b = index%11;
			if (b==10){
				buttonlist = new Vector<TextButton>();
				buttonlistlist.add(buttonlist);
				System.err.println("add button list");
			}
				
			levelnameslist.add(level.getName());
			try{
				buttonlist.add(makeButton(loadmap.get(level.getName()+"STARSCOLLECTED")));
			}catch(Exception e){
				buttonlist.add(makeButton(0));
			}
			index++;
		}
		
		
		if (pageindex<1){
			lastpagebutton.setColor(1, 1, 1, 0.5f);
		}else{
			lastpagebutton.setColor(1, 1, 1, 1);
		}
		if (pageindex>=buttonlistlist.size()){
			nextpagebutton.setColor(1, 1, 1, 0.5f);
		}
		else{
			nextpagebutton.setColor(1, 1, 1, 1);
		}
		
		
		
		index =0;
		for (Vector<TextButton> textblist:buttonlistlist){
			for (TextButton textb:textblist){
				System.out.println("A");
				textb.padBottom(padbottomvalue*4);
				textb.setText(levelnameslist.get(index));
				index++;
			}
		}

		
		
		
		
		table = new Table(new Skin());
		table.setFillParent(true);
		table.center();
		table.padTop(screenheight / 7f);
		table.debug();
		index =0;
		for (TextButton textb:buttonlistlist.get(0)){
			int a = index % 5;
			if (a == 4){
				table.add(textb).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f).row();
			}else{
				table.add(textb).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
			}
			
			index++;
			System.err.println("buttonnumber: "+ index);
			
			textb.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					sounds.playPushButtonSound();
					for (TextButton txtb:buttonlistlist.get(0)){
						if (txtb.isChecked()){
							game.setScreen(new LevelEditorScreen(game,levels.getByName(txtb.getText()+""),false,sounds));
							
						}
					}
					
				}
			});
				
		}
		index=0;
		
			

		
		nextpagebutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (pageindex<buttonlistlist.size()-1){
					pageindex++;
					changePage(pageindex);
					System.err.println(pageindex);
				}
				
			}
		});
		lastpagebutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (pageindex>0){
					pageindex--;
					changePage(pageindex);
					System.err.println(pageindex);
				}
				
			}
		});
		if (path.equals("levels/")){
		stage.addActor(nextpagebutton);
		stage.addActor(lastpagebutton);
		}
		stage.addActor(backtomainbutton);
		stage.addActor(table);
		
		backtomainbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen(game));
			}
		});
		
		
		
		
		
	}

	public void changePage(final int pageindex){
		this.pageindex=pageindex;
		index=0;
		table.clear();
		if (pageindex<1){
			lastpagebutton.setColor(1, 1, 1, 0.5f);
		}else{
			lastpagebutton.setColor(1, 1, 1, 1);
		}
		if (pageindex>=buttonlistlist.size()-1){
			nextpagebutton.setColor(1, 1, 1, 0.5f);
		}
		else{
			nextpagebutton.setColor(1, 1, 1, 1);
		}
		
		
		for (TextButton textb:buttonlistlist.get(pageindex)){
			
			int a = index % 5;
			if (a == 4){
				table.add(textb).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f).row();
			}else{
				table.add(textb).maxWidth(itemsize_large/1.7f).minWidth(itemsize_large/1.7f).minHeight(itemsize_large/1.7f).maxHeight(itemsize_large/1.7f).pad(itemsize_small*1.3f);
			}
			
			index++;
			textb.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					sounds.playPushButtonSound();
					for (TextButton txtb:buttonlistlist.get(pageindex)){
						if (txtb.isChecked()){
							game.setScreen(new LevelEditorScreen(game,levels.getByName(txtb.getText()+""),false,sounds));
						}
					}
					
				}
			});
				
		}
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
