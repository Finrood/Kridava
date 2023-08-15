package model;

/**
 * This class represents an enemy in the game
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Enemy extends Personnage {
	private boolean hasShot = false;
	
	//constructor
	public Enemy(int x, int y, int pointDeVie, int PointAction) {
		super(x, y, pointDeVie, PointAction);
	}
	
	public void setHasShot(boolean hasShot) {
		this.hasShot = hasShot;
	}
	
	public boolean getHasShot() {
		return this.hasShot;
	}
	
}