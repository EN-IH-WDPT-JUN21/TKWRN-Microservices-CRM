package com.ironhack.menuservice.dao;

import com.ironhack.menuservice.MenuServiceApplication;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class Login implements ActionListener {


    private static JLabel title;
    private static JPanel panel;
    private static JLabel user;
    private static JTextField userText;
    private static JLabel password;
    private static JPasswordField passwordText;
    private static JButton submit;
    private static JLabel wrongInput;
    private static JLabel dummyLabel;
    private static JLabel hint;
    private static JFrame frame;
    private static String username;
    private static char[] userPassword;
    public static int isLoggedIn;
    public static JLabel esterEgg;
    public static Desktop desktop;
    public static URI uri;

    // Creates login window
    public static void login() {

        frame = new JFrame("LBL CRM SYSTEM LOGIN");
        frame.setUndecorated(true);
        frame.setSize(400, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        panel.setBorder(new LineBorder(Color.BLACK, 2, true));

        title = new JLabel("LBL CRM SYSTEM LOGIN");
        title.setBounds(40, 10, 300, 25);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(title);

        user = new JLabel("Username");
        user.setFont(new Font("Serif", Font.BOLD, 16));
        user.setBounds(50, 60, 120, 25);
        panel.add(user);

        userText = new JTextField(20);
        userText.setBounds(140, 60, 165, 25);
        panel.add(userText);

        password = new JLabel("Password");
        password.setFont(new Font("Serif", Font.BOLD, 16));
        password.setBounds(50, 90, 120, 25);
        panel.add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(140, 90, 165, 25);
        panel.add(passwordText);

        submit = new JButton("Login");
        submit.setBounds(223, 140, 80, 25);
        submit.addActionListener(new Login());
        panel.add(submit);

        wrongInput = new JLabel();
        wrongInput.setBounds(30, 140, 300, 25);
        panel.add(wrongInput);

        hint = new JLabel("Make your console full screen for a better experience");
        hint.setFont(new Font("Serif", Font.BOLD, 16));
        hint.setForeground(Color.red);
        hint.setBounds(20, 190, 380, 25);
        panel.add(hint);

        esterEgg = new JLabel("Admin or Guest? Too short for a Knight");
        esterEgg.grabFocus();
        esterEgg.setOpaque(false);
        esterEgg.setFont(new Font("Serif", Font.BOLD, 16));
        esterEgg.setForeground(Color.blue);
        esterEgg.setBounds(100, 230, 190, 30);
        esterEgg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        esterEgg.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                        try {
                            uri = new URI("https://www.youtube.com/watch?v=KHqy_AyKIUI&ab_channel=SonyPicturesatHomeUK");
                            desktop.browse(uri);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        esterEgg.setVisible(false);
        panel.add(esterEgg);

        dummyLabel = new JLabel();
        panel.add(dummyLabel);

        frame.add(panel);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setDefaultButton(submit); // Lets you press "Submit" button using ENTER
    }

    // Method to get username input value
    public static String getUsername() {
        username = userText.getText() ;
        return username;
    }

    public static String getPassword() {
        userPassword = passwordText.getPassword();
        return String.valueOf(userPassword);
    }

    public static int getIsLoggedIn() {
        return isLoggedIn;
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        char[] password = passwordText.getPassword();
        String pass = String.valueOf(password);

        // Checks if username and password input equals to any of registered users and runs OS or OSGuest menu
        if (getUsername().equals("Admin") && pass.equals("admin")
        || getUsername().equals("1") && pass.equals("1")) {


            frame.dispose(); // Closes login window
            isLoggedIn = 1;
            try {
                SpringApplication.run(MenuServiceApplication.class);
            } catch (RuntimeException ex) {
                System.out.println("Our server is busy! Please run the program again to login!");
            }

        } else if (getUsername().equals("Guest") && pass.equals("guest")) {
            frame.dispose(); // Closes login window
            isLoggedIn = 2;
            try {
                SpringApplication.run(MenuServiceApplication.class);
            } catch (RuntimeException ex) {
                System.out.println("Our server is busy! Please run the program again to login!");
            }
        } else if (getUsername().equals("Knights") && pass.equals("ni")) {
            wrongInput.setText("");
            frame.setSize(400, 280);
            esterEgg.setVisible(true);
            uri = new URI("https://www.youtube.com/watch?v=KHqy_AyKIUI&ab_channel=SonyPicturesatHomeUK");
            desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } else {
            frame.setSize(400, 230);
            wrongInput.setText("Wrong username or password!");
        }

    }

}
