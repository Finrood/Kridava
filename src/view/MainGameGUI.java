package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.Controller;
import model.Bonus;
import model.Enemy;
import model.Player;
import model.Wall;

public class MainGameGUI extends AbstractMainGame implements Observer {
	private static final long serialVersionUID = 1L;
	final static int WIDTH = 607;
	final static int HEIGHT = 670;
	protected static model.Player player = new model.Player(7, 0, 1, 5);
	protected static model.Enemy enn = new model.Enemy(7, 14, 1, 3);
	protected static model.Bonus bon = new model.Bonus(0, 0, 1);
	protected static controller.Controller control = new controller.Controller(bon, enn, player);
	protected static model.Manager m = new model.Manager();
	protected final static model.Plateau plat = new model.Plateau(15);
	public static int level;
	protected static Choice choice = Choice.menu;
	public static JFrame window = new JFrame();
	private static Keyboard keys = new Keyboard();

	protected enum Choice {
		menu, game
	};
	
	MainGameGUI(Player PM, Enemy EM, Bonus BM, Controller c) {
		super(PM, EM, BM, c);
	}
	
	public void update(Observable o, Object arg) {
		window.repaint();
	}

	/**
	 * Launch the application.
	 * @param args are the arguments
	 */
	public static void main(String[] args) {
		window.setTitle("Kridava");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.addMouseListener(new Mouse());
		window.add(new Graphic());

		window.setVisible(true);
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (choice == Choice.game) {
				run();
			}
		}
	}

	public static void run() {
		for (int i = 0; i < m.listObject.size(); i++) {
			m.listObject.remove(i);
		}
		player.setPointDeVie(1);
		enn = new model.Enemy(7, 14, 1, 3);
		bon = new model.Bonus(0, 0, 1);
		m.listObject.add(player);
		level = 0;
		// Loop of the game
		while (player.getPointDeVie() > 0) {
			int terrain = (int) (Math.random() * 4); // get one of the maps randomely
			if (controller.Controller.noEnemy(m)) {
				// Removes all the existing bonuses and walls
				for (int i = 0; i < m.listObject.size(); i++) {
					if (m.listObject.get(i).getClass().getName().equals("model.Bonus")
							|| m.listObject.get(i).getClass().getName().equals("model.Wall")) {
						m.listObject.remove(i);
						i--;
					}
				}
				level++;
				player.setX(7);
				player.setY(0);
				
				// Spawn Enemy
				for (int i = 0; m.listObject.size() < (level + 1); i++) {
					int x = 0 + (int) ((plat.getTaille() - 0) * Math.random());
					int y = 13 + (int) ((1 + plat.getTaille() - 1 - 13) * Math.random());
					controller.Controller.spawnEnemy(x, y, m);
					enn.setX(x);
					enn.setY(y);
				}
				
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

				default:break;
				}
				
				// Spawn bonus
				int x = 0 + (int) ((plat.getTaille() - 1 - 0) * Math.random());
				int y = 0 + (int) ((plat.getTaille() - 1 - 0) * Math.random());
				controller.Controller.spawnBonus(x, y, m);								
			}

			// Each Objects will perform it's action turn by turn
			for (int i = 0; i < m.listObject.size(); i++) {
				model.GameObject current = m.listObject.get(i);
				
				// Actions of the enemies
				if (m.listObject.get(i).getClass().getName().equals("model.Enemy")) {
					control.setE((model.Enemy) current);
					control.getE().setPointAction(3);
					control.getE().setHasShot(false);
					while (control.getE().getPointAction() > 0 && !control.getE().getHasShot()) {
						if (control.getE().getHasShot() == false && control.isInRange(player, m)
								&& control.getE().getPointAction() >= 2) {
							control.shootAt(player);
							if (control.getE().getHasShot() == true) {
							}
						}
						if (control.getE().getPointAction() >= 1) {
							control.towardsPlayer(player, m);
						}
						try {
							Thread.sleep(150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (player.getPointDeVie() == 0)
							break;
					}
				}
				//Action of the player
				try {
					if (m.listObject.get(i).getClass().getName().equals("model.Player")) {
						window.addKeyListener(keys);
						player = (model.Player) current;
						player.setPointAction(5);
						player.setHasShot(false);
						while (player.getPointAction() > 0) {
							for (int j = 0; j < m.listObject.size(); j++) {
								if (m.listObject.get(j).getClass().getName().equals("model.Bonus")) {
									bon = (model.Bonus) m.listObject.get(j);
									controller.Controller.getHealthBonus(player, m, bon);
								}
							}
						}
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
				}
				window.removeKeyListener(keys);
			}
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < m.listObject.size(); i++) {
			m.listObject.remove(i); //reset the game
		}
		choice = Choice.menu;
		control.client();
	}
}