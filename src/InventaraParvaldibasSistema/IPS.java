package InventaraParvaldibasSistema;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.util.ArrayList;

public class IPS {
    private JFrame frame;
    private CardLayout layout;
    private JPanel cards;
    private ArrayList<Product> products = new ArrayList<>();
    private DefaultTableModel viewTableModel, editTableModel;

    public static void main(String[] args) {
        ModernsDizains.apply();
        SwingUtilities.invokeLater(IPS::new);
    }

    public IPS() {
        frame = new JFrame("Inventāra Pārvaldības Sistēma");
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new CardLayout();
        cards = new JPanel(layout);
        cards.setBackground(ModernsDizains.BG_DARK);

        cards.add(createDashboard(), "MENU");
        cards.add(createFormPanel(), "ADD");
        cards.add(createListPanel(false), "VIEW");
        cards.add(createListPanel(true), "EDIT");

        frame.add(cards);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createDashboard() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);

        JLabel title = new JLabel("INVENTĀRA PĀRVALDĪBAS SISTĒMA");
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(70, 0, 50, 0));
        p.add(title, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        JPanel grid = new JPanel(new GridLayout(2, 2, 30, 30));
        grid.setOpaque(false);

        grid.add(setupCard("Pievienot", "Jauns ieraksts", ModernsDizains.ACCENT_BLUE, "ADD"));
        grid.add(setupCard("Saraksts", "Skatīt visu", new Color(76, 175, 80), "VIEW"));
        grid.add(setupCard("Rediģēt", "Labot vai dzēst", new Color(255, 152, 0), "EDIT"));
        grid.add(setupCard("Eksports", "Saglabāt .txt", new Color(156, 39, 176), "EXPORT"));

        centerWrapper.add(grid);
        p.add(centerWrapper, BorderLayout.CENTER);

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomBar.setOpaque(false);
        bottomBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));

        JButton exitBtn = new JButton("IZIET NO PROGRAMMAS");
        exitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exitBtn.setForeground(new Color(255, 80, 80));
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 80, 80), 2, true));
        exitBtn.setPreferredSize(new Dimension(300, 55));
        exitBtn.addActionListener(e -> System.exit(0));

        bottomBar.add(exitBtn);
        p.add(bottomBar, BorderLayout.SOUTH);
        return p;
    }

    private JPanel setupCard(String t, String s, Color c, String target) {
        JPanel card = ModernsDizains.createCard(t, s, c);
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (target.equals("EXPORT")) {
                    exportToTxt();
                } else {
                    refreshData();
                    layout.show(cards, target);
                }
            }
        });
        return card;
    }

    private JPanel createFormPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(ModernsDizains.CARD_BG);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 65), 1),
            BorderFactory.createEmptyBorder(50, 60, 50, 60)
        ));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(15, 15, 15, 15);
        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField n = new JTextField(20);
        JComboBox<String> k = new JComboBox<>(new String[]{"Pārtika", "Elektronika", "Mēbeles", "Cits"});
        JComboBox<String> s = new JComboBox<>(new String[]{"Izcils", "Lietots", "Bojāts", "Norakstīt"});
        JTextField sk = new JTextField(20);

        styleFieldLabel(form, "Produkta nosaukums", 0, g); g.gridx=1; form.add(n, g);
        styleFieldLabel(form, "Kategorija", 1, g); g.gridx=1; form.add(k, g);
        styleFieldLabel(form, "Stāvoklis", 2, g); g.gridx=1; form.add(s, g);
        styleFieldLabel(form, "Daudzums", 3, g); g.gridx=1; form.add(sk, g);

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 20, 0));
        btnRow.setOpaque(false);
        
        JButton back = new JButton("ATPAKAĻ");
        back.addActionListener(e -> layout.show(cards, "MENU"));
        
        JButton save = new JButton("SAGLABĀT");
        save.setBackground(ModernsDizains.ACCENT_BLUE);
        save.setForeground(Color.WHITE);
        
        save.addActionListener(e -> {
            try {
                String name = n.getText().trim();
                if (name.isEmpty()) { JOptionPane.showMessageDialog(frame, "Ievadiet nosaukumu!"); return; }
                int count = Integer.parseInt(sk.getText().trim());

                products.add(new Product(products.size() + 1, name, k.getSelectedItem().toString(), s.getSelectedItem().toString(), count));
                n.setText(""); sk.setText("");
                refreshData();
                layout.show(cards, "MENU");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Ievadiet korektus datus!");
            }
        });
        
        btnRow.add(back); btnRow.add(save);
        g.gridx=0; g.gridy=4; g.gridwidth=2; g.insets = new Insets(40, 0, 0, 0);
        form.add(btnRow, g);

        p.add(form);
        return p;
    }

    private JPanel createListPanel(boolean editMode) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);

        JLabel l = new JLabel(editMode ? "REDIĢĒŠANA" : "INVENTĀRA SARAKSTS");
        l.setFont(new Font("Segoe UI", Font.BOLD, 30));
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        p.add(l, BorderLayout.NORTH);

        String[] cols = editMode ? new String[]{" ", "ID", "Nosaukums", "Kategorija", "Stāvoklis", "Skaits"} 
                                 : new String[]{"ID", "Nosaukums", "Kategorija", "Stāvoklis", "Skaits"};
        
        DefaultTableModel m = new DefaultTableModel(new Object[][]{}, cols) {
            @Override public Class<?> getColumnClass(int c) { return (editMode && c == 0) ? Boolean.class : Object.class; }
            @Override public boolean isCellEditable(int r, int c) { return editMode && c == 0; }
        };
        
        if(editMode) editTableModel = m; else viewTableModel = m;

        JTable t = new JTable(m);
        t.setRowHeight(50);
        t.setBackground(ModernsDizains.BG_DARK);
        t.setFillsViewportHeight(true);
        t.setGridColor(new Color(40, 40, 45));
        

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(ModernsDizains.BG_DARK);
        centerRenderer.setForeground(Color.WHITE);
        centerRenderer.setBorder(null); 

        for(int i=(editMode?1:0); i<t.getColumnCount(); i++) {
            t.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(t);
        scroll.getViewport().setBackground(ModernsDizains.BG_DARK);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 55)));
        
        JPanel container = new JPanel(new BorderLayout(0, 30));
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(0, 80, 60, 80));
        container.add(scroll, BorderLayout.CENTER);

        JPanel btm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        btm.setOpaque(false);
        if(editMode) {
            JButton d = new JButton("DZĒST ATLASĪTO");
            d.setBackground(new Color(180, 40, 40));
            d.addActionListener(e -> {
                for(int i = m.getRowCount()-1; i>=0; i--) if((Boolean)m.getValueAt(i,0)) products.remove(i);
                refreshData();
            });
            btm.add(d);
        }
        JButton b = new JButton("ATPAKAĻ");
        b.addActionListener(e -> layout.show(cards, "MENU"));
        btm.add(b);
        container.add(btm, BorderLayout.SOUTH);

        p.add(container, BorderLayout.CENTER);
        return p;
    }

    private void styleFieldLabel(JPanel p, String txt, int y, GridBagConstraints g) {
        JLabel l = new JLabel(txt);
        l.setFont(new Font("Segoe UI", Font.BOLD, 15));
        g.gridx=0; g.gridy=y; g.gridwidth=1; p.add(l, g);
    }

    private void refreshData() {
        if(viewTableModel != null) {
            viewTableModel.setRowCount(0);
            for(Product p : products) viewTableModel.addRow(new Object[]{p.id, p.name, p.category, p.condition, p.count});
        }
        if(editTableModel != null) {
            editTableModel.setRowCount(0);
            for(Product p : products) editTableModel.addRow(new Object[]{false, p.id, p.name, p.category, p.condition, p.count});
        }
    }

    private void exportToTxt() {
    	if (products.isEmpty()) {
    		JOptionPane.showMessageDialog(frame, "Nav datu, ko eksportēt!");
    		return;
    	}
        try (FileWriter w = new FileWriter("Inventars.txt")) {

            w.write(String.format("%-5s | %-15s | %-20s | %-15s | %-10s\n", "ID", "STĀVOKLIS", "NOSAUKUMS", "KATEGORIJA", "SKAITS"));
            w.write("------------------------------------------------------------------------------\n");
            
            for(Product p : products) {

                w.write(String.format("%-5d | %-15s | %-20s | %-15s | %-10d\n", 
                        p.id, p.condition, p.name, p.category, p.count));
            }
            JOptionPane.showMessageDialog(frame, "Dati eksportēti (ID | STĀVOKLIS | NOSAUKUMS...)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Kļūda eksportējot!");
        }
    }

    static class Product {
        int id, count; String name, category, condition;
        Product(int i, String n, String cat, String con, int cou) { id=i; name=n; category=cat; condition=con; count=cou; }
    }
}