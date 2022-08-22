package dev.mattson.app;

import dev.mattson.doas.ComplaintDAOPostgres;
import dev.mattson.doas.MeetingDAOPostgres;
import dev.mattson.doas.ResidentDAOPostgres;
import dev.mattson.entities.Meeting;
import dev.mattson.handlers.complainthandlers.CreateComplaintHandler;
import dev.mattson.handlers.complainthandlers.GetAllComplaintsHandler;
import dev.mattson.handlers.complainthandlers.UpdateComplaintHandler;
import dev.mattson.handlers.meetinghandlers.CreateMeetingHandler;
import dev.mattson.handlers.meetinghandlers.GetAllMeetingsHandlers;
import dev.mattson.services.*;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());
    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDAOPostgres());
//    public static ResidentService residentService = new ResidentServiceImpl(new ResidentDAOPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        // Complaint Handlers
        CreateComplaintHandler createComplaintHandler = new CreateComplaintHandler();
        GetAllComplaintsHandler getAllComplaintsHandler = new GetAllComplaintsHandler();
        UpdateComplaintHandler updateComplaintHandler = new UpdateComplaintHandler();

        //Meeting Handlers
        CreateMeetingHandler createMeetingHandler = new CreateMeetingHandler();
        GetAllMeetingsHandlers getAllMeetingsHandlers = new GetAllMeetingsHandlers();


        app.post("/complaint",createComplaintHandler);
        app.get("/complaints",getAllComplaintsHandler);
        app.patch("/complaints/{complaintId}/{status}",updateComplaintHandler);

        app.post("/meeting",createMeetingHandler);
        app.get("/meetings",getAllMeetingsHandlers);


        app.start();
    }
}
