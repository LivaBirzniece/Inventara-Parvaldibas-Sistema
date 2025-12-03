package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IPS implements ActionListener{
	private JFrame frame;
	
	
	public IPS() {

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
		
		/*pogas nonemsanas panelim*/
		JButton nonemtProduktu = new JButton("Noņemt produktu");
		nonemt.add(nonemtProduktu);
		
		/*Funkcijas, kas ļauj pārslēgties uz citu paneli*/
		pievienot1.addActionListener(e -> cardLayout.show(cardPanel, "Pievienot"));
		nonemt2.addActionListener(e -> cardLayout.show(cardPanel, "Noņemt"));
		paradit3.addActionListener(e -> cardLayout.show(cardPanel, "Parādīt"));
		teksts4.addActionListener(e -> cardLayout.show(cardPanel, "Faila izveide"));
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/* Metodes / funkcijias */
	public void Pievienosana() {
		
	}
	
	public void Nonemsana() {
		
	}
	
	public void Paradit() {
		
	}
	
	public void TekstaFails() {
		
	}
	

	public static void main(String[] args) {
		new IPS();

	}


	/* lai izmantotu katrai pogai, šis ir jaliek pie pašas pogas*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
