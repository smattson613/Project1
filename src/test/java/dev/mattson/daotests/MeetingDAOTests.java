package dev.mattson.daotests;

import dev.mattson.doas.MeetingDAO;
import dev.mattson.doas.MeetingDAOPostgres;
import dev.mattson.entities.Meeting;
import dev.mattson.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingDAOTests {

    static MeetingDAO meetingDAO = new MeetingDAOPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table meeting(\n" +
                    "meetingId serial primary key,\n" +
                    "description varchar(40) not null,\n" +
                    "address varchar(180) not null,\n" +
                    "time varchar(15) default 'UNREVIEWED'\n" +
                    ");";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void create_meeting_test() {
        Meeting meeting = new Meeting(0,"Test Meeting","Town Hall",0);
        Meeting savedMeeting = meetingDAO.createMeeting(meeting);
        Assertions.assertEquals(1,savedMeeting.getMeetingId());
    }

    @AfterAll
    static void teardown() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "drop table meeting";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
