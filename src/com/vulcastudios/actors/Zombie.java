package com.vulcastudios.actors;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.vulcastudios.util.ResourceManager;

public class Zombie {

	private float xPos = 0;
	private float yPos = 0;

	private ResourceManager rm;
	
	public Zombie(ResourceManager rm, int xPos, int yPos){
		this.rm = rm;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void render(GameContainer container, Graphics g){

		g.setColor(Color.green);

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
