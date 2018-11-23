package first;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre extends JFrame {

	  public Fenetre(){
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTable");
	    this.setSize(800, 575);
	    
	    //Les données du tableau
	    Tableau tab = new Tableau(6, 6, 4);
	    Object [][] data = tab.getTab();


	    //Les titres des colonnes

	    String  title[] = {"a", "b", "c", "d", "e", "f"};

	    JTable tableau = new JTable(data, title);


	    this.getContentPane().add(new JScrollPane(tableau));

	  }   

	  public static void main(String[] args){

	    Fenetre fen = new Fenetre();

	    fen.setVisible(true);

	  }   

	}
