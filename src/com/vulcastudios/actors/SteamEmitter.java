package com.vulcastudios.actors;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.util.ResourceManager;

public class SteamEmitter {

	private String name;
	private String direction;

	private int x;
	private int y;
	private int width;
	private int height;

	private ResourceManager rm;
	private List<Steam> steams;
	private Steam lastSteam;
	
	private Image image;

	public SteamEmitter(ResourceManager rm, List<Steam> steams, String name, int x, int y, int width, int height, String direction){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rm = rm;
		this.steams = steams;
		this.setDirection(direction);

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
	
	public void update(GameContainer container, StateBasedGame game, int delta){
		Rectangle r = null;
		if(this.direction.equals("left"))
			r = new Rectangle(this.getX()-this.getWidth(), this.getY(), this.getWidth(), this.getHeight());
		else if(this.direction.equals("right"))
			r = new Rectangle(this.getX()+this.getWidth(), this.getY(), this.getWidth(), this.getHeight());
		else if(this.direction.equals("up"))
			r = new Rectangle(this.getX(), this.getY()-this.getHeight(), this.getWidth(), this.getHeight());
		else if(this.direction.equals("down"))
			r = new Rectangle(this.getX(), this.getY()+this.getHeight(), this.getWidth(), this.getHeight());
		
		if(lastSteam == null){
			lastSteam = new Steam(this.rm, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(), this.direction);
			steams.add(lastSteam);
		}
		if(!lastSteam.getBounds().intersects(r)){
			lastSteam = new Steam(this.rm, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(), this.direction);
			steams.add(lastSteam);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g){

		Color prev = g.getColor();
		g.drawImage(this.getImage(), this.getX(), this.getY());
		g.setColor(Color.orange);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(prev);

	}

	public Rectangle getBounds(){
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public Image getImage(){
		if(this.image == null)
			this.image = rm.getImage("door").getScaledCopy(this.getWidth(), this.getHeight());
		return this.image;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}


}
