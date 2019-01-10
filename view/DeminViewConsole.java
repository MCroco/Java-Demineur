package demin.view;

import java.util.Observable;
import java.util.Observer;

import demin.controller.DeminController;
import demin.model.Tableau;


public class DeminViewConsole extends DeminView implements Observer { // qui implémente Observer
	// sert ici à lire en console
//	protected Scanner sc;

	public DeminViewConsole(Tableau model, DeminController controller) {
		super(model, controller);
		// se met à jour lui-même ici
		// paramètres fourni habituellement lors de l'appel par l'Observable pas nécessaires ici
		update(null, null);
//		sc = new Scanner(System.in);
		// La classe interne ReadInput implémente Runnable, contient donc une méthode run() 
		// et peut donc être lancée comme Thread
		new Thread (new ReadInput()).start();	
	}

	// update() de l'interface Observer
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.toStringUpdate());
		printHelp();		
	}


	private void printHelp(){
		affiche("\n#mettez - montrer ligne;colonne - pour retourner une case");
		affiche("\n#mettez - drapeau ligne;colonne - pour mettre un drapeau sur une case");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true) {
				String rep = model.luck.nextLine();
				String data[] = rep.split(" ");
				
				
				if (data[0].equalsIgnoreCase("nathan")) {
					controller.showHiddenCtrl();
				}
				else {
					String coord[] = data[1].split(";");
					
					int x = Integer.parseInt(coord[0]) -1;
					int y = Integer.parseInt(coord[1]) -1;
					
					String dataHidden = model.getCase(x, y);
					String dataHiddenSep[] = dataHidden.split(" ");
					if (data[0].equals("drapeau")) {
						if (dataHiddenSep[1].equals("-")) {
							controller.putFlagOnHiddenCtrl(x, y);
						}
						else if (dataHiddenSep[1].equals("^")) {
							controller.putFlagOnFlagCtrl(x, y);
						}
						else if (dataHiddenSep[1].equals("*")) {
							controller.erroPutFlag();
						}
					}
					else if (data[0].equals("montrer")){
						if (dataHiddenSep[1].equals("^")) {
							controller.showOnFlag();
						}
						else if (dataHiddenSep[1].equals("*")) {
							controller.showAlreadyShown();
						}
						else {
							if (dataHidden.equals("x -")) {
								if ((Math.random()*100)<40) {
									controller.showOnBombLucky();
								}
								else {
									controller.explosionCtrl();
									break;
								}
							}
							else if (dataHidden.equals("0 -")) {
								controller.showOnZeroCtrl(x, y);
								if (controller.isAllFoundCtrl()) {
									break;
								}
								else if ((Math.random()*100)<30) {
									controller.luckyDiscoveryBomb();
								}
							}
							else if (controller.isNumberCtrl(dataHiddenSep[0])){
								controller.showOnNumberCtrl(x, y);
								if (controller.isAllFoundCtrl()) {
									break;
								}
							}
						}
					}
				}
			}
			model.luck.close();
		}	
	}
	

	@Override
	public void affiche(String string) {
		System.out.println(string);
	}

}