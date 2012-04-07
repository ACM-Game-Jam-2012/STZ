package com.vulcastudios;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import util.Config;
import util.ResourceManager;

import com.vulcastudios.actors.Level;
import com.vulcastudios.states.InGameState;
import com.vulcastudios.states.LoadState;
import com.vulcastudios.states.MainMenuState;
import com.vulcastudios.states.SplashState;

public class TestGame extends StateBasedGame {

	public static final int SPLASH_STATE_ID = 0;
	public static final int LOAD_STATE_ID = 1;
	public static final int MAIN_MENU_STATE_ID = 2;
	public static final int IN_GAME_STATE = 3;
	

	public static LinkedList<Level> levels = new LinkedList<Level>();
	public static Level currentLevel;
	private ResourceManager rm;

	
	public TestGame(String windowName){
		super(windowName);
		rm = new ResourceManager("images.xml", "animations.xml", "sounds.xml", "music.xml", "maps.xml");
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new SplashState());
		this.addState(new LoadState(this.rm));
		this.addState(new MainMenuState());
		this.addState(new InGameState());

	}
	

	
	public static void main(String[] args){
		Properties props = new Properties();
		try {
			props.load(ResourceLoader.getResourceAsStream("game.properties"));
			Config config = new Config(props);
			TestGame game = new TestGame("STZ");
			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(config.getInteger(Config.WINDOW_WIDTH_CONFIG_KEY),
					config.getInteger(Config.WINDOW_HEIGHT_CONFIG_KEY),
					config.getBoolean(Config.WINDOW_FULLSCREEN_CONFIG_KEY));
			app.setTargetFrameRate(30);
			app.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
