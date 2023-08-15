package model;

/**
 * This class represents the Player in the game
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Player extends Personnage {
	private boolean hasShot = false;
	
	public Player(int x, int y, int pointDeVie, int pointAction) {
		super(x, y, pointDeVie, pointAction);
	}
	
	public void setHasShot(boolean hasShot){
		this.hasShot = hasShot;
	}
	
	public boolean getHasShot(){
		return hasShot;
	}
}