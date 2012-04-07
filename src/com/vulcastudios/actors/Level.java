package com.vulcastudios.actors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.vulcastudios.util.ResourceManager;


public class Level {
	public TiledMap map;
	private ResourceManager resourceManager;

	
	public Level(String mapName, ResourceManager resourceManager){
		this.resourceManager = resourceManager;
		
		System.out.println(this.resourceManager.maps.size());
		map = this.resourceManager.maps.get(mapName);
	}
	
	
	
	public void render(Graphics g){
		//if(map != null)
			map.render(20, 20, 0, 0, 100, 100);
		
	}
	
	
}
