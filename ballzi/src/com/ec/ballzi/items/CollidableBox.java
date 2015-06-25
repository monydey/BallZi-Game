package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ec.ballzi.data.ItemData;

public class CollidableBox implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = 4257535632768002376L;
private Body body;
private Texture texture;
private Sprite sprite;
private BodyDef bodyDef;
World world;
private FixtureDef fixtureDef;
private Polygon bounds;
private ShapeRenderer renderer;
private float direction;
private float startX,startY;
private Polygon cbounds;
public float getDirection() {
	return direction;
}
private static float screenheight = Gdx.graphics.getHeight();
private static float screenwidth = Gdx.graphics.getWidth();
private static float itemsize_small = Gdx.graphics.getWidth()/40;

public CollidableBox(float startX,float startY,World world,float width,float height,Color color,float direction){
	this.startX=startX;
	this.startY=startY;
	this.direction=direction;
	texture = new Texture(Gdx.files.internal("textures/block.png"));
	sprite = new Sprite(texture);
	this.world=world;
	sprite.setSize(width, height);
	sprite.setOrigin(width/2, height/2);
	sprite.setRotation(direction);
	CreateBody(world,new Vector2(startX,startY),0f);
	MakeRectFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,0.1f,0f);
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2),startY+(screenheight/2)-(sprite.getHeight()/2));
	renderer = new ShapeRenderer();
	float[] vertices = {
			0	,0	, 
			0	,height, 
			width	,height,
			width, 0 };

	bounds = new Polygon(vertices);
	bounds.setOrigin(width/2, height/2);
	bounds.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2),startY+(screenheight/2)-(sprite.getHeight()/2));
	bounds.setRotation(direction);
	
	float[] cvertices = {
			0	,0	, 
			0	,height+itemsize_small-1, 
			width+itemsize_small-1	,height+itemsize_small-1,
			width+itemsize_small-1, 0 };
	cbounds = new Polygon(cvertices);
	cbounds.setOrigin(width/2+(itemsize_small/2), height/2+(itemsize_small/2));
	cbounds.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2)-(itemsize_small/2),startY+(screenheight/2)-(sprite.getHeight()/2)-(itemsize_small/2));
	cbounds.setRotation(direction);
	
}
public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(direction);
	return d;
}
public void CreateBody(World world,Vector2 pos,float angle){
    bodyDef = new BodyDef(); 
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(pos);
    bodyDef.angle=angle;
    body = world.createBody(bodyDef);
}


private void MakeRectFixture(float width,float height,BodyDef.BodyType bodyType,
	     float density,float restitution,float angle){
	     PolygonShape bodyShape = new PolygonShape();
	    
	     float w=width/2;
	     float h=height/2;
	     bodyShape.setAsBox(w,h);
	     fixtureDef=new FixtureDef();
	     fixtureDef.density=density;
	     fixtureDef.restitution=restitution;
	     fixtureDef.shape=bodyShape;
	     
	     body.createFixture(fixtureDef);
	     bodyShape.dispose();
	 }

public void setPosition(float x,float y){
	sprite.setPosition(x, y);
 	Vector2 pos = new Vector2(sprite.getX(),sprite.getY());
	bodyDef.position.set(pos );
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    MakeRectFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,0.1f,0f);
}

public Sprite getSprite() {
	return sprite;
}

public void deleteBody(){
	world.destroyBody(body);
	sprite.setSize(0, 0);
}

public void update(){
	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.RED);
	renderer.polygon(cbounds.getTransformedVertices());
	renderer.setColor(Color.GREEN);
	renderer.polygon(bounds.getTransformedVertices());
	renderer.end();
	*/

	bodyDef.angle=direction * (float)(Math.PI/180);
	world.destroyBody(body);
    body = world.createBody(bodyDef);
   
	MakeRectFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,1f,0.7f,direction * (float)(Math.PI/180));
	sprite.setRotation(direction);
}


public Polygon getBounds() {
	return bounds;
}
public Polygon getCBounds() {
	return cbounds;
}
}