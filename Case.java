
package demineur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//C'est une case de jeux en partie autonome
//Elle gère elle même certains evènements de la souris
//on peut en faire un Bean avec le fichier DéminCase.mf

@SuppressWarnings("serial")
public class Case extends JPanel implements MouseListener {

	private int etat = 0; //0 = rien; 1==enfoncée; 2=drapeau; 3=?; 4=boum; 5=mine; 6=erreur de drapeau
	private boolean mine = false; //Si il y a une mine
	private boolean selected = false; //case enfoncée
	private boolean blocked = false; //bloquée
	private int chiffre = 0; //chiffre affiché s'il doit être affiché
	private Graphisme graphisme = null; //l'objet qui contient les graphismes. Il est indiqué par setGraphisme(Graphisme)

	public Case() {
		try {
			//construction de la case
			jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setBackground(Graphisme.dessus);
		this.setMaximumSize(new Dimension(16, 16)); //On impose la taille
		this.setMinimumSize(new Dimension(16, 16));
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(16, 16));
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		//Selectionne la case si on clique dessus
		if (e.getModifiers() == 16 && etat != 1 && etat != 2 && !blocked) {
			selected = true;
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		//Déselctionne la cases
		selected = false;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		//Si la case est relevée est que la souris passe dessus avec le clic gauche, on séléctionne
		if (e.getModifiers() == 16 && etat != 1 && etat != 2 && !blocked) {
			selected = true;
			repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
		//pas fin mais efficace
		selected = false;
		repaint();
	}

	public boolean isMine() {
		return mine;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getChiffre() {
		return chiffre;
	}

	public void setChiffre(int chiffre) {
		this.chiffre = chiffre;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		this.paintComponent(this.getGraphics());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (graphisme != null) {
			if (!selected) { //case non enfoncée
				if (etat == 0) { //normal
					g.setColor(Color.white); //bordure haut et gauche blanche
					g.drawLine(0, 0, 0, 15);
					g.drawLine(0, 0, 15, 0);
				}
				else if (etat == 1) g.drawImage(Graphisme.chiffre[chiffre], 0, 0, null); //chiffre ou blanc
				else if (etat == 2) g.drawImage(Graphisme.drapeau, 0, 0, null); //drapeau
				else if (etat == 4) g.drawImage(Graphisme.boum, 0, 0, null); //mine sur fond rouge
				else if (etat == 5) g.drawImage(Graphisme.mine, 0, 0, null); //mine
			}
		}
		//g.setStroke(new BasicStroke(1.5f));
		g.setColor(Color.darkGray); //bordure bas et droite
		g.drawLine(0, 15, 15, 15);
		g.drawLine(15, 0, 15, 15);
		g.dispose();
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setGraphisme(Graphisme graphisme) {
		this.graphisme = graphisme;
	}

	public void reset() { //remise à zero des principaux paramètres
		this.etat = 0;
		this.selected = false;
		setMine(false);
		setBlocked(false);
	}
}
