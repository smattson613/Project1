package dev.mattson.doas;

import dev.mattson.entities.Resident;
import dev.mattson.entities.Title;
import dev.mattson.utils.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class ResidentDAOPostgres implements ResidentDAO {
    @Override
    public Resident createResident(Resident resident) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into resident values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, resident.getUsername());
            preparedStatement.setString(2, resident.getPassword());
            preparedStatement.setString(3, resident.getTitle().name());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("residentId");
            resident.setResidentId(generatedKey);
            return resident;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Resident getResidentById(int id) {
        return null;
    }

    @Override
    public List<Resident> getAllResidents() {
        return null;
    }

    @Override
    public Resident updateResident(Resident resident) {
        return null;
    }

    @Override
    public boolean deleteResidentById(int id) {
        return false;
    }

    @Override
    public Resident getResidentByUsername(String username) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from resident where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Resident resident = new Resident();
            resident.setResidentId(rs.getInt("residentId"));
            resident.setUsername(rs.getString("username"));
            resident.setPassword(rs.getString("password"));
            resident.setTitle(Title.valueOf(rs.getString("title")));

            return resident;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
