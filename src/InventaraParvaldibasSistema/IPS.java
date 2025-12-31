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
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;



public class IPS implements ActionListener{
	private JFrame frame;
	private JPanel main;
	private JPanel pievienot;
	private JPanel nonemt;
	private JPanel paradit;
	private JPanel teksts;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JTable ProduktuParadisana;
	private JButton nonemtProduktu;
	private JPanel NonemtTabulu;
	private JPanel ParaditTabulu;
	private ArrayList<Products> ProduktuSaraksts = new ArrayList<>();
	private FileWriter fl;
	private Color bg = new Color(30, 30, 30);
	private Color textColor = new Color(230, 230, 230);
    private Color panel = new Color(45, 45, 45);
	private Color btnNormal = new Color(60, 60, 60);
	private Color btnHover = new Color(90, 90, 90);
	
	
	public class Products{
		int id;
		String nosaukums;
		String kategorija;
		int skaits;
		
		public Products(int id, String nosaukums, String kategorija, int skaits) {
			this.id = id;
			this.nosaukums = nosaukums;
			this.kategorija = kategorija;
			this.skaits = skaits;
					
		}
	}
	
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
		JButton exitBtn = createButton("Beigt darbu","");
		
		addBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Pievienot"));
		removeBtn.addActionListener(e  -> {
		cardLayout.show(cardPanel,"Noņemt");
		TabulaParadit();
		});
		listBtn.addActionListener(e  -> {
		cardLayout.show(cardPanel,"Parādīt");
		ParaditTabulu();
		});
		exportBtn.addActionListener(e  -> cardLayout.show(cardPanel,"Faila izveide"));
		exitBtn.addActionListener(e -> System.exit(0));
		
		main.add(addBtn);
		main.add(removeBtn);
		main.add(listBtn);
		main.add(exportBtn);
		main.add(exitBtn);
		
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
	 	String[] izveles = {"Pārtika","Elektronika","Būvmateriāli","Higiēna","Cits.."};
	 	JComboBox <String> cb = new JComboBox<String>(izveles);
	 	cb.setVisible(true);
	 	forma.add(cb);
	    JTextField skaits = new JTextField(16);
	    skaits.setBackground(btnNormal);
	    skaits.setForeground(textColor);
		forma.add(skaits);
		
		pievienotProduktu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				String ProduktaNosaukums = produkts.getText().trim();
				String PorduktaKategorija = (String) cb.getSelectedItem();
				String ProduktaSkaitsTeksts = skaits.getText().trim();
				if(ProduktaNosaukums.isEmpty() || ProduktaSkaitsTeksts.isEmpty() ) {
					JOptionPane.showMessageDialog(forma, "Lūdzu ievadiet tekstu!");
					return;
				};
				
				int ProduktaSkaits;
			    try {
			    	ProduktaSkaits = Integer.parseInt(ProduktaSkaitsTeksts);
			    }catch(NumberFormatException x) {
			    	JOptionPane.showMessageDialog(forma, "Lūdzu ievadiet skaitli!");
			    	return;
			    }
			    ProduktuSaraksts.add(new Products(i++,ProduktaNosaukums,PorduktaKategorija,ProduktaSkaits));
				produkts.setText("");
			    skaits.setText("");
			}
		});
		pievienot.add(forma, BorderLayout.CENTER);
	}
		
	// - NONEMSANAS LAPA
	private void TabulaParadit() {
    	String[] columnNames = {"Izvēlēties","ID","Nosaukums","Kategorija", "Skaits"};
		
	    Object[][] DatiHM = new Object[ProduktuSaraksts.size()][5];
	   
	    for(int i = 0;i<ProduktuSaraksts.size();i++ ) {
	    	Products p = ProduktuSaraksts.get(i);
	    	DatiHM[i][0] = Boolean.FALSE;
	    	DatiHM[i][1] = p.id;
	    	DatiHM[i][2] = p.nosaukums;
	    	DatiHM[i][3] = p.kategorija;
	    	DatiHM[i][4] = p.skaits;
	    	
	    }

	    DefaultTableModel model = new DefaultTableModel(DatiHM, columnNames);
	    ProduktuParadisana = new JTable(model);
	    
	    ProduktuParadisana.getColumnModel().getColumn(0)
	    .setCellRenderer(ProduktuParadisana.getDefaultRenderer(Boolean.class));
	    ProduktuParadisana.getColumnModel().getColumn(0)
	    .setCellEditor(ProduktuParadisana.getDefaultEditor(Boolean.class));
		
		JScrollPane scrollN = new JScrollPane(ProduktuParadisana);
		NonemtTabulu.removeAll();
		NonemtTabulu.add(scrollN);
		NonemtTabulu.revalidate();
		NonemtTabulu.repaint();
		NonemtTabulu.setBackground(panel);
		ProduktuParadisana.setGridColor(panel);
		scrollN.getViewport().setBackground(Color.LIGHT_GRAY);
		scrollN.setBackground(Color.LIGHT_GRAY);
		
		JTableHeader galvene = ProduktuParadisana.getTableHeader();
		galvene.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		galvene.setBackground(panel);
		galvene.setForeground(textColor);
   
    } 
	private void Remove() {
		nonemt = new JPanel(new BorderLayout());
        nonemt.setBackground(panel);
        
        JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
       
        NonemtTabulu = new JPanel();
        forma.add(NonemtTabulu);
        
		 nonemtProduktu = createButton("Noņemt produktu/s","remove.png");
		 forma.add(nonemtProduktu);
			
		 nonemtProduktu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (ProduktuParadisana == null) {
				        JOptionPane.showMessageDialog(forma, "Vispirms nospied: Parādīt visus produktus!");
				        return;
				    }
					if (ProduktuParadisana.isEditing()) {
					    ProduktuParadisana.getCellEditor().stopCellEditing();
					}
					DefaultTableModel model = (DefaultTableModel) ProduktuParadisana.getModel();
			            

			        for (int viewRow = ProduktuParadisana.getRowCount() - 1; viewRow >= 0; viewRow--) {
			            Object checked = ProduktuParadisana.getValueAt(viewRow, 0);

			            if (Boolean.TRUE.equals(checked)) {
			                int modelRow = ProduktuParadisana.convertRowIndexToModel(viewRow);

			                int id = (int) model.getValueAt(modelRow, 1);
			                ProduktuSaraksts.removeIf(p -> p.id == id);
			                model.removeRow(modelRow);
			            	}
			        }
				}
				
			});
		nonemt.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
		nonemt.add(forma, BorderLayout.CENTER);
	}

	// - PARADISANAS LAPA
	private void ParaditTabulu() {
		String[] columnNames = {"Nosaukums","Kategorija","Skaits"};
		
	    Object[][] DatiHM = new Object[ProduktuSaraksts.size()][4];
	    
	    for(int i = 0;i<ProduktuSaraksts.size();i++ ) {
	    	Products p = ProduktuSaraksts.get(i);
	    	DatiHM[i][0] = p.id;
	    	DatiHM[i][1] = p.nosaukums;
	    	DatiHM[i][2] = p.kategorija;
	    	DatiHM[i][3] = p.skaits;
	    }
		
		ProduktuParadisana = new JTable(DatiHM, columnNames) {
			public boolean editCellAt(int row, int column, EventObject e) {
	    		return false;
	    	}
		};
		JScrollPane scrollN = new JScrollPane(ProduktuParadisana);
		
		ProduktuParadisana.setAutoCreateRowSorter(true);
		TableRowSorter<?> Sakartot = (TableRowSorter<?>) ProduktuParadisana.getRowSorter();
		Sakartot.toggleSortOrder(0);
	
		ParaditTabulu.removeAll();
		ParaditTabulu.add(scrollN);
		ParaditTabulu.revalidate();
		ParaditTabulu.repaint();
		ParaditTabulu.setBackground(panel);
		ProduktuParadisana.setGridColor(panel);
		
		JTableHeader galvene = ProduktuParadisana.getTableHeader();
		galvene.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		galvene.setBackground(panel);
		galvene.setForeground(textColor);
	}
	private void Show() {
		paradit = new JPanel(new BorderLayout());
		paradit.setBackground(panel);
		
		JPanel forma = new JPanel(new GridLayout(0,1,10,10));
        forma.setBackground(panel);
        forma.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
        
        ParaditTabulu = new JPanel();
        forma.add(ParaditTabulu);
		
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
				
				try {
					fl = new FileWriter("Saraksts.txt");
					fl.write(TextString());
					fl.close();
					JOptionPane.showMessageDialog(null,"Fails tika izveidots!","Apstiprināts",JOptionPane.INFORMATION_MESSAGE);
				}catch (IOException ex) {
					JOptionPane.showMessageDialog(null,"Kļūda","Error",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});

		teksts.add(createBackButton("Galvenā lapa"), BorderLayout.NORTH); 
		teksts.add(forma, BorderLayout.CENTER);
	}
	private int TextString() throws IOException {
		for(String nosaukums: ProduktuSaraksts.keySet()) {
			String key = nosaukums.toString();
			String value = ProduktuSaraksts.get(nosaukums).toString();
			fl.write(key+" "+value);
		}
		return 0;
	}

	
	
	// DIZAINS
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
		
	}
}


