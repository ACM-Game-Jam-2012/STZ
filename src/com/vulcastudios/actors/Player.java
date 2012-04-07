package com.vulcastudios.actors;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.vulcastudios.util.ResourceManager;

public class Player {
	
	public static final float DELTA_X = 0.25f;
	public static final float DELTA_Y = 0.25f;

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	
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

		if(this.getXPos() < 0)
			this.setXPos(0);

		if(this.getYPos() < 0)
			this.setYPos(0);

		if(this.getXPos() > container.getWidth() - Player.WIDTH)
			this.setXPos(container.getWidth() - Player.WIDTH);
		
		if(this.getYPos() > container.getHeight() - Player.HEIGHT)
			this.setYPos(container.getHeight() - Player.HEIGHT);


	}

	public void render(GameContainer container, Graphics g){
		g.setColor(Color.pink);

		g.drawRect(this.getXPos(), this.getYPos(), Player.WIDTH, Player.HEIGHT);
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
