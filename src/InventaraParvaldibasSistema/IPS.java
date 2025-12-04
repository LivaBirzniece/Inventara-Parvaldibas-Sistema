package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import java.util.HashMap;



public class IPS implements ActionListener{
	private JFrame frame;
	private HashMap<String,String> ProduktuSaraksts = new HashMap<>();
	
	public IPS() {
		
     	JTextArea tekstsIzdrukat = new JTextArea(16,16);
		
		frame = new JFrame();
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);
		
		
		
		JPanel main = new JPanel(new GridLayout(0,1));
		JPanel pievienot = new JPanel();
		JPanel nonemt = new JPanel();
		JPanel paradit = new JPanel();
		JPanel teksts = new JPanel();
		
		cardPanel.add(main,"Galvenā lapa");
		cardPanel.add(pievienot,"Pievienot");
		cardPanel.add(nonemt,"Noņemt");
		cardPanel.add(paradit,"Parādīt");
		cardPanel.add(teksts,"Faila izveide");
		
		
		main.setBorder(BorderFactory.createEmptyBorder(200,300,200,300));
		
		
		frame.add(cardPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inventāra Pārvaldibas Sistēma");
		
		/*pogas galvenajam ekranam*/
		JButton pievienot1 = new JButton("Pievienot produktu");
		JButton nonemt2 = new JButton("Noņemt produktu");
		JButton paradit3 = new JButton("Parādīt visus produktus");
		JButton teksts4 = new JButton("Pārtaisīt produktu sarakstu teksta failā");
		
		main.add(pievienot1);
		main.add(nonemt2);
		main.add(paradit3);
		main.add(teksts4);
		
		/*pogas pievienosanas panelim*/
		
		JButton pievienotProduktu = new JButton("Pievienot jaunu produktu");
		pievienot.add(pievienotProduktu);
		JTextField produkts = new JTextField(16);
		pievienot.add(produkts);
		JTextField skaits = new JTextField(16);
		pievienot.add(skaits);
		
		
		pievienotProduktu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ProduktaNosaukums = produkts.getText().trim();
				String ProduktaSkaits = skaits.getText().trim();
				ProduktuSaraksts.put(ProduktaNosaukums,ProduktaSkaits);
				produkts.setText("");
				skaits.setText("");
				
			}
			
		
		});
		
		
		/*----------------------------------------------*/
		/*Testa poga, lai redzetu vai strada hashmap*/
//		JButton paraditHashmap = new JButton("PARADIT");
//		pievienot.add(paraditHashmap);
//		paraditHashmap.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tekstsIzdrukat.setEditable(false);
//				/* Šeit vajag noformēt, lai nerādas { } */
//				tekstsIzdrukat.setText(ProduktuSaraksts.toString());
//				pievienot.add(tekstsIzdrukat);
//				
//			}
//			
//		});
		/*---------------------------------------------------*/
		
		/*pogas nonemsanas panelim*/
		JButton nonemtProduktu = new JButton("Noņemt produktu");
		nonemt.add(nonemtProduktu);
		
		
		JCheckBox check = new JCheckBox();
		
		String[] columnNames = {"Nosaukums", "Skaits"};
		
	  
	    Object[][] DatiHM = new Object[ProduktuSaraksts.size()][2];
	    int i = 0;
	    for(String key: ProduktuSaraksts.keySet()) {
	    	DatiHM[i][0] = key;
	    	DatiHM[i][1] = ProduktuSaraksts.get(key);
	    	i++;
	    }
		
		
		JTable ProduktuParadisana = new JTable(DatiHM, columnNames);
		nonemt.add(ProduktuParadisana);
		
		
		
		
		/*Funkcijas, kas ļauj pārslēgties uz citu paneli*/
		pievienot1.addActionListener(e -> cardLayout.show(cardPanel, "Pievienot"));
		nonemt2.addActionListener(e -> cardLayout.show(cardPanel, "Noņemt"));
		paradit3.addActionListener(e -> cardLayout.show(cardPanel, "Parādīt"));
		teksts4.addActionListener(e -> cardLayout.show(cardPanel, "Faila izveide"));
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new IPS();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
