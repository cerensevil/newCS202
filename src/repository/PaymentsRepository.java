package repository;

import Entity.Payments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsRepository {
    // CREATE
    final String INSERT_PAYMENT = "INSERT INTO Payments (paymentId, paymentStatus, amount, paymentDate, bookingId) VALUES (?, ?, ?, ?, ?)";

    // READ
    final String SELECT_ALL_PAYMENTS = "SELECT * FROM Payments";
    final String SELECT_PAYMENT_BY_PaymentID = "SELECT * FROM Payments WHERE paymentId = ?";
    final String SELECT_PAYMENT_BY_BookingID = "SELECT * FROM Payments WHERE BookingID = ?";

    // UPDATE
    final String UPDATE_PAYMENT = "UPDATE Payments SET paymentStatus = ?, amount = ?, paymentDate = ?, bookingId = ? WHERE paymentId = ?";

    // DELETE
    final String DELETE_PAYMENT = "DELETE FROM Payments WHERE paymentId = ?";
    final String DELETE_ALL = "DELETE FROM Payments";
    private final Connection connection;

    public PaymentsRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertPayment(Payments payment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT)) {
            preparedStatement.setInt(1, payment.getPaymentId());
            preparedStatement.setString(2, payment.getPaymentStatus());
            preparedStatement.setDouble(3, payment.getAmount());
            preparedStatement.setDate(4, payment.getPaymentDate());
            preparedStatement.setInt(5, payment.getBookingId());
            preparedStatement.executeUpdate();
        }
    }

    // READ ALL
    public List<Payments> getAllPayments() throws Exception {
        List<Payments> paymentsList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PAYMENTS)) {
            while (resultSet.next()) {
                Payments payment = new Payments(
                        resultSet.getInt("paymentId"),
                        resultSet.getString("paymentStatus"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("paymentDate"),
                        resultSet.getInt("bookingId")
                );
                paymentsList.add(payment);
            }
        }
        return paymentsList;
    }

    // READ BY PaymentID
    public Payments getPaymentById(int paymentId) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_PaymentID)) {
            preparedStatement.setInt(1, paymentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Payments(
                            resultSet.getInt("PaymentID"),
                            resultSet.getString("PaymentStatus"),
                            resultSet.getDouble("Amount"),
                            resultSet.getDate("PaymentDate"),
                            resultSet.getInt("Booking_ID")
                    );
                }
            }
        }
        return null;
    }

    // READ BY BookingID
    public List<Payments> getPaymentsByBookingId(int bookingId) throws Exception {
        List<Payments> paymentsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_BookingID)) {
            preparedStatement.setInt(1, bookingId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payments payment = new Payments(
                            resultSet.getInt("PaymentID"),
                            resultSet.getString("PaymentStatus"),
                            resultSet.getDouble("Amount"),
                            resultSet.getDate("PaymentDate"),
                            resultSet.getInt("Booking_ID")
                    );
                    paymentsList.add(payment);
                }
            }
        }
        return paymentsList;
    }

    // UPDATE
    public void updatePayment(Payments payment) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT)) {
            preparedStatement.setString(1, payment.getPaymentStatus());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setDate(3, payment.getPaymentDate());
            preparedStatement.setInt(4, payment.getBookingId());
            preparedStatement.setInt(5, payment.getPaymentId());
            preparedStatement.executeUpdate();
        }
    }

    // DELETE BY PaymentID
    public void deletePaymentById(int paymentId) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT)) {
            preparedStatement.setInt(1, paymentId);
            preparedStatement.executeUpdate();
        }
    }

    // DELETE ALL
    public void deleteAllPayments() throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ALL)) {
            statement.executeUpdate();
        }
    }
}


