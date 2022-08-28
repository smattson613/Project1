package dev.mattson.services;

import dev.mattson.doas.ResidentDAO;
import dev.mattson.entities.Resident;
import dev.mattson.exceptions.NoUsernameFoundException;
import dev.mattson.exceptions.PasswordMismatchException;

public class LoginServiceImpl implements LoginService{

    private ResidentDAO residentDAO;

    public LoginServiceImpl(ResidentDAO residentDAO) {
        this.residentDAO = residentDAO;
    }
    @Override
    public Resident validateUser(String username, String password) {
        Resident resident = this.residentDAO.getResidentByUsername(username);
        if (resident == null) {
            throw new NoUsernameFoundException("No resident found with that username");
        }
        if (!resident.getPassword().equals(password)) {
            throw new PasswordMismatchException("password does not match");
        }
        return resident;
    }
}
