package First;

import java.util.Scanner;

public class Affichage {
	
	public static boolean devoilement(Tableau aff, Tableau tab, Scanner sc) {
		System.out.println("\n#mettez - montrer ligne;colonne - pour retourner une case\n#mettez - drapeau ligne;colonne - pour mettre un drapeau sur une case");
		String rep = sc.nextLine();
		String repSplit[] = rep.split(" ");
		
		
		if(repSplit[0].equals("drapeau")) {
			
			String repSplitCooDrap[] = repSplit[1].split(";");
			int xDrap = Integer.parseInt(repSplitCooDrap[0])-1;
			int yDrap = Integer.parseInt(repSplitCooDrap[1])-1;
			aff.setCase(xDrap, yDrap, "^");
			System.out.println(aff);
		}
		
		else if(repSplit[0].equals("montre")){
			String repSplitCoo[] = repSplit[1].split(";");
			int x = Integer.parseInt(repSplitCoo[0]) - 1;
			int y = Integer.parseInt(repSplitCoo[1]) - 1;
			
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
		
		else {
			System.out.println("Vous n'avez pas entré correctement la commande");
		}
			
		return false;
	}
	
	public static boolean isAllFound(Tableau aff, Tableau tab) {
		int nbrBombTot = tab.getNombreBombes();
		int nbrCaseDevoil = 0;
		int nbrCaseMax = aff.getColonnes() * aff.getLignes();
		int nbrCaseRest = 0;
		for( int i=0; i<aff.getLignes(); i++) {
			for (int j=0; j<aff.getColonnes(); j++) {
				if (!(aff.getCase(i, j).equals("0"))) {	
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
	
	public static boolean isNumber(String value) {
		for (int i=1; i<10; i++) {
			String parse = i + "";
			if (value.equals(parse)) {
				return true;
			}
		}
		return false;
	}
	
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
	
	public static void devoilChiffres(Tableau aff, Tableau tab, int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax) {
		for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
			for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
				if(!(tab.getCase(x+i, y+j).equals("0")) && !(tab.getCase(x+i, y+j).equals("x")) && !(tab.getCase(x+i, y+j).equals("0"))) {
					aff.setCase(x+i, y+j, tab.getCase(x+i, y+j));
				}
			}
		}
	}
	
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
	
	public static void main(String [] args) {
		Tableau tab = new Tableau(0, 0, 0);
		Tableau tabVide = tab;
		Scanner sc = new Scanner(System.in);
		
		boolean verif = false;
		while (!verif) {
			System.out.println("Quel niveau de difficulté choisissez-vous ? Facile | Moyen | BADASSE | Exit");
			String resultat = sc.nextLine();
			
			if(resultat.equalsIgnoreCase("EXIT")){
				System.out.println("Vous avez quitté le jeu");
				System.exit(0);
			}
			
			if(resultat.equalsIgnoreCase("Facile")){
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
}
