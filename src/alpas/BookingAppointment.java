package alpas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingAppointment extends JFrame {
    private JComboBox<String> appointmentTypeComboBox;
    private JButton bookAppointmentButton;
    private JButton backButton;
    private JLabel queueNumberLabel;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JTextField fullNameField;
    private JTextField ageField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JTextField contactNumberField;
    private int queueNumber; // Store queue number

    public BookingAppointment() {
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(null);

        // Panel for appointment booking
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(null);
        bookingPanel.setBackground(new Color(248, 245, 245)); // Darker background for the booking panel
        bookingPanel.setBounds(0, 0, 800, 500); // Adjusted bounds for centering
        this.add(bookingPanel);

        // Header Label
        JLabel headerLabel = new JLabel("Appointment Booking");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(248, 245, 245));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(0, 0, 785, 50);
        headerLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(55, 66, 117)); // Darker background for the header
        bookingPanel.add(headerLabel);

        // Queue Number Label
        queueNumberLabel = new JLabel("Queue Number: ");
        queueNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        queueNumberLabel.setForeground(new Color(55, 66, 117)); // White text for visibility
        queueNumberLabel.setBounds(20, 60, 300, 30);
        bookingPanel.add(queueNumberLabel);

        // Full Name Label and Field
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fullNameLabel.setForeground(new Color(55, 66, 117));
        fullNameLabel.setBounds(20, 100, 150, 30);
        bookingPanel.add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(200, 100, 400, 30);
        bookingPanel.add(fullNameField);

        // Age Label and Field
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        ageLabel.setForeground(new Color(55, 66, 117));
        ageLabel.setBounds(20, 140, 150, 30);
        bookingPanel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(200, 140, 100, 30);
        bookingPanel.add(ageField);

        // Gender Label and Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        genderLabel.setForeground(new Color(55, 66, 117));
        genderLabel.setBounds(20, 180, 150, 30);
        bookingPanel.add(genderLabel);

        maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        maleButton.setForeground(new Color(55, 66, 117));
        maleButton.setBackground(new Color(248, 245, 245)); // Darker background for the panel
        maleButton.setBounds(200, 180, 100, 30);
        bookingPanel.add(maleButton);

        femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        femaleButton.setForeground(new Color(55, 66, 117));
        femaleButton.setBackground(new Color(248, 245, 245)); // Darker background for the panel
        femaleButton.setBounds(310, 180, 100, 30);
        bookingPanel.add(femaleButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Contact Number Label and Field
        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contactNumberLabel.setForeground(new Color(55, 66, 117));
        contactNumberLabel.setBounds(20, 220, 150, 30);
        bookingPanel.add(contactNumberLabel);

        contactNumberField = new JTextField();
        contactNumberField.setBounds(200, 220, 400, 30);
        bookingPanel.add(contactNumberField);

        // Date Label
        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        dateLabel.setForeground(new Color(55, 66, 117));
        dateLabel.setBounds(20, 260, 300, 30);
        bookingPanel.add(dateLabel);

        // Date Spinner
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setValue(java.sql.Date.valueOf(LocalDate.now())); // Set default date to today
        dateSpinner.setBounds(200, 260, 200, 30);
        bookingPanel.add(dateSpinner);

        // Time Label
        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        timeLabel.setForeground(new Color(55, 66, 117));
        timeLabel.setBounds(20, 300, 300, 30);
        bookingPanel.add(timeLabel);

        // Time Spinner
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(java.sql.Time.valueOf(LocalTime.now())); // Set default time to now
        timeSpinner.setBounds(200, 300, 200, 30);
        bookingPanel.add(timeSpinner);



        // Appointment Type ComboBox
        String[] appointmentTypes = {
                "Birth Control Consultation",
                "STI/STD Testing",
                "Ante-natal Care",
                "Postnatal Care",
                "HIV Screening",
                "Family Planning",
                "Reproductive Health Checkup"
        };
        appointmentTypeComboBox = new JComboBox<>(appointmentTypes);
        appointmentTypeComboBox.setBounds(200, 340, 400, 30);
        bookingPanel.add(appointmentTypeComboBox);

        // Book Appointment Button
        bookAppointmentButton = new JButton("Book Appointment");
        bookAppointmentButton.setBounds(150, 380, 200, 40);
        bookAppointmentButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        bookAppointmentButton.setBackground(new Color(248, 245, 245)); // Dark background for button
        bookAppointmentButton.setForeground(new Color(55, 66, 117));
        bookAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookAppointment();
            }
        });
        bookingPanel.add(bookAppointmentButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(400, 380, 200, 40);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        backButton.setBackground(new Color(248, 245, 245)); // Dark background for button
        backButton.setForeground(new Color(55, 66, 117));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to the previous screen
                dispose(); // Close current window (optional)
                Home homeFrame = new Home("userId"); // Adjust this to match your implementation
                homeFrame.setVisible(true); // Show the home window
            }
        });
        bookingPanel.add(backButton);

        // Center the window
        setLocationRelativeTo(null);
    }

    private void bookAppointment() {
        // Generate a queue number for the appointment
        LocalDate selectedDate = ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime selectedTime = ((java.util.Date) timeSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();

        String appointmentType = (String) appointmentTypeComboBox.getSelectedItem();

        // Insert appointment into the database
        String sql = "INSERT INTO acc (Queue, Date, Time, Appointment) VALUES (?, ?, ?, ?)";

        try {
            queueNumber = QueueNumberGenerator.getQueueNumber(appointmentType, java.sql.Date.valueOf(selectedDate));
            queueNumberLabel.setText("Queue Number: " + queueNumber); // Update the label with the new queue number

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, queueNumber);
                preparedStatement.setDate(2, java.sql.Date.valueOf(selectedDate));
                preparedStatement.setTime(3, java.sql.Time.valueOf(selectedTime));
                preparedStatement.setString(4, appointmentType);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error booking appointment.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookingAppointment().setVisible(true);
        });
    }
}
