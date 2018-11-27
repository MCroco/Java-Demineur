package first;

import java.util.Scanner;

public class Affichage {
	
	public static void devoilement(Tableau aff, Tableau tab, Scanner sc) {
		System.out.println("Entrez les coordonnées de la case à retourner (Ligne;Colonne)");
		String rep = sc.nextLine();
		String temp[] = rep.split(";");
		
		int x = Integer.parseInt(temp[0]) - 1;
		int y = Integer.parseInt(temp[1]) - 1;
		
		String value = tab.getCase(x, y);
		
		aff.setCase(x, y, value);
		System.out.println(aff);
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
			devoilement(tabVide, tab, sc);
		}
	}
}
