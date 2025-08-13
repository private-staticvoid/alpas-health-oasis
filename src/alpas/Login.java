package alpas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame {

    private JPanel jPanel1;
    private JPanel Left;
    private JPanel Right;
    private JLabel Logo;
    private JLabel LoginLabel;
    private JLabel EmailLabel;
    private JTextField email;
    private JLabel PasswordLabel;
    private JPasswordField password;
    private JButton LoginBtn;
    private JLabel NoAccLabel;
    private JButton SignupBtn;

    public Login() {
        initUI();
    }

    private void initUI() {
        setTitle("ALPAS Health Oasis: Earnshaw Health Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(255, 252, 245));
        jPanel1.setLayout(null);
        jPanel1.setBounds(0, 0, 800, 500);

        // Left Panel
        Left = new JPanel();
        Left.setBackground(new Color(55, 66, 117));
        Left.setLayout(null);
        Left.setBounds(0, 0, 400, 500);
        Logo = new JLabel(new ImageIcon(getClass().getResource("/Icon/ALPAS Health Oasis.png")));
        Logo.setBounds(25, 50, 350, 350);
        Left.add(Logo);
        jPanel1.add(Left);

        // Right Panel
        Right = new JPanel();
        Right.setBackground(new Color(255, 252, 245));
        Right.setBounds(400, 0, 400, 500);
        Right.setLayout(null);

        LoginLabel = new JLabel("LOGIN");
        LoginLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        LoginLabel.setForeground(new Color(55, 66, 117));
        LoginLabel.setBounds(145, 60, 200, 50);
        Right.add(LoginLabel);

        EmailLabel = new JLabel("Email");
        EmailLabel.setBounds(30, 130, 100, 30);
        Right.add(EmailLabel);

        email = new JTextField();
        email.setBackground(new Color(255, 252, 245));
        email.setBounds(30, 160, 340, 40);
        Right.add(email);

        PasswordLabel = new JLabel("Password");
        PasswordLabel.setBounds(30, 210, 100, 30);
        Right.add(PasswordLabel);

        password = new JPasswordField();
        password.setBackground(new Color(255, 252, 245));
        password.setBounds(30, 240, 340, 40);
        Right.add(password);

        LoginBtn = new JButton("Log In");
        LoginBtn.setBackground(new Color(55, 66, 117));
        LoginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        LoginBtn.setForeground(new Color(255, 252, 245));
        LoginBtn.setBounds(30, 300, 140, 40);
        LoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });
        Right.add(LoginBtn);

        NoAccLabel = new JLabel("Don't have an account yet?");
        NoAccLabel.setBounds(30, 360, 200, 30);
        Right.add(NoAccLabel);

        SignupBtn = new JButton("Sign Up Here.");
        SignupBtn.setBackground(new Color(255, 252, 245));
        SignupBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        SignupBtn.setForeground(new Color(137, 81, 89));
        SignupBtn.setBorder(null);
        SignupBtn.setBounds(190, 360, 120, 30);
        SignupBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SignupBtnActionPerformed(evt);
            }
        });
        Right.add(SignupBtn);

        jPanel1.add(Right);
        getContentPane().add(jPanel1);
        setVisible(true);
    }

    private void loginUser(String userId) {
        Home homeFrame = new Home(userId); // Pass the userId to Home
        homeFrame.setVisible(true);
        this.dispose(); // Close the login frame
    }

    private void SignupBtnActionPerformed(ActionEvent evt) {
        SignUp signUpFrame = new SignUp();
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setVisible(true);
        this.dispose();
    }

    private void LoginBtnActionPerformed(ActionEvent evt) {
        String Email, Password, query, fname = null, passDb = null;
        String SUrl = "jdbc:MySQL://localhost:3306/java_user_database";
        String SUser  = "root";
        String SPass = "";
        int notFound = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser , SPass);
            Statement st = con.createStatement();
            if (email.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Email = email.getText();
                Password = new String(password.getPassword());
                query = "SELECT * FROM user WHERE email = '" + Email + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    passDb = rs.getString("password");
                    fname = rs.getString("full_name");
                    notFound = 1;
                }
                if (notFound == 1 && Password.equals(passDb)) {
                    Home homeFrame = new Home("userId");
                    homeFrame.setUser (fname);
                    homeFrame.setLocationRelativeTo(null);
                    homeFrame.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                st.execute(query);
                password.setText("");
            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

}