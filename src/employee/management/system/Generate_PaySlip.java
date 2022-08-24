package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JButton.*;

public class Generate_PaySlip extends JFrame implements ActionListener {

    JTextArea t1;
    JLabel l1, l2, l3;
    JButton b1, b2;
    Choice ch1, ch2, ch3;
    JPanel p;
    Font f;

    Generate_PaySlip() {
        super("Generate Pay Slip");
        setSize(800, 500);
        setResizable(false);
        setLocation(50, 90);

        p = new JPanel();
        f = new Font("arial", Font.BOLD, 15);

        l1 = new JLabel("Employee Id");
        l2 = new JLabel("Month");
        l3 = new JLabel("Year");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);

        ch1 = new Choice();

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            String q = "Select eid from employee";
            ResultSet rest = st.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("eid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ch2 = new Choice();
        ch2.add("January");
        ch2.add("Feburary");
        ch2.add("March");
        ch2.add("April");
        ch2.add("May");
        ch2.add("June");
        ch2.add("July");
        ch2.add("August");
        ch2.add("September");
        ch2.add("October");
        ch2.add("November");
        ch2.add("December");

        ch3 = new Choice();
        ch3.add("2015");
        ch3.add("2016");
        ch3.add("2017");
        ch3.add("2018");
        ch3.add("2019");
        ch3.add("2020");
        ch3.add("2021");
        ch3.add("2022");

        ch1.setFont(f);
        ch2.setFont(f);
        ch3.setFont(f);

        b1 = new JButton("Print");
        b2 = new JButton("Close");

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);

        p = new JPanel();
        p.setLayout(new GridLayout(4, 2, 10, 10));

        p.add(l1);
        p.add(ch1);

        p.add(l2);
        p.add(ch2);

        p.add(l3);
        p.add(ch3);

        p.add(b1);
        p.add(b2);

        t1 = new JTextArea();

        JScrollPane js = new JScrollPane(t1);
        t1.setEditable(false);
        t1.setFont(f);

        setLayout(new BorderLayout());

        add(js, "Center");
        add(p, "South");

        ch1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st;
                    st = con.createStatement();
                    String eid2 = ch1.getSelectedItem();
                    String q3 = "select * from employee where eid='" + eid2 + "'";
                    ResultSet rest1 = st.executeQuery(q3);

                } catch (Exception exx) {
                    exx.printStackTrace();

                }
            }

        });

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            t1.setText("\t----------------------- Pay Slip-----------------\n\n");

            try {
                Connection con = ConnectionProvider.getCon();
                Statement st;
                st = con.createStatement();
                String month_year = ch2.getSelectedItem() + " " + ch3.getSelectedItem();
                String eid = ch1.getSelectedItem();

                String q = "select eid,name,email from employee where eid ='" + eid + "' ";
                ResultSet rest1 = st.executeQuery(q);

                while (rest1.next()) {
                    t1.append("\nEmployee Id: " + rest1.getString("eid"));
                    t1.append("\nEmployee name: " + rest1.getString("name"));
                    t1.append("\nEmployee email: " + rest1.getString("email"));

                    t1.append("\n---------------------------------------------------------------\n\n");
                }

                String q1 = "select * from salary where month_year= '" + month_year + "' and eid='" + eid + "'  ";
                ResultSet rest2 = st.executeQuery(q1);

                if (rest2.next() == false) {
                    t1.append("\n\n-------------------------------------------------------------------------------");
                    t1.append("Record Not Found of this month and year.\n");
                    t1.append("\n\n-------------------------------------------------------------------------------");
                    t1.append("Please add the salary of this month and year for this record.\n");

                } else {

                    t1.append("\nHRA : " + Float.parseFloat(rest2.getString("hra")));
                    t1.append("\nDA : " + Float.parseFloat(rest2.getString("da")));
                    t1.append("\nMID : " + Float.parseFloat(rest2.getString("mid")));
                    t1.append("\nPF : " + Float.parseFloat(rest2.getString("pf")));
                    t1.append("\nBASIC SALARY : " + Float.parseFloat(rest2.getString("basic")));

                    t1.append("\n------------------------------------------------------------\n\n");
                    float gross_salary = Float.parseFloat(rest2.getString("hra")) + Float.parseFloat(rest2.getString("da")) + Float.parseFloat(rest2.getString("mid")) + Float.parseFloat(rest2.getString("pf")) + Float.parseFloat(rest2.getString("basic"));
                    double tax = (gross_salary * 2.1) / 100;
                    t1.append("\nGross Salary:" + gross_salary);
                    t1.append("\nTotal:" + gross_salary);
                    t1.append("\nTax 2.1% of Salary:" + tax);

                }

            } catch (Exception exx) {
                exx.printStackTrace();

            }

        }

        if (e.getSource() == b2) {
            JOptionPane.showMessageDialog(null, "Are you Sure???");
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Generate_PaySlip().setVisible(true);
    }
}
