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
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(200,300,200,300));
		panel.setLayout(new GridLayout(0,1));
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inventara Parvaldibas Sistema");
		
		//pogas
		JButton pievienot = new JButton("Pievienot produktu");
		JButton nonemt = new JButton("Noņemt produktu");
		JButton paradit = new JButton("Parādīt visus produktus");
		JButton teksts = new JButton("Pārtaisīt produktu sarakstu teksta failā");
		
		panel.add(pievienot);
		panel.add(nonemt);
		panel.add(paradit);
		panel.add(teksts);
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new IPS();

	}

}
