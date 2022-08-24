/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import javax.swing.*;
import javax.sql.*;

/**
 *
 * @author KARAN
 */
public class View_Employee extends JFrame implements ActionListener {

    JFrame f;
    JTextField t;
    JLabel l1, l2;
    JButton b, b2;

    View_Employee() {
        f = new JFrame("View Employee");
        f.setBackground(Color.GREEN);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 500, 270);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Employee_Management/Icon/ViewEmp.png"));//       
        Image img1 = img.getImage().getScaledInstance(500, 280, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img1);
        l1.setIcon(ic1);

        l2 = new JLabel("Employee Id");
        l2.setVisible(true);
        l2.setBounds(40, 60, 250, 35);
        l2.setForeground(Color.black);
        Font F1 = new Font("Airal", Font.BOLD, 30);
        l2.setFont(F1);
        l1.add(l2);
        f.add(l1);

        t = new JTextField();
        t.setBounds(240, 60, 220, 30);
        l1.add(t);

        b = new JButton("Search");
        b.setBounds(140, 150, 100, 30);
        b.addActionListener(this);
        l1.add(b);
        f.add(l1);

        b2 = new JButton("Cancel");
        b2.setBackground(Color.red);
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        b2.setBounds(260, 150, 100, 30);
        l1.add(b2);

        f.setSize(500, 280);
        f.setLocation(450, 270);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            f.setVisible(false);
            new View_Employee_Data(t.getText());
        }
        if (e.getSource() == b2) {
            f.setVisible(false);
            new HomePage();
        }
    }

    public static void main(String[] args) {
        new View_Employee().setVisible(true);
    }
}
