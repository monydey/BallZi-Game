package com.ec.ballzi.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.ec.ballzi.data.Setup;

public class MySounds implements GameSounds {

	private static Sound mp3PlayCollisionSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound1.mp3"));
    private static Sound mp3PlayStarCollectedSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound2.mp3"));
    private static Sound mp3PlayPushButtonSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound3.mp3"));
    private static Sound mp3PlayPlayPushedSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound4.mp3"));
    private static Sound mp3PlayForceFieldSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound5_loop.mp3"));
    private static Sound mp3PlayMagnetFieldSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound6_loop1-7sec.mp3"));
    private static Sound mp3PlayWinningSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound7.mp3"));
    private static Sound mp3PlayLoosingSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound8.mp3"));
    private static Sound mp3PlayFallinHoleSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound9.mp3"));
    private static Sound mp3PlayFallinFinishSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound10.mp3"));
    private static Sound mp3PlayPlaceItemSound = Gdx.audio.newSound(Gdx.files.internal("soundfiles/sound11.mp3"));
    private static Sound mp3PlayGameMusicLoop;
    private static Sound mp3PlayMenuMusicLoop;
    private static Sound mp3PlayBallMovingLoop;
    private static long idMenuMusic;
    private static long idGameMusic;
    private static long idBallSound;
    private float volumemusic=0;
    private float volumesound=0f;
    private boolean musicOn=false;
    private boolean soundOn=false;
	private HashMap<String, String> entrys;
    
    public MySounds(){
    	Setup setup = new Setup();
    	try {
			entrys = setup.load();
			volumesound =((float)Integer.parseInt(entrys.get("volume:")))/100;
			volumemusic =((float)Integer.parseInt(entrys.get("musicvolume:")))/100;
			String soundon = entrys.get("sound:");
			String musicon = entrys.get("music:");
			if (soundon.equals("on")){
				soundOn=true;
			}
			if (musicon.equals("on")){
				musicOn=true;
			}
			
			System.out.println("volume sound: "+ volumesound);
			System.out.println("sound on: "+soundOn);
			
		} catch (Exception e) {}
    	
    	
    }
    
    public void soundOn(String soundon){
    	if (soundon.equals("on")){
			soundOn=true;
		}else{
			soundOn=false;
		}
    }
    public void musicOn(String musicon){
    	if (musicon.equals("on")){
    		musicOn=true;
		}else{
			musicOn=false;
		}
    }
    public void changeSoundVolume(int volume){
    	volumesound=(float)volume/100f;
    }
    public void changeMusicVolume(int volume){
    	volumemusic=(float)volume/100f;
    	try{
    		mp3PlayMenuMusicLoop.setVolume(idMenuMusic, volumemusic);
    	}catch(Exception e){}
    }
    public void playBallMoveLoop(){
    	if (soundOn){
    		mp3PlayBallMovingLoop= Gdx.audio.newSound(Gdx.files.internal("soundfiles/ballrollingloop.mp3"));;
    		idBallSound = mp3PlayBallMovingLoop.play(volumesound/2);
    		mp3PlayBallMovingLoop.setLooping(idBallSound, true);
    	}
      }
    public void stopBallMoveLoop(){
    	mp3PlayBallMovingLoop.stop();
    	
      }
    public void setBallSoundPitch(float pitch){
    	mp3PlayBallMovingLoop.setPitch(idBallSound, pitch);
    }
    
    public void playFallinHoleSound(){
    	if (soundOn)
    		mp3PlayFallinHoleSound.play(volumesound);
      }
    
    public void playFallinFinishSound(){
    	if (soundOn)
    		mp3PlayFallinFinishSound.play(volumesound);
      }
    
	public void playCollisionSound(){
		if (soundOn)
			mp3PlayCollisionSound.play(volumesound);
      }
	
      public void playStartpushASound(){
    	 if (soundOn)
    		 mp3PlayPlayPushedSound.play(volumesound);
      }
	
      public void playPushButtonSound(){
    	  if (soundOn)
    		  mp3PlayPushButtonSound.play(volumesound);
      }
	
      public void playStarCollectedSound() {
    	  if (soundOn)
    		  mp3PlayStarCollectedSound.play(volumesound);
      }
	
      public void playMagnetfieldSound(){
    	  if (soundOn)
    		  mp3PlayMagnetFieldSound.play(volumesound);
      }
	
      public void playForcefieldSound(){
    	  if (soundOn)
    		  mp3PlayForceFieldSound.play(volumesound/2);
      }
	
      public void playWinningSound(){
    	  if (soundOn)
    		  mp3PlayWinningSound.play(volumesound);
      }
	
      public void playLoosingSound(){
    	  if (soundOn)
    		  mp3PlayLoosingSound.play(volumesound);
      }
	
      public void playPlaceItemSound() {
    	  if (soundOn)
    		  mp3PlayPlaceItemSound.play(volumesound);
      }
      
      
      
      public void playMenuMusicLoop(){
    	  if (musicOn){
    		  mp3PlayMenuMusicLoop = Gdx.audio.newSound(Gdx.files.internal("soundfiles/playMenuMusicLoop.mp3"));
    		  idMenuMusic = mp3PlayMenuMusicLoop.play(volumemusic); 
    		  mp3PlayMenuMusicLoop.setLooping(idMenuMusic,true);
    	  }
      }
      
      public void stopMenuMusicLoop(){
    	  mp3PlayMenuMusicLoop.stop(idMenuMusic); 
    	  mp3PlayMenuMusicLoop.dispose();
      }
      
      public void playGameMusicLoop(){
    	  if (musicOn){
    		  mp3PlayGameMusicLoop = Gdx.audio.newSound(Gdx.files.internal("soundfiles/playGameMusicLoop.mp3"));
    		  idGameMusic = mp3PlayGameMusicLoop.play(volumemusic); 
    		  mp3PlayGameMusicLoop.setLooping(idGameMusic,true);
    	  }
      }
      
      public void stopGameMusicLoop(){
    	  mp3PlayGameMusicLoop.stop(idGameMusic); 
    	  mp3PlayGameMusicLoop.dispose();
      }

	@Override
	public void disposeAllSounds() {
		System.out.println("DISPOSE ALL SOUNDS");
		mp3PlayPlayPushedSound.dispose();
		mp3PlayCollisionSound.dispose();
		mp3PlayMagnetFieldSound.dispose();
        mp3PlayPushButtonSound.dispose();
        mp3PlayForceFieldSound.dispose();
        mp3PlayStarCollectedSound.dispose();
		mp3PlayWinningSound.dispose();
        mp3PlayLoosingSound.dispose();
        mp3PlayPlaceItemSound.dispose();
        mp3PlayGameMusicLoop.dispose();
        mp3PlayMenuMusicLoop.dispose();
        mp3PlayBallMovingLoop.dispose();
        mp3PlayFallinHoleSound.dispose();
        mp3PlayFallinFinishSound.dispose();
	}
      
     
	
}
