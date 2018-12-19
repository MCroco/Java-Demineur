package demin.view;


import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import demin.controller.DeminController;
import demin.model.Tableau;


public class DeminViewConsole extends DeminView { // qui implémente Observer
	// sert ici à lire en console
	protected Scanner sc;

	public DeminViewConsole(Tableau model,
			DeminController controller) {
		super(model, controller);
		// se met à jour lui-même ici
		// paramètres fourni habituellement lors de l'appel par l'Observable pas nécessaires ici
		update(null, null);
		sc = new Scanner(System.in);
		// La classe interne ReadInput implémente Runnable, contient donc une méthode run() 
		// et peut donc être lancée comme Thread
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
		affiche("Pour emprunter : E + numÃ©ro de livre.");
		affiche("Pour rendre : R + numÃ©ro de livre.");
	}*/


	@Override
	public void affiche(String string) {
		System.out.println(string);

	}

}


