package dev.mattson.services;

import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;

import java.util.List;

public interface ComplaintService {

    Complaint registerComplaint(Complaint complaint);

    Complaint retrieveComplaintById(int id);

    List<Complaint> getAllComplaints();

    boolean deleteComplaint(int id);

    Complaint modifyComplaint(int id, Status status);

    Complaint modifyComplaintMeetingId(int complaintId, int meetingId);
}
