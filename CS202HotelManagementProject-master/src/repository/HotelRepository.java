package repository;

public class HotelRepository {
    // CREATE
    final String INSERT_HOTEL = "INSERT INTO Hotel (H_Name, H_Address, H_City, H_phone_number, H_Email) VALUES (?, ?, ?, ?, ?)";

    // READ
    final String SELECT_ALL_HOTELS = "SELECT * FROM Hotel";
    final String SELECT_HOTEL_BY_ID = "SELECT * FROM Hotel WHERE Hotel_ID = ?";

    // UPDATE
    final String UPDATE_HOTEL = "UPDATE Hotel SET H_Name = ?, H_Address = ?, H_City = ?, H_phone_number = ?, H_Email = ? WHERE Hotel_ID = ?";

    // DELETE
    final String DELETE_HOTEL = "DELETE FROM Hotel WHERE Hotel_ID = ?";
    final String DELETE_ALL = "DELETE FROM Hotel";

}
