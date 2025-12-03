package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IPS {
	
	public IPS() {

		JFrame frame = new JFrame();
		JPanel main = new JPanel();
		main.setBorder(BorderFactory.createEmptyBorder(200,300,200,300));
		main.setLayout(new GridLayout(0,1));
		
		frame.add(main, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inventāra Pārvaldibas Sistēma");
		
		/*pogas galvenajam ekranam*/
		JButton pievienot = new JButton("Pievienot produktu");
		JButton nonemt = new JButton("Noņemt produktu");
		JButton paradit = new JButton("Parādīt visus produktus");
		JButton teksts = new JButton("Pārtaisīt produktu sarakstu teksta failā");
		
		main.add(pievienot);
		main.add(nonemt);
		main.add(paradit);
		main.add(teksts);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new IPS();

	}

}
