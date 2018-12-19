package demin.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import demin.controller.DeminController;
import demin.model.Position;
import demin.model.Tableau;


public class DeminViewGUI extends DeminView implements ActionListener{
	
	
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
			//System.out.println("Secondes passées : " + secondPassed);
			txtChrono.setText(""+secondPassed);

		}

	};

	static String messageEnvoie;
	static String messageRecu;

	private JTextField txtEntrezLeMessage;
	private static Scanner sc;



	public DeminViewGUI(Position model, DeminController controller, Tableau aff,Tableau tab, int k, int l, int m){
		//TODO check app constructeur
		
		super(model, controller);
		
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





		this.setTitle("Démineur");

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

				//System.out.println(tabDifficile);

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

				//System.out.println(tabMoyen);

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


				System.out.println(tabFacileAff);


				tabBoutonsDifficile = caseDev(tabFacileAff,tabFacile, aFacile, bFacile);

				//System.out.println(tabFacile);

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



				System.out.println("bouton start pressé");

				txtChrono.setText("");





				chrono.scheduleAtFixedRate(task, 1000, 1000);

			}
		});

		btnStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				changeEtatCase(false);



				System.out.println("bouton stop pressé");

				task.cancel();
				chrono.cancel();



			}
		});	    


	}



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	} 
}



