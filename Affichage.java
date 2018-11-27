package first;

import java.util.Scanner;

public class Affichage {
	
	public static void devoilement(Tableau aff, Tableau tab) {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Entrez les coordonnées de la case à retourner (Ligne;Colonne)");
		String rep = sc2.nextLine();
		String temp[] = rep.split(";");
		
		sc2.close();
		
		int x = Integer.parseInt(temp[0]);
		int y = Integer.parseInt(temp[1]);
		
		String value = tab.getCase(x, y);
		
		aff.setCase(x, y, value);
		System.out.println(aff);
	}	
	
	public static void main(String [] args) {
		Tableau tab = new Tableau(0, 0, 0);
		Tableau tabVide = tab;
		
		boolean verif = false;
		while (!verif) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Quel niveau de difficulté choisissez-vous ? Facile | Moyen | BADASSE | Exit");
			String resultat = sc.nextLine();
			sc.close();
			
		
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
			devoilement(tabVide, tab);
		}
	}
}
