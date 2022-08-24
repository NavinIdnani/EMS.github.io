package employee.management.system;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Employee_Attendance extends JFrame implements ActionListener {

    JLabel id, l1, l2, l3, l4, l5;
    Choice c1, c2, c3;
    JButton b1, b2, b3;
    JTextField tf1, tf2, tf3;
    Font f;
    JPanel p;
    Choice ch;

    Employee_Attendance() {
        super("Employee Attendance");
        setSize(450, 300);
        setLocation(300, 200);
        setResizable(false);
        f = new Font("senserif", Font.BOLD, 15);

//    
//    
//    ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Employee_Management/Icon/EmpAtt.png"));//       
//      Image img1=img.getImage().getScaledInstance(450,300, Image.SCALE_DEFAULT);
//     ImageIcon ic1= new ImageIcon(img1);
//     
        l1 = new JLabel("Select Employee ID");
        l2 = new JLabel("First Half");
        l3 = new JLabel("Second Half");
        l4 = new JLabel("Name");
        l5 = new JLabel("Email");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);

        tf1 = new JTextField();
        tf2 = new JTextField();

        tf1.setFont(f);
        tf2.setFont(f);

        tf1.setEditable(false);
        tf2.setEditable(false);

        c2 = new Choice();
        c2.add("Present");
        c2.add("Absent");

        c3 = new Choice();
        c3.add("Present");
        c3.add("Absent");

        c2.setFont(f);
        c3.setFont(f);

        b1 = new JButton("Submit");
        b2 = new JButton("Close");

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b1.setFont(f);
        b2.setFont(f);

        b1.addActionListener(this);
        b2.addActionListener(this);

        c1 = new Choice();
        try {

            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            String q = "select eid from employee";
            ResultSet rest = st.executeQuery(q);
            while (rest.next()) {
                c1.add(rest.getString("Eid"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        p = new JPanel();
        p.setLayout(new GridLayout(6, 2, 10, 10));

        p.add(l1);
        p.add(c1);
        p.add(l4);
        p.add(tf1);
        p.add(l5);
        p.add(tf2);
        p.add(l2);
        p.add(c2);
        p.add(l3);
        p.add(c3);
        p.add(b1);
        p.add(b2);

        add(p);

        c1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st;
                    st = con.createStatement();

                    String eid = c1.getSelectedItem();
                    String q2 = "select * from employee where eid='" + eid + "'";

                    ResultSet rs = st.executeQuery(q2);

                    while (rs.next()) {
                        tf1.setText(rs.getString("name"));
                        tf2.setText(rs.getString("email"));

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String ch_eid = c1.getSelectedItem();
            String ch_first_half = c2.getSelectedItem();
            String ch_second_half = c3.getSelectedItem();
            String name = tf1.getText();
            String email = tf2.getText();
            String dt = new java.util.Date().toString();

            try {

                String q1 = "Insert into attendance values('" + ch_eid + "','" + name + "','" + email + "','" + ch_first_half + "','" + ch_second_half + "','" + dt + "')";
                Connection con = ConnectionProvider.getCon();
                Statement st;
                st = con.createStatement();
                st.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "data inserted");
                setVisible(false);

            } catch (Exception ee) {
                ee.printStackTrace();
            }

        }
        if (e.getSource() == b2) {
            JOptionPane.showMessageDialog(null, "are you sure to close?");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Employee_Attendance().setVisible(true);
    }

}
