package com.vulcastudios.states;

import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


import com.vulcastudios.TestGame;
import com.vulcastudios.actors.Level;
import com.vulcastudios.util.Resource;
import com.vulcastudios.util.ResourceManager;

public class LoadState extends BasicGameState {

	private ResourceManager rm;
	private Iterator<Entry<String, Resource>> images, maps;
	//private Iterator<Entry<String, Resource>> animations;
	
	public LoadState(ResourceManager rm){
		this.rm = rm;
		images = rm.imageResources.entrySet().iterator();
		maps = rm.mapResources.entrySet().iterator();
		//animations = rm.animationResources.entrySet().iterator();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		rm.startLoad();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Progress: "+this.rm.getProgress(), 50, 50);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(this.rm.getProgress() == 100)
			game.enterState(TestGame.MAIN_MENU_STATE_ID);
		
		if(images.hasNext()){
			Resource r = images.next().getValue();
			rm.load(r.getKey(), new Image(r.getLocation()));
		}

		if(maps.hasNext()){
			Resource r = maps.next().getValue();
			rm.load(r.getKey(), new TiledMap(r.getLocation(), "tilesets/"));
			if(game instanceof TestGame){
				((TestGame)game).levels.add(new Level("map1", this.rm));
				((TestGame)game).currentLevel = TestGame.levels.getFirst();
			}
		}

		/*if(animations.hasNext()){
			Resource r = animations.next().getValue();
			rm.load(r.getKey(), new Image(r.getLocation()));
		}*/
		
		
		if(rm.getProgress() ==100){
			game.enterState(TestGame.IN_GAME_STATE);
			
		}

	}

	@Override
	public int getID() {
		return TestGame.LOAD_STATE_ID;
	}

}
