package com.vulcastudios.actors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {

	public void update(GameContainer container, int delta){

		float boost = 1f;

		if(container.getInput().isKeyDown(Input.KEY_SPACE)){
			if(!this.boosting){
				this.getBoostSound().loop();
			}
			boost = Player.BOOST_SCALER;
			this.boosting = true;
		}else{
			if(this.getBoostSound().playing())
				this.getBoostSound().stop();
			this.boosting = false;
		}

		if(container.getInput().isKeyDown(Input.KEY_W))
			this.setYPos(getYPos() - Player.DELTA_Y*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_S))
			this.setYPos(getYPos() + Player.DELTA_Y*delta*boost);

		if(container.getInput().isKeyDown(Input.KEY_A))
			this.setXPos(getXPos() - Player.DELTA_X*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_D))
			this.setXPos(getXPos() + Player.DELTA_Y*delta*boost);

		if(this.getXPos() > container.getWidth()-Player.WIDTH){
			this.setXPos(container.getWidth()-Player.WIDTH);
		}
		else if(this.getXPos() < 0){
			this.setXPos(0);
		}

		if(this.getYPos() > container.getHeight()-Player.HEIGHT){
			this.setYPos(container.getHeight()-Player.HEIGHT);
		}
		else if(this.getYPos() < 0){
			this.setYPos(0);
		}

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {

		g.setColor(Player.COLOR);
		g.drawImage(this.getImage(), this.getXPos(), this.getYPos());
		//g.drawRect(this.getXPos(), this.getYPos(), Player.WIDTH, Player.HEIGHT);
		
	}
}
