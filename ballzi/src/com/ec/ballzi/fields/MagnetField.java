package com.ec.ballzi.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.ec.ballzi.items.Ball;

public class MagnetField {
private Circle bounds;
//private ShapeRenderer renderer = new ShapeRenderer();
private float forcedirection;
private float direction;

private Ball ball;
private float x;
private float y;
private static float itemsize_small = Gdx.graphics.getWidth()/40;
public void calcDirection(){
	
	float a = (float)Math.atan(((x-(itemsize_small/2))-ball.getSprite().getX())/((y-(itemsize_small/2))-ball.getSprite().getY()));
	a=  (a* (float)(180 / Math.PI));
	if ((x-(itemsize_small/2))<ball.getSprite().getX()&&a<0){
		a+=180f;
	}else if((x-(itemsize_small/2))>ball.getSprite().getX()&&a>=0){
		a+=180f;
	}else if((x-(itemsize_small/2))>ball.getSprite().getX()&&a<0){
		a+=360f;
	}
	forcedirection=a*-1;
}

public float getSpriteDirection(){
	return direction;
}

public MagnetField(float direction,Ball ball){
	this.ball=ball;
	this.direction=direction;
	bounds = new Circle(0,0,itemsize_small*1.5f);
}
public void setBall(Ball ball){
	this.ball=ball;
}
public Circle getBounds(){
	return bounds;
}
public float getDirection(Ball ball){
	this.ball = ball;
	calcDirection();
	return forcedirection;
}

public void setPosition(float x,float y){
	bounds.setPosition(x,y);
	this.x=x;
	this.y=y;
}

public void update(){
	try{
	calcDirection();
	}catch(NullPointerException e){
		
	}
	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.BLACK);
	renderer.circle(x, y, itemsize_small*1.5f);
	renderer.end();*/
	
}
}