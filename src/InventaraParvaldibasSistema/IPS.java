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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.table.JTableHeader;



public class IPS implements ActionListener{
	private JFrame frame;
	private JPanel main;
	private JPanel pievienot;
	private JPanel nonemt;
	private JPanel paradit;
	private JPanel teksts;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private HashMap<String,String> ProduktuSaraksts = new HashMap<>();
	private Color bg = new Color(30, 30, 30);
	private Color textColor = new Color(230, 230, 230);
    private Color panel = new Color(45, 45, 45);
	private Color btnNormal = new Color(60, 60, 60);
	private Color btnHover = new Color(90, 90, 90);
	
	public IPS() {
		
     	
		
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
        
        
        
        
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		
		
		main = new JPanel(new GridLayout(0,1));
		pievienot = new JPanel();
		nonemt = new JPanel();
		paradit = new JPanel();
		teksts = new JPanel();
		
		
		
		
		main.setBorder(BorderFactory.createEmptyBorder(200,300,200,300));
		
		
		frame.add(cardPanel, BorderLayout.CENTER);
		
		frame.setTitle("Inventāra Pārvaldibas Sistēma");
		
		
		
		
		Main();
		Add();
		Remove();
		Show();
		Text();
		
		cardPanel.add(main,"Galvenā lapa");
		cardPanel.add(pievienot,"Pievienot");
		cardPanel.add(nonemt,"Noņemt");
		cardPanel.add(paradit,"Parādīt");
		cardPanel.add(teksts,"Faila izveide");
	}
		
	// - GALVENA LAPA
	private void  Main() {
		main  = new JPanel(new GridLayout(4,1,16,16));
		main.setBackground(panel);
        main.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        JButton addBtn = createButton("Pievienot produktu","Bildes/add.png");
		JButton removeBtn = createButton("Noņemt produktu","remove.png");
		JButton listBtn = createButton("Parādīt visus produktus","list.png");
		JButton exportBtn = createButton("Pārtaisīt produktu sarakstu teksta failā","file.png");
		
		
		addBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Pievienot"));
		removeBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Noņemt"));
		listBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Parādīt"));
		exportBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Faila izveide"));
		
		
		
		main.add(addBtn);
		main.add(removeBtn);
		main.add(listBtn);
		main.add(exportBtn);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
		
	// - PIEVIENOSANAS LAPA
	private void Add() {
		pievienot = new JPanel(new BorderLayout());
        pievienot.setBackground(panel);
        
        pievienot.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
        
        
        JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        
        JButton pievienotProduktu = createButton("Pievienot jaunu produktu","add.png");
	    forma.add(pievienotProduktu);
		JTextField produkts = new JTextField(16);
		
		produkts.setBackground(btnNormal);
		produkts.setForeground(textColor);
	 	forma.add(produkts);
	    JTextField skaits = new JTextField(16);
	    skaits.setBackground(btnNormal);
	    skaits.setForeground(textColor);
		forma.add(skaits);
		
		
		
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
		pievienot.add(forma, BorderLayout.CENTER);
	}
		
	// - NONEMSANAS LAPA
	private void Remove() {
		nonemt = new JPanel(new BorderLayout());
        nonemt.setBackground(panel);
        
        JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        JButton nonemtProduktu = createButton("Noņemt produktu/s","remove.png");
		forma.add(nonemtProduktu);
		
		
		JButton ParaditProduktus = new JButton("Parādīt visus produktus");
		forma.add(ParaditProduktus);
		JPanel Tabula = new JPanel();
		forma.add(Tabula);
		
		ParaditProduktus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JCheckBox check = new JCheckBox();

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
				Tabula.removeAll();
				Tabula.add(scrollN);
				Tabula.revalidate();
				Tabula.repaint();
				Tabula.setBackground(panel);
				ProduktuParadisana.setGridColor(panel);
				
				JTableHeader galvene = ProduktuParadisana.getTableHeader();
				galvene.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				galvene.setBackground(panel);
				galvene.setForeground(textColor);
				
			}
			
			});
		nonemt.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
		nonemt.add(forma, BorderLayout.CENTER);
	}

	// - PARADISANAS LAPA
	private void Show() {
		paradit = new JPanel(new BorderLayout());
		paradit.setBackground(panel);
		
		JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        JButton ParaditProduktus = new JButton("Parādīt visus produktus");
		forma.add(ParaditProduktus);
		
		JPanel Tabula = new JPanel();
		forma.add(Tabula);
		
		ParaditProduktus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JCheckBox check = new JCheckBox();

				String[] columnNames = {"Nosaukums", "Skaits"};
				
			  
			    Object[][] DatiHM = new Object[ProduktuSaraksts.size()][2];
			    int i = 0;
			    for(String key: ProduktuSaraksts.keySet()) {
			    	
			    	DatiHM[i][0] = key;
			    	DatiHM[i][1] = ProduktuSaraksts.get(key);
			    	i++;
			    }
				
				
				JTable ProduktuParadisana = new JTable(DatiHM, columnNames);
				JScrollPane scrollN = new JScrollPane(ProduktuParadisana);
				ProduktuParadisana.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				Tabula.removeAll();
				ProduktuParadisana.add(scrollN);
				Tabula.setBackground(panel);
				Tabula.revalidate();
				Tabula.repaint();		
			}
		});
		paradit.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
		paradit.add(forma, BorderLayout.CENTER);
	}
		
	// - TEKSTA FAILA IZVEIDE
	private void Text() {
		teksts = new JPanel(new BorderLayout());
		teksts.setBackground(panel);
		
		JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        JButton IzveidotTekstu = createButton("Izveidot teksta failu","list.png");
		forma.add(IzveidotTekstu);
		
		IzveidotTekstu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
//				try {
//					File fails = new File("Saraksts.txt");
//					if(fails.createNewFile()) {
//					JOptionPane.showMessageDialog(null,"Fails tika izveidots!","Apsitprināts",JOptionPane.INFORMATION_MESSAGE);
//				}else {
//					JOptionPane.showMessageDialog(null,"Fails jau eksistē","Brīdinājums",JOptionPane.WARNING_MESSAGE);
//				}
//			
//				}catch (IOException ex) {
//			JOptionPane.showMessageDialog(null,"Kļūda","Error",JOptionPane.ERROR_MESSAGE);
//				}
//			}
				try {
					FileWriter fl = new FileWriter("Saraksts.txt");
					fl.write(ProduktuSaraksts.toString());
					fl.close();
					JOptionPane.showMessageDialog(null,"Fails tika izveidots!","Apsitprināts",JOptionPane.INFORMATION_MESSAGE);
				}catch (IOException ex) {
					JOptionPane.showMessageDialog(null,"Kļūda","Error",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
	
		
		teksts.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
		teksts.add(forma, BorderLayout.CENTER);
	}
	

	private JButton createButton(String text, String iconFile) {
		JButton btn = new JButton(text);

        try { btn.setIcon(new ImageIcon(iconFile)); } catch (Exception ignore) {}

        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setIconTextGap(15);
        
        btn.setFocusPainted(false);
        btn.setForeground(textColor);
        btn.setBackground(btnNormal);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBorder(new RoundedBorder(12));
        btn.setContentAreaFilled(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(btnHover); btn.repaint(); }
            public void mouseExited(MouseEvent e) { btn.setBackground(btnNormal); btn.repaint(); }
        });

        return btn;

	}
	
	private JButton createBackButton(String string) {
		//atpkal
		JButton back = new JButton ("<- Atpakaļ");
		back.setFocusPainted(false);
        back.setBackground(btnNormal);
        back.setForeground(textColor);
        back.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        back.setBorder(new RoundedBorder(12));
        back.setContentAreaFilled(false);
        back.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { back.setBackground(btnHover); back.repaint(); }
            public void mouseExited(MouseEvent e) { back.setBackground(btnNormal); back.repaint(); }
        });
         // aizsuta mus atpakal uz main menu , pirmais kods augsa
        back.addActionListener(e -> cardLayout.show(cardPanel, "Galvenā lapa"));
        return back;
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

// Vajag salabot izmerus
