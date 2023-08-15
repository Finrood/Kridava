package model;
import java.util.Observable;
/**
 * This class represents all the objects of the game (players, enemies, bonuses, etc)
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class GameObject extends Observable{
	private int x;
	private int y;	
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
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
}
