package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class GridGenerator extends JComponent {
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Rectangle> cases = new ArrayList<Rectangle>();
	
	public GridGenerator(Graphics2D g2d){
		generateGrid();
		Graphic.isGenerated = true;
	}
	
	/**
	 * This method generates all of the cases
	 */
	public void generateGrid() {
		for(int i=0; i<(GridCoord.coordX.length); i++) {
			cases.add(new Rectangle(40*i, 0, 40, 40));
			cases.add(new Rectangle(40*i, 40, 40, 40));
			cases.add(new Rectangle(40*i, 80, 40, 40));
			cases.add(new Rectangle(40*i, 120, 40, 40));
			cases.add(new Rectangle(40*i, 160, 40, 40));
			
			cases.add(new Rectangle(40*i, 200, 40, 40));
			cases.add(new Rectangle(40*i, 240, 40, 40));
			cases.add(new Rectangle(40*i, 280, 40, 40));
			cases.add(new Rectangle(40*i, 320, 40, 40));
			cases.add(new Rectangle(40*i, 360, 40, 40));
			
			cases.add(new Rectangle(40*i, 400, 40, 40));
			cases.add(new Rectangle(40*i, 440, 40, 40));
			cases.add(new Rectangle(40*i, 480, 40, 40));
			cases.add(new Rectangle(40*i, 520, 40, 40));
			cases.add(new Rectangle(40*i, 560, 40, 40));
		}
	}
}
