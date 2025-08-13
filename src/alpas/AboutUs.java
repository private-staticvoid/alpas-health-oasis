package alpas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs extends JFrame {
    public AboutUs() {
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248, 245, 245)); // Light background for the About Us panel

        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(55, 67, 117));
        titleLabel.setBounds(300, 20, 200, 50);
        panel.add(titleLabel);

        JTextArea aboutText = new JTextArea();
        aboutText.setText("ALPAS is a Doctor's Appointment Booking System designed for Reproductive Health (RH) clinic, with an aim to break the stigma surrounding sexual health and encourage Filipinos especially the youth to take charge of their reproductive well-being. The program will enable National University Students to easily schedule appointments with Earnshaw Health Clinic around (677 Earnshaw Street, Sampaloc Manila) for services such as birth control consultation, STI/STD testing, ante-natal and postnatal care, and HIV screening, among others.");
        aboutText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        aboutText.setBounds(50, 100, 700, 250);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setEditable(false);
        panel.add(aboutText);

        JButton backButton = new JButton("Back");
        backButton.setBounds(650, 400, 100, 40);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(55, 67, 117));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home homeFrame = new Home("userId"); // Adjust with actual user ID
                homeFrame.setVisible(true);
                dispose(); // Close About Us frame
            }
        });
        panel.add(backButton);

        this.add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AboutUs().setVisible(true);
            }
        });
    }
}
