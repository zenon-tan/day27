package day27.workshop.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class ToJson {

    public static JsonObject reviewDocToJson(Document document, Boolean edited) {

        JsonObject json = Json.createObjectBuilder()
        .add("user", document.getString("user"))
        .add("rating", document.getInteger("rating"))
        .add("ID", document.getInteger("ID"))
        .add("posted", document.getDate("posted").toString())
        .add("edited", edited)
        .add("timestamp", LocalDateTime.now().toString())
        .build();

        return json;
        
    }

    public static JsonObject historyDocToJson(Document document, Boolean edited) {

        if(edited) {

            List<Document> editedList = document.getList("edited", Document.class);
            JsonArrayBuilder arr = Json.createArrayBuilder();
            for (Document history : editedList) {

                JsonObjectBuilder obj = Json.createObjectBuilder()
                .add("comment", history.getString("comment"))
                .add("rating", history.getInteger("rating"))
                .add("posted", history.getDate("posted").toString());

                arr.add(obj);
            }

            JsonObject json = Json.createObjectBuilder()
            .add("user", document.getString("user"))
            .add("rating", document.getInteger("rating"))
            .add("ID", document.getInteger("ID"))
            .add("comment", document.getString("comment"))
            .add("posted", document.getDate("posted").toString())
            .add("edited", arr)
            .add("timestamp", LocalDateTime.now().toString())
            .build();

        return json;
        }

        JsonObject json = Json.createObjectBuilder()
        .add("user", document.getString("user"))
        .add("rating", document.getInteger("rating"))
        .add("ID", document.getInteger("ID"))
        .add("comment", document.getString("comment"))
        .add("posted", document.getDate("posted").toString())
        .add("timestamp", LocalDateTime.now().toString())
        .build();

    return json;

    }
    
}
