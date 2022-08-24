package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JButton.*;

public class View_Leaves extends JFrame implements ActionListener {

    JTable t;
    JButton bt1;
    JTextField tf1;
    JPanel p1, p2, p3;
    String x[] = {"Employee ID", "Name", "Email", "Start Date", "End Date", "Reason", "Apply Date"};
    String y[][] = new String[20][7];
    int i = 0, j = 0;
    Font f, f1;
    JLabel l1, l2;

    View_Leaves() {
        super("All Employee Leave records");
        setSize(1480, 400);
        setLocation(0, 10);
        f = new Font("MS UI Gothic", Font.BOLD, 17);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            String q = "select * from applyleave";
            ResultSet rest = st.executeQuery(q);
            while (rest.next()) {
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
            t = new JTable(y, x);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JScrollPane j = new JScrollPane(t);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        t.setFont(f);

        f1 = new Font("Lucida Fax", Font.BOLD, 25);

        l1 = new JLabel("Search any Employee");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.YELLOW);

        l2 = new JLabel("Employee ID");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);

        tf1 = new JTextField();
        tf1.setFont(f);

        bt1 = new JButton("Search Employee");
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
        add(j);

    }

    public void actionPerformed(ActionEvent e1) {
        String eid = tf1.getText();
        if (e1.getSource() == bt1) {
            System.out.println("button clicked");
            new View_leave_single(eid).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new View_Leaves().setVisible(true);
    }
}
