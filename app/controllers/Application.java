package controllers;

import models.Humanoid;
import play.Logger;
import play.libs.ws.WS;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Optional;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(toJson(Humanoid.find.all()));

    }

    public static Result create() {
        return Optional.ofNullable(request().body().asJson())
                .filter(j -> !j.findValue("name").asText().isEmpty())
                .filter(j -> j.findValue("age").asInt() > 18)
                .map(j -> {
                    Humanoid h = new Humanoid();
                    h.name = j.findValue("name").asText();
                    h.age = j.findValue("age").asInt();
                    h.save();
                    return ok(toJson(h));
                })
                .orElse(badRequest("parametrarna fel"));
    }

    public static Result hai() {
        WS.client().url("http://localhost:9000/").get().map(r -> {
            Logger.debug(r.getBody());
            return null;
        });
        return ok();
    }
}
