package com.ec.ballzi.data;

import java.util.Vector;

import com.ec.ballzi.items.AbyssBox;
import com.ec.ballzi.items.Ball;
import com.ec.ballzi.items.CollidableBox;
import com.ec.ballzi.items.FinishBox;
import com.ec.ballzi.items.ForceBox;
import com.ec.ballzi.items.MagnetBox;
import com.ec.ballzi.items.PushBox;
import com.ec.ballzi.items.SpinningCrossBox;
import com.ec.ballzi.items.Star;

public class LevelData {
	private Vector<CollidableBox> boxes = new Vector<CollidableBox>();
	private Vector<ForceBox> forceboxes = new Vector<ForceBox>();
	private Vector<MagnetBox> magnetboxes = new Vector<MagnetBox>();
	private Vector<SpinningCrossBox> crossboxes = new Vector<SpinningCrossBox>();
	private Vector<PushBox> pushboxes = new Vector<PushBox>();
	private Vector<FinishBox> finishboxes = new Vector<FinishBox>();
	private Vector<Ball> balls = new Vector<Ball>();
	private Vector<Star> stars = new Vector<Star>();
	private Vector<AbyssBox> abyssboxes = new Vector<AbyssBox>();
	private Vector<AbyssBox> abyssboxesR = new Vector<AbyssBox>();
	
	public Vector<AbyssBox> getAbyssboxesR() {
		return abyssboxesR;
	}
	public void setAbyssboxesR(Vector<AbyssBox> abyssboxesR) {
		this.abyssboxesR = abyssboxesR;
	}
	public Vector<CollidableBox> getBoxes() {
		return boxes;
	}
	public void setBoxes(Vector<CollidableBox> boxes) {
		this.boxes = boxes;
	}
	public Vector<ForceBox> getForceboxes() {
		return forceboxes;
	}
	public void setForceboxes(Vector<ForceBox> forceboxes) {
		this.forceboxes = forceboxes;
	}
	public Vector<MagnetBox> getMagnetboxes() {
		return magnetboxes;
	}
	public void setMagnetboxes(Vector<MagnetBox> magnetboxes) {
		this.magnetboxes = magnetboxes;
	}
	public Vector<SpinningCrossBox> getCrossboxes() {
		return crossboxes;
	}
	public void setCrossboxes(Vector<SpinningCrossBox> crossboxes) {
		this.crossboxes = crossboxes;
	}
	public Vector<PushBox> getPushboxes() {
		return pushboxes;
	}
	public void setPushboxes(Vector<PushBox> pushboxes) {
		this.pushboxes = pushboxes;
	}
	public Vector<FinishBox> getFinishboxes() {
		return finishboxes;
	}
	public void setFinishboxes(Vector<FinishBox> finishboxes) {
		this.finishboxes = finishboxes;
	}
	public Vector<Ball> getBalls() {
		return balls;
	}
	public void setBalls(Vector<Ball> balls) {
		this.balls = balls;
	}
	public Vector<Star> getStars() {
		return stars;
	}
	public void setStars(Vector<Star> stars) {
		this.stars = stars;
	}
	public Vector<AbyssBox> getAbyssboxes() {
		return abyssboxes;
	}
	public void setAbyssboxes(Vector<AbyssBox> abyssboxes) {
		this.abyssboxes = abyssboxes;
	}
	
	
}
