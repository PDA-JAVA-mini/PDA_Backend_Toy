package itinerary;

import com.fasterxml.jackson.databind.ObjectMapper;
import itinerary.model.Itinerary;
import utils.JsonHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ItineraryHandler extends JsonHandler{
    private static int currentItineraryId;

    public ItineraryHandler(int itineraryCnt){
        currentItineraryId = itineraryCnt;
    }

    public <T> void write(T object, String filePath) {
        String json = serialize(object);
        File file = new File(filePath);
        try(FileWriter writer = new FileWriter(file)){
            writer.write(json);
        }catch (IOException e) {
            throw new RuntimeException("Failed to write itinerary data to file", e);
        }
    }

    public <T> T read(Class<T> clazz, String filePath) {
        try {
            String json = new String(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));
            return deserialize(json, clazz);
        }catch (IOException e){
            throw new RuntimeException("Failed to read itinerary data from file", e);

        }
    }
}
