package view;

import java.util.Scanner;
import java.util.Observable;
import java.util.Observer;

import model.Wall;

public class MainGameConsole extends AbstractMainGame implements Observer{
	protected static model.Player player = new model.Player(7, 0, 1, 5);
	protected static model.Enemy enn = new model.Enemy (0, 0, 1, 3);
	protected static model.Bonus bon = new model.Bonus (0, 0, 1);
	protected static controller.Controller control = new controller.Controller(bon, enn, player);
	protected model.Manager m = new model.Manager();
	public static int level = 0;
	
	public MainGameConsole (model.Player PM, model.Enemy EM, model.Bonus BM, controller.Controller c) {
        super(PM, EM, BM, c);
    }
	
	public void update(Observable o, Object arg) {		
		clearScreen();
		System.out.println("Level " + level);
		System.out.println("Life: "+ player.getPointDeVie());
		System.out.println("--------------------------------");
		System.out.println("POSITIONS");
		System.out.println("--------------------------------");
		for (int j=0; j<m.listObject.size(); j++) {
			if(m.listObject.get(j).getClass().getName().equals("model.Player")){
				System.out.println("Position of the Player: (" + m.listObject.get(j).getX() + ";" + m.listObject.get(j).getY() + ")");
				System.out.println("");
			}
			if(m.listObject.get(j).getClass().getName().equals("model.Enemy")){
				System.out.println("Position of the Enemi: (" + m.listObject.get(j).getX() + ";" + m.listObject.get(j).getY() + ")");
			}
			if(m.listObject.get(j).getClass().getName().equals("model.Bonus")){
				System.out.println("");
				System.out.println("Position of the Bonus: (" + m.listObject.get(j).getX() + ";" + m.listObject.get(j).getY() + ")");
				System.out.println("");
			}
			if(m.listObject.get(j).getClass().getName().equals("model.Wall")){
				Wall aWall = (Wall) m.listObject.get(j);
				if(aWall.isEstVertical())
					System.out.println("Wall from the case\t(" + aWall.getX() + ";" + aWall.getY() + ")\tto the case\t(" + aWall.getX() + ";"  +(aWall.getY() + aWall.getTaille() -1) + ")");
				else
					System.out.println("Wall from the case\t(" + aWall.getX() + ";" + aWall.getY() + ")\tto the case\t(" + (aWall.getX() + aWall.getTaille() -1) + ";"  +aWall.getY() + ")");
					
			}
		}
		System.out.println("--------------------------------");
		System.out.println("You have " + player.getPointAction() + " action points left");
		System.out.print("Do you want to \t1 : move \t2 : shoot \t3 : end your turn ? ");
		System.out.println();
    }
	
	public static void clearScreen() {  
		for(int i=0; i<100; i++) {
			System.out.println();  
		}
	}

	public void game(){
		Scanner sc = new Scanner(System.in);
		String line;
		final model.Plateau plat = new model.Plateau(15);
		m.listObject.add(player);
		level = 0;		
		
		// Loop of the game
		while(player.getPointDeVie() > 0) {
			int terrain = (int)(Math.random()*4);
			if (controller.Controller.noEnemy(m)) {
				//Removes all the existing bonuses
				for (int i=0; i<m.listObject.size(); i++) {
					if (m.listObject.get(i).getClass().getName().equals("model.Bonus") || m.listObject.get(i).getClass().getName().equals("model.Wall")) {
						m.listObject.remove(i);
						i--;
					}
				}
				clearScreen();
				level++;
				player.setX(7);
				player.setY(0);
				// Spawn enemys
				for (int i=0; i < (level); i++) {
					int x = 0 + (int) ((plat.getTaille() - 0) * Math.random());
					int y = 13 + (int) ((1 + plat.getTaille()-1 - 13) * Math.random());
					controller.Controller.spawnEnemy(x, y, m);
				}
				// Spawn bonus
				int x = 0 + (int) ((plat.getTaille()-1 - 0) * Math.random());
				int y = 0 + (int) ((plat.getTaille()-1 - 0) * Math.random());
				controller.Controller.spawnBonus(x, y, m);
				
				// Spawn walls
				switch (terrain) {
					case 0:
						m.listObject.add(new Wall(2, 10, 4, false)); // map 1
						m.listObject.add(new Wall(2, 5, 3, false));
						m.listObject.add(new Wall(7, 6, 3, false));
						m.listObject.add(new Wall(11, 8, 4, true));
						m.listObject.add(new Wall(8, 3, 3, false));
						break;
	
					case 1:
						m.listObject.add(new Wall(3, 3, 9, true)); // map 2
						m.listObject.add(new Wall(1, 8, 4, true));
						m.listObject.add(new Wall(10, 8, 4, false));
						m.listObject.add(new Wall(7, 3, 4, false));
						break;
	
					case 2:
						m.listObject.add(new Wall(2, 7, 4, true)); // map 3
						m.listObject.add(new Wall(5, 6, 5, true));
						m.listObject.add(new Wall(9, 6, 5, true));
						m.listObject.add(new Wall(12, 7, 4, true));
						break;
	
					case 3:
						m.listObject.add(new Wall(2, 5, 4, true)); // map 4
						m.listObject.add(new Wall(3, 10, 9, false));
						m.listObject.add(new Wall(12, 5, 4, true));
						break;
	
					default:
						System.out.println("mauvaise valeur pour le terrain !");
						break;
					}
			}

			// Each Objects will perform it's action turn by turn
			for (int i=0; i<m.listObject.size(); i++) {
				model.GameObject current = m.listObject.get(i);
				// Actions of the enemies
				if (m.listObject.get(i).getClass().getName().equals("model.Enemy")) {
					control.setE((model.Enemy)current);
					control.getE().setPointAction(3);
					control.getE().setHasShot(false);
					while(control.getE().getPointAction()>0 && !control.getE().getHasShot()) {
						if (control.getE().getHasShot()==false &&control.isInRange(player, m) && control.getE().getPointAction()>=2) {
							control.shootAt(player);
							if (control.getE().getHasShot()==true) {
								System.out.println("YOU GOT SHOT !");
							}
						}
						if(control.getE().getPointAction()>=1) {
							control.towardsPlayer(player, m);
						}						
					}
				}
				//Action of the player
				if (m.listObject.get(i).getClass().getName().equals("model.Player")) {
					model.Player me = (model.Player)current;
					me.setPointAction(5);
					me.setHasShot(false);
					while(me.getPointAction()>0) {
						line = sc.nextLine();
						if(line.equals("move") || line.equals("1")){
							System.out.print("Do you want to move \t8 : up, \t6 : right, \t2 :  down, \t4 : left ? ");
							line = sc.nextLine();
							c.move(line, plat, m);
						}
						else if(line.equals("shoot") || line.equals("2")){
							System.out.print("Do you want to shoot \t8 : up, \t6 : right, \t2 :  down, \t4 : left ? ");
							line = sc.nextLine();
							c.shoot(line, plat, m);
						}
						else if(line.equals("end") || line.equals("3")){
							System.out.println("You ended your turn");
							me.setPointAction(0);
						}
						else if(line.equals("secretCode")){
							for (int j=m.listObject.size()-1; j>=0; j--) {
								if(m.listObject.get(j).getClass().getName().equals("model.Enemy")){
									m.listObject.remove(j);
								}								
							}
						}
						else{
							System.out.println("You wrote: \"" + line + "\" instead of move or shoot.");
						}
						for(int j=0; j<m.listObject.size(); j++) {
							if (m.listObject.get(j).getClass().getName().equals("model.Bonus")) {
								model.Bonus b = (model.Bonus)m.listObject.get(j);
								controller.Controller.getHealthBonus(player, m, b);
							}
						}
					}
				}
			}
			System.out.println("--------------------------------");
		}
		if(player.getPointDeVie() == 0) System.out.println("GAME OVER : YOU'RE DEAD");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		MainGameConsole game = new MainGameConsole(player, enn, bon, control);
		while(true){
			System.out.println("Menu");
			System.out.println("-------");
			System.out.println("1. Play");
			System.out.println("2. Exit");
			line = sc.nextLine();
			if(line.equals("1")){
				game.game();
				control.client();
				sc.close();
				System.exit(0);
			}
			else if(line.equals("2")) {
				sc.close();
				System.exit(0);
			}
		}
	}
}
