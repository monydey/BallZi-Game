
package com.ec.ballzi.items;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.ec.ballzi.data.ItemData;

public class Star implements Serializable{

	private static final long serialVersionUID = 1242435058659314092L;
	private static float screenheight = Gdx.graphics.getHeight();
	private static float screenwidth = Gdx.graphics.getWidth();
	private Polygon bounds;
	private Texture texture;
	private Sprite sprite;
	// private ShapeRenderer renderer = new ShapeRenderer();
	private float startX, startY;

	public Star(float startX, float startY, float width, float height) {
		float[] vertices = { 0, 0, 0, height, width, height, width, 0 };

		bounds = new Polygon(vertices);
		bounds.setOrigin(width / 2, height / 2);
		this.startX = startX;
		this.startY = startY;
		texture = new Texture(Gdx.files.internal("textures/star.png"));
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		sprite.setPosition(
				startX + (screenwidth / 2) - (sprite.getWidth() / 2), startY
						+ (screenheight / 2) - (sprite.getHeight() / 2));
		bounds.setPosition(
				startX + (screenwidth / 2) - (sprite.getWidth() / 2), startY
						+ (screenheight / 2) - (sprite.getHeight() / 2));

	}

	public ItemData getItemData() {
		ItemData d = new ItemData();
		d.setX(startX);
		d.setY(startY);
		d.setDirection(0);
		return d;
	}

	public Texture getTexture() {
		return texture;
	}

	public Polygon getBounds() {
		return bounds;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void delete() {
		sprite.setScale(0f);
		bounds.setScale(0, 0);
	}

	public void show() {
		sprite.setScale(1f);
		bounds.setScale(1f, 1f);
	}

	public void update() {
		/*
		 * renderer.begin(ShapeType.Line); renderer.setColor(Color.MAGENTA);
		 * renderer.polygon(bounds.getTransformedVertices()); renderer.end();
		 */
	}
}