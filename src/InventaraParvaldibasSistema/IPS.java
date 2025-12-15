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

    public static void main(String[] args) {
        new IPS();
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
