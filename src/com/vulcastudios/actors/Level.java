package com.vulcastudios.actors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
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
	
	public TiledMap getMap(){
		return this.map;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta){
		player.update(container, game, delta);

	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
			map.render(0, 0, 0, 0, 100, 100);
			player.render(container, game, g);
		
	}
	
	
}
