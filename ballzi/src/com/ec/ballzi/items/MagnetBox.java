
package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ec.ballzi.data.ItemData;
import com.ec.ballzi.fields.MagnetField;

public class MagnetBox implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = 4634942301726217271L;
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
private Texture texture;
private Sprite sprite;
private MagnetField field;
private Circle bounds;
private float startX,startY;
private TextureRegion[] magnetregion;
private Animation magnetanimation;
private Sprite animsprite;
private float statetime;
private Sprite animspriteleft;
private Skin s;
private Sprite animspritebottom;
private Sprite animspritetop;
private float[] vertices;
private Polygon boundsvert1;
private Polygon boundsvert2;
//private ShapeRenderer renderer;
private Polygon boundshori1;
private Polygon boundshori2;

public MagnetBox(float startX,float startY,float width,float height,MagnetField field){
	this.field=field;
	this.startX=startX;
	this.startY=startY;
	//renderer = new ShapeRenderer();
	statetime=0;
	texture = new Texture(Gdx.files.internal("textures/magnet.png"));
	sprite = new Sprite(texture);
	sprite.setSize(width*2, height*2);
	this.field.setPosition(startX+(screenwidth/2), startY+(screenheight/2));
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	sprite.setOrigin(width, height);
	
	texture = new Texture(Gdx.files.internal("textures/magnettexture.png"));
	TextureAtlas atlas = new TextureAtlas("textures/magnetanim2.pack");
	magnetregion = new TextureRegion[atlas.getRegions().size];
	s = new Skin(atlas);
	for (int i = 0; i < atlas.getRegions().size; i++) {
		int a = i + 1;
		if (a<10){
			magnetregion[i] = s.getRegion("magnet.0" + a);
		}else{
			magnetregion[i] = s.getRegion("magnet." + a);
		}
	}
	
	magnetanimation = new Animation(0.05f, magnetregion);
	animsprite = new Sprite(magnetanimation.getKeyFrame(0));
	animsprite.setSize(screenwidth, height*2);
	animsprite.setOrigin(-(sprite.getWidth()/2), height);
	animsprite.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	
	
	animspriteleft = new Sprite(magnetanimation.getKeyFrame(0));
	animspriteleft.setSize(screenwidth, height*2);
	animspriteleft.setOrigin(-(sprite.getWidth()/2), height);
	animspriteleft.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	

	animspritetop = new Sprite(magnetanimation.getKeyFrame(0));
	animspritetop.setSize(screenwidth, height*2);
	animspritetop.setOrigin(-(sprite.getWidth()/2), height);
	animspritetop.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	
	animspritebottom = new Sprite(magnetanimation.getKeyFrame(0));
	animspritebottom.setSize(screenwidth, height*2);
	animspritebottom.setOrigin(-(sprite.getWidth()/2), height);
	animspritebottom.setPosition(startX+(screenwidth/2)+(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	
	vertices= new float[]{
			0	,0	, 
			0	,height*2, 
			screenwidth	,height*2,
			screenwidth, 0 };
	boundsvert1 = new Polygon(vertices);
	boundsvert1.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	boundsvert1.setOrigin(width, height);
	boundsvert2 = new Polygon(vertices);
	boundsvert2.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	boundsvert2.setOrigin(width, height);
	boundshori1 = new Polygon(vertices);
	boundshori1.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	boundshori1.setOrigin(width, height);
	boundshori2 = new Polygon(vertices);
	boundshori2.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2), startY+(screenheight/2)-(sprite.getHeight()/2));
	boundshori2.setOrigin(width, height);
	
}
public float getDirection(){
	return field.getSpriteDirection();
}
public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(field.getSpriteDirection());
	return d;
}

public MagnetField getMagnetField(){
	return field;
}



public Circle getBounds(){
	bounds = field.getBounds();
	return bounds;
}
public Polygon getVertBounds1(){
	return boundsvert1;
}
public Polygon getVertBounds2(){
	return boundsvert2;
}
public Polygon getHoriBounds1(){
	return boundshori1;
}
public Polygon getHoriBounds2(){
	return boundshori2;
}

public Sprite getSprite() {
	return sprite;
}
public Sprite getanimSprite1() {
	return animsprite;
}
public Sprite getanimSprite2() {
	return animspriteleft;
}
public Sprite getanimSprite3() {
	return animspritetop;
}
public Sprite getanimSprite4() {
	return animspritebottom;
}

public void updateAnim(float delta){
	statetime+=delta;
	if (statetime>0.5f)
		statetime=0;
	animsprite.setRegion(magnetanimation.getKeyFrame(statetime));
	animspriteleft.setRegion(magnetanimation.getKeyFrame(statetime));
	animspritetop.setRegion(magnetanimation.getKeyFrame(statetime));
	animspritebottom.setRegion(magnetanimation.getKeyFrame(statetime));
	/*renderer.begin(ShapeType.Line);
	renderer.setColor(Color.RED);
	renderer.polygon(boundsvert1.getTransformedVertices());
	renderer.polygon(boundsvert2.getTransformedVertices());
	renderer.polygon(boundshori1.getTransformedVertices());
	renderer.polygon(boundshori2.getTransformedVertices());
	renderer.end();*/
}

public void update(){
	field.update();
	sprite.setRotation(field.getSpriteDirection()-90);
	animsprite.setRotation(field.getSpriteDirection()-90);
	animspriteleft.setRotation(field.getSpriteDirection()-270);
	animspritetop.setRotation(field.getSpriteDirection()-180);
	animspritebottom.setRotation(field.getSpriteDirection()-360);
	boundsvert1.setRotation(field.getSpriteDirection()-90);
	boundsvert2.setRotation(field.getSpriteDirection()-270);
	boundshori1.setRotation(field.getSpriteDirection()-180);
	boundshori2.setRotation(field.getSpriteDirection()-360);
	//field.getBounds().setRotation(field.getSpriteDirection()-90);
}
}