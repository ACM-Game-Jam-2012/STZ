package com.vulcastudios.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class MainMenuState extends BasicGameState {
	
	private ArrayList<String> menuStrings = new ArrayList<String>();
	private int selection;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		menuStrings.add("play");
		menuStrings.add("controls");
		menuStrings.add("options");
		menuStrings.add("credits");
		
		selection = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.green);
		g.drawString("play", 200, 200);
		g.setColor(Color.white);
		g.drawString("options", 550, 200);
		g.drawString("controls", 200, 400);
		g.drawString("credits", 550, 400);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return TestGame.MAIN_MENU_STATE_ID;
	}

	public void drawMenu(Graphics g) {
		
	}
}
