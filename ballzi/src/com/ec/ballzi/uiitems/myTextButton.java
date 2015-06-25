package com.ec.ballzi.uiitems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class myTextButton {
	private Skin buttonskin;
	private TextButton button;
	TextButtonStyle buttonstyle;
	
	public myTextButton(TextureAtlas atlas,int size,Color color){
		FileHandle fontFile = Gdx.files.internal("fonts/PTC55F.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        BitmapFont mBitmapFont = generator.generateFont(size);
        generator.dispose();
        
        buttonskin = new Skin(atlas);
		buttonstyle = new TextButtonStyle();
		buttonstyle.up = buttonskin.getDrawable("button.1");
		buttonstyle.down = buttonskin.getDrawable("button.2");
		buttonstyle.checked = buttonskin.getDrawable("button.3");
		buttonstyle.font = mBitmapFont;
		buttonstyle.fontColor =color;
		buttonskin.add("default", buttonstyle);
		button = new TextButton("",buttonskin);
		
		button.pad(2);
		
		
	}
	
	
	
	public TextButton getButton(){
		return button;
	}

}
