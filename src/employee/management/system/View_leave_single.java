package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JButton.*;

public class View_leave_single extends JFrame {

    JTable t;
    String x[] = {"Employee ID", "Name", "Email", "Start Date", "End Date", "Reason", "Apply Date"};
    String y[][] = new String[20][7];
    int i = 0, j = 0;
    Font f, f1;
    int count = 0;

    View_leave_single(String eid) {

        super("Particular Employee Leave records");
        setSize(1480, 400);
        setLocation(0, 10);
        f = new Font("MS UI Gothic", Font.BOLD, 17);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            String q = "select * from applyleave where eid='" + eid + "' ";
            ResultSet rest = st.executeQuery(q);
            while (rest.next()) {

                count++;
                y[i][j++] = rest.getString("eid");
                y[i][j++] = rest.getString("name");
                y[i][j++] = rest.getString("email");
                y[i][j++] = rest.getString("startdate");
                y[i][j++] = rest.getString("enddate");
                y[i][j++] = rest.getString("reason");
                y[i][j++] = rest.getString("applydate");
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

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

    }

}
