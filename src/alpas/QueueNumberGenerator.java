package alpas;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class QueueNumberGenerator {

    // A queue to hold the queue numbers
    private static Queue<Integer> queue = new LinkedList<>();

    public static int getQueueNumber(String appointmentType, java.sql.Date appointmentDate) throws SQLException {
        int queueNumber = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
            String sql = "SELECT COUNT(*) as count FROM acc WHERE Date = ? AND Appointment = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, appointmentDate);
            preparedStatement.setString(2, appointmentType);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                queueNumber = resultSet.getInt("count") + 1; // Next queue number
            }

            // Push the new queue number to the queue
            queue.add(queueNumber);
        } catch (SQLException e) {
            e.printStackTrace(); // Log error for debugging
            throw new SQLException("Error while getting queue number: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* ignored */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }

        return queueNumber;
    }

    // Method to pop the next queue number from the queue
    public static Integer popQueueNumber() {
        return queue.poll(); // Removes and returns the head of the queue, or returns null if the queue is empty
    }

    // Method to push a queue number back into the queue
    public static void pushQueueNumber(int queueNumber) {
        queue.add(queueNumber); // Adds the specified queue number to the end of the queue
    }

    // Method to check the current size of the queue
    public static int getQueueSize() {
        return queue.size(); // Returns the number of elements in the queue
    }
}