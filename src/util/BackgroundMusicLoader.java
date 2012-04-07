package util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class BackgroundMusicLoader extends Thread {

	HashMap<String, Resource> soundResources;
	HashMap<String, Sound> sounds;
	HashMap<String, Resource> musicResources;
	HashMap<String, Music> music;
	
	public BackgroundMusicLoader(HashMap<String, Resource> soundResources, 
			HashMap<String, Sound> sounds, 
			HashMap<String, Resource> musicResources, 
			HashMap<String, Music> music){

		this.soundResources = soundResources;
		this.sounds = sounds;
		this.musicResources = musicResources;
		this.music = music;
		
	}
	
	public int getSize(){
		return musicResources.size() + soundResources.size();
	}

	public int getLoaded(){
		return music.size() + sounds.size();
	}
	
	public void run(){
		Set<Entry<String, Resource>> entries = soundResources.entrySet();
		for(Entry<String, Resource> r : entries){
			try {
				sounds.put(r.getKey(), new Sound(r.getValue().getLocation()));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		entries = musicResources.entrySet();
		for(Entry<String, Resource> r : entries){
			try {
				music.put(r.getKey(), new Music(r.getValue().getLocation(), true));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
}
