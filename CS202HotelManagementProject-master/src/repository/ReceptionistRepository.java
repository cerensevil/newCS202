package repository;

public class ReceptionistRepository {
    // INSERT
    final String INSERT_RECEPTIONIST = "INSERT INTO Receptionist (employeeId, languagesSpoken,workShift) VALUES (?, ?,?)";

    // SELECT
    final String SELECT_ALL_RECEPTIONISTS = "SELECT * FROM Receptionist";
   final String SELECT_RECEPTIONIST_BY_ID = "SELECT * FROM Receptionist WHERE employeeId = ?";

    // UPDATE
   final String UPDATE_RECEPTIONIST = "UPDATE Receptionist SET employeeId = ?, languagesSpoken = ?,workShift = ? WHERE employeeId = ?";

    // DELETE
    final String DELETE_RECEPTIONIST = "DELETE FROM Receptionist WHERE employeeId = ?";
}
