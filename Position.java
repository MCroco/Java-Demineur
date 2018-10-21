package first;

public class Position {
	private int ligne;
	private int colonne;
	
	public Position(int maxLigne, int maxColonne) {
		this.ligne = (int)((Math.random() * (maxLigne-1)));
		this.colonne = (int)((Math.random() * (maxColonne-1)));
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
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Position) {
			Position p = (Position)obj;
			if (this.ligne == p.ligne && this.colonne == p.colonne) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return ("Position of the bomb : " + this.ligne + ";" + this.colonne);
	}
}
