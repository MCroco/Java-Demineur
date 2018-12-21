package demin.view;

import java.util.Observer;


import demin.controller.DeminController;
import demin.model.Tableau;


	public abstract class DeminView implements Observer{

		protected Tableau model;
		protected DeminController controller;


		DeminView(Tableau model, DeminController controller) {
			this.model = model;
			this.controller = controller;

			model.addObserver(this);
		}


		public abstract void affiche(String string) ;
	}