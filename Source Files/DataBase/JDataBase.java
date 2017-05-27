package DataBase;

import javax.swing.*;
import java.sql.*;


public class JDataBase {
    Connection myConn;
    Statement myStatement;
    ResultSet myRs;
    boolean isConnected=false;

    public JDataBase(){
        //"jdbc:mysql://localhost/itemlist","root",""
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost/itemlist","root","");
            myStatement = myConn.createStatement();
            isConnected = true;
        }
        catch (Exception e){
            isConnected = false;
            JOptionPane.showMessageDialog(null, "Please make sure Database Server properly running", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JDataBase(String conn,String dbName,String password){
        //"jdbc:mysql://localhost/itemlist","root",""
        try {
            myConn = DriverManager.getConnection(conn,dbName,password);
            myStatement = myConn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getQueryResult(String query){
        try {
            myRs = myStatement.executeQuery(query);
            //Output Test
            /*while (myRs.next()){
                System.out.println(myRs.getString("id")+"--"+myRs.getString("itemname")+"--"+myRs.getString("quantity"));
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return myRs;
    }
     public void insertRow(String query){

         try {
             myStatement.executeUpdate(query);
         }
         catch (Exception e){
             e.printStackTrace();
         }
     }
    public void deleteRow(String query){
        try {
            myStatement.executeUpdate(query);
            //Output Test
            /*while (myRs.next()){
                System.out.println(myRs.getString("id")+"--"+myRs.getString("itemname")+"--"+myRs.getString("quantity"));
            }*/
            JOptionPane.showMessageDialog(null, "Remove Successful", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Unable to Remove Row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean isDataBaseConnected(){
        return isConnected;
    }

}
