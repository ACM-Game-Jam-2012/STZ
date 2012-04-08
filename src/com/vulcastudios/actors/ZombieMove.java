package com.vulcastudios.actors;

public class ZombieMove {
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	
	public boolean isRight() {
		return right;
	}
	
	public void addRight(){
		right = true;
	}
	
	public void noRight(){
		right = false;
	}

	public boolean isUp() {
		return up;
	}
	
	public void addUp(){
		up = true;
	}
	
	public void noUP(){
		up = false;
	}

	public boolean isDown() {
		return down;
	}
	
	public void addDown(){
		down = true;
	}
	
	public void noDown(){
		down = false;
	}

	public boolean isLeft() {
		return left;
	}
	
	public void addLeft(){
		left = true;
	}
	
	public void noLeft(){
		left = false;
	}
}
