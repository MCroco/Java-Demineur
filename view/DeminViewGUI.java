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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import demin.controller.DeminController;
import demin.model.Tableau;


public class DeminViewGUI extends DeminView implements ActionListener{
	
	private JFrame demineurFrame;
	private JTextField txtChrono;
	static JTextArea txtrAfficheLeMessage = new JTextArea();
	static JTextField txtEntrezLeMessage = new JTextField();
	
	static String messageEnvoie="";
	static String messageRecu="";
	static String affichageChat="";
	
	Socket clientSocket;
	ServerSocket serveurSocket  ;
	
//	final BufferedReader in;
//    final PrintWriter out;
    final Scanner sc = new Scanner(System.in);
    
    public DeminViewGUI(Tableau model, DeminController controller) throws IOException {
    	
    	super(model, controller);
    	
//   	out = new PrintWriter(clientSocket.getOutputStream());
//    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	
    	demineurFrame = new JFrame("Démineur MVC");
    	demineurFrame.getContentPane().setBackground(Color.ORANGE);
    	
	    JPanel panel = new JPanel();
	    panel.setAutoscrolls(true);
    	
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{0, 0};
    	gridBagLayout.rowHeights = new int[]{473, 0, 0};
    	gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
    	demineurFrame.getContentPane().setLayout(gridBagLayout);
    	
    	JSplitPane splitPane = new JSplitPane();
	    splitPane.setBorder(new LineBorder(Color.BLACK, 2));
	    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    GridBagConstraints gbc_splitPane = new GridBagConstraints();
	    gbc_splitPane.insets = new Insets(0, 0, 5, 0);
	    gbc_splitPane.fill = GridBagConstraints.BOTH;
	    gbc_splitPane.gridx = 0;
	    gbc_splitPane.gridy = 0;
	    demineurFrame.getContentPane().add(splitPane, gbc_splitPane);
	    
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
	    txtChrono.setText("| niv |");
	    
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
	    
	    JButton btnExpert = new JButton("expert");
	    btnExpert.setForeground(Color.BLACK);
	    btnExpert.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnExpert.setBackground(Color.GRAY);
	    splitPane_3.setLeftComponent(btnExpert);
	   
	    JButton btnMoyen = new JButton("moyen");
	    btnMoyen.setForeground(Color.BLACK);
	    btnMoyen.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnMoyen.setBackground(Color.GRAY);
	    splitPane_2.setLeftComponent(btnMoyen);
	    
	    JButton btnFacile = new JButton("facile");
	    btnFacile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		  
	    btnFacile.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnFacile.setForeground(Color.BLACK);
	    btnFacile.setBackground(Color.GRAY);
	    btnFacile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panel.removeAll();
				
				affiche("Le joueur a choisit le niveau facile");
//                out.flush();
                txtChrono.setText("easy");
				
                JButton[][] tab = updateModel(model.lignes, model.colonnes);
				
				panel.setLayout(new GridLayout(model.lignes, model.colonnes));
				
				for(int i=0; i<model.lignes; i++) {
				    for(int j=0;j<model.colonnes;j++) {
				    	panel.add(tab[i][j]);
				    }
	    		}
				
				panel.updateUI();
				
				
			}
		});
	    
	    splitPane_1.setLeftComponent(btnFacile);
	    
	    
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
	    demineurFrame.getContentPane().add(splitPane_6, gbc_splitPane_6);
	    
	    JButton btnEnvoyer = new JButton("Envoyer");
	    splitPane_6.setLeftComponent(btnEnvoyer);
	    
	    JPanel panel_1 = new JPanel();
	    splitPane_6.setRightComponent(panel_1);
	    panel_1.setLayout(new BorderLayout(0, 0));
	    txtrAfficheLeMessage.setFont(new Font("Monospaced", Font.BOLD, 13));
	    txtrAfficheLeMessage.setBackground(Color.LIGHT_GRAY);
	    
	    
	    txtrAfficheLeMessage.setText("Affiche le message\n");
	    txtrAfficheLeMessage.setEditable(false);
	    panel_1.add(txtrAfficheLeMessage, BorderLayout.CENTER);
	    
	    JScrollPane scroll = new JScrollPane (txtrAfficheLeMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    panel_1.add(scroll);
	    txtEntrezLeMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
	    txtEntrezLeMessage.setBackground(Color.GRAY);
	    
	    txtEntrezLeMessage.setText("");
	    messageEnvoie = txtEntrezLeMessage.getText();
	    panel_1.add(txtEntrezLeMessage, BorderLayout.SOUTH);
	    txtEntrezLeMessage.setColumns(10);
    }
    
    public JButton[][] updateModel(int a, int b){
		  JButton[][] tabCaseDev = new JButton[a][b];
		  for(int i=0; i<a; i++) {
			    for(int j=0;j<b;j++) {
			    	String temp = i+";"+j;
			    	JButton btn = new JButton("");
			    	 
			    	 tabCaseDev [i][j] = btn;
			    	 btn.setForeground(Color.BLACK);
			    	 btn.setBackground(Color.GRAY);
			    	 btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
			    	 btn.setBorder(BorderFactory.createLineBorder(Color.black));
			         btn.setText("");
			         btn.setEnabled(false);
			         btn.setName(temp);
			         btn.setBounds(i*75, j*40, 75, 40);
			    }
		    }
		  return tabCaseDev;
	  }
    
    public void affiche(String msg) {
//   	out.println(msg);
//    	out.flush();
    	System.out.println(msg);
    }
    
    @Override
    public void update(Observable o, Object arg) {
    	updateModel(model.lignes, model.colonnes);
    	
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		 
		
	}
}


