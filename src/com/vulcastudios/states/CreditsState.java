package com.vulcastudios.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;
import com.vulcastudios.util.ResourceManager;

public class CreditsState extends BasicGameState {
	
	private ResourceManager rm;
	
	public CreditsState(ResourceManager rm){
		this.rm = rm;
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Color previousColor = g.getColor();
		
		g.drawImage(this.rm.getImage("creditsScreen"), 0, 0);
		g.drawString("Alex Meade", 50, 100);
		g.drawString("Forrest Meade", 50, 125);
		g.drawString("Andrew Melton", 50, 150);
		g.drawString("Taylor Paschal", 50, 175);
		g.drawString("Stephanie Reese", 50, 200);
		g.drawString("Sam Thomas", 50, 225);
		g.drawString("Press esc to return to the main menu", 50, 300);
		
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
		return TestGame.CREDITS_STATE;
	}

}
