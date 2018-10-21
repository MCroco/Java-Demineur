package first;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre extends JFrame {

	public Fenetre(){
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTable");
	    this.setSize(500, 375);

	    //Les données du tableau
	    Tableau tab = new Tableau(4, 5, 3);
	    Object [][] data = tab.getTab();

	    //Les titres des colonnes
	    String  title[] = new String[data[0].length];
	    for (int i = 0; i<title.length; i++) {
	    	title[i] = "";
	    }
	    JTable tableau = new JTable(data, title);
	    this.getContentPane().add(new JScrollPane(tableau));
	  }

	  public static void main(String[] args){
	    Fenetre fen = new Fenetre();
	    fen.setVisible(true);
	  }   
}