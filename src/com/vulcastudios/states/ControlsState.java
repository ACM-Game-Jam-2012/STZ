package com.vulcastudios.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class ControlsState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Color previousColor = g.getColor();
		
		g.drawString("Directional arrows: move", 50, 50);
		g.drawString("Space bar: suicide", 50, 75);
		g.drawString("Enter: restart level", 50, 100);
		g.drawString("Press esc to return to the main menu", 50, 150);
		
		g.setColor(previousColor);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(TestGame.MAIN_MENU_STATE_ID);
		}
	}

	@Override
	public int getID() {
		return TestGame.CONTROLS_STATE;
	}

}
