package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import employee.management.system.ConnectionProvider;
import javax.swing.*;
import javax.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class loginPage extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1, l2;
    JTextField t1;
    JButton b1, b2;
    JPasswordField t2;

    loginPage() {
        f = new JFrame("login");

        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel("Username");
        l1.setBounds(40, 20, 100, 30);
        f.add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(40, 70, 100, 30);
        f.add(l2);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        f.add(t1);

        t2 = new JPasswordField();
        t2.setBounds(150, 70, 150, 30);
        f.add(t2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Employee_Management/Icon/lock.png"));
        Image i2;
        i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        System.out.println("image k baad");

        JLabel l3 = new JLabel(i3);
        l3.setBounds(350, 20, 150, 150);
        f.add(l3);
        b1 = new JButton("Login");
        b1.setBackground(Color.BLACK);
        b1.setBounds(40, 140, 120, 30);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Close");
        b2.setBackground(Color.BLACK);
        b2.setBounds(180, 140, 120, 30);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(600, 300);
        f.setLocation(400, 300);

    }

    public void actionPerformed(ActionEvent ee) {

        if (ee.getSource() == b1) {

            System.out.println("if wali stmt");

            try {

                Connection con = ConnectionProvider.getCon();

                Statement st;
                st = con.createStatement();
                String name = t1.getText();
                String pass = t2.getText();
                String q = "select * from logindata where username= '" + name + "'and password='" + pass + "'";

                ResultSet rs = st.executeQuery(q);

                if (rs.next()) {
                    new HomePage().setVisible(true);
                    System.out.println("LOGIN");
                    this.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null, "You have entered wrong username and password");
                    f.setVisible(false);
                    f.setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (ee.getSource() == b2) {
            this.f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new loginPage();
    }

}
