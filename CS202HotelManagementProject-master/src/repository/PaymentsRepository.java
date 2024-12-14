package repository;

public class PaymentsRepository {
    // CREATE
    final String INSERT_PAYMENT = "INSERT INTO Payments (PaymentID, PaymentStatus, Amount, PaymentDate, Booking_ID) VALUES (?, ?, ?, ?, ?)";

    // READ
    final String SELECT_ALL_PAYMENTS = "SELECT * FROM Payments";
    final String SELECT_PAYMENT_BY_PaymentID = "SELECT * FROM Payments WHERE PaymentID = ?";
    final String SELECT_PAYMENT_BY_BookingID= "SELECT * FROM Payments WHERE BookingID = ?";

    // UPDATE
    final String UPDATE_PAYMENT = "UPDATE Payments SET PaymentStatus = ?, Amount = ?, PaymentDate = ?, Booking_ID = ? WHERE PaymentID = ?";

    // DELETE
    final String DELETE_PAYMENT = "DELETE FROM Payments WHERE PaymentID = ?";
    final String DELETE_ALL = "DELETE FROM Payments";

}
