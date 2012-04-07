package com.vulcastudios.actors;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.vulcastudios.util.ResourceManager;

public class Player {
	
	public static final float DELTA_X = 0.25f;
	public static final float DELTA_Y = 0.25f;
	
	private float xPos = 0;
	private float yPos = 0;

	private ResourceManager rm;
	
	public Player(ResourceManager rm, int xPos, int yPos){
		this.rm = rm;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void update(GameContainer container, int delta){

		// Handle player movement
		if(container.getInput().isKeyDown(Input.KEY_UP))
			this.setYPos(getYPos() - (Player.DELTA_Y * delta));
		if(container.getInput().isKeyDown(Input.KEY_DOWN))
			this.setYPos(getYPos() + (Player.DELTA_Y * delta));
		if(container.getInput().isKeyDown(Input.KEY_LEFT))
			this.setXPos(getXPos() - (Player.DELTA_X * delta));
		if(container.getInput().isKeyDown(Input.KEY_RIGHT))
			this.setXPos(getXPos() + (Player.DELTA_Y * delta));


	}

	public void render(GameContainer container, Graphics g){

		g.setColor(Color.pink);

		g.drawRect(this.getXPos(), this.getYPos(), 50, 50);
	}
	

	public float getXPos() {
		return xPos;
	}

	public void setXPos(float xPos) {
		this.xPos = xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public void setYPos(float yPos) {
		this.yPos = yPos;
	}
}
