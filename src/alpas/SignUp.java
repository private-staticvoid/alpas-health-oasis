    package alpas;

    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.sql.*;

    public class SignUp extends JFrame {
        private JPanel jPanel1;
        private JPanel Left;
        private JPanel Right;
        private JLabel Logo;
        private JLabel SignUpLabel;
        private JLabel FullNameLabel;
        private JTextField fname;
        private JLabel EmailLabel;
        private JTextField emailAddress;
        private JLabel PasswordLabel;
        private JPasswordField pass;
        private JButton SignUpBtn;
        private JLabel AccountLabel;
        private JButton LoginBtn;

        public SignUp() {
            initUI();
        }

        private void initUI() {
            setTitle("ALPAS Health Oasis: Earnshaw Health Center");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(800, 500);
            setLayout(null);

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

            SignUpLabel = new JLabel("SIGN UP");
            SignUpLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
            SignUpLabel.setForeground(new Color(55, 67, 117));
            SignUpLabel.setBounds(110, 40, 200, 50);
            Right.add(SignUpLabel);

            FullNameLabel = new JLabel("Full Name");
            FullNameLabel.setBounds(30, 100, 100, 30);
            Right.add(FullNameLabel);

            fname = new JTextField();
            fname.setBackground(new Color(255, 252, 245));
            fname.setBounds(30, 130, 340, 40);
            Right.add(fname);

            EmailLabel = new JLabel("Email");
            EmailLabel.setBounds(30, 180, 100, 30);
            Right.add(EmailLabel);

            emailAddress = new JTextField();
            emailAddress.setBackground(new Color(255, 252, 245));
            emailAddress.setBounds(30, 210, 340, 40);
            Right.add(emailAddress);

            PasswordLabel = new JLabel("Password");
            PasswordLabel.setBounds(30, 260, 100, 30);
            Right.add(PasswordLabel);

            pass = new JPasswordField();
            pass.setBackground(new Color(255, 252, 245));
            pass.setBounds(30, 290, 340, 40);
            Right.add(pass);

            SignUpBtn = new JButton("Sign Up");
            SignUpBtn.setBackground(new Color(55, 67, 117));
            SignUpBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            SignUpBtn.setForeground(new Color(255, 252, 245));
            SignUpBtn.setBounds(30, 350, 140, 40);
            SignUpBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    SignUpBtnActionPerformed(evt);
                }
            });
            Right.add(SignUpBtn);

            AccountLabel = new JLabel("I already have an account.");
            AccountLabel.setBounds(30, 410, 200, 30);
            Right.add(AccountLabel);

            LoginBtn = new JButton("Log In Here.");
            LoginBtn.setBackground(new Color(255, 252, 245));
            LoginBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
            LoginBtn.setForeground(new Color(137, 81, 89));
            LoginBtn.setBorder(null);
            LoginBtn.setBounds(180, 410, 120,  30);
            LoginBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    LoginBtnActionPerformed(evt);
                }
            });
            Right.add(LoginBtn);

            jPanel1.add(Right);
            getContentPane().add(jPanel1);
            setVisible(true);
        }

        private void LoginBtnActionPerformed(ActionEvent evt) {
            Login loginFrame = new Login();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
            this.dispose();
        }

        private void SignUpBtnActionPerformed(ActionEvent evt) {
            String fullName, email, Password, query;
            String SUrl = "jdbc:MySQL://localhost:3306/java_user_database";
            String SUser = "root";
            String SPass = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
                Statement st = con.createStatement();
                if (fname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Full name is required", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (emailAddress.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (pass.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(this, "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    fullName = fname.getText();
                    email = emailAddress.getText();
                    Password = new String(pass.getPassword());
                    query = "INSERT INTO user(full_name, email, password)" + "VALUES('" + fullName + "', '" + email + "', '" + Password + "')";
                    st.execute(query);
                    fname.setText("");
                    emailAddress.setText("");
                    pass.setText("");
                    JOptionPane.showMessageDialog(null, "Account has been created succesfully!");
                }
            } catch (Exception e) {
                System.out.println("Error!" + e.getMessage());
            }
        }

    }