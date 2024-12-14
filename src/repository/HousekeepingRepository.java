package repository;

import Entity.Housekeeping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HousekeepingRepository {
    // INSERT..
    final String INSERT_HOUSEKEEPER = "INSERT INTO Housekeeping (employeeId, cleaningArea,shift) VALUES (?, ?,?)";

    // SELECT..
    final String SELECT_ALL_HOUSEKEEPERS = "SELECT * FROM Housekeeping";
    final String SELECT_HOUSEKEEPER_BY_ID = "SELECT * FROM Housekeeping WHERE employeeId = ?";

    // UPDATE
    final String UPDATE_HOUSEKEEPER = "UPDATE Housekeeping SET employeeId = ?, cleaningArea = ?,shift = ? WHERE employeeId = ?";

    // DELETE
    final String DELETE_HOUSEKEEPER = "DELETE FROM Housekeeping WHERE employeeId = ?";


    private final Connection connection;

    public HousekeepingRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertHousekeeper(Housekeeping housekeeping) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOUSEKEEPER)) {
            preparedStatement.setInt(1, housekeeping.getEmployeeId());
            preparedStatement.setString(2, housekeeping.getCleaningArea());
            preparedStatement.setString(3, housekeeping.getShift());
            preparedStatement.executeUpdate();
        }
    }

    // READ ALL
    public List<Housekeeping> getAllHousekeepers() throws SQLException {
        List<Housekeeping> housekeepers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_HOUSEKEEPERS)) {
            while (resultSet.next()) {
                Housekeeping housekeeping = new Housekeeping(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("cleaningArea"),
                        resultSet.getString("shift")
                );
                housekeepers.add(housekeeping);
            }
        }
        return housekeepers;
    }

    // READ BY ID
    public Housekeeping getHousekeeperById(int employeeId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOUSEKEEPER_BY_ID)) {
            preparedStatement.setInt(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Housekeeping(
                            resultSet.getInt("employeeId"),
                            resultSet.getString("cleaningArea"),
                            resultSet.getString("shift")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateHousekeeper(Housekeeping housekeeping) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HOUSEKEEPER)) {
            preparedStatement.setString(1, housekeeping.getCleaningArea());
            preparedStatement.setString(2, housekeeping.getShift());
            preparedStatement.setInt(3, housekeeping.getEmployeeId());
            preparedStatement.executeUpdate();
        }
    }

    // DELETE BY ID
    public void deleteHousekeeperById(int employeeId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HOUSEKEEPER)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        }
    }


}
