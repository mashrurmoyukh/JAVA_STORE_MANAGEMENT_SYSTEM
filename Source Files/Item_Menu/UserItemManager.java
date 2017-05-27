package Item_Menu;

import MainProgram.SourceMain;
import Signup_Login_Manager.LoginManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.sql.ResultSet;


public class UserItemManager extends JFrame{
    JComboBox catagory;
    JButton searchB,logoutB;
    JList itemList;
    JScrollPane jScrollPane;
    JTextField search;
    JLabel price,itemAvailable;
    String[] cat;
    int[] currentItemId;
    LoginManager lmg;
    UserItemManager userItemManager = this;


    public UserItemManager(LoginManager lmgr){
        super("Item List");
        setLayout(null);
        this.lmg = lmgr;

        ItemHandler itemHandler = new ItemHandler();
        ListHandler listHandler = new ListHandler();

        search = new JTextField();
        search.setBounds(200,15,100,25);
        add(search);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String[] str = new String[]{"Yes","No"};
                int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(exit == 0) {   //Delete Row From Table
                    userItemManager.dispose();
                    lmg.clearTextFields();
                    lmg.setVisible(true);
                }
            }
        });

        try {
            int ind=0;
            ResultSet myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM categorie");
            while (myRs.next()){
                ind++;
            }
            myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM categorie");
            cat = new String[ind];
            ind=0;
            while (myRs.next()){
                cat[ind++] = myRs.getString("categories");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Database - Category Error");
        }

        ButtonHandler buttonHandler = new ButtonHandler();

        catagory = new JComboBox(cat);
        catagory.setBounds(20,50,100,catagory.getPreferredSize().height);
        catagory.addItemListener(itemHandler);
        add(catagory);

        itemList = new JList();
        itemList.addListSelectionListener(listHandler);

        jScrollPane = new JScrollPane(itemList);
        jScrollPane.setBounds(200,50,200,400);
        add(jScrollPane);

        price = new JLabel("Price : ");
        price.setBounds(470,50,price.getPreferredSize().width,price.getPreferredSize().height);
        add(price);

        itemAvailable = new JLabel("Available : ");
        itemAvailable.setBounds(470,70,itemAvailable.getPreferredSize().width,itemAvailable.getPreferredSize().height);
        add(itemAvailable);

        searchB = new JButton("SEARCH");
        searchB.setBounds(310,15,120,25);
        searchB.addActionListener(buttonHandler);
        add(searchB);

        logoutB = new JButton("Log Out");
        logoutB.addActionListener(buttonHandler);
        logoutB.setBounds(600,500,120,25);
        add(logoutB);



    }

    public class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == searchB) {
                String str = search.getText();
                ListModel listModel = itemList.getModel();
                for (int i = 0; i < listModel.getSize(); i++) {
                    if (listModel.getElementAt(i).equals(str)) {

                        String[] serItem;
                        System.out.println(itemList.getSelectedValue());
                        ResultSet myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM `itemlist2` WHERE itemname REGEXP '" + str + "'");

                        try {
                            myRs.next();
                            price.setText("Price : " + myRs.getString("price"));
                            itemAvailable.setText("Available : " + myRs.getString("quantity"));
                            serItem = new String[]{myRs.getString("itemname")};
                            //description.setText("Really Dude");

                            price.setBounds(470, 50, price.getPreferredSize().width, price.getPreferredSize().height);
                            itemAvailable.setBounds(470, 80, itemAvailable.getPreferredSize().width, itemAvailable.getPreferredSize().height);
                            //description.setBounds(470,120, description.getPreferredSize().width, description.getPreferredSize().height);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Database - Show Item Description Error");
                            serItem = new String[]{"No Result"};

                        }
                        itemList.setListData(serItem); //JList.setListData() takes an array of Strings as parameter and shows data serially from the array in the table. SOURCE : JAVA Tutorial by Bucky


                    }
                }
            }
            else if(e.getSource() == logoutB){
                String[] str = new String[]{"Yes","No"};
                int logout = JOptionPane.showOptionDialog(null, "Are you sure you want to Log Out", "Logout", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(logout == 0) {   //Delete Row From Table
                    userItemManager.dispose();
                    lmg.clearTextFields();
                    lmg.setVisible(true);
                }
            }

        }
    }

    public class ItemHandler implements ItemListener{  //ItemLister for JComboBox, variable named category.

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == catagory){

                if(e.getStateChange() == ItemEvent.SELECTED) { //It means that is there any item in list is selected. SOURCE : JAVA Tutorial by Bucky
                    String[] items=new String[0];
                    String cat = e.getItem().toString();
                    ResultSet myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM itemlist2 WHERE category LIKE '%"+cat+"%'");
                    int rownum=0;

                    try {
                        while (myRs.next()){
                            rownum++;
                        }
                        myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM itemlist2 WHERE category LIKE '%"+cat+"%'");
                        items = new String[rownum];
                        currentItemId = new int[rownum];
                        for(int i=0 ; myRs.next() ; i++){
                            items[i] = myRs.getString("itemname");

                        }

                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Database - Fetch Item Error");
                    }
                    itemList.setListData(items); //JList.setListData() takes an array of Strings as parameter and shows data serially from the array in the table. SOURCE : JAVA Tutorial by Bucky
                }

            }
        }
    }

    public class ListHandler implements ListSelectionListener{ //ListSelectionListener for JList, variable named itemlist.

        @Override
        public void valueChanged(ListSelectionEvent e) {  //Item Description from Database Work goes here
            if (itemList.getSelectedValue() != null) {
                System.out.println(itemList.getSelectedValue());
                ResultSet myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM `itemlist2` WHERE itemname LIKE '%" + itemList.getSelectedValue() + "%'");
                try {
                    myRs.next();
                    price.setText("Price : " + myRs.getString("price"));
                    itemAvailable.setText("Available : " + myRs.getString("quantity"));
                    price.setBounds(470, 50, price.getPreferredSize().width, price.getPreferredSize().height);
                    itemAvailable.setBounds(470, 80, itemAvailable.getPreferredSize().width, itemAvailable.getPreferredSize().height);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Database - Show Item Description Error");
                }
            }




        }
    }
}

