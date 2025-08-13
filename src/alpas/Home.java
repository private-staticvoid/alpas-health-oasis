package alpas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class Home extends JFrame {
    private JPanel jPanel1;
    private JLabel Hello;
    private JLabel user;
    private JButton LogoutBtn;
    private JButton bookAppointmentBtn;
    private JButton viewAppointmentsBtn;
    private JButton aboutUsBtn;

    private Clip musicClip; // Clip for background music

    public Home(String userId) {
        this.initComponents();
        this.setUser (userId); // Assuming userId is the name to display
    }

    public Home() {}

    private void initComponents() {
        this.jPanel1 = new JPanel() {
            // Override paintComponent to set the background image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("C:\\Users\\Aki\\Downloads\\Background (2).png"); // Update with your image path
                Image img = background.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.Hello = new JLabel();
        this.user = new JLabel();
        this.LogoutBtn = new JButton();
        this.bookAppointmentBtn = new JButton();
        this.viewAppointmentsBtn = new JButton();
        this.aboutUsBtn = new JButton();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.jPanel1.setLayout(null);
        this.jPanel1.setBounds(0, 0, 800, 500);

        this.Hello.setFont(new Font("Segoe UI", Font.BOLD, 36));
        this.Hello.setForeground(new Color(55, 66, 117)); // Text color
        this.Hello.setText("Hello,");
        this.Hello.setBounds(21, 22, 100, 50);

        this.user.setFont(new Font("Segoe UI", Font.BOLD, 36));
        this.user.setForeground(new Color(55, 66, 117)); // Text color
        this.user.setBounds(130, 22, 641, 50);

        this.LogoutBtn.setBackground(new Color(255, 252, 245)); // Button color
        this.LogoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.LogoutBtn.setForeground(new Color(55, 66, 117)); // Text color for button
        this.LogoutBtn.setText("Logout");
        this.LogoutBtn.setBounds(600, 400, 142, 37);
        this.LogoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        // Setup buttons with the specified color scheme
        setupButton(bookAppointmentBtn, "Book Appointment", 180, 225);
        bookAppointmentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBookingAppointment();
            }
        });

        setupButton(viewAppointmentsBtn, "View Appointments", 400, 225);
        viewAppointmentsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openViewAppointments();
            }
        });

        setupButton(aboutUsBtn, "About Us", 290, 275);
        aboutUsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAboutUs();
            }
        });

        this.jPanel1.add(this.Hello);
        this.jPanel1.add(this.user);
        this.jPanel1.add(this.LogoutBtn);
        this.jPanel1.add(this.bookAppointmentBtn);
        this.jPanel1.add(this.viewAppointmentsBtn);
        this.jPanel1.add(this.aboutUsBtn);

        this.add(this.jPanel1);
    }

    private void setupButton(JButton button, String text, int x, int y) {
        button.setText(text);
        button.setBounds(x, y, 200, 40);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(190, 192, 197)); // Button color
        button.setForeground(new Color(55, 66, 117)); // Text color
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false);

        // Mouse hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 252, 245));
                button.setForeground(new Color(4, 4, 66));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(4, 4, 66));
                button.setForeground(Color.WHITE);
            }
        });
    }

    private void openAboutUs() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop(); // Stop the music if already playing
        }
        playMusic("C:\\Users\\Aki\\Downloads\\202410231923 (2).wav"); // Use the correct file path

        JFrame aboutUsFrame = new JFrame("About Us");
        aboutUsFrame.setSize(800, 500);
        aboutUsFrame.setLocationRelativeTo(null);
        aboutUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(null);
        aboutUsPanel.setBackground(new Color(248, 245, 245));

        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(55, 67, 117));
        titleLabel.setBounds(320, 0, 200, 50);
        aboutUsPanel.add(titleLabel);

        JTextArea aboutUsTextArea = new JTextArea(
"ALPAS Health Oasis is a Java-based doctor’s appointment booking system designed to serve the needs of Reproductive Health (RH) clinics. Our mission is to break the stigma surrounding sexual health and empower Filipinos, especially the youth, to take control of their reproductive well-being.\n" +
        "\n" +
        "For this project, we have featured Earnshaw Health Center, located at 677 Earnshaw Street, Sampaloc, Manila City. Situated near the National University - Manila campus. The clinic offers convenient access for Nationalian students seeking RH services such as birth control consultations, STI/STD testing, ante-natal and post-natal care, and HIV screening, among others.\n" +
        "\n" +
        "Through ALPAS Health Oasis, we aim to make scheduling appointments for RH services easy, discreet, and accessible—while promoting education and awareness about sexual health and rights. We strongly believe that every individual has the right to quality reproductive healthcare, and our platform is a step toward making that a reality for the Filipino youth.\n" +
        "\n" +
        "Remember, taking care of your sexual health is not shameful—it’s responsible, empowering, and vital to your overall well-being."        );
        aboutUsTextArea.setBounds(18, 55, 750, 360);
        aboutUsTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        aboutUsTextArea.setForeground(new Color(55, 66, 117));
        aboutUsTextArea.setBackground(new Color(190, 192, 197));
        aboutUsTextArea.setLineWrap(true);
        aboutUsTextArea.setWrapStyleWord(true);
        aboutUsTextArea.setEditable(false);

        JButton backButton = new JButton("Back");
        backButton.setBounds(668, 420, 100, 40);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(4, 4, 66));
        backButton.setForeground(new Color(255, 255, 255));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (musicClip != null && musicClip.isRunning()) {
                    musicClip.stop(); // Stop music when going back
                }
                aboutUsFrame.dispose();
            }
        });

        aboutUsPanel.add(aboutUsTextArea);
        aboutUsPanel.add(backButton);
        aboutUsFrame.add(aboutUsPanel);
        aboutUsFrame.setVisible(true);
    }

    private void playMusic(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Aki\\Downloads\\202410271750 (1).wav")); // Load from a file
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openBookingAppointment() {

        BookingAppointment bookingFrame = new BookingAppointment();
        bookingFrame.setVisible(true);
        this.dispose(); // Close the home frame
    }

    private void openViewAppointments() {

        ViewAppointments viewAppointmentsFrame = new ViewAppointments();
        viewAppointmentsFrame.setVisible(true);
        this.dispose(); // Close the home frame
    }

    private void LogoutBtnActionPerformed(ActionEvent evt) {
        musicClip.stop(); // Stop music on logout
        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        this.dispose();
    }

    public void setUser(String name) {
        this.user.setText(name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            (new Home("userId")).setVisible(true);
        });
    }
}
