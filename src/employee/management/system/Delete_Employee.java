package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.sql.*;
import java.sql.PreparedStatement;

public class Delete_Employee extends JFrame implements ActionListener {

    JTable t;
    JButton bt1;
    JTextField tf1;
    JPanel p1, p2, p3;
    String x[] = {"Employee ID", "Name", "Email", "Age", "Date of Birth", "Post"};
    String y[][] = new String[20][7];
    Font f, f1;
    int i = 0, j = 0;
    JLabel l1, l2;
    int count = 0;

    Delete_Employee(String eid) {
        super("All Employee records");
        setSize(1480, 400);
        setLocation(0, 10);
        f = new Font("MS UI Gothic", Font.BOLD, 17);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            String q = "select eid,name,email,age,dob,post from employee ";
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {

                count++;
                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("age");
                y[i][j++] = rs.getString("dob");
                y[i][j++] = rs.getString("post");

                i++;
                j = 0;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(null, "No Record Found");
            } else {
                t = new JTable(y, x);
                JScrollPane j = new JScrollPane(t);
                t.setBackground(Color.BLACK);
                t.setForeground(Color.WHITE);
                t.setFont(f);
                setSize(1480, 400);
                setLocation(0, 10);
                add(j);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        f1 = new Font("Lucida Fax", Font.BOLD, 25);

        l1 = new JLabel("Delete any Employee");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.YELLOW);

        l2 = new JLabel("Employee ID");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);

        tf1 = new JTextField();
        tf1.setFont(f);

        bt1 = new JButton("Delete Employee");
        bt1.setFont(f);

        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.RED);
        bt1.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt1);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(2, 1, 10, 10));
        p3.add(p1);
        p3.add(p2);
        p3.add(p2);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p3, "South");

    }

    public void actionPerformed(ActionEvent e) {
        String eid = tf1.getText();
        if (e.getSource() == bt1) {
            try {
                Connection con = ConnectionProvider.getCon();
                Statement st;
                st = con.createStatement();

                String q = "delete from employee where eid=? ";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setString(1, eid);

                int aa = ps.executeUpdate();

                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Employee Details deleted succefully !!");
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "No record Found");
                    this.setVisible(false);
                }

                setVisible(false);
                new HomePage();

            } catch (Exception ex) {
                System.out.println("The error is:" + ex);
            }
        }
    }

    public static void main(String[] args) {
        new Delete_Employee("").setVisible(true);
    }

}
