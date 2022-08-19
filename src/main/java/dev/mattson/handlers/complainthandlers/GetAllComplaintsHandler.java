package dev.mattson.handlers.complainthandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllComplaintsHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<Complaint> complaint = App.complaintService.getAllComplaints();
        Gson gson = new Gson();
        String json = gson.toJson(complaint);
        ctx.result(json);
    }
}
