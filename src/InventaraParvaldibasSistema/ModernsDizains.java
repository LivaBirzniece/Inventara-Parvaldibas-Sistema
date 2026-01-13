package InventaraParvaldibasSistema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModernsDizains {
    public static final Color BG_DARK = new Color(10, 10, 12); // Vēl tumšāks
    public static final Color CARD_BG = new Color(22, 22, 25);
    public static final Color TEXT_WHITE = new Color(245, 245, 245);
    public static final Color TEXT_DIM = new Color(130, 130, 135);
    public static final Color ACCENT_BLUE = new Color(0, 122, 255);

    public static void apply() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception e) {}
        
        // Noņem fokusa rāmjus (outline) ap tekstu un pogām
        UIManager.put("Component.focusWidth", 0);
        UIManager.put("Button.focusWidth", 0);
        UIManager.put("ScrollBar.showButtons", true);
        UIManager.put("Table.selectionBackground", new Color(45, 45, 50));
        UIManager.put("Table.alternateRowColor", new Color(15, 15, 18)); // Ļoti tumša josla
        
        UIManager.put("Panel.background", BG_DARK);
        UIManager.put("Label.foreground", TEXT_WHITE);
    }

    public static JPanel createCard(String title, String subtitle, Color iconColor) {
        JPanel card = new JPanel(new BorderLayout(15, 5));
        card.setBackground(CARD_BG);
        card.setPreferredSize(new Dimension(350, 140));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 50), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        JPanel iconPart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(iconColor);
                g2.fillRoundRect(0, 0, 45, 45, 14, 14);
            }
        };
        iconPart.setPreferredSize(new Dimension(45, 45));
        iconPart.setOpaque(false);

        JPanel textPart = new JPanel(new GridLayout(2, 1));
        textPart.setOpaque(false);
        JLabel t = new JLabel(title); 
        t.setFont(new Font("Segoe UI", Font.BOLD, 20));
        JLabel s = new JLabel(subtitle); 
        s.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        textPart.add(t); 
        textPart.add(s);

        card.add(iconPart, BorderLayout.WEST);
        card.add(textPart, BorderLayout.CENTER);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return card;
    }
}