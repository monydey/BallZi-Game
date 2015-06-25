

package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


public class AbyssBox extends FinishBox implements Serializable{

	
	private static final long serialVersionUID = -1942240630574088741L;

	public AbyssBox(float startX, float startY, float width, float height,
			Color color, float direction) {
		super(startX, startY, width, height, direction);
		setTexture(new Texture(Gdx.files.internal("textures/abyss.png")));
		
	}
	

}