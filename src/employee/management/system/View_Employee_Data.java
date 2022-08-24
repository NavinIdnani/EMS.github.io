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
import java.sql.ResultSet;

/**
 *
 * @author KARAN
 */
public class View_Employee_Data extends JFrame implements ActionListener {

    JFrame f;
    JLabel id8, id, aid, id1, aid1, id2, aid2, id3, aid3, id4, aid4, id5, aid5, id6, aid6, id7, aid7, aid8, id9;
    String emp_id, name, father, address, phone, email, education, post, age, dob, aadhar;
    JButton b1, b2;

    View_Employee_Data(String eid) {
        f = new JFrame("Display Employee Details");
        f.setVisible(true);
        f.setSize(595, 642);
        f.setLocation(400, 100);
        f.setBackground(Color.GRAY);
        f.setLayout(null);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from employee where eid='" + eid + "'");

            while (rs.next()) {
                name = rs.getString("name");
                father = rs.getString("fname");
                age = rs.getString("age");
                dob = rs.getString("dob");
                address = rs.getString("address");
                phone = rs.getString("phone");
                email = rs.getString("email");
                education = rs.getString("education");
                post = rs.getString("post");
                aadhar = rs.getString("aadhar");
                emp_id = rs.getString("eid");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        id9 = new JLabel();
        id9.setBounds(0, 0, 595, 642);
        id9.setLayout(null);

        id8 = new JLabel("Employee Details:");
        id8.setBounds(170, 10, 280, 40);
        id8.setFont(new Font("Airal", Font.BOLD, 30));
        f.add(id8);
        id9.add(id8);
        f.add(id9);

        id = new JLabel("Employee ID:");
        id.setBounds(150, 70, 120, 30);
        id.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id);

        aid = new JLabel(emp_id);
        aid.setBounds(330, 70, 200, 30);
        aid.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid);

        id1 = new JLabel("Name:");
        id1.setBounds(150, 120, 100, 30);
        id1.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id1);

        aid1 = new JLabel(name);
        aid1.setBounds(330, 120, 300, 30);
        aid1.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid1);

        id2 = new JLabel("Father's name");
        id2.setBounds(150, 170, 200, 30);
        id2.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id2);

        aid2 = new JLabel(father);
        aid2.setBounds(330, 170, 300, 30);
        aid2.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid2);

        id3 = new JLabel("Address");
        id3.setBounds(150, 220, 100, 30);
        id3.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id3);

        aid3 = new JLabel(address);
        aid3.setBounds(330, 220, 300, 30);
        aid3.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid3);

        id4 = new JLabel("Mobile No:");
        id4.setBounds(150, 270, 100, 30);
        id4.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id4);

        aid4 = new JLabel(phone);
        aid4.setBounds(330, 270, 300, 30);
        aid4.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid4);

        id5 = new JLabel("Email Id:");
        id5.setBounds(150, 320, 100, 30);
        id5.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id5);

        aid5 = new JLabel(email);
        aid5.setBounds(330, 320, 300, 30);
        aid5.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid5);

        id6 = new JLabel("Education");
        id6.setBounds(150, 370, 100, 30);
        id6.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id6);

        aid6 = new JLabel(education);
        aid6.setBounds(330, 370, 300, 30);
        aid6.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid6);

        id7 = new JLabel("Job Post:");
        id7.setBounds(150, 420, 100, 30);
        id7.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(id7);

        aid7 = new JLabel(post);
        aid7.setBounds(330, 420, 300, 30);
        aid7.setFont(new Font("serif", Font.BOLD, 20));
        id9.add(aid7);

        b1 = new JButton("Print");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(160, 520, 100, 30);
        b1.addActionListener(this);
        id9.add(b1);

        b2 = new JButton("Cancel");
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        b2.setBounds(350, 520, 100, 30);
        b2.addActionListener(this);
        id9.add(b2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            JOptionPane.showMessageDialog(null, "printed sucessfully");
            f.setVisible(false);
            new HomePage();
        }
        if (e.getSource() == b2) {
            f.setVisible(false);
            new View_Employee();
        }

    }

    public static void main(String[] args) {
        new View_Employee_Data("Print Data");
    }

}
