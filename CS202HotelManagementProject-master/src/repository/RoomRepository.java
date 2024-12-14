package repository;

public class RoomRepository {
    // CREATE
    final String INSERT_ROOM = "INSERT INTO Room (roomId, hotel_Id, room_number, room_type, R_price, R_availability_status) VALUES (?,?, ?, ?, ?, ?)";

    // READ
    final String SELECT_ALL_ROOMS = "SELECT * FROM Room";
    final String SELECT_ROOM_BY_ID = "SELECT * FROM Room WHERE room_id = ?";
    final String SELECT_AVAILABLE_ROOMS = "SELECT * FROM Room WHERE R_availability_status = 'Available'";

    // UPDATE
    final String UPDATE_ROOM = "UPDATE Room SET hotel_Id = ?, room_number = ?, room_type = ?, R_price = ?, R_availability_status = ? WHERE room_id = ?";

    // DELETE
    final String DELETE_ROOM = "DELETE FROM Room WHERE room_id = ?";
    final String DELETE_ALL = "DELETE FROM Room";

}
