package dev.mattson.entities;

public class Meeting {

    private int meetingId;
    private String description;
    private String date;

    public Meeting(){
    }

    public Meeting(int meetingId, String description, String date) {
        this.meetingId = meetingId;
        this.description = description;
        this.date = date;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
