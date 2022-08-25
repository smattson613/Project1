package dev.mattson.services;

import dev.mattson.doas.ComplaintDAO;
import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @Override
    public Complaint registerComplaint(Complaint complaint) {
        Complaint savedComplaint = this.complaintDAO.createComplaint(complaint);
        return savedComplaint;
    }

    @Override
    public Complaint retrieveComplaintById(int id) {
        return this.complaintDAO.getComplaintById(id);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return this.complaintDAO.getAllComplaints();
    }

    @Override
    public boolean deleteComplaint(int id) {
        return false;
    }

    @Override
    public Complaint modifyComplaint(int id, Status status) {
        Complaint complaint = this.complaintDAO.getComplaintById(id);
        complaint.setStatus(status);
        return this.complaintDAO.updateComplaint(complaint);
    }

    @Override
    public Complaint modifyComplaintMeetingId(int complaintId, int meetingId) {
        Complaint complaint = this.complaintDAO.getComplaintById(complaintId);
        complaint.setMeetingId(meetingId);
        return this.complaintDAO.modifyComplaintMeetingId(complaint);
    }
}
