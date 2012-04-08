package com.vulcastudios.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class TransitionState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		long finalTime = ((TestGame)game).getCurrentLevel().getFinalTime();
		double seconds = finalTime / 1000.0;
		g.drawString("Final Time: " + seconds + " seconds", 50, 50);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return TestGame.TRANSITION_STATE;
	}

}
