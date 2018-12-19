package demin;


import demin.controller.DeminController;
import demin.model.Tableau;
import demin.view.DeminView;
import demin.view.DeminViewConsole;
import demin.view.DeminViewGUI;

public class Demin{
	public Demin() {

		//Création du modèle
		//TODO

		//Création des controleurs : un pour chaque vue
		//Chaque controleur doit avoir une référence vers le modèle pour pouvoir le commander	
		//TODO

		//Création des vues.
		//Chaque vue doit connaitre son controleur et avoir une référence vers le modèle pour pouvoir l'observer		
		//TODO

		//On donne la référence à la vue pour chaque controleur
		//TODO		
	}

	public static void main(String args[]) {
		//Thread pour la gestion des évènements dans le GUI swing (important pour éviter des bugs d'affichage ! )
		//Crée et affiche l'interface graphique
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demin();
			}
		});
	}
}
