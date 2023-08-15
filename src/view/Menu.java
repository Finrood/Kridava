package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class contains the graphic menu of the game
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Menu {
	public static Rectangle play = new Rectangle(MainGameGUI.WIDTH / 2 - 75, 150, 150, 50);
	public static Rectangle exit = new Rectangle(MainGameGUI.WIDTH / 2 - 75, 250, 150, 50);

	public static void menu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fontMenu = new Font("arial", Font.BOLD, 60);
		g.setFont(fontMenu);
		g.setColor(Color.WHITE);
		g.drawString("KRIDAVA", MainGameGUI.WIDTH / 2 - 130, 100);
		Font fontButton = new Font("arial", Font.BOLD, 32);
		g.setFont(fontButton);
		g.drawString("PLAY", play.x + 35, play.y + 35);
		g2d.draw(play);
		g.drawString("EXIT", exit.x + 42, exit.y + 35);
		g2d.draw(exit);

	}
}
