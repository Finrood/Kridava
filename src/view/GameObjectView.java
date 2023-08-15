package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * This class draws the GameObjects on the graphic board.
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class GameObjectView {
	public static Image dead = Toolkit.getDefaultToolkit().createImage("src/dead.png");

	public GameObjectView(Graphics2D g2d){
		drawEnemy(g2d);
	}

	/**
	 * This method draws the GameObjects on the graphic board from their positions.
	 * @param g2d is the library Graphic2D
	 */
	public void drawEnemy(Graphics2D g2d){
		int[] playerPosition = new int[2];
		int[] position = new int[2];
		for (int i=0; i<MainGameGUI.m.listObject.size(); i++) {
			if (MainGameGUI.m.listObject.get(i).getClass().getName().equals("model.Player")) {
				playerPosition = MainGameGUI.control.setPosition(MainGameGUI.m.listObject.get(i).getX(),MainGameGUI.m.listObject.get(i).getY());
				g2d.setColor(Color.BLUE);
				g2d.fillOval(playerPosition[0], playerPosition[1], 40, 40);
			}
			
			if (MainGameGUI.m.listObject.get(i).getClass().getName().equals("model.Enemy")) {
				position = MainGameGUI.control.setPosition(MainGameGUI.m.listObject.get(i).getX(),MainGameGUI.m.listObject.get(i).getY());
				g2d.setColor(Color.RED);
				g2d.fillOval(position[0], position[1], 40, 40);
			}
			
			if (MainGameGUI.m.listObject.get(i).getClass().getName().equals("model.Bonus")) {
				position = MainGameGUI.control.setPosition(MainGameGUI.m.listObject.get(i).getX(),MainGameGUI.m.listObject.get(i).getY());
				g2d.setColor(Color.YELLOW);
				g2d.fillOval(position[0], position[1], 40, 40);
			}
			
			if (MainGameGUI.m.listObject.get(i).getClass().getName().equals("model.Wall")) {
				model.Wall aWall = (model.Wall) MainGameGUI.m.listObject.get(i);
				position = MainGameGUI.control.setPosition(aWall.getX(),aWall.getY());
				g2d.setColor(Color.LIGHT_GRAY);
				if(aWall.isEstVertical())
					g2d.fillRect(position[0], position[1] - 40 * (aWall.getTaille() - 1), 40 , 40 * aWall.getTaille());
				else
					g2d.fillRect(position[0], position[1], 40 * aWall.getTaille() , 40 );
			}
		}
		if(MainGameGUI.player.getPointDeVie() <= 0) { 
			g2d.drawImage(dead, playerPosition[0], playerPosition[1], 40, 40, null);
		}
	}
}
