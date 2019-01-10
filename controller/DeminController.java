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
	
	public void showHiddenCtrl() {
		view.affiche(model.toString());
	}
	
	public void putFlagOnHiddenCtrl(int x, int y) {
		model.putFlagOnHidden(x, y);
	}
	public void putFlagOnFlagCtrl(int x, int y) {
		model.putFlagOnFlag(x, y);
	}
	public void erroPutFlag() {
		view.affiche("Vous ne pouvez pas mettre de drapeau, cette case a d�j� �t� retourn�e.");
	}
	
	
	public void showOnFlag() {
		view.affiche("Vous avez mis un drapeau sur cette case, vous ne pouvez donc pas la retourner. \n Pour la d�bloquer, r�-utiliser la commande drapeau sur la case.");
	}
	public void showAlreadyShown() {
		view.affiche("Cette case a d�j� �t� retourn�e.");
	}
	
	public void showOnBombLucky() {
		view.affiche("Tu as de la chance ! La bombe n'a pas explos�e !");
	}
	public void explosionCtrl() {
		view.affiche("Vous avez explos� !!");
		model.explosion();
	}
	
	public boolean isAllFoundCtrl() {
		boolean safe = false;
		if (safe = model.isAllFound()) {
			view.affiche("\n\n\nTu as GAGNE la partie !!! \n");
		}
		return safe;
	}
	
	public void showOnZeroCtrl(int x, int y) {
		model.showOnZero(x, y);
	}
	
	public void luckyDiscoveryBomb() {
		view.affiche("YAY ! On va maintenant d�couvrir une bombe pour toi !");
		model.RandomPosDiscoveryBomb();
	}
	
	public void showOnNumberCtrl(int x, int y) {
		model.showOnNumber(x, y);
	}
	
	public boolean isNumberCtrl(String data) {
		return model.isNumber(data);
	}
	
	
	/*
	public boolean devoilementCase(String sc) {
		boolean temp = model.devoilement(sc);
		view.affiche(model.toStringUpdate());
		return temp;
	}
	*/

	public void addView(DeminView view) {
		this.view = view;

	}

}
