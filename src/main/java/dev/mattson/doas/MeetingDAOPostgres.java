package dev.mattson.doas;

import dev.mattson.entities.Meeting;
import dev.mattson.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOPostgres implements MeetingDAO{
    @Override
    public Meeting createMeeting(Meeting meeting) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into meeting values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,meeting.getDescription());
            preparedStatement.setString(2,meeting.getAddress());
            preparedStatement.setLong(3,meeting.getTime());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("meetingId");
            meeting.setMeetingId(generatedKey);
            return meeting;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Meeting getMeetingById(int id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from meeting where meetingId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Meeting meeting = new Meeting();

            meeting.setMeetingId(rs.getInt("meetingId"));
            meeting.setDescription(rs.getString("description"));
            meeting.setAddress(rs.getString("address"));
            meeting.setTime(rs.getLong("time"));

            return meeting;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Meeting> getAllMeetings() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from meeting";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Meeting> meetingList = new ArrayList<>();
            while (rs.next()) {
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setDescription(rs.getString("description"));
                meeting.setAddress(rs.getString("address"));
                meeting.setTime(rs.getLong("time"));
                meetingList.add(meeting);
            }

            return meetingList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        return null;
    }

    @Override
    public boolean deleteMeetingById(int id) {
        return false;
    }
}
