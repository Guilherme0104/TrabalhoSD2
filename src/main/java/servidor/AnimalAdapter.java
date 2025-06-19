package servidor;

import com.google.gson.*;
import entidades.animais.*;

import java.lang.reflect.Type;

public class AnimalAdapter implements JsonDeserializer<Animal> {
    
    @Override
    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();

        if (!jsonObj.has("type") || jsonObj.get("type").isJsonNull()) {
            throw new JsonParseException("Campo 'type' ausente ou nulo no objeto Animal. JSON recebido: " + jsonObj);
        }

        String tipo = jsonObj.get("type").getAsString();

        switch (tipo) {
            case "Cachorro":
                return context.deserialize(json, Cachorro.class);
            case "Gato":
                return context.deserialize(json, Gato.class);
            case "Papagaio":
                return context.deserialize(json, Papagaio.class);
            default:
                throw new JsonParseException("Tipo de animal desconhecido: " + tipo);
        }
}
}
