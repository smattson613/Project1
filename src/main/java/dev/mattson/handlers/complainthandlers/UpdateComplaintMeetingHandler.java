package dev.mattson.handlers.complainthandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateComplaintMeetingHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int complaintId = Integer.parseInt(ctx.pathParam("complaintId"));
        int meetingId = Integer.parseInt(ctx.pathParam("meetingId"));
        //Complaint complaint = App.complaintService.retrieveComplaintById(complaintId);
        Complaint complaint = App.complaintService.modifyComplaintMeetingId(complaintId, meetingId);
        Gson gson = new Gson();
        ctx.result(gson.toJson(complaint));
        ctx.status(202);

    }
}
