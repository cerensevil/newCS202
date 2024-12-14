package repository;

import Entity.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    // CREATE
    final String INSERT_ROOM = "INSERT INTO Room (roomId, hotelId, roomNumber, roomTypeId,roomType, price, availabilityStatus) VALUES (?,?, ?, ?, ?, ?,?)";

    // READ
    final String SELECT_ALL_ROOMS = "SELECT * FROM Room";
    final String SELECT_ROOM_BY_ID = "SELECT * FROM Room WHERE roomId = ?";
    final String SELECT_AVAILABLE_ROOMS = "SELECT * FROM Room WHERE availabilityStatus = 'Available'";

    // UPDATE
    final String UPDATE_ROOM = "UPDATE Room SET hotelId = ?, roomNumber = ?, roomTypeId = ?,roomType= ?, price = ?, availabilityStatus = ? WHERE roomId = ?";

    // DELETE
    final String DELETE_ROOM = "DELETE FROM Room WHERE roomId = ?";
    final String DELETE_ALL = "DELETE FROM Room";
    private final Connection connection;

    public RoomRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE: Insert a new Room
    public void insertRoom(Room room) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM)) {
            preparedStatement.setInt(1, room.getRoomId());
            preparedStatement.setInt(2, room.getHotelId());
            preparedStatement.setString(3, room.getRoomNumber());
            preparedStatement.setInt(4, room.getRoomTypeId());
            preparedStatement.setString(5, room.getRoomType());
            preparedStatement.setDouble(6, room.getPrice());
            preparedStatement.setString(7, room.getAvailabilityStatus());
            preparedStatement.setInt(8, room.getBedCount());
            preparedStatement.setInt(9, room.getMaxOccupancy());
            preparedStatement.setInt(10, room.getRoomSize());
            preparedStatement.setString(11, room.getSpecialAmenities());
            preparedStatement.executeUpdate();
        }
    }

    // READ: Get all Rooms
    public List<Room> getAllRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROOMS)) {
            while (resultSet.next()) {
                rooms.add(mapRowToRoom(resultSet));
            }
        }
        return rooms;
    }

    // READ: Get Room by ID
    public Room getRoomById(int roomId) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_BY_ID)) {
            preparedStatement.setInt(1, roomId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToRoom(resultSet);
                }
            }
        }
        return null;
    }

    // READ: Get Available Rooms
    public List<Room> getAvailableRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_AVAILABLE_ROOMS)) {
            while (resultSet.next()) {
                rooms.add(mapRowToRoom(resultSet));
            }
        }
        return rooms;
    }

    // UPDATE: Update Room details
    public void updateRoom(Room room) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROOM)) {
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getRoomNumber());
            preparedStatement.setInt(3, room.getRoomTypeId());
            preparedStatement.setString(4, room.getRoomType());
            preparedStatement.setDouble(5, room.getPrice());
            preparedStatement.setString(6, room.getAvailabilityStatus());
            preparedStatement.setInt(7, room.getBedCount());
            preparedStatement.setInt(8, room.getMaxOccupancy());
            preparedStatement.setInt(9, room.getRoomSize());
            preparedStatement.setString(10, room.getSpecialAmenities());
            preparedStatement.setInt(11, room.getRoomId());
            preparedStatement.executeUpdate();
        }
    }

    // DELETE: Delete a Room by ID
    public void deleteRoomById(int roomId) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROOM)) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        }
    }

    // DELETE: Delete all Rooms
    public void deleteAllRooms() throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL);
        }
    }

    // Helper method to map a ResultSet row to a Room object
    private Room mapRowToRoom(ResultSet resultSet) throws Exception {
        return new Room(
                resultSet.getInt("roomId"),
                resultSet.getInt("hotelId"),
                resultSet.getString("roomNumber"),
                resultSet.getInt("roomTypeId"),
                resultSet.getString("roomType"),
                resultSet.getDouble("price"),
                resultSet.getString("availabilityStatus"),
                resultSet.getInt("bedCount"),
                resultSet.getInt("maxOccupancy"),
                resultSet.getInt("roomSize"),
                resultSet.getString("specialAmenities")
        );
    }

}
