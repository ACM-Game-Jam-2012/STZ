package com.vulcastudios.states;

import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Resource;
import util.ResourceManager;

import com.vulcastudios.TestGame;

public class LoadState extends BasicGameState {

	private ResourceManager rm;
	private Iterator<Entry<String, Resource>> images;
	//private Iterator<Entry<String, Resource>> animations;
	
	public LoadState(ResourceManager rm){
		this.rm = rm;
		images = rm.imageResources.entrySet().iterator();
		//animations = rm.animationResources.entrySet().iterator();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(images.hasNext()){
			Resource r = images.next().getValue();
			rm.load(r.getKey(), new Image(r.getLocation()));
		}
		/*if(animations.hasNext()){
			Resource r = animations.next().getValue();
			rm.load(r.getKey(), new Image(r.getLocation()));
		}*/

	}

	@Override
	public int getID() {
		return TestGame.LOAD_STATE_ID;
	}

}
