
package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.ec.ballzi.data.ItemData;

public class FinishBox implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -2412855416303943208L;
private Circle bounds;
private Texture texture;
private Sprite sprite;
private float direction;
//private ShapeRenderer renderer = new ShapeRenderer();
private float startX,startY;
private float width;
private static float screenheight = Gdx.graphics.getHeight();
private static float screenwidth = Gdx.graphics.getWidth();

public FinishBox(float startX,float startY,float width,float height,float direction){
	bounds = new Circle(0,0,width/2);
	//bounds.setOrigin(width/2, height/2);
	this.startX=startX;
	this.startY=startY;
	this.width = width;
	this.direction=direction;
	texture = new Texture(Gdx.files.internal("textures/hole.png"));
	sprite = new Sprite(texture);
	sprite.setSize(this.width, height);
	sprite.setOrigin(width/2, height/2);
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	bounds.setPosition(startX+(screenwidth/2), startY+(screenheight/2));
	//bounds.setRotation(direction);
}
public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(direction);
	return d;
}
public void setTexture(Texture texture){
	sprite.setTexture(texture);
}
public void setColor(Color color){
	sprite.setColor(color);
}

public Texture getTexture(){
	return texture;
}

public Circle getBounds(){
	return bounds;
}
public Sprite getSprite() {
	return sprite;
}

public float getDirection(){
	return direction;
}
public void changeSize(float width,float height){
	bounds = new Circle(0,0,width/2);
	this.width = width;
	texture = new Texture(Gdx.files.internal("textures/hole.png"));
	sprite = new Sprite(texture);
	sprite.setSize(this.width, height);
	sprite.setOrigin(width/2, height/2);
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	bounds.setPosition(startX+(screenwidth/2), startY+(screenheight/2));
	//bounds.setRotation(direction);
}

public void update(){
	sprite.setRotation(direction);
	
	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.BLACK);
	//renderer.polygon(bounds.getTransformedVertices());
	renderer.circle(bounds.x, bounds.y, width/2);
	renderer.end();
	*/
}
}