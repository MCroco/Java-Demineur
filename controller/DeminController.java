package demin.controller;


import demin.model.Tableau;
import demin.view.DeminView;

//Les vues doivent pouvoir observer le mod�le, d'o� l'impl�mentation de l'interface java.util.Observer
// implique simplement l'impl�mentation d'une m�thode update(), qui permerttra � l'observable 
// de dire � tous les �l�ments observer de se mettre � jour si besoin
//Toutes les vues ayant ce m�canisme, on le reprend ici dans une classe abstraite, 
// qui sera compl�t�e en fonction des sp�cificit�s de la vue
public class DeminController {
	Tableau model; 
	DeminView view;
	
	public DeminController(Tableau model) {
		this.model = model;
	}

	public void emprunteLivre(int numLivre) {	
		//TODO g�rer les affichage dans la vue ou les mises � jours du mod�le en fonction des besoins
	}

	public void rendreLivre(int numLivre) {
		//TODO g�rer les affichage dans la vue ou les mises � jours du mod�le en fonction des besoins
	}

	public void addView(DeminView view) {
		this.view = view;
		
	}

}
