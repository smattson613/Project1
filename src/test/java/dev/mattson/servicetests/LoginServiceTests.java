package dev.mattson.servicetests;

import dev.mattson.doas.ResidentDAO;
import dev.mattson.entities.Resident;
import dev.mattson.entities.Title;
import dev.mattson.exceptions.NoUsernameFoundException;
import dev.mattson.exceptions.PasswordMismatchException;
import dev.mattson.services.LoginService;
import dev.mattson.services.LoginServiceImpl;
import dev.mattson.services.ResidentService;
import dev.mattson.services.ResidentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTests {

    @Test
    void does_user_exist_test() {
        ResidentDAO residentDAO = Mockito.mock(ResidentDAO.class);
        Resident resident = new Resident(0,"Steve","testpass", Title.COUNCIL_MEMBER);
        Mockito.when(residentDAO.createResident(resident)).thenReturn(resident);
        Mockito.when(residentDAO.getResidentByUsername(resident.getUsername())).thenReturn(resident);
        LoginService loginService = new LoginServiceImpl(residentDAO);

        Assertions.assertThrows(NoUsernameFoundException.class, () ->{
            loginService.validateUser("Stev","testpass");
        });
    }

    @Test
    void does_password_match_test() {
        ResidentDAO residentDAO = Mockito.mock(ResidentDAO.class);
        Resident resident = new Resident(0,"Steve","testpass", Title.COUNCIL_MEMBER);
        Mockito.when(residentDAO.createResident(resident)).thenReturn(resident);
        Mockito.when(residentDAO.getResidentByUsername(resident.getUsername())).thenReturn(resident);
        LoginService loginService = new LoginServiceImpl(residentDAO);

        Assertions.assertThrows(PasswordMismatchException.class, () ->{
            loginService.validateUser("Steve","testpass1");
        });
    }
}
