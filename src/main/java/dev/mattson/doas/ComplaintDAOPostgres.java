package dev.mattson.doas;

import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;
import dev.mattson.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAOPostgres implements ComplaintDAO{
    @Override
    public Complaint createComplaint(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into complaint values (default, ?, ?, default, default)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, complaint.getTitle());
            preparedStatement.setString(2, complaint.getDescription());

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
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from complaint where complaintId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Complaint complaint = new Complaint();

            complaint.setComplaintId(rs.getInt("complaintId"));
            complaint.setTitle(rs.getString("title"));
            complaint.setDescription(rs.getString("description"));
            complaint.setStatus(Status.valueOf(rs.getString("status")));
            complaint.setMeetingId(rs.getInt("meetingID"));

            return complaint;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Complaint> getAllComplaints() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from complaint";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Complaint> complaintList = new ArrayList<>();
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getInt("complaintId"));
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(Status.valueOf(rs.getString("status")));
                complaint.setMeetingId(rs.getInt("meetingId"));
                complaintList.add(complaint);
            }

            return complaintList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update complaint set status = ? where complaintId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, complaint.getStatus().name());
            preparedStatement.setInt(2, complaint.getComplaintId());

            preparedStatement.executeUpdate();

            return complaint;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteComplaintById(int id) {
        return false;
    }

    @Override
    public Complaint modifyComplaintMeetingId(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update complaint set meetingId = ? where complaintId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, complaint.getMeetingId());
            preparedStatement.setInt(2, complaint.getComplaintId());

            preparedStatement.executeUpdate();

            return complaint;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
