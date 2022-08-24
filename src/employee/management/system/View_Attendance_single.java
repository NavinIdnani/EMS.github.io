package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_Attendance_single extends JFrame {

    JLabel l1, l2;
    JTable t;
    JTextField tf1;
    JButton b1;
    JPanel p1, p2, p3;
    String x[] = {"Employee ID", "Name", "Email", "First Half", "Second Half", "date"};
    String y[][] = new String[20][6];
    int i = 0, j = 0;
    Font f, f1;
    int count = 0;

    View_Attendance_single(String eid) {
        super("Particular Employee attendance records");
        setSize(1480, 400);
        setLocation(0, 10);
        f = new Font("MS UI Gothic", Font.BOLD, 17);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from attendance where eid='" + eid + "'");

            while (rs.next()) {

                count++;
                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("first_half");
                y[i][j++] = rs.getString("second_half");
                y[i][j++] = rs.getString("day_date");

                i++;
                j = 0;

            }

            if (count == 0) {
                JOptionPane.showMessageDialog(null, "No Record Found");
            } else {

                t = new JTable(y, x);

                t.setBackground(Color.BLACK);
                t.setForeground(Color.WHITE);
                t.setFont(f);
                setSize(1480, 400);
                setLocation(0, 10);
                JScrollPane js = new JScrollPane(t);
                add(js);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
