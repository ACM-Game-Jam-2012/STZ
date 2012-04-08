package com.vulcastudios.actors;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.util.ResourceManager;

public class Button {

	private String name;
	private String activates;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean down = false;
	
	private ResourceManager rm;
	private Image image;
	
	public Button(ResourceManager rm, String name, int x, int y, int width, int height, String activates){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rm = rm;
		this.setActivates(activates);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
	
		Color prev = g.getColor();
		g.setColor(Color.cyan);
		g.drawImage(this.getImage(), this.getX(), this.getY());
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(prev);
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public String getActivates() {
		return activates;
	}

	public void setActivates(String activates) {
		this.activates = activates;
	}
	
	public Image getImage(){
		if(this.image == null){
			image = rm.getImage("button").getScaledCopy(this.getWidth(), this.getHeight());
		}
		return image;
	}
	
}
