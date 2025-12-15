package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.border.AbstractBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class IPS implements ActionListener{
	private JFrame frame;
	private HashMap<String,String> ProduktuSaraksts = new HashMap<>();
    private JPanel mainPanel;      
    private JPanel menuPanel;      
    private Color bg = new Color(30, 30, 30);
    private Color panel = new Color(45, 45, 45);
    private Color btnNormal = new Color(60, 60, 60);
    private Color btnHover = new Color(90, 90, 90);
    private Color textColor = new Color(230, 230, 230);
	
	public IPS() {
		




		 // galvenais window,
        frame = new JFrame("Inventāra Sistēma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(bg);
        frame.setLayout(new BorderLayout());
     	JTextArea tekstsIzdrukat = new JTextArea(16,16);
		
		frame = new JFrame();
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);
		
		   // tituls augša
        JLabel title = new JLabel("Inventāra Pārvaldības Sistēma", SwingConstants.CENTER);
        title.setForeground(textColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        // pogas
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 15, 15));
        menuPanel.setBackground(panel);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 120, 20, 120));

        JButton addBtn    = createButton("Pievienot produktu", "add.png");
        JButton removeBtn = createButton("Noņemt produktu", "remove.png");
        JButton listBtn   = createButton("Parādīt produktus", "list.png");
        JButton exportBtn = createButton("Eksportēt failā", "file.png");

        menuPanel.add(addBtn);
        menuPanel.add(removeBtn);
        menuPanel.add(listBtn);
        menuPanel.add(exportBtn);


        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(panel);
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //  pogas mainit ekranu
        addBtn.addActionListener(e -> switchScreen("Pievienot produktu"));
        removeBtn.addActionListener(e -> switchScreen("Noņemt produktu"));
        listBtn.addActionListener(e -> switchScreen("Produktu saraksts"));
        exportBtn.addActionListener(e -> switchScreen("Eksportēt sarakstu"));
    }

		
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
		// tituls augša
        JLabel title = new JLabel("Inventāra Pārvaldības Sistēma", SwingConstants.CENTER);
        title.setForeground(textColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        // pogas
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 15, 15));
        menuPanel.setBackground(panel);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 120, 20, 120));

        JButton addBtn    = createButton("Pievienot produktu", "add.png");
        JButton removeBtn = createButton("Noņemt produktu", "remove.png");
        JButton listBtn   = createButton("Parādīt produktus", "list.png");
        JButton exportBtn = createButton("Eksportēt failā", "file.png");

        menuPanel.add(addBtn);
        menuPanel.add(removeBtn);
        menuPanel.add(listBtn);
        menuPanel.add(exportBtn);


        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(panel);
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //  pogas mainit ekranu
        addBtn.addActionListener(e -> switchScreen("Pievienot produktu"));
        removeBtn.addActionListener(e -> switchScreen("Noņemt produktu"));
        listBtn.addActionListener(e -> switchScreen("Produktu saraksts"));
        exportBtn.addActionListener(e -> switchScreen("Eksportēt sarakstu"));
    
		
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
	
	
	public static void main(String[] args) {
		new IPS();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

     

    private void switchScreen(String text) {

        JPanel screen = new JPanel(new BorderLayout());
        screen.setBackground(panel);

        // atpakal !!!
        JButton back = new JButton("← Atpakaļ");
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
        back.addActionListener(e -> showMenu());


        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(textColor);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        screen.add(back, BorderLayout.NORTH);
        screen.add(label, BorderLayout.CENTER);


        frame.remove(mainPanel);
        mainPanel = screen;
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }


    private void showMenu() {
        frame.remove(mainPanel);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(panel);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

  // dizains, fonti
    private JButton createButton(String text, String iconFile) {
        JButton btn = new JButton(text);

        try { btn.setIcon(new ImageIcon(iconFile)); } catch (Exception ignore) {}

        btn.setHorizontalAlignment(SwingConstants.LEFT);
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

    // apgriezti sturi , nezzinu kapec met ara error. viss ir ok

    class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int r) { radius = r; }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(new Color(120, 120, 120));
            g.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
        }
    }
    }

