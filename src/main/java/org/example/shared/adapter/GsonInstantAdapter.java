package org.example.shared.adapter;

import java.lang.reflect.Type;
import java.time.Instant;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonInstantAdapter {
    public static JsonSerializer<Instant> instantSerializer = new JsonSerializer<>() {
      
      @Override
      public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
      }
    };

    public static JsonDeserializer<Instant> instantDeserializer = new JsonDeserializer<>() {
      @Override
      public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
          return Instant.parse(json.getAsString());
        } catch (Exception exception) {
          throw new JsonParseException("Formato inválido para Instant: " + json, exception);
        }
      }
    };
}
