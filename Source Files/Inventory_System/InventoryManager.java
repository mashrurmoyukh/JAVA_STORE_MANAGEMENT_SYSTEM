package Inventory_System;

import MainProgram.SourceMain;
import Signup_Login_Manager.LoginManager;
import Signup_Login_Manager.SignupManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class InventoryManager extends JFrame{
    JTable itemList;
    TableModel tableModel; //Custom Table model with no cell editing option
    JScrollPane jScrollPane;
    String[] column;
    String[] row,row2;

    JButton addB,removeB,refreshB,logoutB,addSellerB,historyB;
    InventoryManager im = this;
    LoginManager lmg;
    InventoryManager inventoryManager= this;


    public InventoryManager(LoginManager lmgr){
        super("Inventory Manager");
        setLayout(null);
        ButtonHandler buttonHandler = new ButtonHandler();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String[] str = new String[]{"Yes","No"};
                int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(exit == 0) {   //Delete Row From Table
                    inventoryManager.dispose();
                    lmg.clearTextFields();
                    lmg.setVisible(true);
                }
            }
        });

        this.lmg = lmgr;

        column = new String[]{"Id","Item Name","Quantity","Price","Category"};

        itemList = new JTable();
        tableModel = new TableModel();
        tableModel.setColumnIdentifiers(column);
        itemList.setModel(tableModel);

        jScrollPane = new JScrollPane(itemList);
        jScrollPane.setBounds(50, 50, jScrollPane.getPreferredSize().width, 450);

        add(jScrollPane);

        showItems(); //Loads item into Table from Database


        addB = new JButton("Add Item");
        addB.addActionListener(buttonHandler);
        addB.setBounds(600, 50, 120, 25);
        add(addB);

        removeB = new JButton("Remove");
        removeB.addActionListener(buttonHandler);
        removeB.setBounds(600, 100, 120, 25);
        add(removeB);

        refreshB = new JButton("Refresh");
        refreshB.addActionListener(buttonHandler);
        refreshB.setBounds(600,150,120,25);
        add(refreshB);

        addSellerB = new JButton("Add Seller");
        addSellerB.addActionListener(buttonHandler);
        addSellerB.setBounds(600,200,120,25);
        add(addSellerB);

        historyB = new JButton("Show Report");
        historyB.addActionListener(buttonHandler);
        historyB.setBounds(600,250,120,25);
        add(historyB);


        logoutB = new JButton("Log Out");
        logoutB.addActionListener(buttonHandler);
        logoutB.setBounds(600,300,120,25);
        add(logoutB);




    }
    public class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == addB){    //Add Table Column

                AddItem addItem = new AddItem(tableModel,im);
                addItem.setSize(400,400);
                addItem.setVisible(true);


            }
            else if(e.getSource() == removeB){
                String[] str = new String[]{"Yes","No"};
                int delete = JOptionPane.showOptionDialog(null, "Are you sure you want to delete", "Delete Item", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(delete == 0) {   //Delete Row From Table
                    if(itemList.getSelectedRow() >= 0) {
                        String i = String.valueOf(tableModel.getValueAt(itemList.getSelectedRow(), 0));
                        SourceMain.jDataBase.deleteRow("DELETE FROM `itemlist2` WHERE `id`='"+i+"'");
                        showItems();

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please Select a ROW", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            else if(e.getSource() == refreshB){
                showItems();
            }
            else if(e.getSource() == logoutB){
                String[] str = new String[]{"Yes","No"};
                int logout = JOptionPane.showOptionDialog(null, "Are you sure you want to Log Out", "Logout", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(logout == 0) {   //Delete Row From Table
                    inventoryManager.dispose();
                    lmg.clearTextFields();
                    lmg.setVisible(true);
                }

            }
            else if(e.getSource() == addSellerB){
                SignupManager signupManager = new SignupManager();
                signupManager.setUserIdentifier("seller");
                signupManager.hideLoginButton();
                signupManager.setSize(800,600);
                signupManager.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                signupManager.setVisible(true);
                signupManager.setLoginManagerReference(lmg);
            }
            else if(e.getSource() == historyB){
                ShowHistory showHistory = new ShowHistory();
                showHistory.setSize(800,600);
                showHistory.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                showHistory.setVisible(true);
            }
        }
    }
    public void showItems(){
        try{
            ResultSet myRs= SourceMain.jDataBase.getQueryResult("SELECT * FROM itemlist2");
            tableModel.setNumRows(0);
            while (myRs.next()){
                row = new String[5];
                row[0] = myRs.getString("id");
                row[1] = myRs.getString("itemname");
                row[2] = myRs.getString("quantity");
                row[3] = myRs.getString("price");
                row[4] = myRs.getString("category");
                tableModel.addRow(row);
                System.out.println(myRs.getString("category"));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database Read ERROR in IM", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public class TableModel extends DefaultTableModel{ //A cutom made DefaultTableModel Class so that Table cells are not editable
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
