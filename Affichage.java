package first;

import java.util.Scanner;

public class Affichage {
	
	private static int ligne;
	private static int colonne;
	static boolean verif2 = false;
	
	public static void devoilement(Tableau aff, Tableau tab, Scanner sc) {
		System.out.println(("Entrez les coordonnées de la case à retourner (Ligne(max = "+ligne+");Colonne(max = " + colonne + ")"));
		String rep = sc.nextLine();
		String temp[] = rep.split(";");
		
		int x = Integer.parseInt(temp[0]) - 1;
		int y = Integer.parseInt(temp[1]) - 1;
		
		String value = tab.getCase(x, y);
		System.out.println(value);

		
		if(value.equals("%")) {
			
			System.out.println("Perdu!");
			verif2=true;
			for(int i=0; i<ligne;i++) {
				for(int j=0; j<colonne;j++) {
					if(tab.getCase(i, j).equals("%")) {

						String value2 = tab.getCase(i, j);
						aff.setCase(i, j, value2);
					}
				}
			}
			System.out.println(aff);
		}
		else if(value.equals("0")){
	
					
			algoRetrun(aff, tab, x, y);
			
		}
		else {
			
			aff.setCase(x, y, tab.getCase(x, y));
		}
		
		System.out.println(aff);
		
		
}
	
	public static void algoRetrun(Tableau aff, Tableau tab, int x, int y) {
		for(int i=-1; i<2; i++) {
			
			for(int j=-1; j<2; j++) {
				
				if(tab.getCase(x+i, y+j).equals("0")) {
					aff.setCase(x+i, y+j, " ");
				}
				else aff.setCase(x+i, y+j, tab.getCase(x+i, y+j));
			}
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
			
		
			if(resultat.equalsIgnoreCase("Facile")){
				tab = new Tableau(10, 15, 10);
				tabVide = new Tableau(10, 15, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
				ligne = 10;
				colonne = 15;
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("Moyen")){
				tab = new Tableau(20, 30, 40);
				tabVide = new Tableau(20, 30, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
				ligne = 20;
				colonne = 30;
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("BADASSE")){
				tab = new Tableau(30, 50, 250);
				tabVide = new Tableau(30, 50, 0);
				System.out.println(tab.toString());
				System.out.println(tabVide.toString());
				ligne = 30;
				colonne = 35;
				verif = true;
			}
			else if(resultat.equalsIgnoreCase("Exit")) {
				break;
			}
			else {
				System.out.println("Vous n'avez entré aucune des possibilités proposées.");
			}
		}
		while(!verif2) {
			

			devoilement(tabVide, tab, sc);
			
		}
	}
}
