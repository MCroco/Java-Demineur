package demin.view;


import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import demin.controller.DeminController;
import demin.model.Tableau;


public class DeminViewConsole extends DeminView { // qui impl�mente Observer
	// sert ici � lire en console
	protected Scanner sc;

	public DeminViewConsole(Tableau model,
			DeminController controller) {
		super(model, controller);
		// se met � jour lui-m�me ici
		// param�tres fourni habituellement lors de l'appel par l'Observable pas n�cessaires ici
		update(null, null);
		sc = new Scanner(System.in);
		// La classe interne ReadInput impl�mente Runnable, contient donc une m�thode run() 
		// et peut donc �tre lanc�e comme Thread
		new Thread (new ReadInput()).start();	
	}

	// update() de l'interface Observer
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);
		//TODO
		//printHelp();		
	}

	
	//TODO
	/*private void printHelp(){
		affiche("Pour emprunter : E + numéro de livre.");
		affiche("Pour rendre : R + numéro de livre.");
	}*/


	@Override
	public void affiche(String string) {
		System.out.println(string);

	}

}


