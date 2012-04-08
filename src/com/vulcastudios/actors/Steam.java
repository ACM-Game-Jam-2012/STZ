package com.vulcastudios.actors;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;
import com.vulcastudios.util.ResourceManager;

public class Steam {

	public static final float DELTA_X = 0.15f;
	public static final float DELTA_Y = 0.15f;
	
	private String name;
	private String direction;

	private float x;
	private float y;
	private int width;
	private int height;
	
	private boolean alive;

	private ResourceManager rm;
	
	private Image image;

	public Steam(ResourceManager rm, int x, int y, int width, int height, String direction){
		this.name = "steam"+System.currentTimeMillis();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rm = rm;
		this.setDirection(direction);
		this.alive = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
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
		if(this.getDirection().equals("left")){
			this.setX(this.getX() - (Steam.DELTA_X * delta));
		}
		else if(this.getDirection().equals("right")){
			this.setX(this.getX() + (Steam.DELTA_X * delta));
		}
		else if(this.getDirection().equals("up")){
			this.setY(this.getY() - (Steam.DELTA_Y * delta));
		}
		else if(this.getDirection().equals("down")){
			this.setY(this.getY()+ (Steam.DELTA_Y * delta));
		}
		
		if(((TestGame)game).checkCollision(this)){
			this.setAlive(false);
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g){

		Color prev = g.getColor();
		g.drawImage(this.getImage(), this.getX(), this.getY());
		g.setColor(Color.orange);
		//g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	@Override
	public boolean equals(Object s){
		if(s instanceof Steam){
			return ((Steam) s).getName().equals(this.getName());
		}else{
			return false;
		}
	}


}
