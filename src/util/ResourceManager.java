package util;

import java.util.HashMap;

import javax.xml.bind.JAXB;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {

	HashMap<String, Resource> imageResources = new HashMap<String, Resource>();
	HashMap<String, Resource> animationResources = new HashMap<String, Resource>();
	HashMap<String, Resource> soundResources = new HashMap<String, Resource>();
	HashMap<String, Resource> musicResources = new HashMap<String, Resource>();
	
	HashMap<String, Image> images = new HashMap<String, Image>();
	HashMap<String, Animation> animations = new HashMap<String, Animation>();
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	HashMap<String, Music> musics = new HashMap<String, Music>();
	
	public ResourceManager(String images, String animation, String sound, String music){
		Resources sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(images), Resources.class);
		for(Resource r : sources.getResource()){
			imageResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(animation), Resources.class);
		for(Resource r : sources.getResource()){
			animationResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(sound), Resources.class);
		for(Resource r : sources.getResource()){
			soundResources.put(r.getKey(), r);
			
		}
		sources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(music), Resources.class);
		for(Resource r : sources.getResource()){
			musicResources.put(r.getKey(), r);
			
		}
		
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
