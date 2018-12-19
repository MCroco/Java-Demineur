package demin.controller;


import demin.model.Tableau;
import demin.view.DeminView;

//Les vues doivent pouvoir observer le modèle, d'où l'implémentation de l'interface java.util.Observer
// implique simplement l'implémentation d'une méthode update(), qui permerttra à l'observable 
// de dire à tous les éléments observer de se mettre à jour si besoin
//Toutes les vues ayant ce mécanisme, on le reprend ici dans une classe abstraite, 
// qui sera complétée en fonction des spécificités de la vue
public class DeminController {
	Tableau model; 
	DeminView view;
	
	public DeminController(Tableau model) {
		this.model = model;
	}

	public void emprunteLivre(int numLivre) {	
		//TODO gérer les affichage dans la vue ou les mises à jours du modèle en fonction des besoins
	}

	public void rendreLivre(int numLivre) {
		//TODO gérer les affichage dans la vue ou les mises à jours du modèle en fonction des besoins
	}

	public void addView(DeminView view) {
		this.view = view;
		
	}

}
