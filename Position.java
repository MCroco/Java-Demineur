package first;

public class Position {
	private int ligne;
	private int colonne;
	
	public Position(int maxLigne, int maxColonne) {
		this.ligne = (int)((Math.random() * (maxLigne-1))+1);
		this.colonne = (int)((Math.random() * (maxColonne-1))+1);
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	@Override
	public String toString() {
		return ("Position of the bomb : " + this.ligne + ";" + this.colonne);
	}
}
