package repository;

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

}
