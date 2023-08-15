package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class manages the keyboards input
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Keyboard implements KeyListener{
	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
        // key events for moving
		if(key == KeyEvent.VK_Z) MainGameGUI.control.move("up", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_S) MainGameGUI.control.move("down", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_D) MainGameGUI.control.move("right", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_Q) MainGameGUI.control.move("left", MainGameGUI.plat, MainGameGUI.m);
        
     // key events for shooting
        if(key == KeyEvent.VK_UP) MainGameGUI.control.shoot("up", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_DOWN) MainGameGUI.control.shoot("down", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_RIGHT) MainGameGUI.control.shoot("right", MainGameGUI.plat, MainGameGUI.m);
        if(key == KeyEvent.VK_LEFT) MainGameGUI.control.shoot("left", MainGameGUI.plat, MainGameGUI.m);
        
      // key event for skipping your turn
        if(key == KeyEvent.VK_SPACE) MainGameGUI.player.setPointAction(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
