package com.vulcastudios.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class InGameState extends BasicGameState {
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Color prev = g.getColor();
		((TestGame)game).getCurrentLevel().render(container, game, g);
		
		String par = ((TestGame)game).getCurrentLevel().getPar();
		
		//draw HUD
		g.setColor(Color.green);
		String score = ((TestGame)game).getCurrentLevel().getNumberOfZombies() + "";
		String parString = "Number of Zombies Used: " + score + "  Par: " + par;
		g.drawString(parString, 600, 20);
		g.setColor(prev);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(TestGame.MAIN_MENU_STATE_ID);
		}
		
		((TestGame)game).getCurrentLevel().update(container, game, delta);
	}

	@Override
	public int getID() {
		return TestGame.IN_GAME_STATE;
	}

}
