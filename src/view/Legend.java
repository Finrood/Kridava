package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class contains the in-game informations for the player (life, level, etc)
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Legend {
	public static Rectangle pass = new Rectangle(525, 610, 60, 26);
	
	/**
	 * This method draws the in-game useful informations for the player.
	 * @param g is the library Graphics
	 */
	public static void legend(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.WHITE);
		Font fontButton = new Font("arial", Font.BOLD, 16);
		g.setFont(fontButton);
		g.drawString("Level: " + MainGameGUI.level, 25, 630);
		g.drawString("Lives: " + MainGameGUI.player.getPointDeVie(), 125, 630);
		g.drawString("Action points: " + MainGameGUI.player.getPointAction(), 225, 630);
		if(MainGameGUI.player.getHasShot())	g.drawString("Bullets: 0" , 400, 630);
		else								g.drawString("Bullets: 1" , 400, 630);
		g.setColor(Color.RED);
		g.fillRect(pass.x, pass.y, pass.width, pass.height);
		g.setColor(Color.WHITE);
		g.drawString("PASS", 535, 630);
		g2d.draw(pass);
	}
}
