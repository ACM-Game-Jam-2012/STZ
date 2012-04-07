package util;

import java.util.HashMap;

import javax.xml.bind.JAXB;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {


	public HashMap<String, Resource> mapResources = new HashMap<String, Resource>();
	public HashMap<String, Resource> imageResources = new HashMap<String, Resource>();
	public HashMap<String, Resource> animationResources = new HashMap<String, Resource>();
	public HashMap<String, Resource> soundResources = new HashMap<String, Resource>();
	public HashMap<String, Resource> musicResources = new HashMap<String, Resource>();

	public HashMap<String, Image> images = new HashMap<String, Image>();
	public HashMap<String, Animation> animations = new HashMap<String, Animation>();
	public HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	public HashMap<String, TiledMap> maps = new HashMap<String, TiledMap>();
	public HashMap<String, Music> music = new HashMap<String, Music>();

	
	BackgroundMusicLoader musicLoader;
	
	public ResourceManager(String imageLoc, String animationLoc, String soundLoc, String musicLoc){
		Resources sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(imageLoc), Resources.class);
		for(Resource r : sources.getResource()){
			imageResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(animationLoc), Resources.class);
		for(Resource r : sources.getResource()){
			animationResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(soundLoc), Resources.class);
		for(Resource r : sources.getResource()){
			soundResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(musicLoc), Resources.class);
		for(Resource r : sources.getResource()){
			musicResources.put(r.getKey(), r);
			
		}
		musicLoader = new BackgroundMusicLoader(soundResources, sounds, musicResources, music);
		
	}
	
	public HashMap<String, Resource> getImageResources(){
		return this.imageResources;
	}
	
	public HashMap<String, Resource> getAnimationResources(){
		return this.animationResources;
	}
	
	public void startLoad(){
		musicLoader.start();
	}
	
	public int getProgress(){
		return (int)(((float)(images.size() + animations.size() + musicLoader.getLoaded()))/(imageResources.size() + animationResources.size() + musicLoader.getSize())*100);
	}
	
	public void load(String key, Image i){
		images.put(key, i);
	}
	
	public void load(String key, Animation i){
		animations.put(key, i);
	}
	
	/*
	public static void main(String[] args){
		
		Resources r = JAXB.unmarshal(ResourceLoader.getResourceAsStream("music.xml"), Resources.class);
		JAXB.marshal(r, System.out);
		
		
	}*/
	
}
