package first;

/**
 * 
 * Import of the class scanner so the program can use what the user will enter via the keyboard.
 *
 */

import java.util.Scanner;

/**
 * 
 * This class is showing in the console the grid for the mine-sweeper,
 * Its main goal is to show the console interaction with the user.
 * It's showing questions about how the game will evolve and it transmits messages.
 *
 * 
 * @author Group 14 -2TL2
 * 
 */

public class Affichage{
	/**
	 * 
	 * 
	 	* Goal - putting the program together by calling the methods that are needed so everything can run smoothly.
	 * <p>It creates a new scanner which will asks for different choices of the user.</p>
	 * <p>One the choices are clear it calls the constructors of the other classes.</p>
	 * <p>If the choice is not well written an error message will appear.</p>
	 * <p>At the end of game the scanner is closed.</p>
	 * 
	 * 
	 */
	
	public static void main(String [] args) {
		Tableau tab = new Tableau(0, 0, 0);
		Scanner sc = new Scanner(System.in);
		
		boolean verif = false;
		while (!verif) {
			System.out.println("Quel niveau de difficulté choisissez-vous ? Facile | Moyen | BADASSE | Exit");
			String resultat = sc.nextLine();
			
			if(resultat.equalsIgnoreCase("Facile")){
				tab = new Tableau(10, 15, 10);
				System.out.println(tab.toStringUpdate());
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("Moyen")){
				tab = new Tableau(20, 30, 40);
				System.out.println(tab.toStringUpdate());
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("BADASSE")){
				tab = new Tableau(30, 50, 250);
				System.out.println(tab.toStringUpdate());
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("Exit")) {
				break;
			}
			else {
				System.out.println("Vous n'avez entré aucune des possibilités proposées.");
			}
		}
		boolean verif2 = false;
		while(!verif2) {
			verif2 = tab.devoilement(sc);
		}
		sc.close();
	}
	
	/**
	 * 
	 * Goal - ask the user to put a flag or to do an action to a specific box of the grid 
	 * 
	 * @param aff - it receives the coordinates from the scanner and it shows in the console to the user
	 * 				<p>		If the user chooses a flag than in the console and the given coordinates it will show <b> ^ </b></p>
	 * @param tab - it receives the coordinates from the scanner and it checks the mother table for the data
	 * @param sc - the answer has to be written in the exact same form as in the question
	 * @param sc - the case number have to be one number which exists in the grid
	 * @return true if it is a bomb
	 * 
	 */
}
