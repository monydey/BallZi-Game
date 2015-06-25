
package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ec.ballzi.data.ItemData;

public class PushBox implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = -5045189405132416927L;
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
private Body body;
private Texture texture;
private Sprite sprite;
private BodyDef bodyDef;
private World world;
private FixtureDef fixtureDef;
private Polygon bounds;
private float size=10;
private float direction;
private float startX;
private float startY;
private float height;
private float[]  vertices;
private float[]  vertices2;
private Polygon bounds2;
private ShapeRenderer renderer = new ShapeRenderer();
private TextureRegion[] pushboxregion;
private Skin s2;
private Animation pushboxanimation;
private Polygon cbounds;
private float[] cvertices;
private static float itemsize_small = Gdx.graphics.getWidth()/40;

public PushBox(float startX,float startY,World world,float height,Color color, float direction){
	this.height=height;
	active=false;
	
	vertices= new float[]{
			0	,0	, 
			0	,height, 
			height	,height,
			height, 0 };
	
	vertices2= new float[]{
			0	,0	, 
			0	,height, 
			5	,height,
			5, 0 };
	cvertices= new float[]{
			0	,0	, 
			0	,height+itemsize_small, 
			height+itemsize_small	,height+itemsize_small,
			height+itemsize_small, 0 };
	
	direction-=90;
	texture = new Texture(Gdx.files.internal("textures/pushboxtextue.png"));
	TextureAtlas atlas = new TextureAtlas("textures/pushboxatlas.pack");
	pushboxregion = new TextureRegion[atlas.getRegions().size];
	s2 = new Skin(atlas);
	
	for (int i = 0; i < 30; i++) {
		int a = i + 1;
		if (a<10){
			pushboxregion[i] = s2.getRegion("pushbox.000" + a);
		}else{
			pushboxregion[i] = s2.getRegion("pushbox.00" + a);
		}
	}
	pushboxanimation = new Animation(0.015f, pushboxregion);
	
	sprite = new Sprite(pushboxanimation.getKeyFrame(0));
	this.world=world;
	this.direction = direction;
	this.startX=startX;
	this.startY=startY;
	sprite.setSize(height*2, height);
	sprite.setOrigin(height/2, height/2);
	CreateBody(world,new Vector2(startX,startY),0f);
	MakeRectFixture(sprite.getWidth()/2-(sprite.getWidth()/4),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,1f,direction-45,0);
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/4),startY+(screenheight/2)-(sprite.getHeight()/2));
	sprite.setRotation(direction);

	bounds = new Polygon(vertices);
	bounds.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2),startY+(screenheight/2)-(sprite.getHeight()/2));

	bounds.setOrigin(height/2, height/2);
	bounds.setRotation(direction);
	
	cbounds = new Polygon(cvertices);
	cbounds.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2)+(itemsize_small/2),startY+(screenheight/2)-(sprite.getHeight()/2)-(itemsize_small/2));

	cbounds.setOrigin(height/2+(itemsize_small/2), height/2+(itemsize_small/2));
	cbounds.setRotation(direction);
	
	bounds2 = new Polygon(vertices2);
	bounds2.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/4),startY+(screenheight/2)-(sprite.getHeight()/2));

	bounds2.setOrigin(height/2,height/2);
	bounds2.setRotation(direction);
	
}

public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(direction+90);
	return d;
}

public Texture getTexture(){
	return texture;
}

public float getDirection(){
	return direction;
}

public Polygon getCBounds() {
	return cbounds;
}

public void CreateBody(World world,Vector2 pos,float angle){
    bodyDef = new BodyDef(); 
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(pos);
    bodyDef.angle=angle;
    body = world.createBody(bodyDef);
    
}


private void MakeRectFixture(float width,float height,BodyDef.BodyType bodyType,
	     float density,float restitution,float angle,float center){
	     PolygonShape bodyShape = new PolygonShape();
	    
	     float w=width/2;
	     float h=height/2;
	     bodyShape.setAsBox(w,h,new Vector2(center,0),0);
	     fixtureDef=new FixtureDef();
	     fixtureDef.density=density;
	     fixtureDef.restitution=restitution;
	     fixtureDef.shape=bodyShape;
	     body.createFixture(fixtureDef);
	     bodyShape.dispose();
	 }

public void setPosition(float x,float y){
	sprite.setPosition(x, y);
	reset();
}


public Sprite getSprite() {
	return sprite;
}

public void reset(){
	keytime=0;
	sprite.setRegion(pushboxanimation.getKeyFrame(keytime));
	size = 0f;
	sprite.setSize(height*2, height);
	Vector2 pos = new Vector2(startX,startY);
	bodyDef.position.set(pos );
	world.destroyBody(body);
    body = world.createBody(bodyDef);
   
	MakeRectFixture(sprite.getWidth()/3,sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,0.7f,0f,-10);

}

public void updateset(){
	bodyDef.angle=direction * (float)(Math.PI/180);
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    sprite.setRotation(direction);
	keytime=0;
	sprite.setRegion(pushboxanimation.getKeyFrame(keytime));

	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.RED);
	renderer.polygon(cbounds.getTransformedVertices());
	renderer.end();
	*/
	
	
}

public void deleteBody(){
	world.destroyBody(body);
	sprite.setSize(0, 0);
}

private boolean active;
private float keytime=0;
private String resolution;

public boolean isActive(){
	return active;
}
public void update(){
	
	if (size < 0.4f){
		size += Gdx.graphics.getDeltaTime();
		active=true;
	}else{
		active = false;
	}
	
	resolution = (int) screenwidth + "x" + (int) screenheight;
	
	if  (resolution.equals("1920x1080")) {
		
		vertices2[4]=(float)(height/2 + size*2.5f*height+70);
		vertices2[6]=(float)(height/2 + size*2.5f*height+70);
	}
	else if (resolution.equals("1920x1200")) {
		vertices2[4]=(float)(height/2 + size*2.5f*height+70);
		vertices2[6]=(float)(height/2 + size*2.5f*height+70);
	}else{
		vertices2[4]=(float)(height/2 + size*2.5f*height);
		vertices2[6]=(float)(height/2 + size*2.5f*height);
		
		
	}
	
	vertices2[0]=(float)(height/2 + size*2.5f*height+25);
	vertices2[2]=(float)(height/2 + size*2.5f*height+25);
	
	cvertices[4]=(float)(height/2 + size*2.5f*height)+(itemsize_small*1.4f);
	cvertices[6]=(float)(height/2 + size*2.5f*height)+(itemsize_small*1.4f);
	
	bounds2.setVertices(vertices2);
	cbounds.setVertices(cvertices);
	if (keytime<0.4f)
		keytime+=Gdx.graphics.getDeltaTime();
	
	sprite.setRegion(pushboxanimation.getKeyFrame(keytime));
	sprite.setSize(height*2, height);
	float c = 3 - (keytime*4);
    Vector2 pos = new Vector2(startX + height/4 + size*2.5f*height/2 + 6, startY);
	bodyDef.position.set(pos);
	bodyDef.angle=direction * (float)(Math.PI/180);
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    MakeRectFixture(sprite.getWidth()/(c-0.15f),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,0.7f,0f,size/2-30);

	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.RED);
	renderer.polygon(cbounds.getTransformedVertices());
	renderer.polygon(bounds2.getTransformedVertices());
	renderer.end();
	*/
	
	
}


public Polygon getBounds() {
	return bounds;
}
public Polygon getFrontBounds() {
	return bounds2;
}
}