package model;
/**
 * This class represents the different characters of the game
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Personnage extends GameObject {
	private int pointDeVie;
	private int pointAction;
	
	public Personnage(int x, int y, int pointDeVie, int pointAction) {
		super(x, y);
		this.pointDeVie = pointDeVie;
		this.pointAction = pointAction;
	}
	
	public int getPointDeVie() {
		return pointDeVie;
	}
	public void setPointDeVie(int pointDeVie) {
		this.pointDeVie = pointDeVie;
	}
	public int getPointAction() {
		return pointAction;
	}
	public void setPointAction(int pointAction) {
		this.pointAction = pointAction;
		setChanged();
		notifyObservers();
	}
	// pour future fonctionalités
	public void seDeplace(int x, int y) {
		setX(x);
		setY(y);
	}
}
