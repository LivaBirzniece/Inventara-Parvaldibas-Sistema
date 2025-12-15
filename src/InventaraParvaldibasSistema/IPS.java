package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;



public class IPS implements ActionListener{
	private JFrame frame;
	private HashMap<String,String> ProduktuSaraksts = new HashMap<>();
	private Color bg = new Color(30, 30, 30);
	private Color textColor = new Color(230, 230, 230);
	
	public IPS() {
		
     	JTextArea tekstsIzdrukat = new JTextArea(16,16);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.getContentPane().setBackground(bg);
        frame.setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Inventāra Pārvaldības Sistēma", SwingConstants.CENTER);
        title.setForeground(textColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);
        
        
        
        
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
		
		/*Poga, kas ļauj iet atpakal uz galveno lapu*/
		
		
		
		/*pogas pievienosanas panelim*/
		
		JButton pievienotProduktu = new JButton("Pievienot jaunu produktu");
		pievienot.add(pievienotProduktu);
		JTextField produkts = new JTextField(16);
		pievienot.add(produkts);
		JTextField skaits = new JTextField(16);
		pievienot.add(skaits);
		JButton atpakalP = new JButton("Uz galveno lapu");
		atpakalP.addActionListener(e -> cardLayout.show(cardPanel, "Galvenā lapa"));
		pievienot.add(atpakalP);
		
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
		
		
		

		/*pogas nonemsanas panelim*/
		JButton nonemtProduktu = new JButton("Noņemt produktu/s");
		nonemt.add(nonemtProduktu);
		
		/*Sis ir tests, butu jamaina iespejams ka sis notiek*/
		/* Nonemsanas funkcija */
		JButton ParaditProduktusN = new JButton("Parādīt visus produktus");
		nonemt.add(ParaditProduktusN);
		JPanel TabulaN = new JPanel();
		nonemt.add(TabulaN);
		
		ParaditProduktusN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox check = new JCheckBox();
		
				String[] columnNames = {"Izvēlēties","Nosaukums", "Skaits"};
				
			  
			    Object[][] DatiHM = new Object[ProduktuSaraksts.size()][3];
			    int i = 0;
			    for(String key: ProduktuSaraksts.keySet()) {
			    	DatiHM[i][0] = false;
			    	DatiHM[i][1] = key;
			    	DatiHM[i][2] = ProduktuSaraksts.get(key);
			    	i++;
			    }
				
				
				JTable ProduktuParadisana = new JTable(DatiHM, columnNames);
				JScrollPane scrollN = new JScrollPane(ProduktuParadisana);
				TabulaN.removeAll();
				TabulaN.add(scrollN);
				TabulaN.revalidate();
				TabulaN.repaint();		
			}
			
		});
		
		JButton atpakalN = new JButton("Uz galveno lapu");
		atpakalN.addActionListener(e -> cardLayout.show(cardPanel, "Galvenā lapa"));
		nonemt.add(atpakalN);
		
		/*pogas skatisanas paneli*/
		
		JButton ParaditProduktu = new JButton("Parādīt visus produktus");
		paradit.add(ParaditProduktu);
		JPanel Tabula = new JPanel();
		paradit.add(Tabula);
		
		ParaditProduktu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNamesP = {"Nosaukums", "Skaits"};
		
		  
				Object[][] DatiHMP = new Object[ProduktuSaraksts.size()][2];
				int i = 0;
				for(String key: ProduktuSaraksts.keySet()) {
					DatiHMP[i][0] = key;
					DatiHMP[i][1] = ProduktuSaraksts.get(key);
					i++;
				}
				JTable ProduktuParadisanaP = new JTable(DatiHMP, columnNamesP);
				JScrollPane scroll = new JScrollPane(ProduktuParadisanaP);
				Tabula.removeAll();
				Tabula.add(scroll);
				Tabula.revalidate();
				Tabula.repaint();
				
			}
		});
		
		
		
		
		JButton atpakalS = new JButton("Uz galveno lapu");
		atpakalS.addActionListener(e -> cardLayout.show(cardPanel, "Galvenā lapa"));
		paradit.add(atpakalS);
		
		/*Funkcijas, kas ļauj pārslēgties uz citu paneli*/
		pievienot1.addActionListener(e -> cardLayout.show(cardPanel, "Pievienot"));
		nonemt2.addActionListener(e -> cardLayout.show(cardPanel, "Noņemt"));
		paradit3.addActionListener(e -> cardLayout.show(cardPanel, "Parādīt"));
		teksts4.addActionListener(e -> cardLayout.show(cardPanel, "Faila izveide"));
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int r) { radius = r; }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(new Color(120, 120, 120));
            g.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
        }
    }
	
	public static void main(String[] args) {
		new IPS();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
