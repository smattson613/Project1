package dev.mattson.services;

import dev.mattson.doas.MeetingDAO;
import dev.mattson.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService{

    private MeetingDAO meetingDAO;

    public MeetingServiceImpl(MeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    @Override
    public Meeting registerMeeting(Meeting meeting) {
        Meeting savedMeeting = this.meetingDAO.createMeeting(meeting);
        return savedMeeting;
    }

    @Override
    public Meeting retrieveMeetingById(int id) {
        return this.meetingDAO.getMeetingById(id);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }

    @Override
    public boolean deleteMeetings(int id) {
        return false;
    }

    @Override
    public Meeting modifyMeeting(Meeting meeting) {
        return null;
    }
}
