package repository;

import Entity.Receptionist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private final Connection connection;

    public ReceptionistRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertReceptionist(Receptionist receptionist) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECEPTIONIST)) {
            preparedStatement.setInt(1, receptionist.getEmployeeId());
            preparedStatement.setString(2, receptionist.getLanguagesSpoken());
            preparedStatement.setString(3, receptionist.getWorkShift());
            preparedStatement.executeUpdate();
        }
    }

    // READ ALL
    public List<Receptionist> getAllReceptionists() throws SQLException {
        List<Receptionist> receptionists = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_RECEPTIONISTS)) {
            while (resultSet.next()) {
                Receptionist receptionist = new Receptionist(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("languagesSpoken"),
                        resultSet.getString("workShift")
                );
                receptionists.add(receptionist);
            }
        }
        return receptionists;
    }

    // READ BY ID
    public Receptionist getReceptionistById(int employeeId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEPTIONIST_BY_ID)) {
            preparedStatement.setInt(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Receptionist(
                            resultSet.getInt("employeeId"),
                            resultSet.getString("languagesSpoken"),
                            resultSet.getString("workShift")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateReceptionist(Receptionist receptionist) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECEPTIONIST)) {
            preparedStatement.setString(1, receptionist.getLanguagesSpoken());
            preparedStatement.setString(2, receptionist.getWorkShift());
            preparedStatement.setInt(3, receptionist.getEmployeeId());
            preparedStatement.executeUpdate();
        }
    }

    // DELETE BY ID
    public void deleteReceptionistById(int employeeId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECEPTIONIST)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        }
    }

}

