package com.vulcastudios.actors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.tiled.TiledMap;

import com.vulcastudios.util.ResourceManager;


public class Level {
	public TiledMap map;
	private ResourceManager resourceManager;
	private Player player;
	
	public Level(String mapName, ResourceManager resourceManager){
		this.resourceManager = resourceManager;
		
		System.out.println(this.resourceManager.maps.size());
		map = this.resourceManager.maps.get(mapName);
	}
	
	public void initLevel(){
		player = new Player(this.resourceManager, 0, 0);
		
	}
	
	public void update(GameContainer container, int delta){
		player.update(container, delta);

	}
	
	public void render(GameContainer container, Graphics g){
			map.render(20, 20, 0, 0, 100, 100);
			player.render(container, g);
		
	}
	
	
}
