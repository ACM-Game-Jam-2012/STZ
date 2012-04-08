package com.vulcastudios.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;

public class TransitionState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		long finalTime = ((TestGame)game).getCurrentLevel().getFinalTime();
		double seconds = finalTime / 1000.0;
		g.drawString("Final Time: " + seconds + " seconds", 50, 50);
		int numOfZombies = ((TestGame)game).getCurrentLevel().getNumberOfZombies();
		String par = ((TestGame)game).getCurrentLevel().getPar();
		g.drawString("Zombies Used: " + numOfZombies + " Par: " + par, 50, 100);
		if(!((TestGame)game).isOnLastLevel()){
			g.drawString("Press Enter to continue to the next level", 50, 150);
		}
		else{
			g.drawString("The Game Is Done!", 50, 150);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int g)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(TestGame.MAIN_MENU_STATE_ID);
		}
		else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			if (((TestGame)game).isOnLastLevel()) {
				game.enterState(TestGame.CREDITS_STATE);
			} else {
				((TestGame)game).goToNextLevel();
				game.enterState(TestGame.IN_GAME_STATE);
			}
		}
	}

	@Override
	public int getID() {
		return TestGame.TRANSITION_STATE;
	}

}
