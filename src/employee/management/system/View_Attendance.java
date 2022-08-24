package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_Attendance extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTable t;
    JTextField tf1;
    JButton b1;
    JPanel p1, p2, p3;
    String x[] = {"Employee ID", "Name", "Email", "First Half", "Second Half", "date"};
    String y[][] = new String[20][6];
    int i = 0, j = 0;
    Font f, f1;

    View_Attendance() {
        super("A Employee attendance records");
        setSize(1480, 400);
        f = new Font("MS UI Gothic", Font.BOLD, 17);

        try {

            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from attendance");

            while (rs.next()) {

                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("first_half");
                y[i][j++] = rs.getString("second_half");
                y[i][j++] = rs.getString("day_date");

                i++;
                j = 0;

            }
            t = new JTable(y, x);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        t.setFont(f);

        JScrollPane js = new JScrollPane(t);

        f1 = new Font("Lucida Fax", Font.BOLD, 25);
        l1 = new JLabel("Search any Employee");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l1.setForeground(Color.YELLOW);

        l2 = new JLabel("Employee ID");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);

        tf1 = new JTextField();
        b1 = new JButton("Search Employee");
        tf1.setFont(f);
        b1.setFont(f);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.RED);
        b1.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 3, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(b1);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(2, 1, 10, 10));
        p3.add(p1);
        p3.add(p2);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p3, "South");
        add(js);

    }

    public void actionPerformed(ActionEvent e) {
        String eid = tf1.getText();
        if (e.getSource() == b1) {
            new View_Attendance_single(eid).setVisible(true);

        }

    }

    public static void main(String[] args) {
        new View_Attendance().setVisible(true);
    }
}
