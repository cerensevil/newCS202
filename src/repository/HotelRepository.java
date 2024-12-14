package repository;

import Entity.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class HotelRepository {
    // CREATE
    final String INSERT_HOTEL = "INSERT INTO Hotel (hotelId, hotelName, hotelAddress, hotelCity, phoneNumber, email) VALUES (?, ?, ?, ?, ?,?)";

    // READ
    final String SELECT_ALL_HOTELS = "SELECT * FROM Hotel";
    final String SELECT_HOTEL_BY_ID = "SELECT * FROM Hotel WHERE hotelId = ?";

    // UPDATE
    final String UPDATE_HOTEL = "UPDATE Hotel SET hotelName = ?, hotelAddress = ?, hotelCity = ?, phoneNumber = ?, email = ? WHERE hotelId = ?";

    // DELETE
    final String DELETE_HOTEL = "DELETE FROM Hotel WHERE hotelId = ?";
    final String DELETE_ALL = "DELETE FROM Hotel";

    private final Connection connection;

    public HotelRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertHotel(Hotel hotel) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_HOTEL)) {
            statement.setString(1, hotel.getHotelName());
            statement.setString(2, hotel.getHotelAddress());
            statement.setString(3, hotel.getHotelCity());
            statement.setString(4, hotel.getPhoneNumber());
            statement.setString(5, hotel.getEmail());
            statement.executeUpdate();
        }
    }

    // READ ALL
    public List<Hotel> getAllHotels() throws Exception {
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_HOTELS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Hotel hotel = new Hotel(
                        resultSet.getInt("hotelId"),
                        resultSet.getString("hotelName"),
                        resultSet.getString("hotelAddress"),
                        resultSet.getString("hotelCity"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")
                );
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    // READ BY ID
    public Hotel getHotelById(int hotelId) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_HOTEL_BY_ID)) {
            statement.setInt(1, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Hotel(
                            resultSet.getInt("hotelId"),
                            resultSet.getString("hotelName"),
                            resultSet.getString("hotelAddress"),
                            resultSet.getString("hotelCity"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("email")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateHotel(Hotel hotel) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_HOTEL)) {
            statement.setString(1, hotel.getHotelName());
            statement.setString(2, hotel.getHotelAddress());
            statement.setString(3, hotel.getHotelCity());
            statement.setString(4, hotel.getPhoneNumber());
            statement.setString(5, hotel.getEmail());
            statement.setInt(6, hotel.getHotelId());
            statement.executeUpdate();
        }
    }

    // DELETE BY ID
    public void deleteHotel(int hotelId) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_HOTEL)) {
            statement.setInt(1, hotelId);
            statement.executeUpdate();
        }
    }

    // DELETE ALL
    public void deleteAllHotels() throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ALL)) {
            statement.executeUpdate();
        }
    }
}

