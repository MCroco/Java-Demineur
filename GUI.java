package first;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import javax.swing.JTextArea;



public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtChrono;
	int etat=0;
	private static String niveau;
	//JButton[][] tabBoutonsCons = new JButton[10][15];
	private static JButton[][] tabBoutonsFacile = new JButton[10][15];
	private static JButton[][] tabBoutonsMoyen = new JButton[20][30];
	private static JButton[][] tabBoutonsDifficile = new JButton[30][50];
	int secondPassed = 0;
    Timer chrono = new Timer();
    TimerTask task = new TimerTask() {

		@Override
		public void run() {
			secondPassed++;
			System.out.println("Secondes pass�es : " + secondPassed);
			txtChrono.setText(""+secondPassed);
			
		}
    	
};

	static String messageEnvoie;
	static String messageRecu;
	
    private JTextField txtEntrezLeMessage;
	


		  public GUI(Tableau aff,Tableau tab, int k, int l, int m){
		  	
		  	setBackground(Color.ORANGE);
		  	
		  	
		  			  
		    int a=10;		  
		    int b=15;
		    
		    JButton[][] tabBoutons = new JButton[100][100];
		    JPanel panel = new JPanel();
		    panel.setAutoscrolls(true);
		    
		    JButton[][] tabCaseDev = new JButton[a][b];
		    tabBoutons = caseDev(aff,tab, a, b).clone();
		    for(int i=0; i<k; i++) {
			    for(int j=0;j<l;j++) {
			    	tabCaseDev[i][j] = tabBoutons[i][j] ;
			    }
    		}
		    
		    
		    
		  

		    this.setTitle("D�mineur");

		    this.setSize(a*77,b*46);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    this.setLocationRelativeTo(null);
		    GridBagLayout gridBagLayout = new GridBagLayout();
		    gridBagLayout.columnWidths = new int[]{0, 0};
		    gridBagLayout.rowHeights = new int[]{473, 0, 0};
		    gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		    gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		    getContentPane().setLayout(gridBagLayout);
		    
		    JSplitPane splitPane = new JSplitPane();
		    splitPane.setBorder(new LineBorder(Color.BLACK, 2));
		    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		    GridBagConstraints gbc_splitPane = new GridBagConstraints();
		    gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		    gbc_splitPane.fill = GridBagConstraints.BOTH;
		    gbc_splitPane.gridx = 0;
		    gbc_splitPane.gridy = 0;
		    getContentPane().add(splitPane, gbc_splitPane);
		    
		    JSplitPane splitPane_1 = new JSplitPane();
		    splitPane_1.setBorder(new LineBorder(Color.BLACK, 2));
		    splitPane_1.setBackground(Color.WHITE);
		    splitPane.setLeftComponent(splitPane_1);
		    
		    JSplitPane splitPane_2 = new JSplitPane();
		    splitPane_1.setRightComponent(splitPane_2);
		    
		    JSplitPane splitPane_3 = new JSplitPane();
		    splitPane_2.setRightComponent(splitPane_3);
		    

		    
		    JSplitPane splitPane_4 = new JSplitPane();
		    splitPane_3.setRightComponent(splitPane_4);
		    
		    JButton btnNewButton_3 = new JButton("start/stop");
		    btnNewButton_3.setForeground(Color.YELLOW);
		    btnNewButton_3.setBackground(Color.GRAY);
		    splitPane_4.setRightComponent(btnNewButton_3);
		    
		    
		    txtChrono = new JTextField();
		    txtChrono.setDisabledTextColor(Color.BLACK);
		    txtChrono.setHorizontalAlignment(SwingConstants.CENTER);
		    splitPane_4.setLeftComponent(txtChrono);
		    txtChrono.setFont(new Font("Tahoma", Font.BOLD, 12));
		    txtChrono.setForeground(Color.RED);
		    txtChrono.setBackground(Color.BLACK);
		    txtChrono.setColumns(10);
		    txtChrono.setText("0:0:0000");
		    
		    JSplitPane splitPane_5 = new JSplitPane();
		    splitPane_4.setRightComponent(splitPane_5);
		    
		    JButton btnStop = new JButton("stop");
		    splitPane_5.setLeftComponent(btnStop);
		    btnStop.setForeground(Color.BLACK);
		    btnStop.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnStop.setBackground(Color.GRAY);
		    btnStop.setForeground(Color.YELLOW);
		    btnStop.setBackground(Color.GRAY);
		    
		    JButton btnStart = new JButton("start");
		    splitPane_5.setRightComponent(btnStart);
		    btnStart.setForeground(Color.BLACK);
		    btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnStart.setBackground(Color.GRAY);
		    btnStart.setForeground(Color.YELLOW);
		    btnStart.setBackground(Color.GRAY);
		    
		    JButton btnNewButton_2 = new JButton("expert");
		    btnNewButton_2.setForeground(Color.BLACK);
		    btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnNewButton_2.setBackground(Color.GRAY);
		    splitPane_3.setLeftComponent(btnNewButton_2);
		    btnNewButton_2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					niveau = "difficile";
					
					panel.removeAll();
					
					int aDifficile = 30;
					int bDifficile = 50;
								
					Tableau tabDifficile = new Tableau(aDifficile, bDifficile, 250);
					Tableau tabDifficileAff = new Tableau(aDifficile, bDifficile, 0);
					System.out.println(tabDifficileAff);
									
					//JButton[][] tabBoutonsDifficile = new JButton[aDifficile][bDifficile];
					
					tabBoutonsDifficile = caseDev(tabDifficileAff,tabDifficile, aDifficile, bDifficile);
					
					System.out.println(tabDifficile);
					
					panel.setLayout(new GridLayout(aDifficile, bDifficile));
					
					for(int i=0; i<aDifficile; i++) {
					    for(int j=0;j<bDifficile;j++) {
					    	
					    	panel.add(tabBoutonsDifficile[i][j]);
					    	tabBoutonsDifficile[i][j]=tabBoutonsDifficile[i][j];
					    }
		    		}
					
					panel.updateUI();
				}
			});
		    
		    JButton btnNewButton_1 = new JButton("moyen");
		    btnNewButton_1.setForeground(Color.BLACK);
		    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnNewButton_1.setBackground(Color.GRAY);
		    splitPane_2.setLeftComponent(btnNewButton_1);
		    btnNewButton_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					niveau = "moyen";
					
					panel.removeAll();
					
					int aMoyen = 20;
					int bMoyen = 30;
					
								
					Tableau tabMoyen = new Tableau(aMoyen, bMoyen, 40);
					Tableau tabMoyenAff = new Tableau(aMoyen, bMoyen, 0);
					System.out.println(tabMoyenAff);
									
					//JButton[][] tabBoutonsMoyen = new JButton[aMoyen][bMoyen];
					
					tabBoutonsMoyen = caseDev(tabMoyenAff, tabMoyen, aMoyen, bMoyen);
					
					System.out.println(tabMoyen);
					
					panel.setLayout(new GridLayout(aMoyen, bMoyen));
					
					for(int i=0; i<aMoyen; i++) {
					    for(int j=0;j<bMoyen;j++) {
					    	
					    	panel.add(tabBoutonsMoyen[i][j]);
					    	tabBoutonsMoyen[i][j]=tabBoutonsMoyen[i][j];
					    }
		    		}
					
					panel.updateUI();
					
					
					
					
				}
			});
		    
		    JButton btnNewButton = new JButton("facile");
		    btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		  
		    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnNewButton.setForeground(Color.BLACK);
		    btnNewButton.setBackground(Color.GRAY);
		    btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					niveau = "facile";
					
					panel.removeAll();
					
					int aFacile = 10;
					int bFacile = 15;
								
					Tableau tabFacile = new Tableau(aFacile, bFacile, 10);
					Tableau tabFacileAff = new Tableau(aFacile, bFacile, 0);
									
					//JButton[][] tabBoutonsFacile = new JButton[aFacile][bFacile];
					
					tabBoutonsFacile = caseDev(tabFacileAff, tabFacile, aFacile, bFacile);
					
					System.out.println(tabFacile);
					
					panel.setLayout(new GridLayout(aFacile, bFacile));
					
					for(int i=0; i<aFacile; i++) {
					    for(int j=0;j<bFacile;j++) {
					    	
					    	panel.add(tabBoutonsFacile[i][j]);
					    }
		    		}
					
					panel.updateUI();
					
					
				}
			});

		    splitPane_1.setLeftComponent(btnNewButton);
		    
		    
		    panel.setBackground(Color.WHITE);
		    splitPane.setRightComponent(panel);
		    panel.setLayout(new BorderLayout(0, 0));
		    
		    JTextPane txtpnBienvenueDansLe = new JTextPane();
		    txtpnBienvenueDansLe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    txtpnBienvenueDansLe.setText("Bienvenue dans le jeu du d\u00E9mineur!\r\nVeuillez choisir un niveau pour pouvoir commencer une partie.\r\n\r\nN'oubliez pas de cliquez sur start lorsque vous \u00EAtes pr\u00EAt!");
		    panel.add(txtpnBienvenueDansLe, BorderLayout.NORTH);
		    
		    JSplitPane splitPane_6 = new JSplitPane();
		    GridBagConstraints gbc_splitPane_6 = new GridBagConstraints();
		    gbc_splitPane_6.fill = GridBagConstraints.BOTH;
		    gbc_splitPane_6.gridx = 0;
		    gbc_splitPane_6.gridy = 1;
		    getContentPane().add(splitPane_6, gbc_splitPane_6);
		    
		    JButton btnEnvoyer = new JButton("Envoyer");
		    splitPane_6.setLeftComponent(btnEnvoyer);
		    
		    JPanel panel_1 = new JPanel();
		    splitPane_6.setRightComponent(panel_1);
		    panel_1.setLayout(new BorderLayout(0, 0));
		    
		    txtEntrezLeMessage = new JTextField();
		    txtEntrezLeMessage.setText("Entrez le message ici");
		    messageEnvoie = txtEntrezLeMessage.getText();
		    panel_1.add(txtEntrezLeMessage, BorderLayout.SOUTH);
		    txtEntrezLeMessage.setColumns(10);
		    
		    JTextArea txtrAfficheLeMessage = new JTextArea();
		    txtrAfficheLeMessage.setText(messageRecu);
		    txtrAfficheLeMessage.setEditable(false);
		    panel_1.add(txtrAfficheLeMessage, BorderLayout.CENTER);
		    
		    
		    btnStart.addActionListener(new ActionListener() {
		    	
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		changeEtatCase(true);
		    		

		    		
		    		System.out.println("bouton start press�");
		    		
		    		txtChrono.setText("");
		    		
		    		
		    		
		    	
				    
		    		chrono.scheduleAtFixedRate(task, 1000, 1000);

		    	}
		    });
		    
		    btnStop.addActionListener(new ActionListener() {
		    	
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		changeEtatCase(false);
		    		

		    		
		    		System.out.println("bouton stop press�");
		    		
		    		task.cancel();
		    		chrono.cancel();
				    
				    
				   
		    	}
		    });	    


		  } 
		  
		  public void changeEtatCase(Boolean etat) {
	    		if(niveau.equals("facile")) {
	    			for(int i=0; i<10; i++) {
					    for(int j=0;j<15;j++) {
					    	
					    	tabBoutonsFacile[i][j].setEnabled(etat);
					    }
		    		}
	    		}
	    		else if(niveau.equals("moyen")) {
	    			for(int i=0; i<20; i++) {
					    for(int j=0;j<30;j++) {
					    	
					    	tabBoutonsMoyen[i][j].setEnabled(etat);
					    }
		    		}
	    		}
	    		else if(niveau.equals("difficile")) {
	    			for(int i=0; i<30; i++) {
					    for(int j=0;j<50;j++) {
					    	
					    	tabBoutonsDifficile[i][j].setEnabled(etat);
					    }
		    		}
	    		}
		  }
		  
		  public JButton[][] caseDev(Tableau aff, Tableau tab, int a, int b){
			  JButton[][] tabCaseDev = new JButton[a][b];
			  for(int i=0; i<a; i++) {
				    for(int j=0;j<b;j++) {
				    	String temp = ""+i+";"+j;
				    	JButton btn = new JButton("");
				    	 
				    	 tabCaseDev [i][j] = btn;
				    	// group.add(btn);
				    	 btn.setBackground(Color.GRAY);
				    	 btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
				    	 btn.setBorder(BorderFactory.createLineBorder(Color.black));
				         btn.setText("");
				         btn.setEnabled(false);
				         btn.setName(temp);
				         btn.setBounds(i*75, j*40, 75, 40);
				         //panel.add(btn);
				        

				         btn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String cooCase = btn.getName();
								btn.setForeground(Color.RED);
								String[] coo = cooCase.split(";");	
								System.out.println(btn.getName());
								
								int cooX = Integer.parseInt(coo[0]);
								int cooY = Integer.parseInt(coo[1]);
								
								if(tab.getCase(cooX, cooY).equals("0")) {
									chaineDevoil(tab, cooX, cooY, btn);									
								}
								if(tab.getCase(cooX, cooY).equals("x")) {
									btn.setText(tab.getCase(cooX, cooY));
									btn.setBackground(Color.WHITE);
									changeEtatCase(false);
						    		//afficher un message "PERDU"
						    		task.cancel();
						    		chrono.cancel();
									
								}
								
								else {
									btn.setForeground(Color.RED);
									btn.setText(tab.getCase(cooX, cooY));
									btn.setBackground(Color.WHITE);

								}
														
							}
						} );
				    	
				    }
			    }
			  
			  return tabCaseDev;
		  }
		  
		  public static void chaineDevoil(Tableau tab, int x, int y, JButton btn) {
				int xCond = x+1;
				int yCond = y+1;
				
				if (xCond!=1 && yCond!=1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 2, -1, 2, btn);
					devoilZero(tab, x, y, -1, 2, -1, 2, btn);
				}
				else if (xCond==1 && yCond!=1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, 0, 2, -1, 2, btn);
					devoilZero(tab, x, y, 0, 2, -1, 2, btn);
				}
				else if (xCond!=1 && yCond!=1 && xCond==tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 1, -1, 2, btn);			
					devoilZero(tab, x, y, -1, 1, -1, 2, btn);
				}
				else if (xCond!=1 && yCond==1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 2, 0, 2, btn);
					devoilZero(tab, x, y, -1, 2, 0, 2, btn);
				}
				else if (xCond!=1 && yCond!=1 && xCond!=tab.getLignes() && yCond==tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 2, -1, 1, btn);
					devoilZero(tab, x, y, -1, 2, -1, 1, btn);
				}
				else if (xCond==1 && yCond==1 && xCond!=tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, 0, 2, 0, 2, btn);
					devoilZero(tab, x, y, 0, 2, 0, 2, btn);
				}
				else if (xCond!=1 && yCond!=1 && xCond==tab.getLignes() && yCond==tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 1, -1, 1, btn);
					devoilZero(tab, x, y, -1, 1, -1, 1, btn);
				}
				else if (xCond!=1 && yCond==1 && xCond==tab.getLignes() && yCond!=tab.getColonnes()) {
					devoilChiffres(tab, x, y, -1, 1, 0, 2, btn);
					devoilZero(tab, x, y, -1, 1, 0, 2, btn);
				}
				else if (xCond==1 && yCond!=1 && xCond!=tab.getLignes() && yCond==tab.getColonnes()) {
					devoilChiffres(tab, x, y, 0, 2, -1, 1, btn);
					devoilZero(tab, x, y, 0, 2, -1, 1, btn);
				}
			}
			
			public static void devoilChiffres(Tableau tab, int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax, JButton btn) {
				for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
					for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
						if(!(tab.getCase(x+i, y+j).equals("0")) && !(tab.getCase(x+i, y+j).equals("x")) && !(tab.getCase(x+i, y+j).equals("0"))) {
							if (niveau.equals("facile")) {
								tabBoutonsFacile[x+i][y+j].setText(tab.getCase(x+i, y+j));
								tabBoutonsFacile[x+i][y+j].setBackground(Color.WHITE);
							}
							else if (niveau.equals("moyen")) {
								tabBoutonsMoyen[x+i][y+j].setText(tab.getCase(x+i, y+j));
								tabBoutonsMoyen[x+i][y+j].setBackground(Color.WHITE);
							}
							else {
								tabBoutonsDifficile[x+i][y+j].setText(tab.getCase(x+i, y+j));
								tabBoutonsDifficile[x+i][y+j].setBackground(Color.WHITE);
							}
						}
					}
				}
			}
			
			public static void devoilZero (Tableau tab, int x, int y, int limitPlacementXMin, int limitPlacementXMax, int limitPlacementYMin, int limitPlacementYMax, JButton btn) {
				Position temp;
				boolean done = false;
				Position [][] savePosZero = new Position[3][3];
				if (niveau.equals("facile")) {
					tabBoutonsFacile[x][y].setText("");
				}
				else if (niveau.equals("moyen")) {
					tabBoutonsMoyen[x][y].setText("");
				}
				else {
					tabBoutonsDifficile[x][y].setText("");
				}
				while(!done) {
					for(int i=limitPlacementXMin; i<limitPlacementXMax; i++) {
						for(int j=limitPlacementYMin; j<limitPlacementYMax; j++) {
							if(tab.getCase(x+i, y+j).equals("0") && Math.abs(i) != Math.abs(j)) {
								tab.setCase(x+i, y+j, " ");
								if (niveau.equals("facile")) {
									tabBoutonsFacile[x+i][y+j].setText("");
								}
								else if (niveau.equals("moyen")) {
									tabBoutonsMoyen[x+i][y+j].setText("");
								}
								else {
									tabBoutonsDifficile[x+i][y+j].setText("");
								}
								temp = new Position();
								temp.setLigne(x+i);
								temp.setColonne(y+j);
								savePosZero[i+1][j+1] = temp;
							}
						}
					}
					done = devoilZeroCascade(savePosZero, tab, btn);
				}
			}
			
			public static boolean devoilZeroCascade(Position [][] arg, Tableau tab, JButton btn) {		
				for (int i=0; i<arg.length; i++) {
					for (int j=0; j<arg[i].length; j++) {
						if (arg[i][j] != null) {
							chaineDevoil(tab, arg[i][j].getLigne(), arg[i][j].getColonne(), btn);
						}
					}
				}
				return true;
			}
		  
	public static void main(String[] args) {
		Tableau tab = new Tableau(10, 15, 10);
		Tableau aff = new Tableau(10, 15, 0);
		System.out.println(tab);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(aff,tab,10,15,10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	      final Socket clientSocket;
	      final BufferedReader in;
	      final PrintWriter out;
	      final Scanner sc = new Scanner(System.in);//pour lire � partir du clavier
	  
	      try {
	         /*
	         * les informations du serveur ( port et adresse IP ou nom d'hote
	         * 127.0.0.1 est l'adresse local de la machine
	         */
	         clientSocket = new Socket("127.0.0.1",5000);
	   
	         //flux pour envoyer
	         out = new PrintWriter(clientSocket.getOutputStream());
	         //flux pour recevoir
	         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	   
	         Thread envoyer = new Thread(new Runnable() {
	             String msg;
	              @Override
	              public void run() {
	                while(true){
	                  msg = sc.nextLine();
	                  out.println(messageEnvoie);
	                  out.flush();
	                }
	             }
	         });
	         envoyer.start();
	   
	        Thread recevoir = new Thread(new Runnable() {
	            String msg;
	            @Override
	            public void run() {
	               try {
	                 messageRecu = in.readLine();
	                 while(messageRecu!=null){
	                    System.out.println("Serveur : "+msg);
	                    messageRecu = in.readLine();
	                 }
	                 System.out.println("Serveur d�conect�");
	                 out.close();
	                 clientSocket.close();
	               } catch (IOException e) {
	                   e.printStackTrace();
	               }
	            }
	        });
	        recevoir.start();
	   
	      } catch (IOException e) {
	           e.printStackTrace();
	      }
	}
	
	
}