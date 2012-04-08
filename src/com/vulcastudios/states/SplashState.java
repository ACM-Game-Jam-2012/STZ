package com.vulcastudios.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class SplashState extends BasicGameState {

	public static final int NEXT_KEY = Input.KEY_ENTER;
	
	public static final int TRANSITION_TIME = 3000;
	
	private int timeOnSplash = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("VulcaStudios", (container.getWidth()/2)-75, (container.getHeight()/2-150));

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		timeOnSplash += delta;
		
		if(timeOnSplash >= SplashState.TRANSITION_TIME || container.getInput().isKeyPressed(SplashState.NEXT_KEY)){
			game.enterState(TestGame.LOAD_STATE_ID);
		}

	}

	@Override
	public int getID() {
		return TestGame.SPLASH_STATE_ID;
	}

}
