package dev.mattson.handlers.residenthandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Resident;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateResidentHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Resident resident = gson.fromJson(json, Resident.class);
        Resident registeredResident = App.residentService.registerResident(resident);
        String residentJson = gson.toJson(registeredResident);
        ctx.status(201);
        ctx.result(residentJson);
    }
}
