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
import employee.management.system.ConnectionProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author KARAN
 */
public class Update_Details_Data extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    JButton b1, b2;
    JPanel p1, p2;
    Font f, f1;
    Choice ch;

    Update_Details_Data() {
        super("Update Employee");
        setLocation(300, 20);
        setSize(950, 750);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 20);

        ch = new Choice();

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select eid from employee");

            while (rs.next()) {
                ch.add(rs.getString("eid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        l1 = new JLabel("Update Employee");
        l2 = new JLabel("Name");
        l3 = new JLabel("Father's Name");
        l4 = new JLabel("Age");
        l5 = new JLabel("Date of birth");
        l6 = new JLabel("Address");
        l7 = new JLabel("Phone");
        l8 = new JLabel("Email");
        l9 = new JLabel("Education");
        l10 = new JLabel("Job Post");
        l11 = new JLabel("Aadhar");
        l12 = new JLabel("Employee ID");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();
        tf10 = new JTextField();

        b1 = new JButton("Update Data");
        b2 = new JButton("Back");

        l1.setHorizontalAlignment(JLabel.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);
        l12.setFont(f1);
        ch.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);
        tf10.setFont(f1);

        b1.setFont(f1);
        b2.setFont(f1);

        b1.setBackground(Color.BLACK);
        b2.setBackground(Color.RED);

        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(12, 2, 10, 10));

        p2.add(l12);
        p2.add(ch);
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l5);
        p2.add(tf4);
        p2.add(l6);
        p2.add(tf5);
        p2.add(l7);
        p2.add(tf6);
        p2.add(l8);
        p2.add(tf7);
        p2.add(l9);
        p2.add(tf8);
        p2.add(l10);
        p2.add(tf9);
        p2.add(l11);
        p2.add(tf10);
        p2.add(b1);
        p2.add(b2);

        setLayout(new BorderLayout());
        add(p1, "North");
        add(p2, "Center");

        ch.addMouseListener(new MouseAdapter() {
            public void mouseclicked(MouseEvent arg0) {
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st;
                    st = con.createStatement();
                    String eid = ch.getSelectedItem();
                    String q1 = "select * from employee where eid='" + eid + "'";
                    ResultSet rest1 = st.executeQuery(q1);
                    while (rest1.next()) {
                        tf1.setText(rest1.getString("name"));
                        tf2.setText(rest1.getString("fname"));
                        tf3.setText(rest1.getString("age"));
                        tf4.setText(rest1.getString("dob"));
                        tf5.setText(rest1.getString("address"));
                        tf6.setText(rest1.getString("phone"));
                        tf7.setText(rest1.getString("email"));
                        tf8.setText(rest1.getString("education"));
                        tf9.setText(rest1.getString("job_post"));
                        tf10.setText(rest1.getString("aadhar"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String id = ch.getSelectedItem();
        String name = tf1.getText();
        String fname = tf2.getText();
        String age = tf3.getText();
        String dob = tf4.getText();
        String address = tf5.getText();
        String phone = tf6.getText();
        String email = tf7.getText();
        String education = tf8.getText();
        String job_post = tf9.getText();
        String aadhar = tf10.getText();

        if (e.getSource() == b1) {
            try {
                Connection con = ConnectionProvider.getCon();
                Statement st;
                st = con.createStatement();
                String q1 = "update employee set name='" + name + "',fname='" + fname + "',age='" + age + "',dob='" + dob + "',address='" + address + "',phone='" + phone + "',email='" + email + "',education='" + education + "',post='" + job_post + "',aadhar='" + aadhar + "' where eid='" + id + "'";
                int aa = st.executeUpdate(q1);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "your Data Succussfully Updated!!");
                    this.setVisible(false);
                    new Update_Details_Data();
                } else {
                    JOptionPane.showMessageDialog(null, "Please Fill all Details Carefully");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == b2) {
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Update_Details_Data().setVisible(true);
    }
}
