package dev.mattson.daotests;

import dev.mattson.doas.MeetingDAO;
import dev.mattson.doas.MeetingDAOPostgres;
import dev.mattson.entities.Complaint;
import dev.mattson.entities.Meeting;
import dev.mattson.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
    @Order(1)
    void create_meeting_test() {
        Meeting meeting = new Meeting(0,"Test Meeting","Town Hall",0);
        Meeting savedMeeting = meetingDAO.createMeeting(meeting);
        Assertions.assertEquals(1,savedMeeting.getMeetingId());
    }

    @Test
    @Order(2)
    void get_meeting_by_id_test() {
        Meeting meeting = meetingDAO.getMeetingById(1);
        Assertions.assertEquals("Town Hall",meeting.getAddress());
    }

    @Test
    @Order(3)
    void get_all_meetings_test() {
        List<Meeting> meetingList = meetingDAO.getAllMeetings();
        Assertions.assertEquals(1,meetingList.size());
    }


//    @AfterAll
//    static void teardown() {
//        try (Connection conn = ConnectionUtil.createConnection()) {
//            String sql = "drop table meeting";
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
