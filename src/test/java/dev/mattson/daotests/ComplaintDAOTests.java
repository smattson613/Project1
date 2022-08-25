package dev.mattson.daotests;

import dev.mattson.doas.ComplaintDAO;
import dev.mattson.doas.ComplaintDAOPostgres;
import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;
import dev.mattson.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static dev.mattson.entities.Status.HIGH_PRIORITY;
import static dev.mattson.entities.Status.UNREVIEWED;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDAOTests {

    static ComplaintDAO complaintDAO = new ComplaintDAOPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table complaint(\n" +
                    "complaintId serial primary key,\n" +
                    "title varchar(40) not null,\n" +
                    "description varchar(180) not null,\n" +
                    "status varchar(15) default 'UNREVIEWED',\n" +
                    "meetingId int references meeting(meetingId) default -1\n" +
                    ");";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_complaint_test() {
        Complaint complaint = new Complaint(0,"Drowners","Too many Drowners in the sewers making city unsafe", UNREVIEWED,-1);
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);
        Assertions.assertEquals(1,savedComplaint.getComplaintId());
    }

    @Test
    @Order(2)
    void update_complaint_priority_test() {
        Complaint complaint = complaintDAO.getComplaintById(1);
        complaint.setStatus(HIGH_PRIORITY);
        complaintDAO.updateComplaint(complaint);
        Assertions.assertEquals(HIGH_PRIORITY,complaint.getStatus());
    }

    @Test
    @Order(3)
    void get_complaints_by_id() {
        Complaint complaint = complaintDAO.getComplaintById(1);
        Assertions.assertEquals("Drowners",complaint.getTitle());
    }

    @Test
    @Order(4)
    void get_all_complaints() {
        List<Complaint> complaintList = complaintDAO.getAllComplaints();
        Assertions.assertEquals(1,complaintList.size());
    }

    @AfterAll
    static void teardown() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "drop table complaint";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
