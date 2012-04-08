package com.vulcastudios.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class MainMenuState extends BasicGameState {
	
	private int selection;
	private int x = 300;
	private int startY = 200;
	
	// Selection constants for readability
	private final int PLAY = 0;
	private final int CONTROLS = 1;
	private final int OPTIONS = 2;
	private final int CREDITS = 3;
	private final int EXIT = 4;
	
	private Music title_song = null;
	
	public MainMenuState() {
		
	}

	public MainMenuState(int x, int y){
		this.x = x;
		this.startY = y;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		selection = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Image image = ((TestGame)game).getResourceManager().getImage("titleScreen");
		g.drawImage(image, 0, 0);
		Image cog = ((TestGame)game).getResourceManager().getImage("player1");
		g.drawImage(cog, x, startY + (40*selection));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
			if (selection == 4) {
				selection = 0;
			} else {
				selection ++;
			}
		} else if (container.getInput().isKeyPressed(Input.KEY_UP)) {
			if (selection == 0) {
				selection = 4;
			} else {
				selection --;
			}
		} else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			this.enterSelection(container, game);
		}
		
		
		//play Title Screen song
		title_song = ((TestGame)game).getResourceManager().getMusic("bad_ugly");
		if(title_song != null && !title_song.playing()){
			title_song.loop();
		}
	}

	@Override
	public int getID() {
		return TestGame.MAIN_MENU_STATE_ID;
	}
	
	public void enterSelection(GameContainer container, StateBasedGame game) {
		switch(selection) {
			case PLAY:
				//Stop Title Screen song
				title_song.fade(2000, 0, true);
				game.enterState(TestGame.IN_GAME_STATE);
				break;
			case CONTROLS:
				game.enterState(TestGame.CONTROLS_STATE);
				break;
			case OPTIONS:
				game.enterState(TestGame.GAME_OPTIONS_STATE);
				break;
			case CREDITS:
				game.enterState(TestGame.CREDITS_STATE);
				break;
			case EXIT:
				container.exit();
				break;
		}
	}
}
