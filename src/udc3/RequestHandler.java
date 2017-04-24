/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author nathanielclayarnold
 */
public class RequestHandler {
    private String userRecordLocation;
    private String userNameToIdLocation;
    private Factory factory;
    private FileParser parser;
    private Mediator factoryParserMediator;
    private User loggedInUser;
    
    public void Start(){
        GUI gui = new GUI();
    }
    
    public RequestHandler(String userRecordLocation, String userNameToIdLocation) {
        this.userRecordLocation = userRecordLocation;
        this.userNameToIdLocation = userNameToIdLocation;
        factory = new Factory();
        parser = new FileParser();
        factoryParserMediator = new Mediator();
        loggedInUser = null;
    }

    public String getUserNameToIdLocation() {
        return userNameToIdLocation;
    }

    public void setUserNameToIdLocation(String userNameToIdLocation) {
        this.userNameToIdLocation = userNameToIdLocation;
    }

    public String getUserRecordLocation() {
        return userRecordLocation;
    }

    public void setUserRecordLocation(String userRecordLocation) {
        this.userRecordLocation = userRecordLocation;
    }
    
    public HashMap getUsers(){
        return factoryParserMediator.getHashMapOfUsers();
    }
    
    public User getUser(String id){
        return factoryParserMediator.getUser(id);
    }
    
    public boolean storeUser(String[] info){
        return parser.storeUser(info);
    }
    
    public boolean removeUser (String id){
        return parser.deleteUser(id);
    }
    
    public boolean validateInfo(String[] info){
        return false;
    }
    
    public String userIDFromUsername(String un){
        return parser.userNameToId(un);
    }
    
    public boolean validateLogin(String username, String pass){
        User user = factoryParserMediator.getUser(username);
        if(user != null && user.pswrd.equals(hashPass(pass)))
            return true;
        else
            return false;
    }
    
    private String hashPass(String pass){
        StringBuilder toReturn = new StringBuilder(pass);
        for(int i=0; i<pass.length(); i++){
            toReturn.setCharAt(i, (char)(pass.charAt(i)+1));
        }
        return toReturn.toString();
    }
    
    public void login(User user){
        loggedInUser = user;
    }
    
    public User getLoggedUser(){
        return loggedInUser;
    }
    
    public void logout(){
        loggedInUser=null;
    }
    
 private class Factory{
        public User build(String[] info){
            //"u" for User
            if(info == null){
                return null;
            }
            else if(info[0].equals("u")){
                return new User(info[1], info[2], info[3], 
                info[4], info[5], info[6], info[7].equals("t"));
            }
            //"pu" for PremiumUser
            else if(info[0].equals("pu")){
                return new PremiumUser(info[1], info[2], info[3], 
                info[4], info[5], info[6], info[7].equals("t"),
                info[8], info[9], info[10], info[11]);
            }
            //"pr" for PoliticalRepresentative
            else if(info[0].equals("pr")){
                return new Rep(info[1], info[2], info[3], 
                info[4], info[5], info[6], info[7].equals("t"),
                info[8], info[9], info[10], info[11]);
            }
            else{
                return null;
            }
        }
    }
    
    private class Mediator{
        private HashMap getHashMapOfUsers(){
            HashMap<String, User> toReturn = new HashMap<>();
            List<String[]> users = parser.getUsers();
            
            for(int i =0; i<users.size(); i++)
                toReturn.put(users.get(i)[0], factory.build(users.get(i)));
            
            return toReturn;
        }
        private User getUser(String name){
            String id = parser.userNameToId(name);
            return factory.build(parser.getUser(id));
        }
    }
    
    private class FileParser{
        private boolean doesUserExist(String id){
            File file = new File("testUser.txt");
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNext()){
                    if(scanner.nextLine().contains(id))
                        scanner.close();
                        return true;
                }
                scanner.close();
                return false;
            } catch (Exception ex) {
                return false;
            }
        }
        private String[] getUser(String id){
            File file = new File("testUser.txt");
            Scanner scanner=null;
            String[] toReturn=null;
            String nextLine;
            try {
                scanner = new Scanner(file);
                while(scanner.hasNext()){
                    nextLine = scanner.nextLine();
                    if(nextLine.contains("id") && getInfoAfterColon(nextLine).equals(id)){
                        nextLine = scanner.nextLine();
                        if(getInfoAfterColon(nextLine).equals("u")){
                            toReturn = new String[8];
                            toReturn[0] = "u";
                            toReturn[1] = getInfoAfterColon(scanner.nextLine());
                            toReturn[2] = getInfoAfterColon(scanner.nextLine());
                            toReturn[3] = getInfoAfterColon(scanner.nextLine());
                            toReturn[4] = getInfoAfterColon(scanner.nextLine());
                            toReturn[5] = id;
                            toReturn[6] = getInfoAfterColon(scanner.nextLine());
                            toReturn[7] = getInfoAfterColon(scanner.nextLine());
                            break;
                        }
                        else if(getInfoAfterColon(nextLine).equals("pu")){
                            toReturn = new String[12];
                            toReturn[0]="pu";
                            toReturn[1] = getInfoAfterColon(scanner.nextLine());
                            toReturn[2] = getInfoAfterColon(scanner.nextLine());
                            toReturn[3] = getInfoAfterColon(scanner.nextLine());
                            toReturn[4] = getInfoAfterColon(scanner.nextLine());
                            toReturn[5] = id;
                            toReturn[6] = getInfoAfterColon(scanner.nextLine());
                            toReturn[7] = getInfoAfterColon(scanner.nextLine());
                            toReturn[8] = getInfoAfterColon(scanner.nextLine());
                            toReturn[9] = getInfoAfterColon(scanner.nextLine());
                            toReturn[10] = getInfoAfterColon(scanner.nextLine());
                            toReturn[11] = getInfoAfterColon(scanner.nextLine());
                            break;
                        }
                        else if(getInfoAfterColon(nextLine).equals("pr")){
                            toReturn = new String[12];
                            toReturn[0]="pr";
                            toReturn[1] = getInfoAfterColon(scanner.nextLine());
                            toReturn[2] = getInfoAfterColon(scanner.nextLine());
                            toReturn[3] = getInfoAfterColon(scanner.nextLine());
                            toReturn[4] = getInfoAfterColon(scanner.nextLine());
                            toReturn[5] = id;
                            toReturn[6] = getInfoAfterColon(scanner.nextLine());
                            toReturn[7] = getInfoAfterColon(scanner.nextLine());
                            toReturn[8] = getInfoAfterColon(scanner.nextLine());
                            toReturn[9] = getInfoAfterColon(scanner.nextLine());
                            toReturn[10] = getInfoAfterColon(scanner.nextLine());
                            toReturn[11] = getInfoAfterColon(scanner.nextLine());
                            break;
                        }
                        else{}
                    }
                }
                scanner.close();
                return toReturn;
            } 
            catch(Exception ex){
                if(scanner != null)
                    scanner.close();
                return null;
            }
        }
        private List getUsers(){
            File file = new File(userRecordLocation);
            Scanner scanner=null;
            List<String[]> toReturn= new LinkedList<>();
            String nextLine;
            String[] temp;
            String id;
            
            try {
                scanner = new Scanner(file);
                while(scanner.hasNext()){
                    nextLine = scanner.nextLine();
                    if(nextLine.contains("id")){
                        id = getInfoAfterColon(nextLine);
                        nextLine = scanner.nextLine();
                        if(getInfoAfterColon(nextLine).equals("u")){
                            temp = new String[8];
                            temp[0] = "u";
                            temp[1] = getInfoAfterColon(scanner.nextLine());
                            temp[2] = getInfoAfterColon(scanner.nextLine());
                            temp[3] = getInfoAfterColon(scanner.nextLine());
                            temp[4] = getInfoAfterColon(scanner.nextLine());
                            temp[5] = id;
                            temp[6] = getInfoAfterColon(scanner.nextLine());
                            temp[7] = getInfoAfterColon(scanner.nextLine());
                            toReturn.add(Arrays.copyOf(temp, temp.length));
                        }
                        else if(getInfoAfterColon(nextLine).equals("pu")){
                            temp = new String[12];
                            temp[0]="pu";
                            temp[1] = getInfoAfterColon(scanner.nextLine());
                            temp[2] = getInfoAfterColon(scanner.nextLine());
                            temp[3] = getInfoAfterColon(scanner.nextLine());
                            temp[4] = getInfoAfterColon(scanner.nextLine());
                            temp[5] = id;
                            temp[6] = getInfoAfterColon(scanner.nextLine());
                            temp[7] = getInfoAfterColon(scanner.nextLine());
                            temp[8] = getInfoAfterColon(scanner.nextLine());
                            temp[9] = getInfoAfterColon(scanner.nextLine());
                            temp[10] = getInfoAfterColon(scanner.nextLine());
                            temp[11] = getInfoAfterColon(scanner.nextLine());
                            toReturn.add(Arrays.copyOf(temp, temp.length));
                        }
                        else if(getInfoAfterColon(nextLine).equals("pr")){
                            temp = new String[12];
                            temp[0]="pu";
                            temp[1] = getInfoAfterColon(scanner.nextLine());
                            temp[2] = getInfoAfterColon(scanner.nextLine());
                            temp[3] = getInfoAfterColon(scanner.nextLine());
                            temp[4] = getInfoAfterColon(scanner.nextLine());
                            temp[5] = id;
                            temp[6] = getInfoAfterColon(scanner.nextLine());
                            temp[7] = getInfoAfterColon(scanner.nextLine());
                            temp[8] = getInfoAfterColon(scanner.nextLine());
                            temp[9] = getInfoAfterColon(scanner.nextLine());
                            temp[10] = getInfoAfterColon(scanner.nextLine());
                            temp[11] = getInfoAfterColon(scanner.nextLine());
                            toReturn.add(Arrays.copyOf(temp, temp.length));
                        }
                        else{}
                    }
                }
                scanner.close();
                return toReturn;
            } 
            catch(Exception ex){
                if(scanner != null)
                    scanner.close();
                return null;
            }
        }
        private boolean storeUser(String[] info){
            File file = new File(userRecordLocation);
            File unToid = new File(userNameToIdLocation);
            try {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                if(info[0].equals("u")){
                    bw.write("id:"+info[5]);
                    bw.newLine();
                    bw.write("type:u");
                    bw.newLine();
                    bw.write("fn:"+info[1]);
                    bw.newLine();
                    bw.write("ln:"+info[2]);
                    bw.newLine();
                    bw.write("em:"+info[3]);
                    bw.newLine();
                    bw.write("un:"+info[4]);
                    bw.newLine();
                    bw.write("pw:"+info[6]);
                    bw.newLine();
                    bw.write("ia:"+info[7]);
                    bw.newLine();
                    
                    fw = new FileWriter(unToid, true);
                    bw = new BufferedWriter(fw);
                    bw.write(info[4]+":"+info[5]);
                    bw.newLine();
                    
                    bw.close();
                    fw.close();
                    return true;
                }
                else if(info[0].equals("pu")){
                    bw.write("id:"+info[5]);
                    bw.newLine();
                    bw.write("type:pu");
                    bw.newLine();
                    bw.write("fn:"+info[1]);
                    bw.newLine();
                    bw.write("ln:"+info[2]);
                    bw.newLine();
                    bw.write("em:"+info[3]);
                    bw.newLine();
                    bw.write("un:"+info[4]);
                    bw.newLine();
                    bw.write("pw:"+info[6]);
                    bw.newLine();
                    bw.write("ia:"+info[7]);
                    bw.newLine();
                    bw.write("pl:"+info[8]);
                    bw.newLine();
                    bw.write("dob:"+info[9]);
                    bw.newLine();
                    bw.write("ct:"+info[10]);
                    bw.newLine();
                    bw.write("cty:"+info[11]);
                    bw.newLine();
                    
                    fw = new FileWriter(unToid, true);
                    bw = new BufferedWriter(fw);
                    bw.write(info[4]+":"+info[5]);
                    bw.newLine();
                    
                    bw.close();
                    fw.close();
                    return true;
                }
                else if(info[0].equals("pr")){
                    bw.write("id:"+info[5]);
                    bw.newLine();
                    bw.write("type:pr");
                    bw.newLine();
                    bw.write("fn:"+info[1]);
                    bw.newLine();
                    bw.write("ln:"+info[2]);
                    bw.newLine();
                    bw.write("em:"+info[3]);
                    bw.newLine();
                    bw.write("un:"+info[4]);
                    bw.newLine();
                    bw.write("pw:"+info[6]);
                    bw.newLine();
                    bw.write("ia:"+info[7]);
                    bw.newLine();
                    bw.write("pl:"+info[8]);
                    bw.newLine();
                    bw.write("st:"+info[9]);
                    bw.newLine();
                    bw.write("url:"+info[10]);
                    bw.newLine();
                    bw.write("pn:"+info[11]);
                    bw.newLine();
                    
                    fw = new FileWriter(unToid, true);
                    bw = new BufferedWriter(fw);
                    bw.write(info[4]+":"+info[5]);
                    bw.newLine();
                    
                    bw.close();
                    fw.close();
                    return true;
                }
                else{
                    return false;
                }
            } catch (IOException ex) {
                return false;
            }
        }
        private boolean deleteUser(String id){
            if(doesUserExist(id)){
                File file = new File(userRecordLocation);
                File temp = new File("temp.txt");
                String nextLine;
                boolean deleted = false;
                try {
                    Scanner scanner = new Scanner(file);
                    FileWriter fw = new FileWriter(temp);
                    BufferedWriter bw = new BufferedWriter(fw);
                    while(scanner.hasNext()){
                        nextLine = scanner.nextLine();
                        if(nextLine.contains(id)){
                            switch(getInfoAfterColon(scanner.nextLine())){
                                case "u":
                                    for(int i=0; i<6; i++)
                                        scanner.nextLine();
                                    deleted = true;
                                    break;
                                default:
                                    for(int i=0; i<10; i++)
                                        scanner.nextLine();
                                    deleted = true;
                                    break;
                            }
                        }
                        else{
                            bw.write(nextLine);
                            bw.newLine();
                        }
                    }
                    bw.close();
                    fw.close();
                    scanner.close();
                    if(deleted){
                        file.delete();
                        temp.renameTo(file);
                        return true;
                    }
                    else{
                        temp.delete();
                        return false;
                    }
                } 
                catch(Exception ex){
                    return false;
                }
            }
            return false;
        }
        private String userNameToId(String username){
            File file = new File(userNameToIdLocation);
            try {
                Scanner scanner = new Scanner(file);
                String nextLine;
                while(scanner.hasNext()){
                    nextLine = scanner.nextLine();
                    if(nextLine.contains(username))
                        return getInfoAfterColon(nextLine);
                }
                return null;
            } catch (FileNotFoundException ex) {
                return null;
            }
        }
        private String getInfoAfterColon(String toParse){
            return toParse.substring(toParse.indexOf(':')+1, toParse.length());
        }
    }
    
    private class GUI extends JFrame implements ActionListener{
    private JFrame mainFrame, loginFrame, accountCreationFrame;
    private JTextField search, userName, acUserName, acFirstName, acLastName,
            acEmail;
    private JPasswordField password, acPassword, acRePassword;
    private JButton searchButton, signIn, createAccountMenu, logInButton, accountPage, logout, createAccount;
    private JPanel searchPanel, accountPanel, picPanel;
    private JLabel loginLabel, unLabel, pwLabel, pic, loggedInAs, acUserNameLabel, 
            acFirstNameLabel, acLastNameLabel, acEmailLabel, acPasswordLabel, acRePasswordLabel;
    
    public GUI(){
        mainFrame = new JFrame();
        mainFrame.setLayout(new FlowLayout());
        
        accountPanel = new JPanel();
        accountPanel.setLayout(new FlowLayout());
        
        signIn = new JButton("Log in");
        signIn.addActionListener(this);
        
        createAccountMenu = new JButton("Create Account");
        createAccountMenu.addActionListener(this);
        
        accountPage = new JButton("Account Page");
        accountPage.addActionListener(this);
        
        logout = new JButton("Logout");
        logout.addActionListener(this);
       
        accountPanel.add(signIn);
        accountPanel.add(createAccountMenu);
        
        pic = new JLabel(new ImageIcon("citizen.png"));
        picPanel = new JPanel();
        picPanel.add(pic);
        
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        
        search = new JTextField("Search...", 30);
        search.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                search.setText("");
            }
        });
        searchButton = new JButton("Find");
        
        searchPanel.add(search);
        searchPanel.add(searchButton);
        
        
        mainFrame.add(accountPanel);
        mainFrame.add(picPanel);
        mainFrame.add(searchPanel);
        
        mainFrame.setTitle("Citizen");
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    private void buildLogIn(){
        loginFrame = new JFrame();
        loginFrame.setLayout(new FlowLayout());
        loginLabel = new JLabel("Invalid Credentials");
        
        unLabel = new JLabel("Username: ");
        userName = new JTextField(20);
        
        pwLabel = new JLabel("Password: ");
        password = new JPasswordField(20);

        logInButton = new JButton("Log In");
        logInButton.addActionListener(this);
        
        loginFrame.add(unLabel);
        loginFrame.add(userName);
        loginFrame.add(pwLabel);
        loginFrame.add(password);
        loginFrame.add(logInButton);
        loginFrame.setTitle("Log In");
        loginFrame.setSize(250, 250);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void buildCreateAccount(){
        accountCreationFrame = new JFrame();
        accountCreationFrame.setLayout(new FlowLayout());
        
        acUserNameLabel = new JLabel("User Name: ");
        acUserName = new JTextField(20);
        
        acPasswordLabel = new JLabel("Password: ");
        acPassword = new JPasswordField(20);
        
        acRePasswordLabel = new JLabel("Retype Password: ");
        acRePassword = new JPasswordField(20);
        
        acFirstNameLabel = new JLabel("First Name: ");
        acFirstName = new JTextField(20);
        
        acLastNameLabel = new JLabel("Last Name: ");
        acLastName = new JTextField(20);
        
        acEmailLabel = new JLabel("Email: ");
        acEmail = new JTextField(20);
        
        createAccount = new JButton("Create Account");
        createAccount.addActionListener(this);
        
        accountCreationFrame.add(acUserNameLabel);
        accountCreationFrame.add(acUserName);
        accountCreationFrame.add(acPasswordLabel);
        accountCreationFrame.add(acPassword);
        accountCreationFrame.add(acRePasswordLabel);
        accountCreationFrame.add(acRePassword);
        accountCreationFrame.add(acFirstNameLabel);
        accountCreationFrame.add(acFirstName);
        accountCreationFrame.add(acLastNameLabel);
        accountCreationFrame.add(acLastName);
        accountCreationFrame.add(acEmailLabel);
        accountCreationFrame.add(acEmail);
        accountCreationFrame.add(createAccount);
        
        accountCreationFrame.setTitle("Create Account");
        accountCreationFrame.setSize(250, 400);
        accountCreationFrame.setVisible(true);
        accountCreationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object src = ae.getSource();
            
            if(src == signIn){
                buildLogIn();
            }
            else if(src == createAccountMenu){
                buildCreateAccount();
            }
            else if(src == logInButton){
                if(validateLogin(userName.getText(),String.valueOf(password.getPassword()))){
                    login(factoryParserMediator.getUser(userName.getText()));
                    
                    loggedInAs = new JLabel("Logged In As: "+getLoggedUser().userName);
                    accountPanel.remove(signIn);
                    accountPanel.remove(createAccount);
                    accountPanel.add(loggedInAs);
                    accountPanel.add(accountPage);
                    accountPanel.add(logout);
                    loginFrame.dispose();
                    mainFrame.revalidate();
                }
                else{
                    loginFrame.add(loginLabel);
                    loginFrame.revalidate();
                }
            }
            else if(src == logout){
                logout();
                accountPanel.remove(loggedInAs);
                accountPanel.remove(accountPage);
                accountPanel.remove(logout);
                accountPanel.add(signIn);
                accountPanel.add(createAccount);
                mainFrame.revalidate();
            }
        }
    }
    
    public static void main(String[] args) {
        RequestHandler rh = new RequestHandler("testUser.txt", "testUNtoID.txt");
        rh.Start();
    }
}
