package dev.mattson.services;

import dev.mattson.entities.Resident;

import java.util.List;

public interface ResidentService {

    Resident registerResident(Resident resident);

    Resident retrieveResidentById(int id);

    List<Resident> getAllResidents();

    boolean deleteResident(int id);

    Resident modifyResident(Resident resident);

    Resident getResidentByUsername(String username);
}
