package demin;


import demin.controller.DeminController;
import demin.model.Tableau;
import demin.view.DeminView;
import demin.view.DeminViewConsole;
import demin.view.DeminViewGUI;

public class Demin{
	public Demin() {

		//Cr�ation du mod�le
		//TODO

		//Cr�ation des controleurs : un pour chaque vue
		//Chaque controleur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander	
		//TODO

		//Cr�ation des vues.
		//Chaque vue doit connaitre son controleur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer		
		//TODO

		//On donne la r�f�rence � la vue pour chaque controleur
		//TODO		
	}

	public static void main(String args[]) {
		//Thread pour la gestion des �v�nements dans le GUI swing (important pour �viter des bugs d'affichage ! )
		//Cr�e et affiche l'interface graphique
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demin();
			}
		});
	}
}
