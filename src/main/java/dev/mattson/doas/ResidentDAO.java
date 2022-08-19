package dev.mattson.doas;

import dev.mattson.entities.Resident;

import java.util.List;

public interface ResidentDAO {

    //Create a Resident
    Resident createResident(Resident resident);

    //Read
    Resident getResidentById(int id);
    List<Resident> getAllResidents();

    //Update an employee
    Resident updateResident(Resident resident);

    //Delete and employee
    boolean deleteResidentById(int id);
}
