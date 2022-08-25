package dev.mattson.daotests;

import dev.mattson.doas.ResidentDAO;
import dev.mattson.doas.ResidentDAOPostgres;
import dev.mattson.entities.Resident;
import dev.mattson.entities.Title;
import dev.mattson.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResidentDAOTests {

    static ResidentDAO residentDAO = new ResidentDAOPostgres();

    @BeforeAll
    static void setup() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table resident(\n" +
                    "residentId serial primary key,\n" +
                    "username varchar(40) not null,\n" +
                    "password varchar(40) not null,\n" +
                    "title varchar(20) not null\n" +
                    ");";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_resident_test() {
        Resident resident = new Resident(0,"council","member", Title.COUNCIL_MEMBER);
        Resident savedResident = residentDAO.createResident(resident);
        Assertions.assertEquals(1,savedResident.getResidentId());
    }

    @Test
    @Order(2)
    void get_resident_by_username() {
        Resident resident =residentDAO.getResidentByUsername("council");
        Assertions.assertEquals(1,resident.getResidentId());
    }

    @AfterAll
    static void teardown() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "drop table resident";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
