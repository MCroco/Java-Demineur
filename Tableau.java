package first;

public class Tableau {
	private int [][] tab;
	private Position [] pos;
	
	public Tableau(int lignes, int colonnes, int nbrBomb) {
		for (int i = 0; i<nbrBomb; i++) {
			pos[i] = new Position(lignes, colonnes);
		}
		this.tab = new int[lignes][colonnes];
		remplissageBomb();
	}
	
	public boolean isBomb(int li, int co) {
		if (tab[li][co] == -1) {
			return true;
		}
		return false;
	}
	
	public void remplissageBomb() {
		for (int i=0; i<pos.length;i++) {
			Position temp = pos[i];
			tab[temp.getLigne()][temp.getColonne()] = -1;
		}
	}
	
	public void remplissageTableau() {
		int count = 0;
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				count = 0;
				if (i==0) {
					if (j==0) {
						count += coinHautGauche(i, j);
					}
					else if (j == tab[i].length) {
						count += coinHautDroit(i, j);
					}
					else {
						count += millieuHorizontalHaut(i, j);
					}
				}
				else if (i==tab.length) {
					if (j==0) {
						count += coinBasGauche(i, j);
					}
					else if (j==tab[i].length) {
						count += coinBasDroit(i, j);
					}
					else {
						count += millieuHorizontalBas(i, j);
					}
				}
				else if (j==0) {
					count += millieuVerticalGauche(i, j);
				}
				else if (j==tab[i].length) {
					count += millieuVerticalDroit(i, j);
				}
				else {
					count += millieu(i, j);
				}
				tab[i][j] = count;
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
}
