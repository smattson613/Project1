package dev.mattson.services;

import dev.mattson.entities.Meeting;

import java.util.List;

public interface MeetingService {

    Meeting registerMeeting(Meeting meeting);

    Meeting retrieveMeetingById(int id);

    List<Meeting> getAllMeetings();

    boolean deleteMeetings(int id);

    Meeting modifyMeeting(Meeting meeting);
}
