package first;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre extends JFrame {

	private static String donnees ="";

	  public Fenetre(){

	    this.setLocationRelativeTo(null);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setTitle("JTable");

	    this.setSize(500, 375);


	    //Les données du tableau


		
	    Object [][] data = {
	    		
	    		{"1", "", "",},
	    		{"2", "", "",},
	    		{"3", "", "",},
	    		{"4", "", "",},
	    		{"5", "", "",},
	    		{"6", "", "",},

	    		
	    };


	    //Les titres des colonnes

	    String  title[] = {"a", "b", "c"};

	    JTable tableau = new JTable(data, title);


	    this.getContentPane().add(new JScrollPane(tableau));

	  }   

	  public static void main(String[] args){

	    Fenetre fen = new Fenetre();

	    fen.setVisible(true);

	  }   

	}
