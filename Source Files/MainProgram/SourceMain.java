package MainProgram;


import DataBase.JDataBase;
import Inventory_System.InventoryManager;
import Item_Menu.SellerItemManager;
import Item_Menu.UserItemManager;
import Signup_Login_Manager.LoginManager;
import javax.swing.*;
import java.sql.ResultSet;


public class SourceMain {
    public static JDataBase jDataBase;


    public static void main(String[] arg){
        LoginManager loginManager = new LoginManager();
        jDataBase = new JDataBase();

        if(jDataBase.isDataBaseConnected()){
            loginManager.setSize(800,600);
            loginManager.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            loginManager.setVisible(true);
        }

        /*SellerItemManager sellerItemManager = new SellerItemManager(loginManager);
        sellerItemManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sellerItemManager.setSize(800,600);
        sellerItemManager.setVisible(true);*/

        /*InventoryManager inventoryManager = new InventoryManager(loginManager);
        inventoryManager.setSize(800,600);
        inventoryManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryManager.setVisible(true);

        /*UserItemManager inventoryManager = new UserItemManager();
        inventoryManager.setSize(800,600);
        inventoryManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventoryManager.setVisible(true);*/



    }

}
