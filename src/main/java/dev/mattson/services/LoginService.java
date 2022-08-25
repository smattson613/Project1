package dev.mattson.services;

import dev.mattson.entities.Resident;

public interface LoginService {

    Resident validateUser(String username, String password);
}
