package com.vulcastudios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

import com.vulcastudios.actors.Button;
import com.vulcastudios.actors.Door;
import com.vulcastudios.actors.Level;
import com.vulcastudios.actors.Player;
import com.vulcastudios.actors.Zombie;
import com.vulcastudios.states.ControlsState;
import com.vulcastudios.states.CreditsState;
import com.vulcastudios.states.GameOptionsState;
import com.vulcastudios.states.InGameState;
import com.vulcastudios.states.LoadState;
import com.vulcastudios.states.MainMenuState;
import com.vulcastudios.states.TransitionState;
import com.vulcastudios.util.Config;
import com.vulcastudios.util.ResourceManager;

public class TestGame extends StateBasedGame {

	public static final int SPLASH_STATE_ID = 0;
	public static final int LOAD_STATE_ID = 1;
	public static final int MAIN_MENU_STATE_ID = 2;
	public static final int IN_GAME_STATE = 3;
	public static final int CONTROLS_STATE = 4;
	public static final int GAME_OPTIONS_STATE = 5;
	public static final int CREDITS_STATE = 6;
	public static final int TRANSITION_STATE = 7;

	private ArrayList<Level> levels;
	private int currentLevelIndex = 0;
	private ResourceManager rm;


	public TestGame(String windowName){
		super(windowName);
		levels = new ArrayList<Level>();
		rm = new ResourceManager("images.xml", "animations.xml", "sounds.xml", "music.xml", "maps.xml");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new LoadState(this.rm));
		this.addState(new MainMenuState());
		this.addState(new InGameState());
		this.addState(new ControlsState());
		this.addState(new GameOptionsState());
		this.addState(new CreditsState());
		this.addState(new TransitionState());
	}

	public void goToNextLevel(){
		currentLevelIndex++;
		levels.get(currentLevelIndex).initLevel();
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}

	public void addLevel(Level level) {
		this.levels.add(level);
	}

	public Level getCurrentLevel() {
		return levels.get(currentLevelIndex);
	}
	
	public void checkEndPoint(Player p) {
		Rectangle endBounds = this.getCurrentLevel().getEnd().getBounds();
		Rectangle playerBounds = p.getBounds();
		
		if (playerBounds.intersects(endBounds)) {
			long finalTime = System.currentTimeMillis() - this.getCurrentLevel().getStartTime();
			this.getCurrentLevel().setFinalTime(finalTime);
			this.enterState(TRANSITION_STATE);
		}
	}

	public void checkObjects(Player p){
		Set<Entry<String, Button>> buttons = this.getCurrentLevel().getButtons().entrySet();
		for(Entry<String, Button> entry : buttons){
			if(p.getBounds().intersects(entry.getValue().getBounds())){
				Door door = this.getCurrentLevel().getDoors().get(entry.getValue().getActivates());
				door.setOpen(!door.isOpen());
			}
		}
	}

	public void checkObjects(Zombie z){
		Set<Entry<String, Button>> buttons = this.getCurrentLevel().getButtons().entrySet();
		for(Entry<String, Button> entry : buttons){
			if(z.getBounds().intersects(entry.getValue().getBounds())){
				Door door = this.getCurrentLevel().getDoors().get(entry.getValue().getActivates());
				door.setOpen(!door.isOpen());
			}
		}
	}

	public boolean checkCollision(Player p){
		TiledMap map = levels.get(currentLevelIndex).getMap();

		boolean doorCol = false;

		Set<Entry<String, Door>> doors = this.getCurrentLevel().getDoors().entrySet();
		for(Entry<String, Door> entry : doors){

			if(p.getBounds().intersects(entry.getValue().getBounds())){
				if(!entry.getValue().isOpen())
					doorCol = true;
			}

		}

		int tileTL = map.getTileId((int)(p.getXPos()/map.getTileWidth()),(int)(p.getYPos()/map.getTileHeight()), 1);
		int tileBL = map.getTileId((int)(p.getXPos()/map.getTileWidth()),(int)((p.getYPos()+Player.HEIGHT)/map.getTileHeight()), 1);
		int tileTR = map.getTileId((int)((p.getXPos()+Player.WIDTH)/map.getTileWidth()),(int)(p.getYPos()/map.getTileHeight()), 1);
		int tileBR = map.getTileId((int)((p.getXPos()+Player.WIDTH)/map.getTileWidth()),(int)((p.getYPos()+Player.HEIGHT)/map.getTileHeight()), 1);

		return tileTL != 0 || tileBL != 0 || tileTR != 0 || tileBR != 0 || doorCol;
	}

	public boolean checkCollision(Zombie z){
		TiledMap map = levels.get(currentLevelIndex).getMap();

		boolean doorCol = false;

		Set<Entry<String, Door>> doors = this.getCurrentLevel().getDoors().entrySet();
		for(Entry<String, Door> entry : doors){

			if(z.getBounds().intersects(entry.getValue().getBounds())){
				if(!entry.getValue().isOpen())
					doorCol = true;
			}

		}

		int tile1 = map.getTileId((int)(z.getXPos()/map.getTileWidth()),(int)(z.getYPos()/map.getTileHeight()), 1);
		int tile2 = map.getTileId((int)(z.getXPos()/map.getTileWidth()),(int)((z.getYPos()+Player.HEIGHT)/map.getTileHeight()), 1);
		int tile3 = map.getTileId((int)((z.getXPos()+Player.WIDTH)/map.getTileWidth()),(int)(z.getYPos()/map.getTileHeight()), 1);
		int tile4 = map.getTileId((int)((z.getXPos()+Player.WIDTH)/map.getTileWidth()),(int)((z.getYPos()+Player.HEIGHT)/map.getTileHeight()), 1);
		return tile1 != 0 || tile2 != 0 || tile3 != 0 || tile4 != 0 || doorCol;
	}
	
	public ResourceManager getResourceManager(){
		return this.rm;
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
