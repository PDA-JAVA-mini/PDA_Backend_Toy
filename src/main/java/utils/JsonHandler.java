package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public abstract class JsonHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    protected JsonHandler() { // 제어접근자 추가
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
    }

    public abstract <T> void write(T object, String filePath);
    public abstract <T> T read(String filePath);

    public <T> String serialize(T object) throws RuntimeException{
        try{
            return mapper.writeValueAsString(object);
        }catch(Exception e){
            throw new RuntimeException("Failed to serialize", e);
        }
    }

    public <T> T deserialize(String json, Class<T> clazz) throws RuntimeException {
        try {
            return mapper.readValue(json, clazz);
        }catch(Exception e){
            throw new RuntimeException("Failed to deserialize", e);
        }
    }

    public <T> T deserialize(String json, TypeReference<T> typeRef) throws RuntimeException {
        try {
            return mapper.readValue(json, typeRef);
        }catch(Exception e){
            throw new RuntimeException("Failed to deserialize generic type", e);
        }
    }
}
