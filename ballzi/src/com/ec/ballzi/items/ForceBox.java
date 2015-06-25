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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ec.ballzi.data.ItemData;
import com.ec.ballzi.fields.forceField;

public class ForceBox implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = -6718317429617537186L;
	private static float screenheight =  Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
private Texture texture;
private Sprite sprite;
private forceField field;
float rotation =0;
private float startX,startY;
private Polygon bounds;
private TextureRegion[] forceregion;
private Skin s;
private Animation forceanimation;
private Sprite animsprite;

public ForceBox(float startX,float startY,float width,float height,forceField field){
	this.field=field;
	this.startX=startX;
	this.startY=startY;
	statetime=0;
	texture = new Texture(Gdx.files.internal("textures/fan.png"));
	sprite = new Sprite(texture);
	sprite.setSize(width*2, height*2);
	sprite.setOrigin(width, height);
	this.field.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	
	float[] vertices = {
			0	,0	, 
			0	,height*2, 
			screenwidth	,height*2,
			screenwidth, 0 };

	bounds = new Polygon(vertices);
	bounds.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	bounds.setOrigin(-(sprite.getWidth()/2), height);
	
	texture = new Texture(Gdx.files.internal("textures/forcetexture.png"));
	TextureAtlas atlas = new TextureAtlas("textures/fan.pack.pack");
	forceregion = new TextureRegion[atlas.getRegions().size];
	s = new Skin(atlas);
	for (int i = 0; i < atlas.getRegions().size; i++) {
		int a = i + 1;
		if (a<10){
			forceregion[i] = s.getRegion("fan.0" + a);
		}else{
			forceregion[i] = s.getRegion("fan." + a);
		}
	}
	
	forceanimation = new Animation(0.05f, forceregion);
	animsprite = new Sprite(forceanimation.getKeyFrame(0));
	animsprite.setSize(screenwidth, height*2);
	animsprite.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	animsprite.setOrigin(-(sprite.getWidth()/2), height);
}

public forceField getForceField(){
	return field;
}
public float getDirection(){
	return field.getDirection();
}
public Polygon getBounds(){
	return bounds;
}

public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(field.getDirection());
	return d;
}


public Sprite getSprite() {
	return sprite;
}
public Sprite getAnimSprite() {
	return animsprite;
}
private ShapeRenderer renderer = new ShapeRenderer();
private float statetime;
public void update(){
	field.update();
	sprite.setRotation(field.getDirection()-90);
	bounds.setRotation(field.getDirection()-90);
	field.getBounds().setRotation(field.getDirection()-90);
	animsprite.setRotation(field.getDirection()-90);
	
	statetime+=Gdx.graphics.getDeltaTime();
	if (statetime>0.6f){
		statetime=0;
	}
	animsprite.setRegion(forceanimation.getKeyFrame(statetime));
	/*renderer.begin(ShapeType.Line);
	renderer.setColor(Color.RED);
	renderer.polygon(bounds.getTransformedVertices());
	renderer.end();*/
	
}
}