package com.vulcastudios.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class MainMenuState extends BasicGameState {
	
	private ArrayList<String> menuStrings = new ArrayList<String>();
	private int selection;
	private int x = 300;
	private int startY = 200;
	
	// Selection constants for readability
	private final int PLAY = 0;
	private final int CONTROLS = 1;
	private final int OPTIONS = 2;
	private final int CREDITS = 3;

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
		Color defaultColor = g.getColor();
		this.drawMenu(g);
		g.setColor(defaultColor);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
			if (selection == 3) {
				selection = 0;
			} else {
				selection ++;
			}
		} else if (container.getInput().isKeyPressed(Input.KEY_UP)) {
			if (selection == 0) {
				selection = 3;
			} else {
				selection --;
			}
		} else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			this.enterSelection(game);
		}
	}

	@Override
	public int getID() {
		return TestGame.MAIN_MENU_STATE_ID;
	}
	
	public void enterSelection(StateBasedGame game) {
		switch(selection) {
			case PLAY:
				System.out.println(menuStrings.get(PLAY));
				game.enterState(TestGame.IN_GAME_STATE);
				break;
			case CONTROLS:
				System.out.println(menuStrings.get(CONTROLS));
				game.enterState(TestGame.CONTROLS_STATE);
				break;
			case OPTIONS:
				System.out.println(menuStrings.get(OPTIONS));
				game.enterState(TestGame.GAME_OPTIONS_STATE);
				break;
			case CREDITS:
				System.out.println(menuStrings.get(CREDITS));
				game.enterState(TestGame.CREDITS_STATE);
				break;
		}
	}

	public void drawMenu(Graphics g) {
		for (int index = 0; index < menuStrings.size(); index++) {
			if (index == selection) {
				g.setColor(Color.green);
			} else if (!g.getColor().equals(Color.white)) {
				g.setColor(Color.white);
			}
			this.drawString(g, index);
		}
	}
	
	private void drawString(Graphics g, int index) {
		g.drawString(menuStrings.get(index), x, startY + (50*index));
	}
}
