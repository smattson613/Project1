package dev.mattson.services;

import dev.mattson.doas.ResidentDAO;
import dev.mattson.entities.Resident;

import java.util.List;

public class ResidentServiceImpl implements ResidentService{

    private ResidentDAO residentDAO;

    public ResidentServiceImpl(ResidentDAO residentDAO) {
        this.residentDAO = residentDAO;
    }

    @Override
    public Resident registerResident(Resident resident) {
        Resident savedResident = this.residentDAO.createResident(resident);
        return savedResident;
    }

    @Override
    public Resident retrieveResidentById(int id) {
        return null;
    }

    @Override
    public List<Resident> getAllResidents() {
        return null;
    }

    @Override
    public boolean deleteResident(int id) {
        return false;
    }

    @Override
    public Resident modifyResident(Resident resident) {
        return null;
    }

    @Override
    public Resident getResidentByUsername(String username) {
        return this.residentDAO.getResidentByUsername(username);
    }
}
