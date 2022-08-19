package dev.mattson.handlers.complainthandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Complaint;
import dev.mattson.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class UpdateComplaintHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        String status = ctx.pathParam("status");
        Complaint complaint = App.complaintService.retrieveComplaintById(id);
        if (complaint == null) {
            return;
        }
        status.toLowerCase();
        switch (status) {
            case "high":
                complaint = App.complaintService.modifyComplaint(id, Status.HIGH_PRIORITY);
                break;
            case "low":
                complaint = App.complaintService.modifyComplaint(id, Status.LOW_PRIORITY);
                break;
            case "ignore":
                complaint = App.complaintService.modifyComplaint(id, Status.IGNORED);
                break;
            case "addressed":
                complaint = App.complaintService.modifyComplaint(id, Status.ADDRESSED);
                break;
            default:
                ctx.status(304);
                return;
        }
        Gson gson = new Gson();
        ctx.result(gson.toJson(complaint));
        ctx.status(202);
    }
}
