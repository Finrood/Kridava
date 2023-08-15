package model;
/**
 * This class is the board of the game
 *
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Plateau{
	final private int taille;
		
	public Plateau(int taille) {
		this.taille = taille;
	}
	
	public int getTaille(){
		return taille;
	}
}
