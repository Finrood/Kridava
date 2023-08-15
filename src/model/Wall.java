package model;

/**
 * This class represents a Wall in the game
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Wall extends GameObject {
	private int taille;
	private boolean estVertical;
	
	public Wall(int x, int y, int taille, boolean estVertical){
		super(x,y);		
		this.taille = taille;
		this.estVertical = estVertical;
	}

	public int getTaille() {
		return taille;
	}

	public boolean isEstVertical() {
		return estVertical;
	}
}