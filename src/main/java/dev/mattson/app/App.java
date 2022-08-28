package dev.mattson.app;

import com.google.gson.Gson;
import dev.mattson.doas.ComplaintDAOPostgres;
import dev.mattson.doas.MeetingDAOPostgres;
import dev.mattson.doas.ResidentDAOPostgres;
import dev.mattson.dtos.LoginCredentials;
import dev.mattson.entities.Meeting;
import dev.mattson.entities.Resident;
import dev.mattson.handlers.complainthandlers.CreateComplaintHandler;
import dev.mattson.handlers.complainthandlers.GetAllComplaintsHandler;
import dev.mattson.handlers.complainthandlers.UpdateComplaintHandler;
import dev.mattson.handlers.complainthandlers.UpdateComplaintMeetingHandler;
import dev.mattson.handlers.meetinghandlers.CreateMeetingHandler;
import dev.mattson.handlers.meetinghandlers.GetAllMeetingsHandlers;
import dev.mattson.handlers.residenthandlers.CreateResidentHandler;
import dev.mattson.services.*;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());
    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDAOPostgres());
    public static ResidentService residentService = new ResidentServiceImpl(new ResidentDAOPostgres());
    public static LoginService loginService = new LoginServiceImpl(new ResidentDAOPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        // Complaint Handlers
        CreateComplaintHandler createComplaintHandler = new CreateComplaintHandler();
        GetAllComplaintsHandler getAllComplaintsHandler = new GetAllComplaintsHandler();
        UpdateComplaintHandler updateComplaintHandler = new UpdateComplaintHandler();
        UpdateComplaintMeetingHandler updateComplaintMeetingHandler = new UpdateComplaintMeetingHandler();

        //Meeting Handlers
        CreateMeetingHandler createMeetingHandler = new CreateMeetingHandler();
        GetAllMeetingsHandlers getAllMeetingsHandlers = new GetAllMeetingsHandlers();

        //Resident Handlers
        CreateResidentHandler createResidentHandler = new CreateResidentHandler();


        app.post("/complaint",createComplaintHandler);
        app.get("/complaints",getAllComplaintsHandler);
        app.patch("/complaints/{complaintId}/{status}",updateComplaintHandler);
        app.patch("assign/{complaintId}/{meetingId}", updateComplaintMeetingHandler);

        app.post("/meeting",createMeetingHandler);
        app.get("/meetings",getAllMeetingsHandlers);

        app.post("/resident",createResidentHandler);

        app.post("/login", ctx ->{
            String body = ctx.body();
            Gson gson = new Gson();
            LoginCredentials credentials = gson.fromJson(body, LoginCredentials.class);

            Resident resident = loginService.validateUser(credentials.getUsername(), credentials.getPassword());
            String employeeJSON = gson.toJson(resident);
            ctx.result(employeeJSON);
        });


        app.start();
    }
}
