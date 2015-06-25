package com.ec.ballzi.items;

import java.io.Serializable;

import pong.client.core.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ec.ballzi.data.ItemData;

public class SpinningCrossBox implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = -1359265169303725194L;
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
private Body body;
private Texture texture;
private Sprite sprite;
private BodyDef bodyDef;
World world;
private FixtureDef fixtureDef;
private Vector2 bottleModelOrigin;
private Polygon bounds;
private float direction;
//private ShapeRenderer renderer = new ShapeRenderer();
private float startX;
private float startY;
private Polygon crossBoundsNorth;
private Polygon crossBoundsEast;
private Polygon crossBoundsSouth;
private Polygon crossBoundsWest;
private float width, height;

public SpinningCrossBox(float startX,float startY,World world,float width,float height,Color color, float direction){
	this.direction = direction;
	this.startX=startX;
	this.startY=startY;
	this.width = width;
	this.height = height;
	texture = new Texture(Gdx.files.internal("textures/cross.png"));
	sprite = new Sprite(texture);
	this.world=world;
	sprite.setSize(width, height);
	sprite.setOrigin(width/2, height/2);
	sprite.setRotation(direction);
	CreateBody(world,new Vector2(startX,startY),0f);
	MakeCrossFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,0.01f,0.01f,0f);
	sprite.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2),startY+(screenheight/2)-(sprite.getHeight()/2));
	
	float[] vertices = {
			0	,0	, 
			0	,width, 
			width	,width,
			width, 0 };
	
	float[] horizontalvertices = {
			0	,0	, 
			0	,height/2, 
			width/4 - 10	,height/2,
			width/4 - 10, 0 };
	
	
	bounds = new Polygon(vertices);
	bounds.setOrigin(width/2, height/2);
	bounds.setPosition(startX+(screenwidth/2)-(sprite.getWidth()/2),startY+(screenheight/2)-(sprite.getHeight()/2));
	bounds.setRotation(direction);
	
	crossBoundsNorth = new Polygon(horizontalvertices);
	crossBoundsNorth.setOrigin(width/5, 0);
	crossBoundsNorth.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsNorth.setRotation(direction);
	
	crossBoundsWest = new Polygon(horizontalvertices);
	crossBoundsWest.setOrigin(width/5, 0);
	crossBoundsWest.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsWest.setRotation(direction-90);
	
	crossBoundsSouth = new Polygon(horizontalvertices);
	crossBoundsSouth.setOrigin(width/5, 0);
	crossBoundsSouth.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsSouth.setRotation(direction-180);
	
	crossBoundsEast = new Polygon(horizontalvertices);
	crossBoundsEast.setOrigin(width/5, 0);
	crossBoundsEast.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsEast.setRotation(direction-270);
	
}

public float getDirection(){
	return direction;
}

public float getMiddlepointX(){
	return sprite.getX()+sprite.getOriginX();
}

public float getMiddlepointY(){
	return sprite.getY()+sprite.getOriginY();
}

public ItemData getItemData(){
	ItemData d = new ItemData();
	d.setX(startX);
	d.setY(startY);
	d.setDirection(direction);
	return d;
}
public Texture getTexture(){
	return texture;
}

public Polygon getBounds() {
	return bounds;
}

public Polygon getCrossBoundsNorth() {
	return crossBoundsNorth;
}

public Polygon getCrossBoundsEast() {
	return crossBoundsEast;
}

public Polygon getCrossBoundsSouth() {
	return crossBoundsSouth;
}

public Polygon getCrossBoundsWest() {
	return crossBoundsWest;
}

public void setPosition(float x,float y){
	sprite.setPosition(x, y);
 	Vector2 pos = new Vector2(sprite.getX(),sprite.getY());
	bodyDef.position.set(pos );
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    MakeCrossFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,0.0001f,0.01f,0f);
}

public void CreateBody(World world,Vector2 pos,float angle){
    bodyDef = new BodyDef(); 
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(pos);
    body = world.createBody(bodyDef);
}



private void MakeCrossFixture(float width,float height,BodyDef.BodyType bodyType,
	     float density,float restitution,float angle){
	     PolygonShape bodyShape = new PolygonShape();
	    
	     float w=width/2;
	     float h=height/2;
	     bodyShape.setAsBox(w,h);
	     fixtureDef=new FixtureDef();
	     fixtureDef.density=density;
	     fixtureDef.restitution=restitution;
	     //fixtureDef.shape=bodyShape;
	     
	     BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/test4.json"));
	    // loader.attachFixture(body, "test", fixtureDef, BOTTLE_WIDTH);
	     loader.attachFixture(body,"cross.png", fixtureDef, width);
	     bottleModelOrigin = loader.getOrigin("cross.png", width).cpy();
	     //body.createFixture(fixtureDef);
	     bodyShape.dispose();
	 }

public Sprite getSprite() {
	return sprite;
}
public void deleteBody(){
	world.destroyBody(body);
	sprite.setSize(0, 0);
}

float angle = 1f;
private int rotation = 1;

public void updateset(){

	bodyDef.angle=direction * (float)(Math.PI/180);
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    MakeCrossFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,0.01f,0.01f,0f);
    sprite.setOrigin(bottleModelOrigin.x, bottleModelOrigin.y);
    sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    
}
public void update(){
	angle+= rotation * Gdx.graphics.getDeltaTime();
	bodyDef.angle=angle;
	world.destroyBody(body);
    body = world.createBody(bodyDef);
    MakeCrossFixture(sprite.getWidth()*sprite.getScaleX(),sprite.getHeight()*sprite.getScaleY(),BodyType.DynamicBody,0.01f,0.01f,0f);
    sprite.setOrigin(bottleModelOrigin.x, bottleModelOrigin.y);
    sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    
    crossBoundsNorth.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    crossBoundsWest.setRotation((body.getAngle() * MathUtils.radiansToDegrees)-90);
    crossBoundsSouth.setRotation((body.getAngle() * MathUtils.radiansToDegrees)-180);
    crossBoundsEast.setRotation((body.getAngle() * MathUtils.radiansToDegrees)-270);
    /*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.BLUE);
	renderer.polygon(crossBoundsNorth.getTransformedVertices());
	renderer.polygon(crossBoundsSouth.getTransformedVertices());
	renderer.setColor(Color.GREEN);
	renderer.polygon(crossBoundsWest.getTransformedVertices());
	renderer.polygon(crossBoundsEast.getTransformedVertices());
	renderer.setColor(Color.MAGENTA);
	renderer.polygon(bounds.getTransformedVertices());
	renderer.end();
	*/
	
}
public void toggleDirection() {
	rotation = -rotation;
	
	float[] horizontalvertices = {
			0	, 0	, 
			0	, rotation * height/2, 
			width/4 - 10, rotation * height/2,
			width/4 - 10, 0 };
	
	crossBoundsNorth = new Polygon(horizontalvertices);
	crossBoundsNorth.setOrigin(width/5, 0);
	crossBoundsNorth.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsNorth.setRotation(direction);
	
	crossBoundsWest = new Polygon(horizontalvertices);
	crossBoundsWest.setOrigin(width/5, 0);
	crossBoundsWest.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsWest.setRotation(direction-90);
	
	crossBoundsSouth = new Polygon(horizontalvertices);
	crossBoundsSouth.setOrigin(width/5, 0);
	crossBoundsSouth.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsSouth.setRotation(direction-180);
	
	crossBoundsEast = new Polygon(horizontalvertices);
	crossBoundsEast.setOrigin(width/5, 0);
	crossBoundsEast.setPosition(startX+(screenwidth/2)-(width/5),startY+(screenheight/2));
	crossBoundsEast.setRotation(direction-270);
}

public int getRotation() {
	return rotation;
}


}