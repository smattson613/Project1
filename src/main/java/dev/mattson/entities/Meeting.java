package dev.mattson.entities;

public class Meeting {

    private int meetingId;
    private String description;
    private String address;
    private int time;

    public Meeting() {
    }

    public Meeting(int meetingId, String description, String address, int time) {
        this.meetingId = meetingId;
        this.description = description;
        this.address = address;
        this.time = time;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                '}';
    }
}