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

public class Affichage {


	
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
		Tableau tabVide = tab;
		Scanner sc = new Scanner(System.in);

		boolean verif = false;
		while (!verif) {
			System.out.println("Quel niveau de difficulté choisissez-vous ? Facile | Moyen | BADASSE | Exit");
			String resultat = sc.nextLine();

			if(resultat.equalsIgnoreCase("Exit")) {
				System.out.println("Vous avez quitté le jeu.");
				System.exit(0);
			}
			else if(resultat.equalsIgnoreCase("Facile")){
				tab = new Tableau(10, 15, 10);
				tabVide = new Tableau(10, 15, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("Moyen")){
				tab = new Tableau(20, 30, 40);
				tabVide = new Tableau(20, 30, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("BADASSE")){
				tab = new Tableau(30, 50, 250);
				tabVide = new Tableau(30, 50, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
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
			verif2 = devoilement(tabVide, tab, sc);
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

	public static boolean devoilement(Tableau aff, Tableau tab, Scanner sc) {
		System.out.println("Entrez les coordonnées de la case à retourner (Ligne;Colonne) ou mettez \"drapeau\"\npour mettre un drapeau sur un case");
		String rep = sc.nextLine();

		if(rep.equalsIgnoreCase("drapeau")) {
			
			System.out.println("Entrez les coordonnées de la case où vous voulez mettre un drapeau (Ligne;Colonne)");
			String drap = sc.nextLine();
			String cooDrap[] = drap.split(";");
			int xDrap = Integer.parseInt(cooDrap[0])-1;
			int yDrap = Integer.parseInt(cooDrap[1])-1;
			aff.setCase(xDrap, yDrap, "^");
			System.out.println(aff);

		}
		else {
			String temp[] = rep.split(";");

			int x = Integer.parseInt(temp[0]) - 1;
			int y = Integer.parseInt(temp[1]) - 1;

			String value = tab.getCase(x, y);

			if (value.equals("x")) {
				System.out.println("Vous avez perdu !!");
				for (int i=0; i<tab.getLignes(); i++) {
					for (int j=0; j<tab.getColonnes(); j++) {
						if (tab.getCase(i, j).equals("x")) {
							aff.setCase(i, j, "x");
						}
					}
				}
				System.out.println(aff.toString());
				return true;
			}

			else if (value.equals("0")) {
				chaineDevoil(aff, tab, x, y);
				if (isAllFound(aff, tab)) {
					System.out.println("Tu as GAGNE la partie !!! \n");
					System.out.println(aff);
					return true;
				}
				else {
					System.out.println(aff);
				}
			}

			else if (isNumber(value)) {
				aff.setCase(x, y, tab.getCase(x, y));
				if (isAllFound(aff, tab)) {
					System.out.println("\n\n\nTu as GAGNE la partie !!! \n");
					System.out.println(aff);
					return true;
				}
				else {
					System.out.println(aff);
				}
			}
		}

		return false;
	}


/**
 * 
 	* This method checks if the value of the chosen box is a number between 1 to 10.
 * 
 * 
 * @param value has to be between 1 - 10 and if it is different it means that the value of the box is not a number.
 * @return true if the value is between 1 - 10
 * @return false if the value is <b>NOT</b> between 1 -10
 */

	public static boolean isNumber(String value) {
		for (int i=1; i<10; i++) {
			String parse = i + "";
			if (value.equals(parse)) {
				return true;
			}
		}
		return false;
	}

	
	
	/**
	 * 
	 * This method receives the coordinates entered by the user via the scanner.
	 * It checks if the value of the box equals zero.
	 * If it finds another zero it will put it in a variable that will be used for the cascade check.
	 * The cascade check will see if there are any other zero valued boxes near the one that it just found.
	 * 
	 * @param aff - table that is shown to the user
	 * @param tab - mother table that is used as reference for the hole game
	 * @param x the coordinates of the table on the x - horizontally
	 * @param y the coordinates of the table on the y - vertically
	 * 
	 * 
	 */

	public static void devoilZero (Tableau aff, Tableau tab, int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax) {
		Position temp;
		boolean done = false;
		Position [][] savePosZero = new Position[3][3];
		aff.setCase(x, y, " ");
		while(!done) {
			for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
				for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
					if(tab.getCase(x+i, y+j).equals("0") && Math.abs(i) != Math.abs(j)) {
						tab.setCase(x+i, y+j, " ");
						aff.setCase(x+i, y+j, " ");
						temp = new Position();
						temp.setLigne(x+i);
						temp.setColonne(y+j);
						savePosZero[i+1][j+1] = temp;
					}
				}
			}
			done = devoilZeroCascade(savePosZero, aff, tab);
		}
	}

	

	public static boolean devoilZeroCascade(Position [][] arg, Tableau aff, Tableau tab) {		
		for (int i=0; i<arg.length; i++) {
			for (int j=0; j<arg[i].length; j++) {
				if (arg[i][j] != null) {
					chaineDevoil(aff, tab, arg[i][j].getLigne(), arg[i][j].getColonne());
				}
			}
		}
		return true;
	}

	
	/**
	 * 
	 * This method will show every number that is around a group of zero valued boxes.
	 * It does so by checking the coordinates around every zero and if it is a number in the mother table (tab)
	 * it will be showed in the display table (aff).
	 * 
	 * @param aff - table that is shown to the user
	 * @param tab - mother table that is used as reference for the hole game
	 * @param x the coordinates of the table on the x - horizontally
	 * @param y the coordinates of the table on the y - vertically
	 *  
	 */
	public static void devoilChiffres(Tableau aff, Tableau tab, int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax) {
		for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
			for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
				if(!(tab.getCase(x+i, y+j).equals("0")) && !(tab.getCase(x+i, y+j).equals("x")) && !(tab.getCase(x+i, y+j).equals("0"))) {
					aff.setCase(x+i, y+j, tab.getCase(x+i, y+j));
				}
			}
		}
	}

	
	
	/**
	 * 
	 * This methods permits to check the cases around and it checks if it is a number or a zero.
	 * The check is made 8 times for the 8 boxes around the chosen box.
	 * 
	 * @param aff - table that is shown to the user
	 * @param tab - mother table that is used as reference for the hole game
	 * @param x the coordinates of the table on the x - horizontally
	 * @param y the coordinates of the table on the y - vertically
	 * 
	 * 
	 */
	public static void chaineDevoil(Tableau aff, Tableau tab, int x, int y) {
		int xCond = x+1;
		int yCond = y+1;

		if (xCond!=1 && yCond!=1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 2, -1, 2);
			devoilZero(aff, tab, x, y, -1, 2, -1, 2);
		}
		else if (xCond==1 && yCond!=1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, 0, 2, -1, 2);
			devoilZero(aff, tab, x, y, 0, 2, -1, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond==tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 1, -1, 2);			
			devoilZero(aff, tab, x, y, -1, 1, -1, 2);
		}
		else if (xCond!=1 && yCond==1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 2, 0, 2);
			devoilZero(aff, tab, x, y, -1, 2, 0, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond!=tab.getLignes() && yCond==tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 2, -1, 1);
			devoilZero(aff, tab, x, y, -1, 2, -1, 1);
		}
		else if (xCond==1 && yCond==1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, 0, 2, 0, 2);
			devoilZero(aff, tab, x, y, 0, 2, 0, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond==tab.getLignes() && yCond==tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 1, -1, 1);
			devoilZero(aff, tab, x, y, -1, 1, -1, 1);
		}
		else if (xCond!=1 && yCond==1 && xCond==tab.getLignes() && yCond!=tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, -1, 1, 0, 2);
			devoilZero(aff, tab, x, y, -1, 1, 0, 2);
		}
		else if (xCond==1 && yCond!=1 && xCond!=tab.getLignes() && yCond==tab.getColonnes()) {
			devoilChiffres(aff, tab, x, y, 0, 2, -1, 1);
			devoilZero(aff, tab, x, y, 0, 2, -1, 1);
		}
	}



	
	/**
	 * 
	 * Goal - recover the full number of bombs from the mother table which is hidden.
	 * Check the maximum number of available boxes.
	 * Verifies that the number of the unveiled boxes is equal to the number of 
	 * possible boxes, and if it is the case the game is won.
	 * 
	 * @param aff
	 * @param tab
	 * @return true and the game ends - you win
	 * @return false - the game is still running because you still have boxes to unveil
	 */
	public static boolean isAllFound(Tableau aff, Tableau tab) {
		int nbrBombTot = tab.getNombreBombes();
		int nbrCaseDevoil = 0;
		int nbrCaseMax = aff.getColonnes() * aff.getLignes();
		int nbrCaseRest = 0;
		for( int i=0; i<aff.getLignes(); i++) {
			for (int j=0; j<aff.getColonnes(); j++) {
				if (!(aff.getCase(i, j).equals("0"))&&!(aff.getCase(i, j).equals("^"))) {	
					nbrCaseDevoil++;
				}
			}
		}

		nbrCaseRest = nbrCaseMax - nbrCaseDevoil;
		if (nbrBombTot == nbrCaseRest) {
			return true;
		}
		return false;
	}
}