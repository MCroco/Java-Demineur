package first;

import java.util.Scanner;

public class Tableau {
	private String [][] tab;
	private Position [] pos;
	public int colonnes;
	public int lignes;
	public int nombreBombes;
	
	public Tableau(int lignes, int colonnes, int nbrBomb) {
		this.lignes = lignes;
		this.colonnes = colonnes;
		this.nombreBombes = nbrBomb;
		pos = new Position[nbrBomb];
		for (int i = 0; i<nbrBomb; i++) {
			pos[i] = new Position(lignes, colonnes);
		}
		tab = new String[lignes][colonnes];
		remplissageBomb();
		remplissageTableau();
	}

	public String getCase(int x, int y) {
		return tab[x][y];
	}
	
	public void setCase(int x, int y, String setter) {
		String part[] = tab[x][y].split(" ");
		tab[x][y] = part[0] + " " + setter;
	}

	public int getNombreBombes() {
		return nombreBombes;
	}
	
	public int getLignes() {
		return this.lignes;
	}
	
	public int getColonnes() {
		return this.colonnes;
	}


	public void setNombreBombes(int nombreBombes) {
		this.nombreBombes = nombreBombes;
	}
	
	public Position [] getBombe () {
		return this.pos;
	}



	public String[][] getTab() {
		return tab;
	}
	public void setTab(String[][] tab2) {
		tab = tab2;
	}

	public boolean isBomb(int li, int co) {
		if (tab[li][co] == null) {
			return false;
		}
		if (tab[li][co].equals("x -")) {
			return true;
		}
		return false;
	}
	
	public void remplissageBomb() {
		for (int i=0; i<pos.length;i++) {
			Position temp = pos[i];
			tab[temp.getLigne()][temp.getColonne()] = "x -";
		}
	}
	
	public void remplissageTableau() {
		int count = 0;
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				count = 0;
				if (!isBomb(i, j)) {
					if (i==0) {
						if (j==0) {
							count += coinHautGauche(i, j);
						}
						else if (j == tab[i].length-1) {
							count += coinHautDroit(i, j);
						}
						else {
							count += millieuHorizontalHaut(i, j);
						}
					}
					else if (i==tab.length-1) {
						if (j==0) {
							count += coinBasGauche(i, j);
						}
						else if (j==tab[i].length-1) {
							count += coinBasDroit(i, j);
						}
						else {
							count += millieuHorizontalBas(i, j);
						}
					}
					else if (j==0) {
						count += millieuVerticalGauche(i, j);
					}
					else if (j==tab[i].length-1) {
						count += millieuVerticalDroit(i, j);
					}
					else {
						count += millieu(i, j);
					}
					tab[i][j] = Integer.toString(count) + " -";
				}
			}
		}
	}
	
	public int millieuHorizontalHaut(int i, int j) {
		int countMillieux = 0;
		if (isBomb(i,j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1,j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1,j)) {
			countMillieux++;
		}
		if (isBomb(i+1,j-1)) {
			countMillieux++;
		}
		if (isBomb(i,j-1)) {
			countMillieux++;
		}
		return countMillieux;
	}
	public int millieuVerticalGauche(int i, int j) {
		int countMillieux = 0;
		if (isBomb(i-1,j)) {
			countMillieux++;
		}
		if (isBomb(i-1,j+1)) {
			countMillieux++;
		}
		if (isBomb(i,j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1,j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1,j)) {
			countMillieux++;
		}
		return countMillieux;
	}
	public int millieuHorizontalBas(int i, int j) {
		int countMillieux = 0;
		if (isBomb(i, j-1)) {
			countMillieux++;
		}
		if (isBomb(i-1, j-1)) {
			countMillieux++;
		}
		if (isBomb(i-1, j)) {
			countMillieux++;
		}
		if (isBomb(i-1, j+1)) {
			countMillieux++;
		}
		if (isBomb(i, j+1)) {
			countMillieux++;
		}
		return countMillieux;
	}
	public int millieuVerticalDroit(int i, int j) {
		int countMillieux = 0;
		if (isBomb(i+1, j)) {
			countMillieux++;
		}
		if (isBomb(i+1, j-1)) {
			countMillieux++;
		}
		if (isBomb(i, j-1)) {
			countMillieux++;
		}
		if (isBomb(i-1, j-1)) {
			countMillieux++;
		}
		if (isBomb(i-1, j)) {
			countMillieux++;
		}
		return countMillieux;
	}
	public int coinHautGauche(int i, int j) {
		int countCoin = 0;
		if (isBomb(i,j+1)) {
			countCoin++;
		}
		if (isBomb(i+1,j+1)) {
			countCoin++;
		}
		if (isBomb(i+1,j)) {
			countCoin++;
		}
		return countCoin;
	}
	public int coinHautDroit(int i, int j)  {
		int countCoin = 0;
		if (isBomb(i+1, j)) {
			countCoin++;
		}
		if (isBomb(i+1, j-1)) {
			countCoin++;
		}
		if (isBomb(i, j-1)) {
			countCoin++;
		}
		return countCoin;
	}
	public int coinBasGauche(int i, int j) {
		int countCoin = 0;
		if (isBomb(i-1, j)) {
			countCoin++;
		}
		if (isBomb(i-1, j+1)) {
			countCoin++;
		}
		if (isBomb(i, j+1)) {
			countCoin++;
		}
		return countCoin;
	}
	public int coinBasDroit(int i, int j) {
		int countCoin = 0;
		if (isBomb(i-1, j)) {
			countCoin++;
		}
		if (isBomb(i-1, j-1)) {
			countCoin++;
		}
		if (isBomb(i, j-1)) {
			countCoin++;
		}
		return countCoin;
	}
	public int millieu(int i, int j) {
		int countMillieux = 0;
		if (isBomb(i-1, j-1)) {
			countMillieux++;
		}
		if (isBomb(i-1, j)) {
			countMillieux++;
		}
		if (isBomb(i-1, j+1)) {
			countMillieux++;
		}
		if (isBomb(i, j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1, j+1)) {
			countMillieux++;
		}
		if (isBomb(i+1, j)) {
			countMillieux++;
		}
		if (isBomb(i+1, j-1)) {
			countMillieux++;
		}
		if (isBomb(i, j-1)) {
			countMillieux++;
		}
		return countMillieux;
	}

	@Override
	public String toString() {
		String temp= "";
		for (int i=0; i<tab.length; i++) {
			temp += "\n";
			if (i==0) {
				temp += "  ";
				for (int m = 0; m<tab[i].length; ++m) {
					if(m<9){
						temp += " 0"+ (m+1) + "";
					}
					else if (m==9) {
						temp += " " + (m+1) + " ";
					}
					else{
						temp +=  (m+1) + " ";
					}
				}
			}
			temp += "\n";
			if(i<9){
				temp += i+1 + " ";
			}
			else{
				temp += i+1;
			}
			
			for (int j=0; j<tab[i].length; j++) {
				
				if(tab[i][j].equals("0")){
					temp+= "[+]";
				}
				else{
					temp += "[" + tab[i][j] + "]";
				}				
			}
		}
		return temp;
		
	}
	
	public boolean devoilement(Scanner sc) {
		System.out.println("\n#mettez - montrer ligne;colonne - pour retourner une case\n#mettez - drapeau ligne;colonne - pour mettre un drapeau sur une case");
		String rep = sc.nextLine();
		String repSplit[] = rep.split(" ");
		
		if (rep.equalsIgnoreCase("nathan")) {
			System.out.println(tab.toString());
		}
		else if(repSplit[0].equals("drapeau")) {
			
			String repSplitCooDrap[] = repSplit[1].split(";");
			int xDrap = Integer.parseInt(repSplitCooDrap[0])-1;
			int yDrap = Integer.parseInt(repSplitCooDrap[1])-1;
			String tempSplit = this.getCase(xDrap, yDrap);
			String test[] = tempSplit.split(" ");
			if (test[1].equals("-")) {
				this.setCase(xDrap, yDrap, "^");
			}
			else if (test[1].equals("^")) {
				this.setCase(xDrap, yDrap, "-");
			}
			else {
				System.out.println("Vous ne pouvez pas mettre de drapeau, cette case a déjà été retournée.");
			}
			System.out.println(this.toStringUpdate());
		}
		
		else if(repSplit[0].equals("montrer")){
			String repSplitCoo[] = repSplit[1].split(";");
			int x = Integer.parseInt(repSplitCoo[0]) - 1;
			int y = Integer.parseInt(repSplitCoo[1]) - 1;
			
			String value = this.getCase(x, y);
			
			String isDrap[] = value.split(" ");
			
			if (isDrap[1].equals("^")) {
				System.out.println("Vous avez mis un drapeau sur cette case, vous ne pouvez donc pas la retourner. \n Pour la débloquer, ré-utiliser la commande drapeau sur la case.");
			}
			else if (isDrap[1].equals("*")) {
				System.out.println("Cette case a déjà été retournée.");
			}
			else if (value.equals("x -")) {
				if ((int)(Math.random()*100) < 100) {
					System.out.println("Tu as de la chance ! La bombe n'a pas explosée !");
				}
				else {
					System.out.println("Vous avez perdu !!");
					for (int i=0; i<this.getLignes(); i++) {
						for (int j=0; j<this.getColonnes(); j++) {
							if (this.getCase(i, j).equals("x -")) {
								this.setCase(i, j, "*");
							}
						}
					}
					System.out.println(this.toStringUpdate());
					return true;
				}
			}
				
			else if (value.equals("0 -")) {
				chaineDevoil(x, y);
				if (isAllFound()) {
					System.out.println("Tu as GAGNE la partie !!! \n");
					return true;
				}
				else {
					if ((int)(Math.random()*100) < 5) {
						System.out.println("YAY ! On va maintenant découvrir une bombe pour toi !");
						Position [] posBombe = this.getBombe();
						int posBombeADecouvrir = (int)(Math.random()*posBombe.length);
						Position randomChoice = posBombe[posBombeADecouvrir];
						int cooX = randomChoice.getLigne();
						int cooY = randomChoice.getColonne();
						this.setCase(cooX, cooY, "*");
					}
					System.out.println(this.toStringUpdate());
				}
			}
			
			else if (isNumber(isDrap[0])) {
				this.setCase(x, y, "*");
				if (isAllFound()) {
					System.out.println("\n\n\nTu as GAGNE la partie !!! \n");
					return true;
				}
				else {
					System.out.println(this.toStringUpdate());
				}
			}
		}
		
		else {
			System.out.println("Vous n'avez pas entré correctement la commande");
		}
			
		return false;
	}
	
	public boolean isAllFound() {
		int nbrBombTot = this.getNombreBombes();
		int nbrCaseDevoil = 0;
		int nbrCaseMax = this.getColonnes() * this.getLignes();
		int nbrCaseRest = 0;
		for( int i=0; i<this.getLignes(); i++) {
			for (int j=0; j<this.getColonnes(); j++) {
				String sent = this.getCase(i, j);
				String type[] = sent.split(" ");
				if (type[1].equals("*")) {	
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
	
	public boolean isNumber(String value) {
		for (int i=1; i<10; i++) {
			String parse = i + "";
			if (value.equals(parse)) {
				return true;
			}
		}
		return false;
	}
	
	public void devoilZero (int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax) {
		Position temp;
		boolean done = false;
		Position [][] savePosZero = new Position[3][3];
		this.setCase(x, y, "*");
		while(!done) {
			for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
				for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
					if(this.getCase(x+i, y+j).equals("0 -") && Math.abs(i) != Math.abs(j)) {
						this.setCase(x+i, y+j, "*");
						temp = new Position();
						temp.setLigne(x+i);
						temp.setColonne(y+j);
						savePosZero[i+1][j+1] = temp;
					}
				}
			}
			done = devoilZeroCascade(savePosZero);
		}
	}
	
	public boolean devoilZeroCascade(Position [][] arg) {		
		for (int i=0; i<arg.length; i++) {
			for (int j=0; j<arg[i].length; j++) {
				if (arg[i][j] != null) {
					chaineDevoil(arg[i][j].getLigne(), arg[i][j].getColonne());
				}
			}
		}
		return true;
	}
	
	public void devoilChiffres(int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax) {
		for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
			for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
				if(!(this.getCase(x+i, y+j).equals("0 -")) && !(this.getCase(x+i, y+j).equals("x -"))) {
					this.setCase(x+i, y+j, "*");
				}
			}
		}
	}
	
	public void chaineDevoil(int x, int y) {
		int xCond = x+1;
		int yCond = y+1;
		
		if (xCond!=1 && yCond!=1 && xCond!=this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, -1, 2, -1, 2);
			devoilZero(x, y, -1, 2, -1, 2);
		}
		else if (xCond==1 && yCond!=1 && xCond!=this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, 0, 2, -1, 2);
			devoilZero(x, y, 0, 2, -1, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond==this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, -1, 1, -1, 2);			
			devoilZero(x, y, -1, 1, -1, 2);
		}
		else if (xCond!=1 && yCond==1 && xCond!=this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, -1, 2, 0, 2);
			devoilZero(x, y, -1, 2, 0, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond!=this.getLignes() && yCond==this.getColonnes()) {
			devoilChiffres(x, y, -1, 2, -1, 1);
			devoilZero(x, y, -1, 2, -1, 1);
		}
		else if (xCond==1 && yCond==1 && xCond!=this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, 0, 2, 0, 2);
			devoilZero(x, y, 0, 2, 0, 2);
		}
		else if (xCond!=1 && yCond!=1 && xCond==this.getLignes() && yCond==this.getColonnes()) {
			devoilChiffres(x, y, -1, 1, -1, 1);
			devoilZero(x, y, -1, 1, -1, 1);
		}
		else if (xCond!=1 && yCond==1 && xCond==this.getLignes() && yCond!=this.getColonnes()) {
			devoilChiffres(x, y, -1, 1, 0, 2);
			devoilZero(x, y, -1, 1, 0, 2);
		}
		else if (xCond==1 && yCond!=1 && xCond!=this.getLignes() && yCond==this.getColonnes()) {
			devoilChiffres(x, y, 0, 2, -1, 1);
			devoilZero(x, y, 0, 2, -1, 1);
		}
	}
	
	public String toStringUpdate() {
		String temp = "";
		for (int i=0; i<tab.length; i++) {
			temp += "\n";
			if (i==0) {
				temp += " ";
				for (int j=0; j<tab[i].length; j++) {
					if (j<9) {
						temp += " 0" + (j+1);
					}
					else if (j==9) {
						temp += " " + (j+1) + " ";
					}
					else {
						temp += (j+1) + " ";
					}
				}
			}
			temp += "\n";
			if (i<9) {
				temp += (i+1) + " ";
			}
			else {
				temp += i+1;
			}
			for (int m=0; m<tab[i].length; m++) {
				String filler[] = tab[i][m].split(" ");
				if (filler[1].equals("^")) {
					temp += "[^]";
				}
				else {
					if (filler[1].equals("-")) {
						temp += "[+]";
					}
					else if (tab[i][m].equals("0 *")) {
						temp += "[ ]";
					}
					else {
						temp += "[" + filler[0] + "]";
					}
				}
			}
		}
		return temp;
	}
	
}
