package Inventory_System;

import javax.swing.*;

import DataBase.JDataBase;
import MainProgram.SourceMain;
import Signup_Login_Manager.LoginManager;
import Signup_Login_Manager.SignupManager;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

public class ShowHistory extends JFrame{
    JLabel totalSoldText;
    JTable itemList;
    TableModel tableModel;
    String[] column;
    JButton lastMonthB,currentMonthB, specificDateB,allB,thisYearB,lastYearB,goB,todayB,yesterdayB;
    JTextField day,month,year;
    Date currentDate;
    Calendar cal;
    public ShowHistory(){
        super("History");
        setLayout(null);
        column = new String[]{"Item Name","Sold Quantity","Total Price"};

        itemList = new JTable();
        tableModel = new TableModel();
        tableModel.setColumnIdentifiers(column);
        itemList.setModel(tableModel);

        JScrollPane jScrollPane = new JScrollPane(itemList);
        jScrollPane.setBounds(50, 50, jScrollPane.getPreferredSize().width, 450);

        add(jScrollPane);

        Date currentDate= new Date(); //Get Current Calender Date START-------
        cal = Calendar.getInstance();
        cal.setTime(currentDate);
        /*int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);*/ //--------- Get Calender Date END

        ButtonHandler buttonHandler = new ButtonHandler();

        specificDateB = new JButton("Specific Date");
        specificDateB.setBounds(580,50,120,25);
        specificDateB.addActionListener(buttonHandler);
        add(specificDateB);

        todayB = new JButton("Today");
        todayB .setBounds(580, 100, 120, 25);
        todayB .addActionListener(buttonHandler);
        add(todayB);

        yesterdayB = new JButton("Yesterday");
        yesterdayB.setBounds(580,150,120,25);
        yesterdayB.addActionListener(buttonHandler);
        add(yesterdayB);

        currentMonthB = new JButton("This Month");
        currentMonthB.setBounds(580,200,120,25);
        currentMonthB.addActionListener(buttonHandler);
        add(currentMonthB);

        lastMonthB = new JButton("Last Month");
        lastMonthB.setBounds(580,250,120,25);
        lastMonthB.addActionListener(buttonHandler);
        add(lastMonthB);

        thisYearB = new JButton("This Year");
        thisYearB.setBounds(580,300,120,25);
        thisYearB.addActionListener(buttonHandler);
        add(thisYearB);

        lastYearB = new JButton("Last Year");
        lastYearB.setBounds(580,350,120,25);
        lastYearB.addActionListener(buttonHandler);
        add(lastYearB);

        allB = new JButton("Show All");
        allB.setBounds(580,400,120,25);
        allB.addActionListener(buttonHandler);
        add(allB);

        totalSoldText = new JLabel();
        add(totalSoldText);




    }
    public class ButtonHandler implements ActionListener{
        boolean cnd;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == specificDateB){
                JFrame jFrame = new JFrame();
                jFrame.setLayout(null);
                jFrame.setSize(400,250);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jFrame.setVisible(true);

                goB = new JButton("GO");
                goB.addActionListener(this);
                goB.setBounds(310,80,60,25);
                jFrame.add(goB);

                JLabel l1 = new JLabel("DAY");
                l1.setBounds(75,55,30,25);
                jFrame.add(l1);

                JLabel l2 = new JLabel("MONTH");
                l2.setBounds(155,55,50,25);
                jFrame.add(l2);

                JLabel l3 = new JLabel("YEAR");
                l3.setBounds(235,55,30,25);
                jFrame.add(l3);

                day = new JTextField();
                day.setBounds(70,80,50,30);
                jFrame.add(day);

                month = new JTextField();
                month.setBounds(150,80,50,30);
                jFrame.add(month);

                year = new JTextField();
                year.setBounds(230,80,50,30);
                jFrame.add(year);


            }

            else if(e.getSource() == todayB){
                String d,m,y;
                d = String.valueOf(cal.get(Calendar.DATE));
                m = String.valueOf(cal.get(Calendar.MONTH)+1);
                y = String.valueOf(cal.get(Calendar.YEAR));
                System.out.println(d+"--"+m+"--"+y);
                addTableColumns(d, m, y);

            }

            else if(e.getSource() == yesterdayB){
                String d,m,y;
                d = String.valueOf(cal.get(Calendar.DATE)-1);
                m = String.valueOf(cal.get(Calendar.MONTH)+1);
                y = String.valueOf(cal.get(Calendar.YEAR));

                addTableColumns(d,m,y);

                System.out.println(d+"--"+m+"--"+y);

            }
            else if(e.getSource() == currentMonthB){
                String m,y;
                m = String.valueOf(cal.get(Calendar.MONTH)+1);
                y = String.valueOf(cal.get(Calendar.YEAR));
                System.out.println("--"+m+"--"+y);

                addTableColumns(m,y);

            }
            else if(e.getSource() == lastMonthB){
                String m,y;
                m = String.valueOf(cal.get(Calendar.MONTH));
                y = String.valueOf(cal.get(Calendar.YEAR));
                System.out.println("--"+m+"--"+y);

                addTableColumns(m,y);
            }
            else if(e.getSource() == thisYearB){
                String y;
                y = String.valueOf(cal.get(Calendar.YEAR));

                addTableColumns(y);
            }
            else if(e.getSource() == lastYearB){
                String y;
                y = String.valueOf(cal.get(Calendar.YEAR)-1);

                addTableColumns(y);
            }
            else if(e.getSource() == allB){
                addTableColumns();
            }
            else if(e.getSource() == goB){
                String d=day.getText(),y=month.getText(),m=year.getText();
                int d2=-1,m2=-1,y2=-1;
                cnd = true;

                if(d.equals("") || m.equals("") || y.equals("")){
                    JOptionPane.showMessageDialog(null, "Make sure you entered all values", "Error", JOptionPane.ERROR_MESSAGE);
                    cnd = false;
                }
                try {
                    d2 = Integer.parseInt(d);
                    m2 = Integer.parseInt(y);
                    y2 = Integer.parseInt(m);
                }
                catch (Exception ex){
                    cnd =false;
                    JOptionPane.showMessageDialog(null, "Entered value must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(cnd){
                    if((d2>0 && d2<=31) && (m2>0 && m2<=12) && (y2 > 0 && y2<=cal.get(Calendar.YEAR))){
                        addTableColumns(day.getText(),month.getText(),year.getText()); //SELECT * FROM history2 WHERE `day` LIKE '16' AND `month` LIKE '11' AND `year` LIKE '2015'
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Make you you typed date correctly", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        }

        public void addTableColumns(String d,String m,String y){ //OVERLOADED Function for Specefic day,month,year
            ResultSet myRs;
            int prof=0;
            for( int i = tableModel.getRowCount() - 1; i >= 0; i-- ) {
                tableModel.removeRow(i);
            }
            myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM history2 WHERE `day` LIKE '"+d+"' AND `month` LIKE '"+m+"' AND `year` LIKE '"+y+"'");
            try{
                while (myRs.next()){
                    int prc = Integer.parseInt(myRs.getString("itemprice")) * Integer.parseInt(myRs.getString("quantitySold"));
                    prof+= prc;
                    column = new String[]{myRs.getString("itemname"),myRs.getString("quantitySold"),String.valueOf(prc)};
                    tableModel.addRow(column);
                }
                totalSoldText.setText("Total Sold : " + String.valueOf(prof));
                totalSoldText.setBounds(580, 470, totalSoldText.getPreferredSize().width, totalSoldText.getPreferredSize().height);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Exception Caught", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        public void addTableColumns(String m,String y){//OVERLOADED Function for only month and year
            ResultSet myRs;
            int prof=0;
            for( int i = tableModel.getRowCount() - 1; i >= 0; i-- ) {
                tableModel.removeRow(i);
            }
            myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM history2 WHERE `month` LIKE '"+m+"' AND `year` LIKE '"+y+"'");
            try{
                while (myRs.next()){

                    int prc = Integer.parseInt(myRs.getString("itemprice")) * Integer.parseInt(myRs.getString("quantitySold"));
                    prof+= prc;
                    column = new String[]{myRs.getString("itemname"),myRs.getString("quantitySold"),String.valueOf(prc)};
                    tableModel.addRow(column);
                }
                totalSoldText.setText("Total Sold : " + String.valueOf(prof));
                totalSoldText.setBounds(580, 470, totalSoldText.getPreferredSize().width, totalSoldText.getPreferredSize().height);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Exception Caught", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        public void addTableColumns(String y){ //OVERLOADED Function for only year
            ResultSet myRs;
            int prof=0;
            for( int i = tableModel.getRowCount() - 1; i >= 0; i-- ) {
                tableModel.removeRow(i);
            }
            myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM history2 WHERE `year` LIKE '"+y+"'");
            try{
                while (myRs.next()){
                    int prc = Integer.parseInt(myRs.getString("itemprice")) * Integer.parseInt(myRs.getString("quantitySold"));
                    prof+= prc;
                    column = new String[]{myRs.getString("itemname"),myRs.getString("quantitySold"),String.valueOf(prc)};
                    tableModel.addRow(column);
                }
                totalSoldText.setText("Total Sold : " + String.valueOf(prof));
                totalSoldText.setBounds(580, 470, totalSoldText.getPreferredSize().width, totalSoldText.getPreferredSize().height);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Exception Caught", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        public void addTableColumns(){ //OVERLOADED Function for all the items
            ResultSet myRs;
            int prof=0;
            for( int i = tableModel.getRowCount() - 1; i >= 0; i-- ) {
                tableModel.removeRow(i);
            }
            myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM history2");
            try{
                while (myRs.next()){
                    int prc = Integer.parseInt(myRs.getString("itemprice")) * Integer.parseInt(myRs.getString("quantitySold"));
                    prof+= prc;
                    column = new String[]{myRs.getString("itemname"),myRs.getString("quantitySold"),String.valueOf(prc)};
                    tableModel.addRow(column);
                }
                totalSoldText.setText("Total Sold : " + String.valueOf(prof));
                totalSoldText.setBounds(580,470,totalSoldText.getPreferredSize().width,totalSoldText.getPreferredSize().height);
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Exception Caught", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public class TableModel extends DefaultTableModel{ //A custom made DefaultTableModel Class so that Table cells are not editable
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
