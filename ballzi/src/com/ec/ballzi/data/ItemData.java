package com.ec.ballzi.data;

import java.io.Serializable;

public class ItemData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float x,y,direction;

	public ItemData(){
		
	}
	
	
	
	public ItemData(float x, float y, float direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}



	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}
	
}
