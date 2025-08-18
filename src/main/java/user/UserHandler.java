package user;

import java.io.File;
import java.io.IOException;
import utils.JsonHandler;

public class UserHandler extends JsonHandler {

  @Override
  public <T> void write(T object, String filePath) {
    try {
      String json = serialize(object);
      File file = new File(filePath);
      file.getParentFile().mkdirs();
      java.nio.file.Files.write(file.toPath(), json.getBytes());
    } catch (IOException e) {
      throw new RuntimeException("Failed to write user data to file", e);
    }
  }

  @Override
  public <T> T read(Class<T> clazz, String filePath) {
    try {
      String json = new String(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));
      return deserialize(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read user data from file", e);
    }
  }
}
