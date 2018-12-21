package demin;


import java.io.IOException;

import demin.controller.DeminController;
import demin.model.Tableau;
import demin.view.DeminView;
import demin.view.DeminViewConsole;
import demin.view.DeminViewGUI;

public class Demin{
	public Demin() throws IOException{

		//Cr�ation du mod�le
 		Tableau tableau =  new Tableau();

		//Cr�ation des controleurs : un pour chaque vue
		//Chaque controleur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander	
		DeminController ctrlConsole = new DeminController(tableau);
//		DeminController ctrlGUI = new DeminController(tableau);

		//Cr�ation des vues.
		//Chaque vue doit connaitre son controleur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer		
		DeminView console = new DeminViewConsole(tableau, ctrlConsole);
//		DeminView gui = new DeminViewGUI(tableau, ctrlGUI);

		//On donne la r�f�rence � la vue pour chaque controleur
		ctrlConsole.addView(console);
//		ctrlGUI.addView(gui);
	}

	public static void main(String args[]) throws IOException{
		//Thread pour la gestion des �v�nements dans le GUI swing (important pour �viter des bugs d'affichage ! )
		//Cr�e et affiche l'interface graphique
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Demin();
				}
				catch (IOException e) {
					
				}
			}
		});
	}
}
