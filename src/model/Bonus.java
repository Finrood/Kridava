package model;
/**
 * This class represents a bonus in the game
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Bonus extends GameObject {
	private int chanceDeSpawn;
	
	public Bonus(int x, int y, int chanceDeSpawn) {
		super(x, y);
		this.chanceDeSpawn = chanceDeSpawn;		
	}
	
	/**
	 * This method return the chance of spawn
	 *  @return the chance of spawn of a bonus
	*/
	public int getChanceDeSpawn() {
		return chanceDeSpawn;
	}
	
	/**
	 * This method sets the chance of spawn
	 * @param chanceDeSpawn is the chance of spawn of a bonus
	*/
	public void setChanceDeSpawn(int chanceDeSpawn) {
		this.chanceDeSpawn = chanceDeSpawn;
		setChanged();
		notifyObservers();
	}	
}
