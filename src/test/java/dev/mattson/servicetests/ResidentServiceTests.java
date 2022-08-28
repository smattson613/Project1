package dev.mattson.servicetests;

import dev.mattson.doas.ResidentDAO;
import dev.mattson.entities.Resident;
import dev.mattson.entities.Title;
import dev.mattson.exceptions.ShortPasswordException;
import dev.mattson.exceptions.ShortUsernameException;
import dev.mattson.services.ResidentService;
import dev.mattson.services.ResidentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ResidentServiceTests {

    @Test
    void resident_username_correct_length_test() {
        ResidentDAO residentDAO = Mockito.mock(ResidentDAO.class);
        Resident resident = new Resident(0,"Jo","testpass", Title.COUNCIL_MEMBER);
        Mockito.when(residentDAO.createResident(resident)).thenReturn(resident);
        ResidentService residentService = new ResidentServiceImpl(residentDAO);

        Assertions.assertThrows(ShortUsernameException.class, () ->{
            residentService.registerResident(resident);
        });
    }

    @Test
    void resident_password_correct_length_test() {
        ResidentDAO residentDAO = Mockito.mock(ResidentDAO.class);
        Resident resident = new Resident(0,"Jorge","test", Title.COUNCIL_MEMBER);
        Mockito.when(residentDAO.createResident(resident)).thenReturn(resident);
        ResidentService residentService = new ResidentServiceImpl(residentDAO);

        Assertions.assertThrows(ShortPasswordException.class, () ->{
            residentService.registerResident(resident);
        });
    }
}
