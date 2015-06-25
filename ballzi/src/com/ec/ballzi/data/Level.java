package com.ec.ballzi.data;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

	public class Level implements Serializable {
		
	/**
		 * 
		 */
	private static final long serialVersionUID = -4765621478253958404L;
	private Vector<ItemData> stars;
	private Vector<ItemData> boxes_fixed;
	private Vector<ItemData> forceboxes_fixed;
	private Vector<ItemData> magnetboxes_fixed;
	private Vector<ItemData> crossboxes_fixed;
	private Vector<ItemData> pushboxes_fixed;
	private Vector<ItemData> finishboxes_fixed;
	private Vector<ItemData> balls_fixed;
	private Vector<ItemData> abyssboxes_fixed;
	private Vector<ItemData> abyssboxesR_fixed;
	private String background_path = "";
	private String name = "";
	private int maxBoxes;
	private int maxCrossBoxes;
	private int maxForceBoxes;
	private int maxMagnetBoxes;
	private int saveresolutionwidth;
	private int saveresolutionheight;
	private int allowedTime;
	private HashMap<String,Integer> highscores = new HashMap<String,Integer>();
	
	public void addHighscore(String name,int value){
		highscores.put(name, value);
	}
	public void removeHighscore(String key){
		highscores.remove(key);
	}
	
	public HashMap<String, Integer> getHighscores() {
		return highscores;
	}

	public Vector<ItemData> getAbyssboxesR_fixed() {
		return abyssboxesR_fixed;
	}

	public void setAbyssboxesR_fixed(Vector<ItemData> abyssboxesR_fixed) {
		this.abyssboxesR_fixed = abyssboxesR_fixed;
	}

	public int getSaveresolutionwidth() {
		return saveresolutionwidth;
	}

	public void setSaveresolutionwidth(int saveresolutionwidth) {
		this.saveresolutionwidth = saveresolutionwidth;
	}

	public int getAllowedTime() {
		return allowedTime;
	}

	public void setAllowedTime(int allowedTime) {
		this.allowedTime = allowedTime;
	}

	public int getSaveresolutionwidht() {
		return saveresolutionwidth;
	}

	public void setSaveresolutionwidht(int saveresolutionwidht) {
		this.saveresolutionwidth = saveresolutionwidht;
	}

	public int getSaveresolutionheight() {
		return saveresolutionheight;
	}

	public void setSaveresolutionheight(int saveresolutionheight) {
		this.saveresolutionheight = saveresolutionheight;
	}

	public int getMaxBoxes() {
		return maxBoxes;
	}

	public void setMaxBoxes(int maxBoxes) {
		this.maxBoxes = maxBoxes;
	}

	public int getMaxCrossBoxes() {
		return maxCrossBoxes;
	}

	public void setMaxCrossBoxes(int maxCrossBoxes) {
		this.maxCrossBoxes = maxCrossBoxes;
	}

	public int getMaxForceBoxes() {
		return maxForceBoxes;
	}

	public void setMaxForceBoxes(int maxForceBoxes) {
		this.maxForceBoxes = maxForceBoxes;
	}

	public int getMaxMagnetBoxes() {
		return maxMagnetBoxes;
	}

	public void setMaxMagnetBoxes(int maxMagnetBoxes) {
		this.maxMagnetBoxes = maxMagnetBoxes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBackground_path() {
		return background_path;
	}

	public void setBackground_path(String background_path) {
		this.background_path = background_path;
	}

	public Vector<ItemData> getPushboxes_fixed() {
		return pushboxes_fixed;
	}

	public void setPushboxes_fixed(Vector<ItemData> pushboxes_fixed) {
		this.pushboxes_fixed = pushboxes_fixed;
	}

	public Vector<ItemData> getFinishboxes_fixed() {
		return finishboxes_fixed;
	}

	public void setFinishboxes_fixed(Vector<ItemData> finishboxes_fixed) {
		this.finishboxes_fixed = finishboxes_fixed;
	}

	public Vector<ItemData> getBalls_fixed() {
		return balls_fixed;
	}

	public void setBalls_fixed(Vector<ItemData> balls_fixed) {
		this.balls_fixed = balls_fixed;
	}

	public Vector<ItemData> getAbyssboxes_fixed() {
		return abyssboxes_fixed;
	}

	public void setAbyssboxes_fixed(Vector<ItemData> abyssboxes_fixed) {
		this.abyssboxes_fixed = abyssboxes_fixed;
	}

	public Vector<ItemData> getStars() {
		return stars;
	}

	public void setStars(Vector<ItemData> stars) {
		this.stars = stars;

	}

	public Vector<ItemData> getBoxes_fixed() {
		return boxes_fixed;
	}

	public void setBoxes_fixed(Vector<ItemData> boxes_fixed) {
		this.boxes_fixed = boxes_fixed;
	}

	public Vector<ItemData> getForceboxes_fixed() {
		return forceboxes_fixed;
	}

	public void setForceboxes_fixed(Vector<ItemData> forceboxes_fixed) {
		this.forceboxes_fixed = forceboxes_fixed;
	}

	public Vector<ItemData> getMagnetboxes_fixed() {
		return magnetboxes_fixed;
	}

	public void setMagnetboxes_fixed(Vector<ItemData> magnetboxes_fixed) {
		this.magnetboxes_fixed = magnetboxes_fixed;
	}

	public Vector<ItemData> getCrossboxes_fixed() {
		return crossboxes_fixed;
	}

	public void setCrossboxes_fixed(Vector<ItemData> crossboxes_fixed) {
		this.crossboxes_fixed = crossboxes_fixed;
	}

}
