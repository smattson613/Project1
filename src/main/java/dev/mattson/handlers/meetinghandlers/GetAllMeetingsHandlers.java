package dev.mattson.handlers.meetinghandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Meeting;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllMeetingsHandlers implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<Meeting> meeting = App.meetingService.getAllMeetings();
        Gson gson = new Gson();
        String json = gson.toJson(meeting);
        ctx.result(json);
    }
}
