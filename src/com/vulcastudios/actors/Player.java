package com.vulcastudios.actors;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import util.ResourceManager;

public class Player {
	private float xPos = 0;
	private float yPos = 0;
	
	public static final float DELTA_X = 0.2f;
	public static final float DELTA_Y = 0.2f;

	private ResourceManager rm;
	
	
	public Player(ResourceManager rm, int xPos, int yPos){
		this.rm = rm;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void update(GameContainer container, int delta){

		if(container.getInput().isKeyDown(Input.KEY_W))
			this.setYPos(getYPos() - Player.DELTA_Y);
		if(container.getInput().isKeyDown(Input.KEY_S))
			this.setYPos(getYPos() + Player.DELTA_Y);

		if(container.getInput().isKeyDown(Input.KEY_A))
			this.setXPos(getXPos() - Player.DELTA_X);
		if(container.getInput().isKeyDown(Input.KEY_D))
			this.setXPos(getXPos() + Player.DELTA_Y);


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
