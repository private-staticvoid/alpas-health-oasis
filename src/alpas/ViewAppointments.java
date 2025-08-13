package alpas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class ViewAppointments extends JFrame {
    private JPanel viewPanel;
    private JPanel appointmentsPanel;
    private JButton btnLogout;
    private JButton btnBack;

    public ViewAppointments() {
        setTitle("View Appointments");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createViewPanel();
        add(viewPanel);
        fetchAppointments();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void createViewPanel() {
        viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.setBackground(new Color(248, 245, 245));

        appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        appointmentsPanel.setBackground(new Color(248, 245, 245)); // Light background for the appointments

        JScrollPane scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the scroll pane

        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(4, 4, 66));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(e -> {
            this.dispose(); // Close the current window
            Home home = new Home("userId"); // Replace "userId" with the actual user ID if needed
            home.setVisible(true); // Show the home window
        });

        btnLogout = new JButton("Logout");
        btnLogout.setBackground(new Color(4, 4, 66));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            dispose(); // Close current window
            new Login().setVisible(true); // Show the login window
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(55, 67, 117)); // Same color as viewPanel
        buttonPanel.add(btnBack);
        buttonPanel.add(btnLogout);

        viewPanel.add(scrollPane, BorderLayout.CENTER);
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fetchAppointments() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
            String sql = "SELECT * FROM acc"; // Modify according to your table structure
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Map to hold appointments grouped by date
            Map<String, List<String>> appointmentsByDate = new HashMap<>();

            while (resultSet.next()) {
                String service = resultSet.getString("Appointment");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");
                String queueNumber = resultSet.getString("Queue");

                // Create the appointment detail string
                String appointmentDetail = "Queue No: " + queueNumber +
                        ", Service: " + service + ", Time: " + time;

                // Group appointments by date
                appointmentsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(appointmentDetail);
            }

            // Now, create UI components for each date and its appointments
            for (Map.Entry<String, List<String>> entry : appointmentsByDate.entrySet()) {
                String date = entry.getKey();
                List<String> appointmentDetails = entry.getValue();

                // Create a panel for the date
                JPanel datePanel = new JPanel();
                datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
                datePanel.setBorder(BorderFactory.createTitledBorder(date)); // Title with the date
                datePanel.setBackground(Color.WHITE);

                for (String detail : appointmentDetails) {
                    JLabel appointmentLabel = new JLabel(detail);
                    appointmentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    appointmentLabel.setForeground(new Color(55, 67, 117)); // Darker text for visibility
                    datePanel.add(appointmentLabel);
                }

                appointmentsPanel.add(datePanel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching appointments: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewAppointments().setVisible(true));
    }
}
