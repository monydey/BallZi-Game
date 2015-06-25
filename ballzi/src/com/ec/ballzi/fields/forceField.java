package com.ec.ballzi.fields;

import com.badlogic.gdx.math.Polygon;

public class forceField {
private Polygon bounds;
//private ShapeRenderer renderer = new ShapeRenderer();
private float direction;

public forceField(float direction,float size){
	this.direction=direction;
	float[] vertices = {
			0	,0	, 
			0	,size, 
			size	,size,
			size, 0 };

	bounds = new Polygon(vertices);
	bounds.setOrigin(size/2, size/2);
	
}
public Polygon getBounds(){
	return bounds;
}
public float getDirection(){
	return direction;
}

public void setPosition(float x,float y){
	bounds.setPosition(x,y);
}

public void update(){
	/*
	renderer.begin(ShapeType.Line);
	renderer.setColor(Color.BLACK);
	renderer.polygon(bounds.getTransformedVertices());
	renderer.end();*/
}
}
