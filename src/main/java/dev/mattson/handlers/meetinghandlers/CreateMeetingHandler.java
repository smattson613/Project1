package dev.mattson.handlers.meetinghandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Meeting;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateMeetingHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Meeting meeting = gson.fromJson(json, Meeting.class);
        Meeting registeredMeeting = App.meetingService.registerMeeting(meeting);
        String meetingJson = gson.toJson(registeredMeeting);
        ctx.status(201);
        ctx.result(meetingJson);
    }
}
