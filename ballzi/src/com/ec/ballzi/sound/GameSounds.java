package com.ec.ballzi.sound;


public interface GameSounds {
	
	public void playBallMoveLoop();

	public void setBallSoundPitch(float pitch);

	public void playFallinHoleSound();

	public void playFallinFinishSound();

	public void playCollisionSound();

	public void playStartpushASound();

	public void playPushButtonSound();

	public void playStarCollectedSound();

	public void playMagnetfieldSound();

	public void playForcefieldSound();

	public void playWinningSound();

	public void playLoosingSound();

	public void playPlaceItemSound();

	public void playMenuMusicLoop();

	public void playGameMusicLoop();

	public void stopMenuMusicLoop();

	public void stopGameMusicLoop();

	public void disposeAllSounds();
	
}
