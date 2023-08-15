package controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import model.Bonus;
import model.Enemy;
import model.Manager;
import model.Player;
import model.Wall;

/**
 * This class represents the controller which controls the entire game
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Controller {
	private model.Bonus b;
	private model.Enemy e;
	private static model.Player p;
	private view.AbstractMainGame vue = null;
	
	public Controller(model.Bonus b, model.Enemy e, model.Player p) {
		this.b = b;
		this.e= e;
		this.p = p;
	}
	
	public model.Enemy getE() {
		return e;
	}
	
	public void setE(model.Enemy e) {
		this.e = e;
	}
	
	public static model.Player getP() {
		return p;
	}
	
	public void setP(model.Player p) {
		this.p = p;
	}
	
	public model.Bonus getB() {
		return b;
	}
	
	public void setB(model.Bonus b) {
		this.b = b;
	}
	
	public void setView(view.AbstractMainGame view) {
        this.vue = view;
	}
	
	
	
	
	//THE PLAYER
	
	/**
	 * Move to the given direction if it's possible
	 * @param direction 0 for up, 1 for right, 2 for down and 3 for left.
	 * @param plat is the board
	 * @param m is the manager
	 */
	public void move(String direction, model.Plateau plat, model.Manager m){
		Wall aWall;
		int errorMessage = -1;
		boolean blocked = false;
		switch(direction){
		case "up": case "u": case "8": 	if(p.getY()+1<plat.getTaille() && p.getPointAction() >= 1){ //se deplace vers le haut
						for(int i = 0; i < m.listObject.size(); i++){								//parcourt tous les objets
							if(m.listObject.get(i).getClass().getName() == "model.Wall"){			//si l'objet est un mur:
								aWall = (Wall)m.listObject.get(i);
								if(!aWall.isEstVertical()){											//si ce mur est vertical
									for(int j = 0; j < aWall.getTaille(); j++){						//pour chaque case ou le mur se trouve
										if(p.getY()+1 == aWall.getY() && p.getX() == aWall.getX() + j){ //si cette partie de mur se trouve la ou on veut se deplacer
											blocked = true;											//enregistre que le joueur va  etre bloque
											errorMessage = 1; 										// There is a Wall
										}
									}
								}
								else{																//si le mur est horisontal
									if(p.getY()+1 == aWall.getY() && p.getX() ==aWall.getX()){		//si cette partie de mur se trouve la ou on veut se deplacer
										blocked = true;												//enregistre que le joueur va  etre bloque
										errorMessage = 1; 											// There is a Wall
									}
								}																
							}
						}
						if(!blocked){			//si on a enregistre qu'il n'a pas ete bloque
							p.setY(p.getY()+1); //se deplace d'une case vers le haut
							p.setPointAction(p.getPointAction()-1);	// enleve un point d'action au joueur
						}										
					}else{
						errorMessage = 2; //You can't go further up
					}						
					break;
		case "right": case "r": case "6": 	if(p.getX()+1<plat.getTaille() && p.getPointAction() >= 1){//se deplace vers la droite (fonctionne de la meme maniere que le deplacement vers le haut)
						for(int i = 0; i < m.listObject.size(); i++){
							if(m.listObject.get(i).getClass().getName() == "model.Enemi"){
								if(p.getY() == m.listObject.get(i).getY() && p.getX() +1 == m.listObject.get(i).getX()){
									blocked = true;
									errorMessage = 0; // There is an Enemi
								}
							}
							else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
								aWall = (Wall)m.listObject.get(i);
								if(aWall.isEstVertical()){
									for(int j = 0; j < aWall.getTaille(); j++){
										if(p.getY() == aWall.getY() + j && p.getX()+1 == aWall.getX()){											
											blocked = true;
											errorMessage = 1; // There is a Wall
										}
									}
								}
								else{
									if(p.getY() == aWall.getY() && p.getX() + 1 ==aWall.getX()){
										blocked = true;
										errorMessage = 1; // There is a Wall
									}
								}																
							}
						}
						if(!blocked){
							p.setX(p.getX()+1); //se deplace d'une case vers la droite
							p.setPointAction(p.getPointAction()-1);	
						}										
					}else{
						errorMessage = 3; //You can't go further right
					}	
					break;
		case "down": case "d": case "2": 	if(p.getY()-1 >= 0 && p.getPointAction() >= 1){//se deplace vers le bas (fonctionne de la meme maniere que le deplacement vers le haut)
						for(int i = 0; i < m.listObject.size(); i++){
							if(m.listObject.get(i).getClass().getName() == "model.Enemi"){
								if(p.getY() -1 == m.listObject.get(i).getY() && p.getX() == m.listObject.get(i).getX()){
									blocked = true;
									errorMessage = 0; // There is an Enemy
								}
							}
							else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
								aWall = (Wall)m.listObject.get(i);
								if(!aWall.isEstVertical()){
									for(int j = 0; j < aWall.getTaille(); j++){
										if(p.getY()-1 == aWall.getY() && p.getX() == aWall.getX() +j ){
											blocked = true;
											errorMessage = 1; // There is a Wall
										}
									}
								}
								else{
									for(int j = 0 ; j < aWall.getTaille() ; j++){
										if(p.getY() -1 == aWall.getY() + j && p.getX() == aWall.getX()){
											blocked = true;
											errorMessage = 1; // There is a Wall
										}
									}
								}																
							}
						}
						if(!blocked){
							p.setY(p.getY()-1); //se deplace d'une case vers le bas
							p.setPointAction(p.getPointAction()-1);	
						}										
					}else{
						errorMessage = 4; //You can't go further down
					}	
					break;
		case "left": case "l": case "4": 	if(p.getX()-1 >= 0 && p.getPointAction() >= 1){//se deplace vers la gauche (fonctionne de la meme maniere que le deplacement vers le haut)
						for(int i = 0; i < m.listObject.size(); i++){
							if(m.listObject.get(i).getClass().getName() == "model.Enemi"){
								if(p.getY() == m.listObject.get(i).getY() && p.getX() -1 == m.listObject.get(i).getX()){
									blocked = true;
									errorMessage = 0; // There is an Enemi
								}
							}
							else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
								aWall = (Wall)m.listObject.get(i);
								if(aWall.isEstVertical()){
									for(int j = 0; j < aWall.getTaille(); j++){
										if(p.getY() == aWall.getY() +j && p.getX()-1 == aWall.getX() ){
											blocked = true;
											errorMessage = 1; // There is a Wall
										}
									}
								}
								else{
									for(int j = 0 ; j < aWall.getTaille() ; j++){
										if(p.getY()  == aWall.getY() && p.getX() -1 == aWall.getX() + j){
											blocked = true;
											errorMessage = 1; // There is a Wall
										}
									}
								}																
							}
						}
						if(!blocked){
							p.setX(p.getX()-1); //se deplace d'une case vers la gauche
							p.setPointAction(p.getPointAction()-1);	
						}										
					}else{
						errorMessage = 5; //You can't go further down
					}	
					break;
		default:	System.out.println("direction invalid");
					break;		
		}
		switch(errorMessage){	//affiche le message d'erreur
			case 0: 	System.out.println("There is an Enemi");
						break;
			case 1: 	System.out.println("There is a Wall");
						break;
			case 2: 	System.out.println("You can't go further up");
						break;
			case 3: 	System.out.println("You can't go further right");
						break;
			case 4: 	System.out.println("You can't go further down");
						break;
			case 5: 	System.out.println("You can't go further left");
						break;
			default: 	break;
		}
	}	

	
	/**
	 * Shoot at a direction. If an enemy is in this direction and there is no Wall in front of him, he loses health. This action costs 2 action points.
	 * You can only shoot once per turn.
	 * @param direction 0 for up, 1 for right, 2 for down and 3 for left.
	 * @param plat is the board
	 * @param m is the list of all the object in the game
	 */
	public void shoot(String direction, model.Plateau plat, model.Manager m){ //direction : 0 = haut, 1 = droite, 2 = bas, 3 = gauche
		model.Enemy enem;
		model.Wall wall;
		int sauvegarde = -1; 	//enregistre la position x ou y la plus proche du joueur.
		int index = -1;			//enregdistre l'index de l'objet le plus proche du joueur
		if(!p.getHasShot() && p.getPointAction() >= 2){
			switch(direction){
			case "up": case "u": case "8": p.setHasShot(true);			//tire vers le haut
						for(int i = 0; i < m.listObject.size(); i++){ 	//on parcoure les objects
							if(m.listObject.get(i).getClass().getName() == "model.Enemy"){	//verifie si l'ojet est bien un enemy.
								enem = (model.Enemy) m.listObject.get(i);					
								if(p.getX() == enem.getX() && p.getY() < enem.getY()){	//si l'enemy se trouve sur la m�me verticale et qu'il est au dessus du joueur.
									if(sauvegarde != -1) {				//si une sauvegarde � d�ja �t� faite
										if(enem.getY()<sauvegarde){		//compare la position de la sauvegarde avec celle de l'enemy
											sauvegarde = enem.getY();	
											index = m.listObject.indexOf(enem);
										}
									}
									else {							//s'il n'y a pas de sauvegarde
										sauvegarde = enem.getY();	//enregiste la position y de l'ennemie
										index = m.listObject.indexOf(enem);		//et son index				
									}
								}
							}							
							else if(m.listObject.get(i).getClass().getName() == "model.Wall"){ //verifie si l'ojet est bien un mur.
								wall = (model.Wall) m.listObject.get(i);
								if(!wall.isEstVertical()){		//si le mur est horisontal
									if(p.getX() >= wall.getX() && p.getX() <= wall.getX() + wall.getTaille() -1 && p.getY() < wall.getY()){	 // verifie si le mur se trouve au dessus de lui et si celui-ci se trouve dans le chemin du tir									
										if(sauvegarde != -1) {			//si une sauvegarde a deja ete faite
											if(wall.getY()<sauvegarde){	//compare la position de la sauvegarde avec celle du mur.
												sauvegarde = wall.getY();
												index = m.listObject.indexOf(wall);
											}
										}
										else {							//s'il n'y a pas de sauvegarde
											sauvegarde = wall.getY();	//enregiste la position y du mur
											index = m.listObject.indexOf(wall);		//et son index	
										}
									}
								}
								else{							//si le mur est vertical
									if(p.getX() == wall.getX() && p.getY() < wall.getY()){	// verifie si le mur se trouve au dessus de lui et si celui-ci se trouve dans le chemin du tir	
										if(sauvegarde != -1) {				//si une sauvegarde a deja ete faite
											if(wall.getY()<sauvegarde){		//compare la position de la sauvegarde avec celle du mur.
												sauvegarde = wall.getY();
												index = m.listObject.indexOf(wall);
											}
										}
										else {							//s'il n'y a pas de sauvegarde
											sauvegarde = wall.getY();	//enregiste la position y du mur
											index = m.listObject.indexOf(wall);		//et son index									
										}
									}
								}
							}
						}
						p.setPointAction(p.getPointAction()-2);
						break;
			case "right": case "r": case "6": p.setHasShot(true);	//tire vers la droite (fonctionne de la m�me mani�re que le tir vers le haut)
						for(int i = 0; i < m.listObject.size(); i++){
							if(m.listObject.get(i).getClass().getName() == "model.Enemy"){
								enem = (model.Enemy) m.listObject.get(i);						
								if(p.getX() < enem.getX() && p.getY() == enem.getY()){
									if(sauvegarde != -1) {
										if(enem.getX()<sauvegarde){
											sauvegarde = enem.getX();
											index = m.listObject.indexOf(enem);
										}
									}
									else {
										sauvegarde = enem.getX();
										index = m.listObject.indexOf(enem);											
									}
								}
							}							
							else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
								wall = (model.Wall) m.listObject.get(i);
								if(wall.isEstVertical()){				// si le mur est vertical
									if(p.getX() < wall.getX()  && p.getY() >= wall.getY() && p.getY() < wall.getY() + wall.getTaille()){
										if(sauvegarde != -1) {
											if(wall.getX()<sauvegarde){
												sauvegarde = wall.getX();
												index = m.listObject.indexOf(wall);
											}
										}
										else {
											sauvegarde = wall.getX();
											index = m.listObject.indexOf(wall);
										}
									}
								}
								else{
									if(p.getX() < wall.getX() && p.getY() == wall.getY()){
										if(sauvegarde != -1) {
											if(wall.getY()<sauvegarde){
												sauvegarde = wall.getX();
												index = m.listObject.indexOf(wall);
											}
										}
										else {
											sauvegarde = wall.getX();
											index = m.listObject.indexOf(wall);											
										}
									}
								}
							}
						}
						p.setPointAction(p.getPointAction()-2);
						break;
			case "down": case "d": case "2": p.setHasShot(true); //tire vers le bas (fonctionne de la meme maniere que le tir vers le haut)
				for(int i = 0; i < m.listObject.size(); i++){ 
					if(m.listObject.get(i).getClass().getName() == "model.Enemy"){
						enem = (model.Enemy) m.listObject.get(i);						
						if(p.getX() == enem.getX() && p.getY() > enem.getY()){
							if(sauvegarde != -1) {
								if(enem.getY()>sauvegarde){
									sauvegarde = enem.getY();
									index = m.listObject.indexOf(enem);
								}
							}
							else {
								sauvegarde = enem.getY();
								index = m.listObject.indexOf(enem);											
							}
						}
					}							
					else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
						wall = (model.Wall) m.listObject.get(i);
						if(!wall.isEstVertical()){
							if(p.getX() >= wall.getX() && p.getX() <= wall.getX() + wall.getTaille() && p.getY() >= wall.getY()){
								if(sauvegarde != -1) {
									if(wall.getY()>sauvegarde){
										sauvegarde = wall.getY();
										index = m.listObject.indexOf(wall);
									}
								}
								else {
									sauvegarde = wall.getY();
									index = m.listObject.indexOf(wall);
								}
							}
						}
						else{
							if(p.getX() == wall.getX() && p.getY() > wall.getY()){
								if(sauvegarde != -1) {
									if(wall.getY()>sauvegarde){
										sauvegarde = wall.getY();
										index = m.listObject.indexOf(wall);
									}
								}
								else {
									sauvegarde = wall.getY();
									index = m.listObject.indexOf(wall);											
								}
							}
						}
					}
				}
				p.setPointAction(p.getPointAction()-2);
				break;
			case "left": case "l": case "4": p.setHasShot(true); // tire vers la gauche (fonctionne de la meme maniere que le tir vers le haut)
				for(int i = 0; i < m.listObject.size(); i++){ 
					if(m.listObject.get(i).getClass().getName() == "model.Enemy"){
						enem = (model.Enemy) m.listObject.get(i);						
						if(p.getX() > enem.getX() && p.getY() == enem.getY()){
							if(sauvegarde != -1) {
								if(enem.getX()>sauvegarde){
									sauvegarde = enem.getX();
									index = m.listObject.indexOf(enem);
								}
							}
							else {
								sauvegarde = enem.getX();
								index = m.listObject.indexOf(enem);											
							}
						}
					}							
					else if(m.listObject.get(i).getClass().getName() == "model.Wall"){
						wall = (model.Wall) m.listObject.get(i);
						if(wall.isEstVertical()){							
							System.out.println(wall.getY() + wall.getTaille());
							if(p.getX() > wall.getX()  && p.getY() >= wall.getY() && p.getY() < wall.getY() + wall.getTaille()){
								if(sauvegarde != -1) {
									if(wall.getX()>sauvegarde){
										sauvegarde = wall.getX();
										index = m.listObject.indexOf(wall);
									}
								}
								else {
									sauvegarde = wall.getX();
									index = m.listObject.indexOf(wall);
								}
							}
						}
						else{
							if(p.getX() > wall.getX() && p.getY() == wall.getY()){
								if(sauvegarde != -1) {
									if(wall.getY()>sauvegarde){
										sauvegarde = wall.getX();
										index = m.listObject.indexOf(wall);
									}
								}
								else {
									sauvegarde = wall.getX();
									index = m.listObject.indexOf(wall);											
								}
							}
						}
					}
				}
				p.setPointAction(p.getPointAction()-2);
				break;
				default:System.out.println("direction invalid");
						break;
			}
			if(sauvegarde != -1 && (m.listObject.get(index).getClass().getName() == "model.Enemy")){ //si un ennemie a ete touche
				enem = (model.Enemy) m.listObject.get(index);
				enem.setPointDeVie(enem.getPointDeVie()-1);		//reduit les points de vie de l'enemy touche de 1
				System.out.println("Enemy hit !");
				if(enem.getPointDeVie() <= 0){
					m.listObject.remove(index);
				}
			}
			else{
				System.out.println("Target missed !");
			}		
		}
		else{
			System.out.println("You can't shoot this turn anymore");
		}
	}	
	
	//THE ENEMY
		
	/**
	 * This method creates an Enemy and adds him is the game list.
	 * @param x is the X value where the Enemy is created
	 * @param y is the Y value where the Enemy is created
	 * @param m is the manager
	 */
	public static void spawnEnemy(int x, int y, Manager m) {
		if(isEmptyCase(x, y, m)) {
			m.listObject.add(new Enemy(x, y, 1, 3));
		}
	}
	
	/**
	 * Check if a case if empty or not
	 * @param x is the x value where the Enemy is going to move this turn (not his current coordinates)
	 * @param y is the y value where the Enemy is going to move this turn (not his current coordinates)
	 * @param m is the Manager. Only one in the whole game.
	 * @return true if there is no other Enemy or Player on the case. False otherwise.
	 */
	public static boolean isEmptyCase(int x, int y, Manager m) {
		for (int i=0; i<m.listObject.size(); i++) {
			// If the object we're facing is a Enemy or a Player, we can't go on that case
			if(!(m.listObject.get(i).getClass().getName().equals("model.Bonus"))){
				if (m.listObject.get(i).getX() == x && m.listObject.get(i).getY() == y) {
					return false;
				}
				if(m.listObject.get(i).getClass().getName().equals("model.Wall")){
					Wall aWall = (Wall)m.listObject.get(i);
					if(aWall.isEstVertical()){
						for(int j = 0; j < aWall.getTaille(); j++){
							if (aWall.getX() == x && aWall.getY() +j == y) {
								return false;	
							}
						}
					}
					else{
						for(int j = 0; j < aWall.getTaille(); j++){
							if (aWall.getX() +j == x && aWall.getY() == y) {
								return false;	
							}
						}
					}
					
				}
			}
		}
		return true;
	}
	
	/**
	 * The enemy will move one case towards the player to be in range of attack
	 * @param p is the player
	 * @param m is the manager
	 */
	public void towardsPlayer(model.Player p, model.Manager m) {
		int diffX = getDifference(e.getX(), p.getX());
		int diffY = getDifference(e.getY(), p.getY());
		
		//Enemy will first get on the same  Y value (line) as the player
		if (diffY > 0 && !isInRange(p, m) && isEmptyCase(e.getX(), e.getY()-1, m)) {
			e.setY(e.getY()-1);
			e.setPointAction(e.getPointAction()-1);			
		}
		else if (diffY < 0 && !isInRange(p, m) && isEmptyCase(e.getX(), e.getY()+1, m)) {
			e.setY(e.getY()+1);
			e.setPointAction(e.getPointAction()-1);
		}
		//if enemy and players are the same line (Y)
		else if (diffX > 0 && !isInRange(p, m) && isEmptyCase(e.getX()-1, e.getY(), m)) {
			e.setX(e.getX()-1);
			e.setPointAction(e.getPointAction()-1);
		}
		else if (diffX < 0 && !isInRange(p, m) && isEmptyCase(e.getX()+1, e.getY(), m)) {
			e.setX(e.getX()+1);
			e.setPointAction(e.getPointAction()-1);
		}
		//Ends the Enemy turn
		else {
			e.setPointAction(0);
		}
	}
	
	/**
	 * This method will check if the enemy is in range to attack.
	 * @param p is the player
	 * @param m is the Manager
	 * @return true if the player p is in range
	 */
	public boolean isInRange(Player p, Manager m) {
		int diffX = getDifference(e.getX(), p.getX());
		int diffY = getDifference(e.getY(), p.getY());
		boolean inRange = false;
		// The range of attack of the enemy is maximum 2 cases from himself
		if ( (diffX == 0 && ( diffY <= 3 && diffY >= -3))) {
			inRange = true;
			for (int i=0; i<m.listObject.size(); i++) {
				if(m.listObject.get(i).getClass().getName().equals("model.Wall")){
					model.Wall wall = (model.Wall) m.listObject.get(i);
					if(!wall.isEstVertical()){						
						for(int j = 0; j < wall.getTaille() && inRange; j++){
							if(diffY<= 0){
								if((p.getX() == wall.getX() + j) && (Math.abs(p.getY() - wall.getY()) < Math.abs(diffY)) && p.getY() - wall.getY() > 0){
									inRange = false;
								}
							}
							else{
								if((p.getX() == wall.getX() + j) && (Math.abs(p.getY() - wall.getY()) < Math.abs(diffY)) && p.getY() - wall.getY() < 0){
									inRange = false;
								}
							}
						}
					}
				}				
			}
		}
		if (diffY == 0 && ( diffX <= 3 && diffX >= -3)){
			inRange = true;
			for (int i=0; i<m.listObject.size(); i++) {
				if(m.listObject.get(i).getClass().getName().equals("model.Wall")){
					model.Wall wall = (model.Wall) m.listObject.get(i);
					if(wall.isEstVertical()){						
						for(int j = 0; j < wall.getTaille() && inRange; j++){
							if(wall.isEstVertical()){
								if(diffX<= 0){
									if((p.getY() == wall.getY() + j) && (Math.abs(p.getX() - wall.getX()) < Math.abs(diffX)) && p.getX() - wall.getX() > 0){
										inRange = false;
									}
								}
								else{
									if((p.getY() == wall.getY() + j) && (Math.abs(p.getX() - wall.getX()) < Math.abs(diffX)) && p.getX() - wall.getX() < 0){
										inRange = false;
									}
								}
							}
						}
					}
				}				
			}
		}
		return inRange;
	}
	
	/**
	 * Shoot at the player. If the player is in range, he loses health. The action costs 2 action points.
	 * @param p is the player
	 */
	public void shootAt(model.Player p) {
		//Shoot at player; the player loses 1 health
		p.setPointDeVie(p.getPointDeVie()-1);
		e.setPointAction(e.getPointAction()-2);
		e.setHasShot(true);
	}
	
	/**
	 * This method calculate the difference between 2 variables.
	 * @param var1 is the X or Y coordinate of the enemy
	 * @param var2 is the X or Y coordinate of the player
	 * @return the difference between the 2 coordinates
	 */
	public int getDifference (int var1, int var2) {
		return var1-var2;
	}
	
	/**
	 * This method checks if it's a enemy
	 * @param m is the manager
	 * @return true if it's not a enemy, false else
	 */
	public static boolean noEnemy(Manager m) {
		for (int i=0; i<m.listObject.size(); i++) {
			if (m.listObject.get(i).getClass().getName().equals("model.Enemy")) return false;
		}
		return true;
	}
	
	
	
	
	//THE BONUS
	
	/**
	 * This method choose where the bonus will spawn 
	 * @param x is the X coordinate of the bonus 
	 * @param y is the Y coordinate of the bonus 
	 * @param m is the manager
	 */
	public static void spawnBonus(int x, int y, Manager m) { 
		if(isNoWallOnCase(x,y,m)) {                      
			m.listObject.add(new Bonus(x,y,1));	 
		}    
	}
	
	/**
	 * This method check if there is a bonus on the case
	 * @param x is the X coordinate of the bonus
	 * @param y is the Y coordinate of the bonus
	 * @param m is the manager
	 * @return true if there is no bonus on the case, false else
	 */
	public static boolean isNoWallOnCase(int x, int y, Manager m) {
		for (int i=0; i<m.listObject.size(); i++) {
			if((m.listObject.get(i).getClass().getName().equals("model.Wall"))){
				model.Wall wall = (model.Wall) m.listObject.get(i);
				for(int j = 0; j < wall.getTaille() ; j++){
					if(wall.isEstVertical()){
						if(x == wall.getX() && y == wall.getY() + j) return false;						
					}
					else{
						if(x == wall.getX() + j && y == wall.getY()) return false;
					}
				}	
			}
		}
		return true;
	}
	
	/**
	 * This method compares the position between the bonus and the object 
	 * @param p the player compared
	 * @param b is the bonus compared
	 * @return true if the player is on the same case as the bonus, false else
	 */
	public static boolean isOnSameCase(Player p, Bonus b) {
		if (b.getX() == p.getX() && b.getY() == p.getY()) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method gives the bonus to the player
	 * @param p the player
	 * @param m is the manager
	 * @param b is the bonus
	 */
	public static void getHealthBonus(Player p, Manager m, Bonus b) {             
		if(isOnSameCase(p,b)) {
			p.setPointDeVie(p.getPointDeVie() + 1);
			b.setX(-1);
			b.setY(-1);
			m.listObject.remove(b);
		}
	}
	
	
	
	
	//THE VIEW
	
	/**
	 * Converts the positions x and y of a GameObject to the positions in pixel.
	 * @param x is the x value of a GameObject
	 * @param y is the y value of a GameObject
	 * @return the positions of the GameObject in pixel
	 */
	public int[] setPosition(int x, int y) {
		int[] position = new int[2];
		position[0] = x * 40;
		position[1] = 560 - y * 40;
		return position;
	}
	
	/**
     * This method sends information(s) to the server when the player dies.
     * The information is the level where the player dies
     */
	public void client() {
    	try {
    		Socket socket = new Socket("localhost",7469);
    		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    		boolean dead = (getP().getPointDeVie() == 0);
    		boolean guiLaunched = (view.MainGameGUI.level != 0)==true;
    		
    		if(dead) {		
    			if(guiLaunched) {
    				out.println(view.MainGameGUI.level);
    			}
    			else {
    				out.println(view.MainGameConsole.level);
    			}
    		}
    		out.close();
    		socket.close();
    	} catch (IOException e) {
    		System.out.println("Le client bug");
    	}
    }
}