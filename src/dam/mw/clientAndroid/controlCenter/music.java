package dam.mw.clientAndroid.controlCenter;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.audiofx.AudioEffect.Descriptor;

public class music {

	Context context;
	MediaPlayer music = new MediaPlayer();
	MediaPlayer sound = new MediaPlayer();
	MediaPlayer musicBattle = new MediaPlayer();

	public music (Context c) {
		this.context = c;
	}

	public void onMainTheme() {
		try {
			AssetManager assetManager = context.getAssets();
			AssetFileDescriptor descriptor = assetManager.openFd("music/maintheme.mp3");
			music.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());
			music.prepare();
			music.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onSearchBattle() {
		try {
			AssetManager assetManager = context.getAssets();
			AssetFileDescriptor descriptor = assetManager.openFd("music/busquedabatalla.mp3");
			musicBattle.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());
			musicBattle.prepare();
			musicBattle.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void offMusic() {
		music.stop();
	}
	
	public void offBattleMusic() {
		music.stop();
	}
	
	
	public void onSoundBasic(String songName){
		try {
			AssetManager assetManager = context.getAssets();
			AssetFileDescriptor descriptor = assetManager.openFd(songName);
			sound.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());
			sound.prepare();
			sound.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clean() {
		music.reset();
		musicBattle.reset();
	}
	
	public void cleanSound() {
		sound.reset();
	}
	
}
