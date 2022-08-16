package dev.mattson.doas;

import dev.mattson.entities.Complaint;
import dev.mattson.utils.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class ComplaintDAOPostgres implements ComplaintDAO{
    @Override
    public Complaint createComplaint(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into complaint values (default, ?, ?, default, default)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, complaint.getTitle());
            preparedStatement.setString(2, complaint.getDescription());
//            preparedStatement.setString(3, complaint.getStatus().name());
//            preparedStatement.setInt(4, complaint.getMeetingId());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("complaintId");
            complaint.setComplaintId(generatedKey);
            return complaint;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Complaint getComplaintById(int id) {
        return null;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return null;
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return null;
    }

    @Override
    public boolean deleteComplaintById(int id) {
        return false;
    }
}
