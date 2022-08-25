package dev.mattson.doas;

import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;

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

    //patch a complaint
//    Complaint modifyComplaint(int id, Status status);
}
