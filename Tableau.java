package first;

/**
 * This class is calculating :
 * <ul>
 * <li> the number of bombs </li>
 * <li> the numbers around the bombs </li>
 * <li> the table before creating it</li>
 * </ul>
 * @author Group 14 -2TL2
 *
 */


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
		tab[x][y] = setter;
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
		if (tab[li][co].equals("x")) {
			return true;
		}
		return false;
	}
	
	public void remplissageBomb() {
		for (int i=0; i<pos.length;i++) {
			Position temp = pos[i];
			tab[temp.getLigne()][temp.getColonne()] = "x";
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
					tab[i][j] = Integer.toString(count);
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
	
}
