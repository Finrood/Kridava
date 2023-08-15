package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * This class draws the GameObjects on the graphic board.
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Graphic extends JComponent {
	private static final long serialVersionUID = 1L;
	protected static boolean isGenerated = false; // generate once all the rectangles
	public static Image plateau = Toolkit.getDefaultToolkit().createImage("src/board.jpg");

	@Override
	/**
	 * This method paints the GUI
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		backGround(g2d);
		if (MainGameGUI.choice == MainGameGUI.Choice.game) {
			if (!isGenerated) {
				new GridGenerator(g2d);
			}
			new GridDraw(g2d);
			Legend.legend(g2d);
			new GameObjectView(g2d);
		} else if (MainGameGUI.choice == MainGameGUI.Choice.menu) {
			Menu.menu(g2d);
		}
	}

	/**
	 * This method paints the original window.
	 * @param g2d is the library Graphic2D
	 */
	public void backGround(Graphics2D g2d) {
		g2d.drawImage(plateau, 0, 0, MainGameGUI.window.getWidth(), MainGameGUI.window.getHeight(), null);
		MainGameGUI.window.repaint();
	}
}
