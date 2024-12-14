package repository;

import Entity.Roles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RolesRepository {
    // CREATE
    final String INSERT_ROLE = "INSERT INTO Roles (roleId, role_name) VALUES (?,?)";

    // READ
    final String SELECT_ALL_ROLES = "SELECT * FROM Roles";
    final String SELECT_ROLE_BY_roleId = "SELECT * FROM Roles WHERE roleId = ?";
    final String SELECT_ROLE_BY_role_name= "SELECT * FROM Roles WHERE role_name = ?";

    // UPDATE
    final String UPDATE_ROLE = "UPDATE Roles SET role_name = ? WHERE role_id = ?";

    // DELETE
    final String DELETE_ROLE = "DELETE FROM Roles WHERE role_id = ?";
    private final Connection connection;

    public RolesRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertRole(Roles role) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE)) {
            preparedStatement.setInt(1, role.getRoleId());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.executeUpdate();
        }
    }

    // READ ALL
    public List<Roles> getAllRoles() throws SQLException {
        List<Roles> roles = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROLES)) {
            while (resultSet.next()) {
                Roles role = new Roles(
                        resultSet.getInt("roleId"),
                        resultSet.getString("role_name")
                );
                roles.add(role);
            }
        }
        return roles;
    }

    // READ BY ID
    public Roles getRoleById(int roleId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_roleId)) {
            preparedStatement.setInt(1, roleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Roles(
                            resultSet.getInt("roleId"),
                            resultSet.getString("role_name")
                    );
                }
            }
        }
        return null;
    }

    // READ BY NAME
    public Roles getRoleByName(String roleName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_role_name)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Roles(
                            resultSet.getInt("roleId"),
                            resultSet.getString("role_name")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE..
    public void updateRole(Roles role) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getRoleId());
            preparedStatement.executeUpdate();
        }
    }

    // DELETE BY ID
    public void deleteRoleById(int roleId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.executeUpdate();
        }
    }

}
