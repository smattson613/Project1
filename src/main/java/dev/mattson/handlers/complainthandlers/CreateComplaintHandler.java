package dev.mattson.handlers.complainthandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateComplaintHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Complaint complaint = gson.fromJson(json, Complaint.class);
        Complaint registeredComplaint = App.complaintService.registerComplaint(complaint);
        String complaintJson = gson.toJson(registeredComplaint);
        ctx.status(201);
        ctx.result(complaintJson);
    }
}
