package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class manages the mouse input
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Mouse implements MouseListener {

	int mouseLocation[] = { 0, 0 };

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		// If you are in the menu, you can click on play or exit. Else, you cannot.
		if (MainGameGUI.choice == MainGameGUI.Choice.menu) {
			if (mouseX >= MainGameGUI.WIDTH / 2 - 75 && mouseX <= MainGameGUI.WIDTH / 2 + 76) {
				if (mouseY >= 175 && mouseY <= 225) {
					MainGameGUI.choice = MainGameGUI.Choice.game;
				}
			}

			if (mouseX >= MainGameGUI.WIDTH / 2 - 75 && mouseX <= MainGameGUI.WIDTH / 2 + 76) {
				if (mouseY >= 275 && mouseY <= 325) {
					System.exit(1);
				}
			}			
		}
		else{
			// If you click on the button 'pass', you'll end your turn
			if (mouseX >= Legend.pass.x && mouseX <= Legend.pass.x + Legend.pass.width) {
				if (mouseY >= Legend.pass.y + Legend.pass.height && mouseY <= Legend.pass.y + 2 * Legend.pass.height) {
				MainGameGUI.player.setPointAction(0);
				}
			}
		}
			
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
