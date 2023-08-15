package model;

import java.util.LinkedList;
/**
 * This class will manage all the objects in the game.
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Manager {
	//This list contains ALL of the GameObjects in the game (Player, Enemies, Bonuses, etc)
	public LinkedList<GameObject> listObject = new LinkedList<GameObject>();
}