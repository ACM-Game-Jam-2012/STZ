package com.vulcastudios.actors;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.vulcastudios.TestGame;
import com.vulcastudios.util.ResourceManager;

public class Zombie{

	private static final int WIDTH = 16;
	private static final int HEIGHT = 20;
	private float xPos = 0;
	private float yPos = 0;

	private float xPosStart = 0;
	private float yPosStart = 0;

	private Button onButton = null;
	private Image image = null;

	private ResourceManager rm;	
	private LinkedList<ZombieMove> movementMap;
	private LinkedList<ZombieMove> movementMapMaster;

	public Zombie(ResourceManager rm, int xPos, int yPos, LinkedList<ZombieMove> moveMap){
		this.rm = rm;
		this.xPos = xPos;
		this.yPos = yPos;
		this.xPosStart = xPos;
		this.yPosStart = yPos;
		this.movementMapMaster = moveMap;
		this.movementMap = (LinkedList<ZombieMove>)moveMap.clone();
	}

	public void restartZombie(){
		this.setXPos(this.xPosStart);
		this.setYPos(this.yPosStart);
		this.movementMap = (LinkedList<ZombieMove>)this.movementMapMaster.clone();
	}

	public void update(GameContainer container, StateBasedGame game, int delta){

		float oldX = this.getXPos();
		float oldY = this.getYPos();

		int usedDelta = 0;
		while(usedDelta < delta){
			if(!movementMap.isEmpty()){
				ZombieMove move = movementMap.pop();
				
				int moveDelta = move.getDelta();
				if(moveDelta > (delta-usedDelta)){
					ZombieMove newMove = move.clone();
					newMove.setDelta(moveDelta - (delta-usedDelta));
					movementMap.push(newMove);
					moveDelta = (delta-usedDelta);
				}
				usedDelta += moveDelta;

				// Vertical
				if(move.isUp()){
					this.setYPos(getYPos() - (Player.DELTA_Y * moveDelta));
				}
				if(move.isDown()){
					this.setYPos(getYPos() + (Player.DELTA_Y * moveDelta));
				}
				
				if(((TestGame)game).checkCollision(this)){
					this.setYPos(oldY);
				}

				// Horizontal
				if(move.isLeft()){
					this.setXPos(getXPos() - (Player.DELTA_X * moveDelta));
				}
				if(move.isRight()){
					this.setXPos(getXPos() + (Player.DELTA_Y * moveDelta));
				}
				
				if(((TestGame)game).checkCollision(this)){
					this.setXPos(oldX);
				}

			}else{
				break;
			}

		}
		

		
		
		
	}

	public void render(GameContainer container, Graphics g){
		g.setColor(Color.green);
		//g.drawRect(this.getXPos(), this.getYPos(), WIDTH, HEIGHT);
		g.drawImage(this.getImage(), this.getXPos(), this.getYPos());
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

	public Rectangle getBounds(){
		return new Rectangle(this.getXPos(), this.getYPos(), Zombie.WIDTH, Zombie.HEIGHT);
	}

	public void setOnButton(Button b){
		if(b == null){
			this.onButton = null;
		}else if(this.onButton == null){
			this.onButton = b;
			this.rm.getSound("menu_button").play();
		}else if(!this.onButton.getName().equals(b.getName())){
			this.onButton = b;
			this.rm.getSound("menu_button").play();
		}
	}

	public Image getImage(){
		if(this.image == null)
			this.image = this.rm.getImage("zombie").getScaledCopy(Zombie.WIDTH, Zombie.HEIGHT);
		return this.image;

	}

}
