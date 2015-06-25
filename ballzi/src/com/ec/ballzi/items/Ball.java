package com.ec.ballzi.items;

import java.io.Serializable;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ec.ballzi.data.ItemData;
import com.ec.ballzi.sound.MySounds;

public class Ball implements Serializable{

	private static final long serialVersionUID = 7992888515462955341L;

	private Body body;
	private static float height = Gdx.graphics.getHeight();
	private static float width = Gdx.graphics.getWidth();
	private Texture texture;
	private Sprite sprite;
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private Vector2 bounds = new Vector2(0,0);
	private World world;
    private Polygon bound;
    private float startX;
    private float startY;
	private float lastpositionX = 0;
	private float lastpositionY = 0;
	private ShapeRenderer renderer = new ShapeRenderer();
	private float x;
	private float y;
	private String resolution;
	private boolean running;
	private static float itemsize_small = Gdx.graphics.getWidth() / 40;
	private Circle cbounds;
	private MySounds sounds;
    private Vector<Float> XBuffer = new Vector<Float>();
    private Vector<Float> YBuffer = new Vector<Float>();
    
    
	public Ball(float startX, float startY, World world,float size,MySounds sounds) {
		this.world=world;
		this.startX=startX;
		this.startY=startY;
		this.sounds=sounds;
		texture = new Texture(Gdx.files.internal("textures/ball.png"));
		sprite = new Sprite(texture);
		sprite.setSize(size, size);
		sprite.setOrigin(size/2, size/2);
		CreateBody(world, new Vector2(startX, startY), 0f);
		MakeCircleFixture(BodyType.DynamicBody, 0.01f, 0.02f, 0f);
		sprite.setPosition(
				startX + (width / 2)
						- (sprite.getWidth() / 2),
				startY + (height / 2)
						- (sprite.getHeight() / 2));
		
		float[] vertices = {
				0	,0	, 
				0	,25, 
				25	,25,
				25, 0 };

		bound = new Polygon(vertices);
		bound.setOrigin(25, 25);
		bound.setPosition(startX + (width / 2)
				- 12.5f,startY + (height / 2)
				- 12.5f);
		cbounds = new Circle(0,0,itemsize_small/2);
			
		}

	
	public float getStartX() {
		return startX;
	}
	public float getStartY() {
		return startY;
	}
	public Texture getTexture(){
		return texture;
	}
	
	public ItemData getItemData(){
		ItemData d = new ItemData();
		d.setX(startX);
		d.setY(startY);
		d.setDirection(0);
		return d;
	}
	
	
	public Polygon getBounds(){
		return bound;
	}
	public void CreateBody(World world, Vector2 pos, float angle) {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(pos);
		bodyDef.angle = angle;
		bodyDef.linearDamping = 0.3f;
		
		body = world.createBody(bodyDef);
	}
	
	public void deleteBody(){
		world.destroyBody(body);
		sprite.setSize(0, 0);
	}

	void MakeCircleFixture(BodyDef.BodyType bodyType, float density,
			float restitution, float angle) {

		fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2.2f);
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef);
		shape.dispose();
	}

	public void setPosition(float x,float y){
		cbounds.setPosition(x,y);
		this.x=x;
		this.y=y;
		sprite.setPosition(
				x + (width / 2)
						- (sprite.getWidth() / 2),
				y + (height / 2)
						- (sprite.getHeight() / 2));
		//sprite.setPosition(x, y);
		 	Vector2 pos = new Vector2(sprite.getX()- (width / 2)
					+ (sprite.getWidth() / 2),sprite.getY()- (height / 2)
					+ (sprite.getHeight() / 2));
			bodyDef.position.set(pos );
			world.destroyBody(body);
		    body = world.createBody(bodyDef);
		   
		    MakeCircleFixture(BodyType.DynamicBody, 0.01f, 0.01f, 0f);
		
	}
	public Sprite getSprite() {
		return sprite;
	}
	public Circle getCBounds() {
		return cbounds;
	}
	public boolean isrunnig(){
		return running;
	}
	
	public void update() {
		//System.out.println(lastpositionX-body.getPosition().x);
		//System.err.println("DAMPING: "+body.getLinearDamping());
		try {
			if (Math.abs(lastpositionX - body.getPosition().x) > Math
					.abs(lastpositionY - body.getPosition().y)) {
				sounds.setBallSoundPitch(Math.abs(lastpositionX
						- body.getPosition().x) / 3f);
			} else {
				sounds.setBallSoundPitch(Math.abs(lastpositionY
						- body.getPosition().y) / 3f);
			}
		} catch (Exception e) {
		}
		if (Math.abs(lastpositionX-body.getPosition().x)<0.1f&&Math.abs(lastpositionY-body.getPosition().y)<0.1f){
			running=false;
		}else{
			running=true;
		}
		sprite.setPosition(
				body.getPosition().x + (width / 2)
						- (sprite.getWidth() / 2),
				body.getPosition().y + (height / 2)
						- (sprite.getHeight() / 2));
		
		lastpositionX=body.getPosition().x;
		lastpositionY=body.getPosition().y;
		
		XBuffer.insertElementAt(lastpositionX, 0);
		if (XBuffer.size()>120)
			XBuffer.removeElementAt(120);
		float a=-1000;
		boolean relevantchangex = false;
		for(Float f : XBuffer){
			if (a!=-1000){
				if (Math.abs(a-f)>0.02)
					relevantchangex=true;
			}
			a = f;
		}
		
		YBuffer.insertElementAt(lastpositionX, 0);
		if (YBuffer.size()>120)
			YBuffer.removeElementAt(120);
		a=-1000;
		boolean relevantchangey = false;
		for(Float f : YBuffer){
			if (a!=-1000){
				if (Math.abs(a-f)>0.02)
					relevantchangey=true;
			}
			a = f;
		}
		if (Math.abs(YBuffer.lastElement()-YBuffer.firstElement())>0.2){
			//System.err.print(":"+Math.abs(YBuffer.lastElement()-YBuffer.firstElement()));
			relevantchangey=true;
		}
		if (Math.abs(XBuffer.lastElement()-XBuffer.firstElement())>0.2){
			//System.err.print(":"+Math.abs(XBuffer.lastElement()-XBuffer.firstElement()));
			relevantchangex = true;
		}
		try {
			if (Math.abs(YBuffer.get(60) - YBuffer.firstElement()) > 0.1) {
				//System.err.print(":"+Math.abs(YBuffer.get(60)-YBuffer.firstElement()));
				relevantchangey = true;
			}
		} catch (Exception e) {
		}
		try {
			if (Math.abs(XBuffer.get(60) - XBuffer.firstElement()) > 0.1) {
				//System.err.print(":"+Math.abs(YBuffer.get(60)-YBuffer.firstElement()));
				relevantchangex = true;
			}
		} catch (Exception e) {
		}
		try {
			if (Math.abs(YBuffer.lastElement() - YBuffer.get(60)) > 0.1) {
				relevantchangey = true;
			}
		} catch (Exception e) {
		}
		try {
			if (Math.abs(XBuffer.lastElement() - XBuffer.get(60)) > 0.1) {
				relevantchangex = true;
			}
		} catch (Exception e) {
		}
		if (!relevantchangex && !relevantchangey) {
			running = false;
		}else{
			running=true;
		}
		
		//System.out.println("");
		//System.err.println(relevantchangex);
		//System.err.println(relevantchangey);
		//System.out.println(running);
		
		x=body.getPosition().x+(width/2);
		y=body.getPosition().y+(height/2);
		cbounds.setPosition(x,y);
		/*
		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.BLACK);
		renderer.circle(cbounds.x, cbounds.y, cbounds.radius);
		renderer.end();
		
		renderer .begin(ShapeType.Line);
		renderer.setColor(Color.GREEN);
		renderer.circle(getFront().x,getFront().y, 2);
		renderer.setColor(Color.BLACK);
		renderer.polygon(bound.getTransformedVertices());
		renderer.end();*/
	}
	public void force(float angle) {
		resolution = (int) width + "x" + (int) height;
		if (resolution.equals("800x600")){
			body.applyForce(new Vector2(0f,-950).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1024x768")){
			body.applyForce(new Vector2(0f,-2000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1280x720")){
			body.applyForce(new Vector2(0f,-4000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1280x800")){
			body.applyForce(new Vector2(0f,-4000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1920x1080")){
			body.applyForce(new Vector2(0f,-15000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1920x1200")){
			body.applyForce(new Vector2(0f,-16000).rotate(angle), body.getPosition(), true);
		}
		
		
	}
	
	boolean damping=true;
	public void resetDamping() {
		if (!damping){
			body.setLinearDamping(0.3f);
		    damping=true;
			}
	}
	public void forceMagnet(float angle) {
		if (damping){
			body.setLinearDamping(5f);
			damping=false;
			}
		resolution = (int) width + "x" + (int) height;
		if (resolution.equals("800x600")){
			body.applyForce(new Vector2(0f,-950).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1024x768")){
			body.applyForce(new Vector2(0f,-2000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1280x720")){
			body.applyForce(new Vector2(0f,-5000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1280x800")){
			body.applyForce(new Vector2(0f,-5000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1920x1080")){
			body.applyForce(new Vector2(0f,-15000).rotate(angle), body.getPosition(), true);
		}else if(resolution.equals("1920x1200")){
			body.applyForce(new Vector2(0f,-16000).rotate(angle), body.getPosition(), true);
		}
			
		    
	}
	public void impulse(float angle,float strength) {
		float a = 0;

		resolution = (int) width + "x" + (int) height;
		if (resolution.equals("800x600")) {
			a = (strength) / 380;
		} else if (resolution.equals("1024x768")) {
			a = (strength) / 150;
		} else if (resolution.equals("1280x720")) {
			a = (strength) / 70;
		} else if (resolution.equals("1280x800")) {
			a = (strength) / 75;
		} else if (resolution.equals("1920x1080")) {
			a = (strength) / 50;
		} else if (resolution.equals("1920x1200")) {
			a = (strength) / 45;
		}
		body.applyForce(new Vector2(0f, a).rotate(angle), body.getPosition(),
				true);
		//body.applyAngularImpulse(-50000.f, true);
	}
	public Vector2 getFront() {
		bounds.set(0,0);
		bounds.add(this.getSprite().getX() + this.getSprite().getOriginX(), this.getSprite().getY() + this.getSprite().getOriginY());
		return bounds;
	}
	
}
