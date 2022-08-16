package dev.mattson.services;

import dev.mattson.doas.ComplaintDAO;
import dev.mattson.entities.Complaint;

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
        return null;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return null;
    }

    @Override
    public boolean deleteComplaint(int id) {
        return false;
    }

    @Override
    public Complaint modifyComplaint(Complaint complaint) {
        return null;
    }
}
