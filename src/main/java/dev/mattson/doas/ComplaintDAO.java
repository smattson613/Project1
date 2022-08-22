package dev.mattson.doas;

import dev.mattson.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    //Create a complaint
    Complaint createComplaint(Complaint complaint);

    //Read
    Complaint getComplaintById(int id);
    List<Complaint> getAllComplaints();

    //Update a complaint
    Complaint updateComplaint(Complaint complaint);

    //Delete a complaint
    boolean deleteComplaintById(int id);
}
