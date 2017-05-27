package Signup_Login_Manager;

import MainProgram.SourceMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;


public class SignupManager extends JFrame{
    private JLabel label_user,label_password,label_repeatPassword,label_email;
    private JButton button_signup,button_login;
    private JTextField tf_username,tf_email;
    private JPasswordField tf_password,tf_repeatPassword;
    private LoginManager lmg;
    private SignupManager smg;
    private EventHandler eh;
    private String userIdentifier="user";

    public SignupManager(){
        super("Signup/Login Menu");
        setLayout(null);
        smg = this;
        eh = new EventHandler();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(userIdentifier.equals("user")) {
                    String[] str = new String[]{"Yes", "No"};
                    int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                    if (exit == 0) {   //Delete Row From Table
                        smg.dispose();
                        lmg.clearTextFields();
                        lmg.setVisible(true);
                    }
                }
                else {
                    String[] str = new String[]{"Yes", "No"};
                    int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, null);

                    if (exit == 0) {   //Delete Row From Table
                        smg.dispose();
                    }
                }
            }
        });

        label_user = new JLabel("USERNAME : ");
        add(label_user);
        label_user.setBounds(100,60,label_user.getPreferredSize().width,label_user.getPreferredSize().height);

        label_password = new JLabel("PASSWORD : ");
        add(label_password);
        label_password.setBounds(100,120,label_password.getPreferredSize().width,label_password.getPreferredSize().height);

        label_repeatPassword = new JLabel("Repeat PASSWORD : ");
        add(label_repeatPassword);
        label_repeatPassword.setBounds(100,180,label_repeatPassword.getPreferredSize().width,label_repeatPassword.getPreferredSize().height);

        label_email = new JLabel("Email : ");
        add(label_email);
        label_email.setBounds(100,240,label_email.getPreferredSize().width,label_email.getPreferredSize().height);

        tf_username = new JTextField();
        add(tf_username);
        tf_username.setBounds(240,58,130,25);

        tf_password = new JPasswordField();
        add(tf_password);
        tf_password.setBounds(240,118,130,25);

        tf_repeatPassword = new JPasswordField();
        add(tf_repeatPassword);
        tf_repeatPassword.setBounds(240,178,130,25);

        tf_email = new JTextField();
        add(tf_email);
        tf_email.setBounds(240,238,130,25);

        button_login = new JButton("LOGIN");
        add(button_login);
        button_login.addActionListener(eh);
        button_login.setBounds(270,350,100,button_login.getPreferredSize().height);

        button_signup = new JButton("SIGN UP");
        add(button_signup);
        button_signup.addActionListener(eh);
        button_signup.setBounds(270,290,100,button_signup.getPreferredSize().height);

    }
    public class EventHandler implements ActionListener{
        boolean cnd = true; //For checking weather username already exists in seller or user table in Database

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == button_signup){

                ResultSet myRs = SourceMain.jDataBase.getQueryResult("SELECT * FROM user"); //Checking weather username already exists in seller table in Database
                try {
                    while (myRs.next()){
                        if(tf_username.getText().equals(myRs.getString("name"))){
                            JOptionPane.showMessageDialog(null,"Username already Exists","",JOptionPane.ERROR_MESSAGE);
                            cnd = false;

                        }
                    }

                }
                catch (Exception ex){}

                ResultSet myRs2 = SourceMain.jDataBase.getQueryResult("SELECT * FROM seller"); //For checking weather username already exists in user table in Database
                try {
                    while (myRs2.next()){
                        if(tf_username.getText().equals(myRs2.getString("name"))){
                            JOptionPane.showMessageDialog(null,"Username already Exists","",JOptionPane.ERROR_MESSAGE);
                            cnd = false;

                        }
                    }

                }
                catch (Exception ex){}


                if(tf_username.getText().equals("")|| tf_password.getText().equals("") || tf_repeatPassword.getText().equals("") || tf_email.getText().equals("")){ //For checking weather any field is left empty
                    JOptionPane.showMessageDialog(null,"Please Make Sure you entered all Information : ","",JOptionPane.QUESTION_MESSAGE);
                }
                else {
                    if(!tf_password.getText().equals(tf_repeatPassword.getText())){ //For checking repeated password matched
                        JOptionPane.showMessageDialog(null,"Repeated Password Didn't match : ","",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(cnd){  //Finally put data in Database
                        SourceMain.jDataBase.insertRow("INSERT INTO `"+userIdentifier+"`(`id`,`name`,`password`,`email`) VALUES ( NULL,'"+tf_username.getText()+"','"+tf_password.getText()+"','"+tf_email.getText()+"');");
                        JOptionPane.showMessageDialog(null,"Signup Successful : ","",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else if(e.getSource() == button_login){
                System.out.println("Login");
                smg.dispose();
                lmg.setVisible(true);
            }
        }
    }

    public void setLoginManagerReference(LoginManager lmg){
        this.lmg = lmg;
    }

    public void setUserIdentifier(String userIdentifier){//Trick used to consider weather Signup will be for user or seller.
        //parameter is a userIdentifier variable which is set by calling a public function in setUserIdentifier in SignupManager
        //and the userIdentifier used in INSERT query.
        this.userIdentifier= userIdentifier;
    }

    public void hideLoginButton(){
        button_login.setVisible(false);
    }


}
