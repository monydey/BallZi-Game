package com.ec.ballzi.uiitems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class myLabel {
	private Skin labelskin;
	private LabelStyle labelstyle;
	private Label menuLabel;

	public myLabel(String labelText, int fontSize,Color color){
		fontSize = (int)(Math.round(fontSize *((float)Gdx.graphics.getHeight()/720)));
		FileHandle fontFile = Gdx.files.internal("fonts/PTC55F.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
		BitmapFont mBitmapFont = generator.generateFont(fontSize);
		generator.dispose();
		labelskin = new Skin();
		labelstyle = new LabelStyle();
		labelstyle.font = mBitmapFont;
		labelstyle.fontColor = color;
		labelskin.add("default", labelstyle);
		menuLabel = new Label(labelText, labelskin);
		
	 }
	
	public Label getMyLabel(){
		return menuLabel;
	}
}
