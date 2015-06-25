package com.ec.ballzi.screens;

import java.util.HashMap;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ec.ballzi.BallZi;
import com.ec.ballzi.data.Highscore;
import com.ec.ballzi.data.Highscores;
import com.ec.ballzi.data.ItemData;
import com.ec.ballzi.data.Level;
import com.ec.ballzi.data.LevelData;
import com.ec.ballzi.data.Levels;
import com.ec.ballzi.data.LoadImage;
import com.ec.ballzi.data.LoadLevelProperties;
import com.ec.ballzi.data.LoadLevels;
import com.ec.ballzi.data.LoadSaveHighscores;
import com.ec.ballzi.data.SaveLevelProperties;
import com.ec.ballzi.data.SaveLevels;
import com.ec.ballzi.elements.LevelTimer;
import com.ec.ballzi.fields.MagnetField;
import com.ec.ballzi.fields.forceField;
import com.ec.ballzi.items.AbyssBox;
import com.ec.ballzi.items.Ball;
import com.ec.ballzi.items.CollidableBox;
import com.ec.ballzi.items.FinishBox;
import com.ec.ballzi.items.ForceBox;
import com.ec.ballzi.items.MagnetBox;
import com.ec.ballzi.items.PushBox;
import com.ec.ballzi.items.SpinningCrossBox;
import com.ec.ballzi.items.Star;
import com.ec.ballzi.sound.MySounds;
import com.ec.ballzi.uiitems.myLabel;
import com.ec.ballzi.uiitems.myTextButton;

public class LevelEditorScreen implements Screen {
	private static float height = Gdx.graphics.getHeight();
	private static float width = Gdx.graphics.getWidth();
	private BallZi ballzi;
	private Texture texture;
	private SpriteBatch batch;
	private Sprite sprite_backgroundplayground;
	private World world ;
	private CollidableBox collidebox;
	private Texture texturebox;
	private Sprite sprite_mouse;
	private boolean a = false;
	private Vector<CollidableBox> boxes = new Vector<CollidableBox>();
	private Vector<ForceBox> forceboxes = new Vector<ForceBox>();
	private Vector<MagnetBox> magnetboxes = new Vector<MagnetBox>();
	private Vector<SpinningCrossBox> crossboxes = new Vector<SpinningCrossBox>();
	private Vector<PushBox> pushboxes = new Vector<PushBox>();
	private Vector<FinishBox> finishboxes = new Vector<FinishBox>();
	private Vector<Ball> balls = new Vector<Ball>();
	private Vector<Star> stars = new Vector<Star>();
	private Vector<AbyssBox> abyssboxes = new Vector<AbyssBox>();
	private Vector<AbyssBox> abyssboxesR = new Vector<AbyssBox>();
	private Vector<CollidableBox> boxes_fixed = new Vector<CollidableBox>();
	private Vector<ForceBox> forceboxes_fixed = new Vector<ForceBox>();
	private Vector<MagnetBox> magnetboxes_fixed = new Vector<MagnetBox>();
	private Vector<SpinningCrossBox> crossboxes_fixed = new Vector<SpinningCrossBox>();
	private Vector<PushBox> pushboxes_fixed = new Vector<PushBox>();
	private Vector<FinishBox> finishboxes_fixed = new Vector<FinishBox>();
	private Vector<Ball> balls_fixed = new Vector<Ball>();
	private Vector<Star> stars_fixed= new Vector<Star>();
	private Vector<AbyssBox> abyssboxes_fixed = new Vector<AbyssBox>();
	private Vector<AbyssBox> abyssboxesR_fixed = new Vector<AbyssBox>();
	private static final float BOX_STEP = 1 / 120f;
	private static final int BOX_VELOCITY_ITERATIONS = 8;
	private static final int BOX_POSITION_ITERATIONS = 3;
	private float accumulator;
	private int selected = 0;
	private int degrees = 90;
	private float keytime = 0f;
	private boolean pressed;
	private MagnetBox magnetbox;
	private ForceBox forcebox;
	private boolean isoneselected = false;
	private SpinningCrossBox spinbox;
	private boolean startpushed = false;
	private boolean playable;
	private Ball ball;
	private PushBox pushbox;
	private FinishBox finishbox;
	private Star star;
	private AbyssBox abyssbox;
	private AbyssBox abyssboxR;
	private Stage stage = new Stage();
	private Table table1 = new Table();
	private Level level;
	private TextField textfield;
	private TextButton buttonplay;
	private TextButton buttonclear;
	private Label nameLabel;
	private TextButton buttonloadbackground;
	private TextButton buttonstart;
	private TextButton buttonreset;
	private Sprite sprite_backgrounditems;
	private boolean pushed = false;
	private Label headerLabel;
	private float texttime = 2f;
	private Polygon bounds;
	//private ShapeRenderer renderer;
	private int del_grid_rows=8;
	private float[] vertices = {
			width	- (width/5)	,0	, 
			width	- (width/5)	,height, 
			width,height,
			width, 0 };
	private float[] vertices2 = {
			0	,0	, 
			0	,50, 
			50	,50,
			50, 0 };
	private static final int gridsize=25;
	private Vector<Vector<Polygon>> gridbounds = new Vector<Vector<Polygon>>();
	private TextButton buttonback;
	private TextButton savebutton;
	private int collectedstars= 0;
	private HashMap<String, Integer> configmap;
	private TextButton menubutton;
	private TextButton levelmenubutton;
	private int ballcount;
	private String backgroundtexpath="textures/box.png";
	private SaveLevelProperties saveconfig = new SaveLevelProperties();
	private Slider forceslider;
	private Slider magnetslider;
	private Slider boxslider;
	private Skin skin;
	private Slider crossboxslider;
	private Label forceLabel;
	private Label magnetLabel;
	private Label boxLabel;
	private Label crossboxLabel;
	private int maxBoxes=1000;
	private int maxCrossBoxes=1000;
	private int maxForceBoxes=1000;
	private int maxMagnetBoxes=1000;
	private LevelEndScreen endscreen;
	private boolean levelfailed;
	private Texture bgtexture;
	private float timertime = 0;
	private Label timeLabel;
	private static float itemsize_small = width/40;
	private static float itemsize_medium = width/20;
	private static float itemsize_big = width/10;
	private boolean is_on_playground = false;
	private float rotkeytime =0;
	private String resolution;
	private MySounds sounds;
	private boolean passedfailedminimizer;
	private LevelTimer timer;
	private float waitingtimer = 0;
	private CollidableBox edgesprite1;
	private CollidableBox edgesprite2;
	private CollidableBox edgesprite3;
	private CollidableBox edgesprite4;
	private boolean levelover= false;
	private float forcesoundtimer = 0;
	private float magnetsoundtimer = 0;
	private TextButton highscorebutton;
	private HighscoreScreen hcscreen;
	private boolean showbackbutton;
	
	public LevelEditorScreen(final BallZi ballzi,Level level,boolean showbackbutton, MySounds sound) {
		super();
		this.showbackbutton=showbackbutton;
		this.sounds=sound;
		try{
			sounds.stopMenuMusicLoop();
			}catch(Exception e){}
		try{
		sounds.stopGameMusicLoop();
		}catch(Exception e){}
		sound.playGameMusicLoop();
		collectedstars=0;
		startpushed=false;
		this.level=level;
		LoadLevelProperties load = new LoadLevelProperties();
		configmap = load.getConfig();
		passedfailedminimizer=true;
		this.ballzi = ballzi;
		endscreen = new LevelEndScreen(ballzi,level,showbackbutton,sounds);
		world= new World(new Vector2(0, 0), true);
		//ball=level.getBall();
		collidebox = new CollidableBox((width/2)-(width/11), (height/2)-(7*(height/40)), world, itemsize_medium, itemsize_medium, Color.YELLOW,0);
		magnetbox = new MagnetBox((width/2)-(width/11), (height/2)-(16*(height/40)), itemsize_small, itemsize_small,new MagnetField(0,ball));
		forcebox = new ForceBox((width/2)-(width/11), (height/2)-(25*(height/40)), itemsize_small, itemsize_small,new forceField(180,itemsize_medium));
		spinbox = new SpinningCrossBox((width/2)-(width/11), (height/2)-(34*(height/40)),world, itemsize_medium, itemsize_medium, Color.ORANGE,0);
		
		
		maxBoxes=level.getMaxBoxes();
		maxCrossBoxes=level.getMaxCrossBoxes();
		maxForceBoxes=level.getMaxForceBoxes();
		maxMagnetBoxes=level.getMaxMagnetBoxes();
		
		forceLabel = new myLabel(""+maxForceBoxes, (int)itemsize_small,Color.BLACK).getMyLabel();
		forceLabel.setPosition((width)-(width/30), (height)-(28*(height/40)));
		
		magnetLabel = new myLabel(""+maxMagnetBoxes, (int)itemsize_small,Color.BLACK).getMyLabel();
		magnetLabel.setPosition((width)-(width/30), (height)-(19*(height/40)));
		
		crossboxLabel = new myLabel(""+maxCrossBoxes, (int)itemsize_small,Color.BLACK).getMyLabel();
		crossboxLabel.setPosition((width)-(width/30), (height)-(37*(height/40)));
		
		boxLabel = new myLabel(""+maxBoxes, (int)itemsize_small,Color.BLACK).getMyLabel();
		boxLabel.setPosition((width)-(width/30), (height)-(10*(height/40)));
		
		
		Color color = new Color(0,0,0,0.5f);
		timeLabel = new myLabel("0:00", (int)(itemsize_small/2),color).getMyLabel();
		timeLabel.setPosition(width/80, height-(width/40));
		
		float x;
		float y;
		for(ItemData idata:level.getStars()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			stars_fixed.add(new Star(x,y,itemsize_medium,itemsize_medium));
		}
		
		for(ItemData idata:level.getFinishboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			finishboxes_fixed.add(new FinishBox(x,y,itemsize_medium,itemsize_medium,idata.getDirection()));
		}
		
		for(ItemData idata:level.getAbyssboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			abyssboxes_fixed.add(new AbyssBox(x,y,itemsize_small,itemsize_small,Color.BLACK,idata.getDirection()));
		}
		for(ItemData idata:level.getAbyssboxesR_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			abyssboxesR_fixed.add(new AbyssBox(x,y,itemsize_medium,itemsize_medium,Color.BLACK,idata.getDirection()));
		}
		for(ItemData idata:level.getCrossboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			crossboxes_fixed.add(new SpinningCrossBox(x,y,world,itemsize_big,itemsize_big,Color.RED,idata.getDirection()));
		}
		
		for(ItemData idata:level.getPushboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			pushboxes_fixed.add(new PushBox(x,y,world,itemsize_medium,Color.YELLOW,idata.getDirection()));
		}
		
		for(ItemData idata:level.getBoxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			boxes_fixed.add(new CollidableBox(x,y,world,itemsize_medium,itemsize_medium,Color.YELLOW,idata.getDirection()));
		}
		
		for(ItemData idata:level.getBalls_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			balls_fixed.add(new Ball(x,y,world,itemsize_small,sounds));
		}
		
		ballcount = balls_fixed.size();
		
		for(PushBox pb:pushboxes_fixed){
			pb.updateset();
			pb.reset();
		}

		for(ItemData idata:level.getMagnetboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			magnetboxes_fixed.add(new MagnetBox(x,y,itemsize_small,itemsize_small,new MagnetField(idata.getDirection(),ball)));
		}
		
		for(ItemData idata:level.getForceboxes_fixed()){
			x=idata.getX()*width/level.getSaveresolutionwidht();
			y = idata.getY()*height/level.getSaveresolutionheight();
			forceboxes_fixed.add(new ForceBox(x,y,itemsize_small,itemsize_small,new forceField(idata.getDirection(),itemsize_medium)));
		}
		
		creation();
		removepaintedItems();
		try{
			sprite_backgroundplayground.setTexture(new Texture(Gdx.files.local(level.getBackground_path())));
		}catch(Exception e){
		}
		
		playable = true;

		resolution = (int) width + "x" + (int) height;
		int btfontsize = 0;
		int btwidth=0;
		int btheight=0;
		int btwidthsmall=0;
		if (resolution.equals("800x600")) {
			btfontsize=10;
			btwidth=60;
			btwidthsmall=50;
			btheight=15;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		} else if (resolution.equals("1024x768")) {
			btfontsize=11;
			btwidth=80;
			btwidthsmall=60;
			btheight=20;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		} else if (resolution.equals("1280x720")) {
			btfontsize=12;
			btwidth=100;
			btwidthsmall=76;
			btheight=25;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		} else if (resolution.equals("1280x800")) {
			btfontsize=12;
			btwidth=100;
			btwidthsmall=76;
			btheight=25;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		}
		else if (resolution.equals("1920x1080")) {
			btfontsize=16;
			btwidth=120;
			btwidthsmall=100;
			btheight=35;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		}
		else if (resolution.equals("1920x1200")) {
			btfontsize=16;
			btwidth=120;
			btwidthsmall=100;
			btheight=35;
			setupButtons(btfontsize,btwidth,btwidthsmall,btheight,showbackbutton);
		}
		LoadSaveHighscores lsh = new LoadSaveHighscores();
		Highscores highscores;
		Highscore hc=null;
		try{
			highscores = lsh.load();
			hc = highscores.get(level.getName());
		}catch(Exception e){
			
		}
		if (hc!=null){
			stage.addActor(highscorebutton);
		}
		
		
		stage.addActor(menubutton);
		stage.addActor(levelmenubutton);
		stage.addActor(buttonstart);
		stage.addActor(buttonreset);
		stage.addActor(magnetLabel);
		stage.addActor(boxLabel);
		stage.addActor(crossboxLabel);
		stage.addActor(forceLabel );
		stage.addActor(timeLabel );
		
		startpushed=false;
		pushed = false;

		if(!playable)
			ball.setPosition(ball.getStartX(), ball.getStartY());
		for(Ball bal:balls_fixed){
			bal.setPosition(bal.getStartX(), bal.getStartY());
		}
		for (Star star : stars_fixed) {
			star.show();
		}
		
		
		
		levelmenubutton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	ballzi.setScreen(new LoadLevelScreen2(ballzi,sounds,"gamelevels/"));
            	
            	}});
		buttonstart.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	sounds.playStartpushASound();
            	sounds.playBallMoveLoop();
            	waitingtimer=0f;
            	startpushed=true;
            	collectedstars=0;
            	timer.resetTimer();
            	}});
		buttonreset.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				startpushed = false;
				pushed = false;
				for (PushBox pb : pushboxes_fixed) {
					pb.reset();
				}

				for (Ball bal : balls_fixed) {
					bal.setPosition(bal.getStartX(), bal.getStartY());

					System.out.println("x: " + bal.getStartX() + "   y: "
							+ bal.getStartY());
				}
				for (Star star : stars_fixed) {
					star.show();
				}
				ballcount = balls_fixed.size();
			}
		});
		buttonback.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				backtoedit();
			}
		});
		menubutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				ballzi.setScreen(new StartScreen(ballzi));

			}
		});
		highscorebutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				sounds.playPushButtonSound();
				openHScore();

			}
		});
	
	}
	private void openHScore(){
		LoadSaveHighscores lsh = new LoadSaveHighscores();
		Highscores highscores;
		Highscore hc=null;
		try{
			highscores = lsh.load();
			hc = highscores.get(level.getName());
		}catch(Exception e){
			
		}
		if (hc!=null){
			hcscreen = new HighscoreScreen(ballzi,hc,level,showbackbutton,sounds);
			stage = hcscreen.getStage();
			Gdx.input.setInputProcessor(stage);
		}
	}
	
	private void setupButtons(int btfontsize,int btwidth,int btwidthsmall,int btheight,boolean showbackbutton){
		TextureAtlas levelmenubuttonAtlas = new TextureAtlas("buttons/levelmenubutton.pack");
		TextureAtlas playbuttonAtlas = new TextureAtlas("buttons/playbutton.pack");
		TextureAtlas stopbuttonAtlas = new TextureAtlas("buttons/stopbutton.pack");
		TextureAtlas backbuttonAtlas = new TextureAtlas("buttons/backbutton.pack");
		TextureAtlas homebuttonAtlas = new TextureAtlas("buttons/homebutton.pack");
		TextureAtlas scorebuttonAtlas = new TextureAtlas("buttons/hscore.pack");
		
		levelmenubutton = new myTextButton(levelmenubuttonAtlas,btfontsize,Color.WHITE).getButton();
		buttonstart = new myTextButton(playbuttonAtlas,btfontsize,Color.WHITE).getButton();
		buttonreset = new myTextButton(stopbuttonAtlas,btfontsize,Color.WHITE).getButton();
		buttonback = new myTextButton(backbuttonAtlas,btfontsize,Color.WHITE).getButton();
		menubutton = new myTextButton(homebuttonAtlas,btfontsize,Color.WHITE).getButton();
		highscorebutton = new myTextButton(scorebuttonAtlas,btfontsize,Color.WHITE).getButton();
		levelmenubutton.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonstart.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonback.setSize(btwidthsmall/2,btwidthsmall/2);
		menubutton.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonreset.setSize(btwidthsmall/2,btwidthsmall/2);
		highscorebutton.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonstart.setPosition(width-(8.5f*width/50),height-(2.7f*height/50));
		buttonback.setPosition(width-(5.3f*width/50),height-(2.7f*height/50));
		buttonreset.setPosition(width-(2.1f*width/50),height-(2.7f*height/50));
		levelmenubutton.setPosition(width-(2.1f*width/50),(0.2f*height/50));
		menubutton.setPosition(width-(8.5f*width/50),(0.2f*height/50));
		highscorebutton.setPosition(width-(5.3f*width/50),(0.2f*height/50));
		if (showbackbutton){
			stage.addActor(buttonback);
		}
	}
	public LevelEditorScreen(BallZi ballzi,LevelData data,MySounds sounds) {
		this.sounds=sounds;
		try{
			sounds.stopMenuMusicLoop();
			}catch(Exception e){}
		try{
		sounds.stopGameMusicLoop();
		}catch(Exception e){}
		sounds.playGameMusicLoop();
		this.ballzi = ballzi;
		buildeditscreen();
		boxes = data.getBoxes();
		forceboxes = data.getForceboxes();
		magnetboxes = data.getMagnetboxes();
		crossboxes = data.getCrossboxes();
		pushboxes = data.getPushboxes();
		finishboxes = data.getFinishboxes();
		balls = data.getBalls();
		stars = data.getStars();
		abyssboxes = data.getAbyssboxes();
		abyssboxesR = data.getAbyssboxesR();
	}
	
	
	public LevelEditorScreen(BallZi ballzi,MySounds sounds) {
		super();
		try{
			sounds.stopMenuMusicLoop();
			}catch(Exception e){}
		try{
		sounds.stopGameMusicLoop();
		}catch(Exception e){}
		sounds.playGameMusicLoop();
		this.sounds =sounds;
		this.ballzi = ballzi;
		buildeditscreen();
	}

	private void buildeditscreen(){
		level = new Level();
		world = new World(new Vector2(0, 0), true);
		ball = new Ball((width/2)-(width/6.5f), (height/2)-(9*(height/40)),world,itemsize_small,sounds);
		abyssbox = new AbyssBox((width/2)-(width/11), (height/2)-(9*(height/40)), itemsize_small, itemsize_small, Color.BLACK,0);
		finishbox = new FinishBox((width/2)-(width/6.5f), (height/2)-(15*(height/40)), itemsize_medium, itemsize_medium,0);
		abyssboxR = new AbyssBox((width/2)-(width/24), (height/2)-(9*(height/40)), itemsize_medium, itemsize_medium, Color.BLACK,0);
		pushbox = new PushBox((width/2)-(width/11), (height/2)-(15*(height/40)), world, itemsize_medium, Color.YELLOW,90);
		star = new Star((width/2)-(width/24), (height/2)-(15*(height/40)), itemsize_medium, itemsize_medium);
		
		collidebox = new CollidableBox((width/2)-(width/12), (height/2)-(23*(height/40)), world, itemsize_medium, itemsize_medium, Color.YELLOW,0);
		magnetbox = new MagnetBox((width/2)-(width/12), (height/2)-(27*(height/40)), itemsize_small, itemsize_small,new MagnetField(0,ball));
		forcebox = new ForceBox((width/2)-(width/12), (height/2)-(32*(height/40)), itemsize_small, itemsize_small,new forceField(180,itemsize_medium));
		spinbox = new SpinningCrossBox((width/2)-(width/12), (height/2)-(36*(height/40)),world, itemsize_medium, itemsize_medium, Color.ORANGE,0);
		
		stars_fixed = new Vector<Star>();
		creation();
		playable=false;
		skin = new Skin();
		skin.add("knob", new Texture(Gdx.files.internal("slider/sliderknob.png")));
		skin.add("bgs", new Texture(Gdx.files.internal("slider/sliderbg.png")));

		Slider.SliderStyle sliderstyle = new Slider.SliderStyle();
		sliderstyle.background = skin.getDrawable("bgs");
		sliderstyle.knob = skin.getDrawable("knob");
		
		forceslider = new Slider(0, 20, 1, true, sliderstyle);
		forceslider.setVisible(true);
		forceslider.setValue(2);
		forceslider.setHeight(itemsize_medium);
		forceslider.setPosition((width)-(width/7), (height)-(34*(height/40)));
		forceLabel = new myLabel(""+(int)forceslider.getValue(), (int)(itemsize_small/1.5),Color.BLACK).getMyLabel();
		forceLabel.setPosition((width)-(width/20), (height)-(34*(height/40)));
		
		
		magnetslider = new Slider(0, 20, 1, true, sliderstyle);
		magnetslider.setVisible(true);
		magnetslider.setValue(2);
		magnetslider.setHeight(itemsize_medium);
		magnetslider.setPosition((width)-(width/7), (height)-(29*(height/40)));
		magnetLabel = new myLabel(""+(int)magnetslider.getValue(), (int)(itemsize_small/1.5),Color.BLACK).getMyLabel();
		magnetLabel.setPosition((width)-(width/20), (height)-(29*(height/40)));

		boxslider = new Slider(0, 20, 1, true, sliderstyle);
		boxslider.setVisible(true);
		boxslider.setValue(2);
		boxslider.setHeight(itemsize_medium);
		boxslider.setPosition((width)-(width/7), (height)-(25*(height/40)));
		boxLabel = new myLabel(""+(int)boxslider.getValue(), (int)(itemsize_small/1.5),Color.BLACK).getMyLabel();
		boxLabel.setPosition((width)-(width/20), (height)-(25*(height/40)));

		crossboxslider = new Slider(0, 20, 1, true, sliderstyle);
		crossboxslider.setVisible(true);
		crossboxslider.setValue(2);
		crossboxslider.setHeight(itemsize_medium);
		crossboxslider.setPosition((width)-(width/7), (height)-(38*(height/40)));
		crossboxLabel = new myLabel(""+(int)crossboxslider.getValue(), (int)(itemsize_small/1.5),Color.BLACK).getMyLabel();
		crossboxLabel.setPosition((width) - (width / 20), (height)
				- (38 * (height / 40)));

		

		resolution = (int) width + "x" + (int) height;
		int btfontsize = 0;
		int btwidth=0;
		int btheight=0;
		int btwidthsmall=0;
		
		if (resolution.equals("800x600")) {
			btfontsize=10;
			btwidth=58;
			btwidthsmall=50;
			btheight=15;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		} else if (resolution.equals("1024x768")) {
			btfontsize=11;
			btwidth=80;
			btwidthsmall=60;
			btheight=20;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		} else if (resolution.equals("1280x720")) {
			btfontsize=12;
			btwidth=100;
			btwidthsmall=76;
			btheight=25;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		} else if (resolution.equals("1280x800")) {
			btfontsize=12;
			btwidth=100;
			btwidthsmall=76;
			btheight=25;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		}else if (resolution.equals("1920x1080")) {
			btfontsize=16;
			btwidth=120;
			btwidthsmall=100;
			btheight=35;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		}
		else if (resolution.equals("1920x1200")) {
			btfontsize=16;
			btwidth=120;
			btwidthsmall=100;
			btheight=35;
			setupButtons2(btfontsize,btwidth,btwidthsmall,btheight);
		}

		stage.addActor(buttonplay);
		stage.addActor(buttonclear);
		stage.addActor(savebutton );
		stage.addActor(buttonloadbackground);
		stage.addActor(levelmenubutton);
		stage.addActor(menubutton );
		stage.addActor(textfield );
		stage.addActor(nameLabel );
		stage.addActor(magnetLabel );
		stage.addActor(boxLabel );
		stage.addActor(crossboxLabel );
		stage.addActor(forceLabel );
		stage.addActor(boxslider );
		stage.addActor(magnetslider );
		stage.addActor(forceslider );
		stage.addActor(crossboxslider );
		
		
		menubutton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	ballzi.setScreen(new StartScreen(ballzi));
            	
            	}});
		buttonplay.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	play();
            	}});
		buttonclear.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	removepaintedItems();
            	}});
		buttonloadbackground.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	loadBackgroundImage();
            	
            	}});
		savebutton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	save();
            	
            	}});
		forceslider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	forceLabel.setText(""+(int)forceslider.getValue());
            	
            	}});
		boxslider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	boxLabel.setText(""+(int)boxslider.getValue());
            	
            	}});
		crossboxslider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	crossboxLabel.setText(""+(int)crossboxslider.getValue());
            	
            	}});
		magnetslider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	magnetLabel.setText(""+(int)magnetslider.getValue());
            	
            	}});
		levelmenubutton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	sounds.playPushButtonSound();
            	ballzi.setScreen(new LevelSelectScreen(ballzi,sounds));
            	
            	}});
	}
	
	
	
	private void setupButtons2(int btfontsize,int btwidth,int btwidthsmall,int btheight){
		
		
		TextureAtlas levelmenubuttonAtlas = new TextureAtlas("buttons/levelmenubutton.pack");
		TextureAtlas playbuttonAtlas = new TextureAtlas("buttons/playbutton.pack");
		TextureAtlas clearbuttonAtlas = new TextureAtlas("buttons/clearbutton.pack");
		TextureAtlas savebuttonAtlas = new TextureAtlas("buttons/savebutton.pack");
		TextureAtlas homebuttonAtlas = new TextureAtlas("buttons/homebutton.pack");
		TextureAtlas loadbuttonAtlas = new TextureAtlas("buttons/loadbutton.pack");
		
		TextureAtlas atlas = new TextureAtlas("buttons/textfield.pack");
		FileHandle fontFile = Gdx.files.internal("fonts/PTC55F.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        BitmapFont mBitmapFont = generator.generateFont(btfontsize);
        generator.dispose();
       
		Skin textfieldskin = new Skin(atlas);
		TextFieldStyle style = new TextFieldStyle();
		style.fontColor = Color.BLACK;
		style.font = mBitmapFont;
		style.background = textfieldskin.getDrawable("textfield.1");
		style.cursor= textfieldskin.getDrawable("textfield.2");
		
		textfield = new TextField("",style);
		textfield .setMessageText("mylevel");
		textfield.setBlinkTime(0.5f);
		textfield.setWidth(btwidth*1.3f);
		textfield.setHeight(btheight);
		
		
		menubutton = new myTextButton(homebuttonAtlas,(int)(itemsize_small/2),Color.WHITE).getButton();
		buttonplay = new myTextButton(playbuttonAtlas,btfontsize,Color.WHITE).getButton();
		buttonclear = new myTextButton(clearbuttonAtlas,btfontsize,Color.WHITE).getButton();
		buttonloadbackground = new myTextButton(loadbuttonAtlas,btfontsize,Color.WHITE).getButton(); 
		savebutton = new myTextButton(savebuttonAtlas,btfontsize,Color.WHITE).getButton(); 
		levelmenubutton = new myTextButton(levelmenubuttonAtlas,btfontsize,Color.WHITE).getButton();
		
		nameLabel = new myLabel("name:", (int)(btfontsize*1.5),Color.BLACK).getMyLabel();
		
		
		buttonplay.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonclear.setSize(btwidthsmall/2,btwidthsmall/2);
		buttonloadbackground.setSize(btwidthsmall/2,btwidthsmall/2);
		savebutton.setSize(btwidthsmall/2,btwidthsmall/2);
		levelmenubutton.setSize(btwidthsmall/2,btwidthsmall/2);
		menubutton.setSize(btwidthsmall/2,btwidthsmall/2);
		
		textfield.setPosition(width-(5.3f*width/50),height-(6*height/50));
		nameLabel.setPosition(width-(8.5f*width/50),height-(6*height/50));
		buttonplay.setPosition(width-(8.5f*width/50),height-(2.7f*height/50));
		buttonclear.setPosition(width-(5.3f*width/50),height-(2.7f*height/50));
		savebutton.setPosition(width-(2.1f*width/50),height-(2.7f*height/50));
		
		buttonloadbackground.setPosition(width-(8.5f*width/50),0.2f*height/50);
		levelmenubutton.setPosition(width-(5.3f*width/50),0.2f*height/50);
		menubutton.setPosition(width-(2.1f*width/50),0.2f*height/50);
		
	}
	private void save(){
		buildlevel();
		SaveLevels save = new SaveLevels();
		LoadLevels ll= new LoadLevels();
		Levels lev;
		try{
			lev = ll.load("bin/levels/");
		}catch(Exception e){
			lev = new Levels();
		}
		lev.addLevel(level);
		save.save(lev);
		collectedstars=0;
		LoadLevelProperties load = new LoadLevelProperties();
		configmap = load.getConfig();
		configmap.put(level.getName()+"STARSCOLLECTED", collectedstars);
		saveconfig.save(configmap);
	}
	private void buildlevel(){
		Vector<ItemData> balldata = new Vector<ItemData>();
		for (Ball bal:balls){
			balldata.add(bal.getItemData());
			//bal.deleteBody();
		}
		level.setBalls_fixed(balldata);
		
		Vector<ItemData> collideboxdata = new Vector<ItemData>();
		for (CollidableBox box:boxes){
			collideboxdata.add(box.getItemData());
			//box.deleteBody();
		}
		level.setBoxes_fixed(collideboxdata);
		
		Vector<ItemData> pushboxdata = new Vector<ItemData>();
		for (PushBox box:pushboxes){
			pushboxdata.add(box.getItemData());
			//box.deleteBody();
		}
		level.setPushboxes_fixed(pushboxdata);
		
		Vector<ItemData> crossboxdata = new Vector<ItemData>();
		for (SpinningCrossBox box:crossboxes){
			crossboxdata.add(box.getItemData());
			//box.deleteBody();
		}
		level.setCrossboxes_fixed(crossboxdata);
		
		Vector<ItemData> abyssboxdata = new Vector<ItemData>();
		for (AbyssBox box:abyssboxes){
			abyssboxdata.add(box.getItemData());
		}
		level.setAbyssboxes_fixed(abyssboxdata);
		
		Vector<ItemData> abyssboxRdata = new Vector<ItemData>();
		for (AbyssBox box:abyssboxesR){
			abyssboxRdata.add(box.getItemData());
		}
		level.setAbyssboxesR_fixed(abyssboxRdata);
		
		Vector<ItemData> finishboxdata = new Vector<ItemData>();
		for (FinishBox box:finishboxes){
			finishboxdata.add(box.getItemData());
		}
		level.setFinishboxes_fixed(finishboxdata);
		
		Vector<ItemData> forceboxdata = new Vector<ItemData>();
		for (ForceBox box:forceboxes){
			forceboxdata.add(box.getItemData());
		}
		level.setForceboxes_fixed(forceboxdata);
		
		Vector<ItemData> magnetboxdata = new Vector<ItemData>();
		for (MagnetBox box:magnetboxes){
			magnetboxdata.add(box.getItemData());
		}
		level.setMagnetboxes_fixed(magnetboxdata);
		
		Vector<ItemData> starsdata = new Vector<ItemData>();
		for (Star box:stars){
			starsdata.add(box.getItemData());
		}
		level.setStars(starsdata);
		
		level.setName(textfield.getText());
		if (textfield.getText().length()==0){
			level.setName("level"+System.currentTimeMillis());
		}
		level.setBackground_path(backgroundtexpath);
		level.setMaxBoxes((int)boxslider.getValue());
		level.setMaxCrossBoxes((int)crossboxslider.getValue());
		level.setMaxForceBoxes((int)forceslider.getValue());
		level.setMaxMagnetBoxes((int)magnetslider.getValue());
		level.setSaveresolutionwidht((int)width);
		level.setSaveresolutionheight((int)height);
	}
	
	private void play(){
		buildlevel();
		ballzi.setScreen(new LevelEditorScreen(ballzi,level,true,sounds));
	}
	
	private void backtoedit(){
		startpushed=false;
		pushed = false;
		for(PushBox pb:pushboxes_fixed){
			pb.reset();
		}
		
		for (Ball bal:balls_fixed){
			bal.setPosition(bal.getStartX(), bal.getStartY());
		}
		for (Star star : stars_fixed) {
			star.show();
		}
		LevelData data = new LevelData();
		data.setBoxes(boxes_fixed);
		data.setForceboxes(forceboxes_fixed);
		data.setMagnetboxes(magnetboxes_fixed);
		data.setCrossboxes(crossboxes_fixed);
		data.setPushboxes(pushboxes_fixed);
		data.setFinishboxes(finishboxes_fixed);
		data.setBalls(balls_fixed);
		data.setStars(stars_fixed);
		data.setAbyssboxes(abyssboxes_fixed);
		data.setAbyssboxes(abyssboxesR_fixed);
		ballzi.setScreen(new LevelEditorScreen(ballzi,data,sounds));
	}
	private void creation(){
		timer = new LevelTimer();
		
		batch = new SpriteBatch();
		edgesprite1 = new CollidableBox(width / 3.2f, 0, world, 10,height, Color.BLUE,0);
		
		edgesprite2 = new CollidableBox(-width / 2, 0, world, 10,height, Color.RED,0);
		
		edgesprite3 = new CollidableBox(0, height / 2, world,width, 10, Color.BLACK,0);
		
		edgesprite4 = new CollidableBox(0, (height/-2), world, width, 10,Color.YELLOW,0);

		
		texturebox = new Texture(Gdx.files.internal("textures/box.png"));
		sprite_mouse = new Sprite(texturebox);
		sprite_mouse.setColor(Color.BLUE);
		sprite_mouse.setSize(0f, 0f);

		texture = new Texture(Gdx.files.internal("textures/box.png"));
		bgtexture = new Texture(Gdx.files.internal("textures/main-screen.png"));
		sprite_backgroundplayground = new Sprite(texture);
		sprite_backgrounditems = new Sprite(bgtexture);
		
		sprite_backgroundplayground.setSize(width-(width/5)+(width/300), height-(height/110));
		sprite_backgrounditems.setSize(width,height);
		
		sprite_backgroundplayground.setPosition((height/120), (height/220));
		sprite_backgrounditems.setPosition(0, 0);
	
		int t = Math.round(height/gridsize);
		int z = Math.round(width/gridsize);
		
		
		for (int a = 0;a<=t;a++){
			if (a>=del_grid_rows){
			gridbounds.add(new Vector<Polygon>());
			for (int b = 0;b<=z;b++){
				gridbounds.get(a-del_grid_rows).add(new Polygon(vertices2));
				gridbounds.get(a-del_grid_rows).get(b).setPosition((b*gridsize), (a*gridsize));
			}}
		}
		

		headerLabel = new myLabel("",(int)itemsize_medium,Color.BLACK).getMyLabel();
	    table1 = new Table(new Skin());
        table1.setFillParent(true);
        table1.center().top();
        table1.add(headerLabel);
        table1.debug();
        
        stage.addActor(table1);
		bounds = new Polygon(vertices);
		//cam = new OrthographicCamera(width,
		//		height);
		//renderer = new ShapeRenderer();
	}


	public void update(float dt) {
		if (startpushed){
		accumulator += dt;
		while (accumulator > BOX_STEP) {
			world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS,
					BOX_POSITION_ITERATIONS);
			accumulator -= BOX_STEP;
		}
		}

	}
	
	/*
	private void disableButtons(){
		buttonstart.setDisabled(true);
		buttonstart.setColor(1, 1, 1, 0.8f);
		buttonreset.setDisabled(true);
		buttonreset.setColor(1, 1, 1, 0.8f);
		buttonback.setDisabled(true);
		buttonback.setColor(1, 1, 1, 0.8f);
		levelmenubutton.setDisabled(true);
		levelmenubutton.setColor(1, 1, 1, 0.8f);
		menubutton.setDisabled(true);
		menubutton.setColor(1, 1, 1, 0.8f);
	}
	
	private void enableButtons(){
		buttonstart.setDisabled(false);
		buttonstart.setColor(1, 1, 1, 0);
		buttonreset.setDisabled(false);
		buttonreset.setColor(1, 1, 1, 0);
		buttonback.setDisabled(false);
		buttonback.setColor(1, 1, 1, 0);
		levelmenubutton.setDisabled(false);
		levelmenubutton.setColor(1, 1, 1, 0);
		menubutton.setDisabled(false);
		menubutton.setColor(1, 1, 1, 0);
	}
	*/
	
	private void playCollideSound(){
		sounds.playCollisionSound();
	}
	
	private void levelpassed(float delta){
		endscreen.setWon(true,collectedstars,timer.getSeconds(),level);
		levelover =true;
	}
	private void levelFailed(float delta){
		
		endscreen.setWon(false,collectedstars,timer.getSeconds(),level);
		levelover =true;
	}
	
	private float collideSoundTimer = 0;
	@Override
	public void render(float delta) {
		magnetsoundtimer+=delta;
		forcesoundtimer+=delta;
		collideSoundTimer+=delta;
		timertime += delta;
		keytime += delta;
		rotkeytime += delta;
		texttime += delta;
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (!playable){
			textfield.act(delta);
		}else{
			forceLabel.setText(""+maxForceBoxes);
			magnetLabel.setText(""+maxMagnetBoxes);
			crossboxLabel.setText(""+maxCrossBoxes);
			boxLabel.setText(""+maxBoxes);
			if (timertime>1){
				timertime=0;
				timeLabel.setText(timer.getTime());
			}
			
		}

		selectItem();
		rotateSelectedItem();
		createItem();
		moveSpriteBox();
		batch.begin();
		
		
		sprite_backgroundplayground.draw(batch);
		for (MagnetBox magnet : magnetboxes) {
			magnet.getanimSprite1().draw(batch);
			magnet.getanimSprite2().draw(batch);
			magnet.getanimSprite3().draw(batch);
			magnet.getanimSprite4().draw(batch);
		}
		for (MagnetBox magnet : magnetboxes_fixed) {
			magnet.getanimSprite1().draw(batch);
			magnet.getanimSprite2().draw(batch);
			magnet.getanimSprite3().draw(batch);
			magnet.getanimSprite4().draw(batch);
		}
		for (ForceBox force : forceboxes) {
			force.getAnimSprite().draw(batch);
		}
		for (ForceBox force : forceboxes_fixed) {
			force.getAnimSprite().draw(batch);
		}
		sprite_backgrounditems.draw(batch);
		drawpaintedItems();
		drawfixedItems();
		drawSelectionItems();
		//drawEdges();
		if (texttime > 2f) {
			headerLabel.setText("");
		}
		
		sprite_mouse.draw(batch);
		batch.end();
		/*
		for (Vector<Polygon> poly : gridbounds) {
			for (Polygon pol : poly) {
				renderer.begin(ShapeType.Line);
				renderer.setColor(0, 0, 0, 1);
				renderer.polygon(pol.getTransformedVertices());
				renderer.end();

			}
		}*/
		
		//render.render(world, cam.combined);
		
		collidebox.update();
		
		if (!playable){
			ball.update();
			finishbox.update();
		}
		spinbox.update();
		update(delta);
		if ((ballcount == 0||levelfailed)&&playable){
			if (passedfailedminimizer){
				passedfailedminimizer=false;
				
				if (!levelfailed) {
					levelpassed(delta);
					try {
						sounds.stopBallMoveLoop();
					} catch (Exception e) {
					}
				}
				if (levelfailed) {
					levelFailed(delta);
					try {
						sounds.stopBallMoveLoop();
					} catch (Exception e) {
					}
				}
			}
			endscreen.update(delta);
			stage = endscreen.getStage();
			
			Gdx.input.setInputProcessor(stage);
		}
		try {
			hcscreen.update(delta);
		}catch(Exception e){}
		stage.act(delta);
		stage.draw();
		/*
		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.GREEN);
		renderer.polygon(bounds.getTransformedVertices());
		renderer.end();
		*/
	}

	public void loadBackgroundImage(){
		try{
		LoadImage li = new LoadImage();
		backgroundtexpath=li.getImagepath();
		sprite_backgroundplayground.setTexture(new Texture(Gdx.files.local(li.getImagepath())));
		}catch(Exception e){
			
		}
		
	}
	
	private void drawselectedBox(float width, float height,Texture tex,int selectionnumber){
		a = true;
		
		sprite_mouse.setSize(width, height);
		sprite_mouse.setTexture(tex);
		sprite_mouse.setOrigin(width/2, height/2);
		sprite_mouse.setRotation(degrees-90);
		selected = selectionnumber;
		keytime = 0;
		isoneselected = true;
	}

	private void selectItem() {
		if (!startpushed){
		if (forcebox
				.getForceField()
				.getBounds()
				.contains(Gdx.input.getX(),
						height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&maxForceBoxes>0) {
			drawselectedBox(itemsize_medium,itemsize_medium,forcebox.getSprite().getTexture(),2);
			degrees=90;
		}

		if (magnetbox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&maxMagnetBoxes>0) {
			drawselectedBox(itemsize_medium,itemsize_medium,magnetbox.getSprite().getTexture(),1);
			degrees=90;
		}
		if (collidebox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&maxBoxes>0) {
			drawselectedBox(itemsize_medium,itemsize_medium,collidebox.getSprite().getTexture(),3);
			degrees=90;
			
		}
		if (spinbox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&maxCrossBoxes>0) {
			drawselectedBox(itemsize_big,itemsize_big,spinbox.getTexture(),4);
			degrees=90;
			
		}
		if (!playable){
		if (ball.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_small,itemsize_small,ball.getTexture(),5);
			degrees=90;
		}

		if (pushbox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_medium,itemsize_medium,pushbox.getTexture(),6);
			degrees=90;
			
		}
		if (finishbox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_medium,itemsize_medium,finishbox.getTexture(),7);
			degrees=90;
			
		}
		if (star.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_medium,itemsize_medium,star.getTexture(),8);
			degrees=90;
			
		}
		if (abyssbox.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_small,itemsize_small,abyssbox.getTexture(),9);
			degrees=90;
		}
		if (abyssboxR.getBounds().contains(Gdx.input.getX(),
				height - Gdx.input.getY())
				&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			drawselectedBox(itemsize_medium,itemsize_medium,abyssboxR.getTexture(),10);
			degrees=90;
		}
		}
		}
	}
	private void removepaintedItems(){
		for (CollidableBox cbox : boxes) {
			cbox.deleteBody();
		}
		for (SpinningCrossBox cross : crossboxes) {
			cross.deleteBody();
		}
			
		for (Ball balla : balls) {
			balla.deleteBody();
		}
			
		for (PushBox pbox : pushboxes){ 
			pbox.deleteBody();
		}
		sprite_mouse.setPosition(-2000, -2000);
		forceboxes.removeAllElements();
		magnetboxes.removeAllElements();
		crossboxes.removeAllElements();
		boxes.removeAllElements();
		pushboxes.removeAllElements();
		finishboxes.removeAllElements();
		balls.removeAllElements();
		stars.removeAllElements();
		abyssboxes.removeAllElements();
		abyssboxesR.removeAllElements();
		sprite_backgroundplayground.setTexture(texture);
	}
	
	private float calcDirection(float x, float y, Ball ball){
		float a = (float)Math.atan(((x)-ball.getSprite().getX())/((y)-ball.getSprite().getY()));
		a=  (a* (float)(180 / Math.PI));
		if ((x)<ball.getSprite().getX()&&a<0){
			a+=180f;
		}else if((x)>ball.getSprite().getX()&&a>=0){
			a+=180f;
		}else if((x)>ball.getSprite().getX()&&a<0){
			a+=360f;
		}

		return -a;
	
	}
	
	private void drawpaintedItems(){
		int f = 0;
		int fo = 0;
		boolean del = false;
		for (CollidableBox cbox : boxes) {
			cbox.update();
			for (Ball bal : balls_fixed) {
				if (cbox.getCBounds().contains(bal.getFront().x,bal.getFront().y)&& collideSoundTimer > 0.3f&&!levelover) {
					collideSoundTimer = 0f;
					playCollideSound();
				}
			}
			if (keytime>0.2f&&cbox.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&!startpushed) {
				fo = f;
				del = true;
				cbox.deleteBody();
				degrees=(int)cbox.getDirection();
				drawselectedBox(itemsize_medium,itemsize_medium,cbox.getSprite().getTexture(),3);
			}
			cbox.getSprite().draw(batch);
			f++;
		}
		if (del){
			boxes.remove(fo);
			del=false;
			maxBoxes++;
		}
		f = 0;
		fo = 0;
		del = false;
		for (ForceBox force : forceboxes) {
			force.update();
			for(Ball bal:balls_fixed){
				if (force.getBounds().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.force(force.getForceField().getDirection());
					if (forcesoundtimer>1f){
						sounds.playForcefieldSound();
						forcesoundtimer=0;
					}
				}
			}
			if (keytime>0.2f&&force.getForceField().getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&!startpushed) {
				fo = f;
				del = true;
				degrees=(int)force.getDirection();
				drawselectedBox(itemsize_medium,itemsize_medium,forcebox.getSprite().getTexture(),2);
			}
			
			force.getSprite().draw(batch);
			f++;
		}
		if (del){
			forceboxes.remove(fo);
			del=false;
			maxForceBoxes++;
		}
		f = 0;
		fo = 0;
		del = false;
		for (MagnetBox magnet : magnetboxes) {
			for(Ball bal:balls_fixed){
				boolean t=false;
				if (magnet.getVertBounds1().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if (magnet.getVertBounds2().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if (magnet.getHoriBounds1().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if (magnet.getHoriBounds2().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if(!t)
					bal.resetDamping();
			}
			if (keytime>0.2f&&magnet.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&!startpushed) {
				fo = f;
				del = true;
				degrees=(int)magnet.getDirection();
				drawselectedBox(itemsize_medium,itemsize_medium,magnet.getSprite().getTexture(),1);
			}
			magnet.getSprite().draw(batch);
			magnet.updateAnim(Gdx.graphics.getDeltaTime());
			f++;
			magnet.update();
		}
		if (del){
			magnetboxes.remove(fo);
			del=false;
			maxMagnetBoxes++;
		}
		f = 0;
		fo = 0;
		del = false;
			
		for (SpinningCrossBox cross : crossboxes) {
			if (playable){
				cross.update();
			}else{
				cross.updateset();
			}
			
			if (keytime>0.2f&&cross.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&!startpushed) {
				fo = f;
				del = true;
				degrees=(int)cross.getDirection();
				cross.deleteBody();
				drawselectedBox(itemsize_big,itemsize_big,cross.getSprite().getTexture(),4);
			}
			
			if (keytime > 0.2 && cross.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
				cross.toggleDirection();
				keytime = 0;
			}
			for (Ball ball:balls_fixed){
				if (cross.getCrossBoundsNorth().contains(ball.getFront().x, ball.getFront().y) ||
						cross.getCrossBoundsEast().contains(ball.getFront().x, ball.getFront().y) ||
						cross.getCrossBoundsSouth().contains(ball.getFront().x, ball.getFront().y) ||
						cross.getCrossBoundsWest().contains(ball.getFront().x, ball.getFront().y)){
					ball.impulse(calcDirection(cross.getMiddlepointX(),cross.getMiddlepointY(),ball)+90,cross.getRotation()*itemsize_small*2000);
				}
			}
			cross.getSprite().draw(batch);
			f++;
		}
			
		if (del){
			crossboxes.remove(fo);
			del=false;
			maxCrossBoxes++;
		}
		f = 0;
		fo = 0;
		del = false;
			
		for (Ball balla : balls) {
			if (keytime>0.2f&&balla.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				balla.deleteBody();
				degrees=90;
				drawselectedBox(itemsize_small,itemsize_small,balla.getSprite().getTexture(),5);
			}
			balla.getSprite().draw(batch);
			f++;
		}
		if (del){
			balls.remove(fo);
			del=false;
		}
		f = 0;
		fo = 0;
		del = false;
		
		
		for (PushBox pbox : pushboxes){ 
			pbox.updateset();
			if (keytime>0.2f&&pbox.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				pbox.deleteBody();
				degrees=(int)pbox.getDirection()-90;
				drawselectedBox(itemsize_medium,itemsize_medium,pushbox.getTexture(),6);
			}
			pbox.getSprite().draw(batch);
			f++;
		}
		if (del){
			pushboxes.remove(fo);
			del=false;
		}
		f = 0;
		fo = 0;
		del = false;
		
		for (FinishBox fbox : finishboxes){ 
			fbox.update();
			
			if (keytime>0.2f&&fbox.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				degrees=90;
				drawselectedBox(itemsize_medium,itemsize_medium,fbox.getSprite().getTexture(),7);
			}
			
			fbox.getSprite().draw(batch);
			f++;
		}
		if (del){
			finishboxes.remove(fo);
			del=false;
		}
		f = 0;
		fo = 0;
		del = false;
		
		for (AbyssBox abybox : abyssboxes){ 
			abybox.update();
			if (keytime>0.2f&&abybox.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				degrees=90;
				drawselectedBox(itemsize_small,itemsize_small,abybox.getSprite().getTexture(),9);
			}
			abybox.getSprite().draw(batch);
			f++;
		}
		if (del){
			abyssboxes.remove(fo);
			del=false;
		}
		f = 0;
		fo = 0;
		del = false;
		
		for (AbyssBox abybox : abyssboxesR){ 
			abybox.update();
			if (keytime>0.2f&&abybox.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				degrees=90;
				drawselectedBox(itemsize_medium,itemsize_medium,abybox.getSprite().getTexture(),10);
			}
			abybox.getSprite().draw(batch);
			f++;
		}
		if (del){
			abyssboxesR.remove(fo);
			del=false;
		}
		f = 0;
		fo = 0;
		del = false;
		
		for (Star star : stars) {
			if (star.getBounds().contains(ball.getFront().x, ball.getFront().y)&&playable) {
				star.delete();
				collectedstars+=1;
			}
			star.update();
			if (keytime>0.2f&&star.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				fo = f;
				del = true;
				degrees=90;
				drawselectedBox(itemsize_medium,itemsize_medium,star.getSprite().getTexture(),8);
			}
			star.getSprite().draw(batch);
			f++;
			
		}
		if (del){
			stars.remove(fo);
			del=false;
		}
		
	}
	boolean out = true;
	private void drawfixedItems(){
		for (CollidableBox cbox : boxes_fixed) {
			cbox.update();
			cbox.getSprite().draw(batch);
			for (Ball bal : balls_fixed) {
				if (cbox.getCBounds().contains(bal.getFront().x,bal.getFront().y)&& collideSoundTimer > 0.3f&&!levelover) {
					collideSoundTimer = 0f;
					playCollideSound();
				}
			}
		}
		
		for (ForceBox force : forceboxes_fixed) {
			force.update();
			for(Ball bal:balls_fixed){
				if (force.getBounds().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.force(force.getForceField().getDirection());
					if (forcesoundtimer>1f){
						sounds.playForcefieldSound();
						forcesoundtimer=0;
					}
				}
			}
			force.getSprite().draw(batch);
		}
			
		for (MagnetBox magnet : magnetboxes_fixed) {
			magnet.update();
			for(Ball bal:balls_fixed){
				boolean t=false;
				if (magnet.getVertBounds1().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				
				if (magnet.getVertBounds2().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if (magnet.getHoriBounds1().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if (magnet.getHoriBounds2().contains(bal.getFront().x, bal.getFront().y)&&playable&&startpushed) {
					bal.forceMagnet(magnet.getMagnetField().getDirection(bal));
					if (magnetsoundtimer>1.7f){
						sounds.playMagnetfieldSound();
						magnetsoundtimer=0;
					}
					t=true;
				}
				if(!t)
					bal.resetDamping();
			}
			magnet.getSprite().draw(batch);
			magnet.updateAnim(Gdx.graphics.getDeltaTime());
		}
			
		for (SpinningCrossBox cross : crossboxes_fixed) {
			if (playable){
				cross.update();
				if (keytime > 0.2 && cross.getBounds().contains(Gdx.input.getX(),height - Gdx.input.getY())&& Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
					cross.toggleDirection();
					keytime = 0;
				}
				for (Ball ball:balls_fixed){
					if (cross.getCrossBoundsNorth().contains(ball.getFront().x, ball.getFront().y) ||
							cross.getCrossBoundsEast().contains(ball.getFront().x, ball.getFront().y) ||
							cross.getCrossBoundsSouth().contains(ball.getFront().x, ball.getFront().y) ||
							cross.getCrossBoundsWest().contains(ball.getFront().x, ball.getFront().y)){
						System.out.println("hit the ball");
						ball.impulse(calcDirection(cross.getMiddlepointX(),cross.getMiddlepointY(),ball)+90,itemsize_small*1000);
					}
				}
			}else{
				cross.updateset();
			}
			cross.getSprite().draw(batch);
		}
			
		for (Ball bal : balls_fixed) {
			bal.update();
			if (edgesprite1.getCBounds().contains(bal.getFront().x,
					bal.getFront().y)
					&& collideSoundTimer > 0.3f && !levelover) {
				collideSoundTimer = 0f;
				playCollideSound();
			}
			if (edgesprite2.getCBounds().contains(bal.getFront().x,
					bal.getFront().y)
					&& collideSoundTimer > 0.3f && !levelover) {
				collideSoundTimer = 0f;
				playCollideSound();
			}
			if (edgesprite3.getCBounds().contains(bal.getFront().x,
					bal.getFront().y)
					&& collideSoundTimer > 0.3f && !levelover) {
				collideSoundTimer = 0f;
				playCollideSound();
			}
			if (edgesprite4.getCBounds().contains(bal.getFront().x,
					bal.getFront().y)
					&& collideSoundTimer > 0.3f && !levelover) {
				collideSoundTimer = 0f;
				playCollideSound();
			}
			if (!bal.isrunnig() && startpushed && waitingtimer > 3f) {
				levelfailed = true;
			}

			bal.getSprite().draw(batch);
		}
		
		
		for (PushBox pb : pushboxes_fixed) {
			if (playable && startpushed) {
				pb.update();
				for (Ball bal : balls_fixed) {
					if (!pushed&& pb.getFrontBounds().contains(bal.getFront().x,bal.getFront().y)) {
						bal.impulse(pb.getDirection() - 90, 500000);
					}
					if (pb.getCBounds().contains(bal.getFront().x,
							bal.getFront().y)
							&& collideSoundTimer > 0.3f && !levelover&&waitingtimer>2f) {
						collideSoundTimer = 0f;
						playCollideSound();
					}
				}
				if (!pb.isActive()) {
					// pushed = true;
				}
			}else{
				pb.updateset();
			}
			waitingtimer += Gdx.graphics.getDeltaTime();
			pb.getSprite().draw(batch);
		}
		
		
		
		int f = 0;
		int fo = 0;
		boolean del = false;
		
		for (FinishBox fbox : finishboxes_fixed){ 
			fbox.update();
			f = 0;
			fo = 0;
			del = false;
			for(Ball bal:balls_fixed){
				if (fbox.getBounds().contains(bal.getFront().x, bal.getFront().y)) {
					sounds.playFallinFinishSound();
					texttime=0;
					keytime=0;
					configmap.put(level.getName()+"STARSCOLLECTED", collectedstars);
					saveconfig.save(configmap);
					ballcount--;
					fo = f;
					del=true;
				}
				f++;
			}
			if (del){
			balls_fixed.remove(fo);
			del=false;
			}
			
			fbox.getSprite().draw(batch);
		}
		
		
		for (AbyssBox abybox : abyssboxes_fixed) {
			abybox.update();
			f = 0;
			fo = 0;
			del = false;
			for (Ball bal : balls_fixed) {
				if (abybox.getBounds().contains(bal.getFront().x,
						bal.getFront().y)) {
					sounds.playFallinHoleSound();
					levelfailed=true;
					texttime = 0;
					keytime = 0;
					ballcount--;
					fo = f;
					del=true;
				}
				f++;
			}
			if (del){
				balls_fixed.remove(fo);
				del=false;
			}
		}
		
		
		for (AbyssBox abybox : abyssboxesR_fixed) {
			abybox.update();
			f = 0;
		fo = 0;
		del = false;
			for (Ball bal : balls_fixed) {
				if (abybox.getBounds().contains(bal.getFront().x,
						bal.getFront().y)) {
					sounds.playFallinHoleSound();
					levelfailed=true;
					texttime = 0;
					keytime = 0;
					ballcount--;
					fo = f;
					del=true;
				}
				f++;
			}
			if (del){
			balls_fixed.remove(fo);
			del=false;
		}
			abybox.getSprite().draw(batch);
		}
		
		for (Star star : stars_fixed) {
			for (Ball bal : balls_fixed) {
				if (star.getBounds().contains(bal.getFront().x,
						bal.getFront().y)
						&& playable) {
					star.delete();
					collectedstars += 1;
					sounds.playStarCollectedSound();
				}
			}
			star.update();
			star.getSprite().draw(batch);

		}
		
	}
	private void drawSelectionItems(){
		collidebox.getSprite().draw(batch);
		magnetbox.getSprite().draw(batch);
		forcebox.getSprite().draw(batch);
		spinbox.getSprite().draw(batch);
		
		
		if (!playable){
			finishbox.getSprite().draw(batch);
			ball.getSprite().draw(batch);
			pushbox.getSprite().draw(batch);
			star.getSprite().draw(batch);
			abyssbox.getSprite().draw(batch);
			abyssboxR.getSprite().draw(batch);
		}
		
	}
	//private void drawEdges(){
	//	edgesprite1.getSprite().draw(batch);
	//	edgesprite2.getSprite().draw(batch);
	//	edgesprite3.getSprite().draw(batch);
	//	edgesprite4.getSprite().draw(batch);
	//}
	
	
	private void createItem(){
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&isoneselected&& keytime > 0.2f) {
			sounds.playPlaceItemSound();
			pressed = true;
			if (bounds.contains(Gdx.input.getX(),
					height - Gdx.input.getY())){
				is_on_playground=false;
			}else{
				is_on_playground=true;
			}
			keytime = 0;
		}
			
		 if (pressed &&is_on_playground) {
			if (a && selected == 1) {
				/*float w = 0;
				float h = 0;
				for (Vector<Polygon> poly:gridbounds){
					for (Polygon pol:poly){
				    	
						if (pol.contains(Gdx.input.getX(), height - Gdx.input.getY())){
							w=pol.getX()-(width/2)+(gridsize/2);
							h=pol.getY()-(height/2)+(gridsize/2);
							 
						}
					}
				}
				magnetboxes.add(new MagnetBox(w,h,itemsize_small, itemsize_small, new MagnetField(degrees,ball)));
				*/
				magnetboxes.add(new MagnetBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),itemsize_small, itemsize_small, new MagnetField(degrees,ball)));
				maxMagnetBoxes--;
			}
			if (a && selected == 2) {
				forceboxes.add(new ForceBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						itemsize_small, itemsize_small, new forceField(degrees,itemsize_medium)));
				maxForceBoxes--;
			}
			if (a && selected == 3) {
				boxes.add(new CollidableBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						world, itemsize_medium, itemsize_medium, Color.YELLOW,degrees));
				maxBoxes--;
			}
			if (a && selected == 4) {
				crossboxes.add(new SpinningCrossBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						world, itemsize_big, itemsize_big, Color.YELLOW,degrees));
				maxCrossBoxes--;
			}
			if (a && selected == 5) {
				balls.add(new Ball((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						world,itemsize_small,sounds));
				// a=false;
			}
			
			if (a && selected == 6) {
				pushboxes.add(new PushBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						 world,itemsize_medium, Color.YELLOW,degrees));
				// a=false;
			}
			if (a && selected == 7) {
				finishboxes.add((new FinishBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						itemsize_medium, itemsize_medium,degrees)));
				// a=false;
			}
			if (a && selected == 8) {
				stars.add((new Star((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						itemsize_medium, itemsize_medium)));
				// a=false;
			}
			
			if (a && selected == 10) {
				abyssboxesR.add((new AbyssBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						itemsize_medium, itemsize_medium, Color.BLACK,degrees)));
				// a=false;
			}
			sprite_mouse.setPosition(-2000, 2000);
			pressed = false;
			isoneselected =false;
			if (a && selected == 9) {
				abyssboxes.add((new AbyssBox((Gdx.input.getX() - (Gdx.graphics
						.getWidth() / 2)),
						((height / 2) - Gdx.input.getY()),
						itemsize_small, itemsize_small, Color.BLACK,degrees)));
				sprite_mouse.setPosition(-2000, 2000);
				pressed = false;
				isoneselected =true;
			}

			
		}else if(pressed && keytime > 0.2f&&!is_on_playground){
			sprite_mouse.setPosition(-2000, 2000);
			pressed = false;
			keytime = 0;
			isoneselected =false;
		}
	}
	
	private void rotateSelectedItem(){
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && rotkeytime > 0.1f) {
			
			degrees = (degrees+45) % 360;
			System.out.println(degrees);
			
			rotkeytime = 0;
			
			
			sprite_mouse.setRotation(degrees-90);
			
		}
	}

	public void moveSpriteBox(){
		if (isoneselected){
			sprite_mouse.setPosition(
					Gdx.input.getX() - (sprite_mouse.getWidth() / 2),
					height - Gdx.input.getY()
							- (sprite_mouse.getHeight() / 2));
			}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		//ball.setPosition(-2000, -2000);
		spinbox.setPosition(-2000, -2000);
		collidebox.setPosition(-2000, -2000);
		
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

	}
}

