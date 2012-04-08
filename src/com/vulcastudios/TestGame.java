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
import com.vulcastudios.actors.Collidable;
import com.vulcastudios.actors.Door;
import com.vulcastudios.actors.Level;
import com.vulcastudios.actors.Player;
import com.vulcastudios.actors.Steam;
import com.vulcastudios.actors.Zombie;
import com.vulcastudios.actors.ZombieMove;
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

	public void goToLevel(int level) {
		currentLevelIndex = level;
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


	public boolean isOnLastLevel() {
		return currentLevelIndex == (levels.size()-1);
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
		boolean onButton = false;
		for(Entry<String, Button> entry : buttons){
			if(p.getBounds().intersects(entry.getValue().getBounds())){
				p.setOnButton(entry.getValue());
				onButton = true;
				String[] activates = entry.getValue().getActivates().split(",");
				for(String doorName : activates){
					String trimmedName = doorName.trim();
					Door door = this.getCurrentLevel().getDoors().get(trimmedName);
					door.setOpen(!door.isOpen());
				}
			}
		}
		if(!onButton)
			p.setOnButton(null);
	}

	public void checkObjects(Zombie z){
		Set<Entry<String, Button>> buttons = this.getCurrentLevel().getButtons().entrySet();
		boolean onButton = false;
		for(Entry<String, Button> entry : buttons){
			if(z.getBounds().intersects(entry.getValue().getBounds())){
				z.setOnButton(entry.getValue());
				onButton = true;
				String[] activates = entry.getValue().getActivates().split(",");
				for(String doorName : activates){
					String trimmedName = doorName.trim();
					Door door = this.getCurrentLevel().getDoors().get(trimmedName);
					door.setOpen(!door.isOpen());
				}
			}
		}
		if(!onButton)
			z.setOnButton(null);
	}

	public boolean checkCollision(Player p){
		boolean doorCol = false;

		Set<Entry<String, Door>> doors = this.getCurrentLevel().getDoors().entrySet();
		for(Entry<String, Door> entry : doors){

			if(p.getBounds().intersects(entry.getValue().getBounds())){
				if(!entry.getValue().isOpen())
					doorCol = true;
			}

		}

		boolean collidableCollide = false;

		for(Collidable collidable : this.getCurrentLevel().getCollidables()){
			if(p.getBounds().intersects(collidable.getBounds()))
				collidableCollide = true;
		}

		return collidableCollide || doorCol;
	}

	public boolean checkCollision(Steam s){

		Player p = this.getCurrentLevel().getPlayer();
		boolean playerCol = p.getBounds().intersects(s.getBounds());
		if(playerCol){
			p.setAlive(false);
			ZombieMove move = new ZombieMove();
			if(p.getXPos() > s.getX())
				move.addLeft();
			if(p.getXPos() < s.getX())
				move.addRight();
			if(p.getYPos() > s.getY())
				move.addUp();
			if(p.getYPos() < s.getY())
				move.addDown();
			p.getMovementMap().add(move);
				
		}

		boolean zombieCol = false;
		for(Zombie zombie : this.getCurrentLevel().getZombies()){
			if(s.getBounds().intersects(zombie.getBounds()))
				zombieCol = true;
		}

		boolean doorCol = false;

		Set<Entry<String, Door>> doors = this.getCurrentLevel().getDoors().entrySet();
		for(Entry<String, Door> entry : doors){

			if(s.getBounds().intersects(entry.getValue().getBounds())){
				if(!entry.getValue().isOpen())
					doorCol = true;
			}

		}

		boolean collidableCollide = false;

		for(Collidable collidable : this.getCurrentLevel().getCollidables()){
			if(s.getBounds().intersects(collidable.getBounds()))
				collidableCollide = true;
		}

		return collidableCollide || doorCol || playerCol || zombieCol;
	}

	public boolean checkCollision(Zombie z){
		boolean doorCol = false;

		Set<Entry<String, Door>> doors = this.getCurrentLevel().getDoors().entrySet();
		for(Entry<String, Door> entry : doors){

			if(z.getBounds().intersects(entry.getValue().getBounds())){
				if(!entry.getValue().isOpen())
					doorCol = true;
			}

		}

		boolean collidableCollide = false;

		for(Collidable collidable : this.getCurrentLevel().getCollidables()){
			if(z.getBounds().intersects(collidable.getBounds()))
				collidableCollide = true;
		}

		return collidableCollide || doorCol;
	}

	public ResourceManager getResourceManager(){
		return this.rm;
	}

	public void resetLevels(){
		this.currentLevelIndex = 0;
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
