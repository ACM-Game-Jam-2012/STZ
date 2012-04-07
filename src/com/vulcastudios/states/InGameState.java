package com.vulcastudios.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;
import com.vulcastudios.actors.Level;

public class InGameState extends BasicGameState {
	
	private Level currentLevel;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		currentLevel = TestGame.levels.getFirst();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		this.currentLevel.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return TestGame.IN_GAME_STATE;
	}

}
