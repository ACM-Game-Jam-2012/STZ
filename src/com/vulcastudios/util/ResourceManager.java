package com.vulcastudios.util;

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
	
	public ResourceManager(String imageLoc, String animationLoc, String soundLoc, String musicLoc, String mapLoc){
		Resources sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(imageLoc), Resources.class);
		for(Resource r : sources.getResource()){
			imageResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(mapLoc), Resources.class);
		for(Resource r : sources.getResource()){
			mapResources.put(r.getKey(), r);
			
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
		return (int)(((float)(maps.size() + images.size() + animations.size() + musicLoader.getLoaded()))/( mapResources.size() + imageResources.size() + animationResources.size() + musicLoader.getSize())*100);
	}
	
	public void load(String key, Image i){
		images.put(key, i);
	}
	
	public void load(String key, Animation i){
		animations.put(key, i);
	}
	
	public Image getImage(String key){
		if(this.images.containsKey(key))
			return this.images.get(key);
		else
			throw new RuntimeException("Image not found: "+key);
	}
	
	public Sound getSound(String key){
		if(this.sounds.containsKey(key))
			return this.sounds.get(key);
		else
			throw new RuntimeException("Sound not found: "+key);
	}
	
	public Music getMusic(String key){
		if(this.music.containsKey(key))
			return this.music.get(key);
		else
			throw new RuntimeException("Music not found: "+key);

	}
	
	public void load(String key, TiledMap i){
		maps.put(key, i);
	}
	
	/*
	public static void main(String[] args){
		
		Resources r = JAXB.unmarshal(ResourceLoader.getResourceAsStream("music.xml"), Resources.class);
		JAXB.marshal(r, System.out);
		
		
	}*/
	
}
