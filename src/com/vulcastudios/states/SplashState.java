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
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("splash", 50, 50);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		if(container.getInput().isKeyPressed(SplashState.NEXT_KEY)){
			game.enterState(TestGame.LOAD_STATE_ID);
		}

	}

	@Override
	public int getID() {
		return TestGame.SPLASH_STATE_ID;
	}

}
