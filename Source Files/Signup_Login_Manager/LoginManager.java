package Signup_Login_Manager;


import Item_Menu.SellerItemManager;
import MainProgram.SourceMain;
import Inventory_System.InventoryManager;
import Item_Menu.UserItemManager;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;


public class LoginManager extends JFrame{
    private JLabel label_user,label_password;
    private JButton button_signup,button_login;
    private JTextField tf_username;
    private JPasswordField pf_user_password;
    private LoginManager reference;
    private LoginManager lmgr = this;

    public LoginManager(){
        super("Signup/Login Menu");
        reference = this;
        setLayout(null);
        EventHandler eh = new EventHandler();
        KeyboardHandler kl = new KeyboardHandler();
        addKeyListener(kl);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String[] str = new String[]{"Yes","No"};
                int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                if(exit == 0) {   //Delete Row From Table
                    lmgr.dispose();
                }
            }
        });

        label_user = new JLabel("USERNAME : ");
        add(label_user);
        label_user.setBounds(100,60,label_user.getPreferredSize().width,label_user.getPreferredSize().height);

        label_password = new JLabel("PASSWORD : ");
        add(label_password);
        label_password.setBounds(370,60,label_password.getPreferredSize().width,label_password.getPreferredSize().height);

        tf_username = new JTextField();
        add(tf_username);
        tf_username.addKeyListener(kl);
        tf_username.setBounds(180,57,130,25);

        pf_user_password = new JPasswordField();
        pf_user_password.addKeyListener(kl);
        add(pf_user_password);
        pf_user_password.setBounds(460, 57, 130, 25);

        button_login = new JButton("LOGIN");
        button_login.addActionListener(eh);
        add(button_login);
        button_login.setBounds(488,100,100,button_login.getPreferredSize().height);

        button_signup = new JButton("SIGN UP");
        add(button_signup);
        button_signup.addActionListener(eh);
        button_signup.setBounds(488,150,100,button_signup.getPreferredSize().height);



    }
    public void clearTextFields(){ //This method called from every place where Log Out button is present so that last username nad password not shown.
        tf_username.setText("");
        pf_user_password.setText("");
    }

    public class KeyboardHandler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                System.out.println("Enter Pressed");
                button_login.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public class EventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == button_login){
                boolean nf1=true;
                boolean nf2=true;

                try {
                    ResultSet myRs1 = SourceMain.jDataBase.getQueryResult("SELECT * FROM seller"); //Here 1st time checked if the user is in seller then nf1 = false
                                                                                                    //Otherwise it wil check if the user is  normal user
                    while (myRs1.next()) {
                        if(  tf_username.getText().equals(myRs1.getString("name")) && pf_user_password.getText().equals(myRs1.getString("password"))  ){
                            JOptionPane.showMessageDialog(null,"Login Successful");

                            SellerItemManager sellerItemManager = new SellerItemManager(lmgr);
                            sellerItemManager .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                            sellerItemManager .setSize(800, 600);
                            sellerItemManager .setVisible(true);
                            lmgr.setVisible(false);

                            nf1 = false;
                            break;
                        }

                    }
                    if(nf1) { //if not a seller then it must be an admin or normal user

                        ResultSet myRs2 = SourceMain.jDataBase.getQueryResult("SELECT * FROM user");
                        while (myRs2.next()) {
                            if (tf_username.getText().equals(myRs2.getString("name")) && pf_user_password.getText().equals(myRs2.getString("password"))) {
                                JOptionPane.showMessageDialog(null, "Login Successful");
                                if (tf_username.getText().equals("admin")) {
                                    InventoryManager inventoryManager = new InventoryManager(lmgr);
                                    inventoryManager.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    inventoryManager.setSize(800, 600);
                                    inventoryManager.setVisible(true);
                                    lmgr.setVisible(false);
                                } else {
                                    UserItemManager userItemManager = new UserItemManager(lmgr);
                                    userItemManager.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    userItemManager.setSize(800, 600);
                                    userItemManager.setVisible(true);
                                    lmgr.setVisible(false);
                                }
                                nf2 = false;
                                break;
                            }

                        }
                        if (nf2 && nf1) { //If any condition not fullfills that mens username.passowrd incorrect
                            JOptionPane.showMessageDialog(null, "Username/Password Incorrect");
                        }
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"DataBase Read Error");
                }

            }
            else if(e.getSource() == button_signup){ //If signup button clicked a new signup window will appear
                SignupManager signupManager = new SignupManager();
                signupManager.setUserIdentifier("user"); //Trick used to consider weather Signup will be for user or seller.
                //parameter is a userIdentifier variable which is set by calling a public function in setUserIdentifier in SignupManager
                //and the userIdentifier used in INSERT query.
                signupManager.setSize(800,600);
                signupManager.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                signupManager.setVisible(true);
                signupManager.setLoginManagerReference(reference);
                reference.setVisible(false);

            }

        }
    }
}
