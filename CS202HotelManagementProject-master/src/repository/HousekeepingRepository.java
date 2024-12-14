package repository;

public class HousekeepingRepository {
    // INSERT
   final String INSERT_HOUSEKEEPER = "INSERT INTO Housekeeping (employeeId, cleaningArea,shift) VALUES (?, ?,?)";

    // SELECT
    final String SELECT_ALL_HOUSEKEEPERS = "SELECT * FROM Housekeeping";
  final String SELECT_HOUSEKEEPER_BY_ID = "SELECT * FROM Housekeeping WHERE employeeId = ?";

    // UPDATE
    final String UPDATE_HOUSEKEEPER = "UPDATE Housekeeping SET employeeId = ?, cleaningArea = ?,shift = ? WHERE employeeId = ?";

    // DELETE
    final String DELETE_HOUSEKEEPER = "DELETE FROM Housekeeping WHERE employeeId = ?";
}
