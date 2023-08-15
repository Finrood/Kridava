package view;

import java.util.Observer;
import javax.swing.JFrame;

/**
 * This class defines the basics (player, enemy, bonus, controller) of the game
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public abstract class AbstractMainGame extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	protected model.Player PM;
	protected model.Enemy EM;
	protected model.Bonus BM;
	protected controller.Controller c;

    AbstractMainGame(model.Player PM, model.Enemy EM, model.Bonus BM, controller.Controller c) {
        this.PM = PM;
        this.EM = EM;
        this. BM = BM;
        this.c = c;
        PM.addObserver(this);
    }

}
