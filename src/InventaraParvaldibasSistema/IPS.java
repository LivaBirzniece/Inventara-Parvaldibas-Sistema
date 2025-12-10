package InventaraParvaldibasSistema;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IPS {

    public IPS() {

        JFrame frame = new JFrame("Inventāra Pārvaldibas Sistēma");


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(0, 1, 10, 10)); // atstarpes, var palielinat
        main.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));


        frame.add(main, BorderLayout.CENTER);



        JButton pievienot = createModernButton("Pievienot produktu");
        JButton nonemt = createModernButton("Noņemt produktu");
        JButton paradit = createModernButton("Parādīt visus produktus");
        JButton teksts = createModernButton("Pārtaisīt produktu sarakstu teksta failā");


        main.add(pievienot);
        main.add(nonemt);
        main.add(paradit);
        main.add(teksts);


        frame.pack();
        frame.setLocationRelativeTo(null); // centrs
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private JButton createModernButton(String text) {
        JButton button = new JButton(text);

  
        button.setFocusPainted(false);


        button.setBackground(new Color(245, 245, 245)); // light gray
        button.setForeground(Color.DARK_GRAY);

        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

 
        button.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(230, 230, 230)); // peleks
            }


            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(245, 245, 245)); // back to white
            }
        });

        return button;
    }


    // Main method
    public static void main(String[] args) {
        new IPS();
    }


}


