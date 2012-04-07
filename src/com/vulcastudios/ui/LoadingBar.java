package com.vulcastudios.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingBar {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int progress = 0;	
	
	public LoadingBar(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setProgress(int progress){
		this.progress = progress;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		Color prev = g.getColor();
		g.setColor(Color.white);
		g.drawRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.green);
		g.fillRect(this.x+1, this.y+1, (int)(this.width*(progress/100.0))-1, this.height-1);
		g.setColor(prev);
		
	}
	
}
