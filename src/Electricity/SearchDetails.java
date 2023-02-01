package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class SearchDetails extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1, b2;
    JLabel l1, l2;
    Choice c1, c2;
    JTextArea ta1;
    String x[] = {"Name","Meter","Address","City","State","Email", "Phone"};
    String y[][] = new String[40][8];
    int i=0, j=0;
    SearchDetails(){
        super("Search Details");
        setSize(700,750);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        l1 = new JLabel("Search by Name");
        l1.setBounds(20, 20, 150, 20);
        add(l1);
        
        c1 = new Choice();
        t1 = new JTable(y,x);
        
        try{
            Conn c  = new Conn();
            String s1 = "select * from customer1";
            ResultSet rs  = c.s.executeQuery(s1);
            
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            
            String str2 = "select * from customer";
            rs = c.s.executeQuery(str2);
            while(rs.next()){
                c1.add(rs.getString("name"));
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        c1.setBounds(180,20, 150, 20);
        add(c1);
        
        b1 = new JButton("Search");
        b1.setBounds(20, 70, 80, 20);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Print");
        b2.setBounds(120, 70, 80, 20);
        b2.addActionListener(this);
        add(b2);
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String str = "select * from customer where name = '"+c1.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(str);
                t1.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){}
        }else if(ae.getSource() == b2){
            try{
                t1.print();
            }catch(Exception e){}
        }
    }
    
    public static void main(String[] args){
        new SearchDetails().setVisible(true);
    }
    
}

