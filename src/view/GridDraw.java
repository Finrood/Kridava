package view;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class GridDraw extends JComponent{
	private static final long serialVersionUID = 1L;

	public GridDraw(Graphics2D g2d) {
		drawCases(g2d);
	}
	
	/**
	 * This method draws the grid on the board.
	 * @param g2d is the library Graphic2D
	 */
	public void drawCases(Graphics2D g2d) {
		for(int i=0; i<GridCoord.coordX.length*15; i++) {
			g2d.setColor(new Color(240, 240, 240, 0));
			g2d.fillRect(GridGenerator.cases.get(i).x, GridGenerator.cases.get(i).y, GridGenerator.cases.get(i).width, GridGenerator.cases.get(i).height);
			
			g2d.setColor(new Color(0, 0, 0, 255));
			g2d.drawRect(GridGenerator.cases.get(i).x, GridGenerator.cases.get(i).y, GridGenerator.cases.get(i).width, GridGenerator.cases.get(i).height);
		}
	}
}
