package dev.mattson.doas;

import dev.mattson.entities.Meeting;

import java.util.List;

public interface MeetingDAO {

    //Create a meeting
    Meeting createMeeting(Meeting meeting);

    //Read
    Meeting getMeetingById(int id);
    List<Meeting> getAllMeetings();

    //Update a meeting
    Meeting updateMeeting(Meeting meeting);

    //Delete a meeting
    boolean deleteMeetingById(int id);
}
