package com.kingaspx.main;

// Java Program to create a simple JComboBox 
// and add elements to it
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import org.w3c.dom.css.RGBColor;

public class ActivitySelect extends JFrame implements ItemListener {

    static javax.swing.JPanel slc_JPanel;
    static JFrame slc_frame;
    static JComboBox slc_JComboBox;
    static JTextField slc_JTextField;

    private static void runActivitySelector() {
        slc_frame = new JFrame("Select Activity");
        ActivitySelect as = new ActivitySelect();
        
        slc_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] secenekler = { "Act 1", "Act 2", "Act 3", "Act 4",
                "Act 5", "Act 6", "Act 7",
                "Act 8"};
        slc_JComboBox = new JComboBox(secenekler);
        slc_JComboBox.addItemListener(as);
        slc_JTextField = new JTextField("Which activity is starting?");
        slc_JTextField.setEditable(false);
        slc_JTextField.setHorizontalAlignment(JTextField.CENTER);
        Font font = new Font("Verdana", Font.BOLD, 30);
        slc_JTextField.setFont(font);
        slc_JTextField.setOpaque(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        slc_frame.setSize(500, 250);
        int x = (dim.width - slc_frame.getWidth()) / 2;
        int y = (dim.height - slc_frame.getHeight()) / 2;
        JButton button = new JButton("Start QR reader");
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBackground(new Color(4,170,109));
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.runQRReader(slc_JComboBox.getSelectedItem().toString());
                slc_frame.dispose();
            }
            
        });
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,1, 5,5));
        p.add(slc_JTextField);
        p.add(slc_JComboBox);
        p.add(button);
        slc_frame.add(p);
        slc_frame.setLocation(x,y);
        slc_frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public static void main(String[] args) {
        runActivitySelector();
    }
}
