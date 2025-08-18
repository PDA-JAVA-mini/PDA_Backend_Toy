package user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import trip.Trip;
import trip.TripHandler;
import utils.JsonHandler;

public class UserHandler extends JsonHandler {

  private final String usersBasePath;
  private final TripHandler tripHandler;

  public UserHandler(String usersBasePath) {
    // UserHandler는 TripHandler를 내부적으로 사용하여 작업을 위임합니다.
    this.usersBasePath = usersBasePath;
    this.tripHandler = new TripHandler(usersBasePath);
  }

  /**
   * 특정 사용자에게 새로운 여행을 생성하고 모든 파일을 업데이트합니다.
   */
  public Trip createTripForUser(int userId, Trip newTripData) {
    // 1. 전체 trips 폴더를 스캔하여 전역적으로 고유한 새 tripId 생성
    int newTripId = generateNextGlobalTripId();

    // 2. userId와 tripId를 모두 포함한 완전한 Trip 객체 생성
    Trip finalTrip = newTripData.withIdAndUserId(newTripId, userId);

    // 3. TripHandler에게 완성된 객체를 주며 파일 저장을 지시
    tripHandler.save(userId, finalTrip);

    // 4. user_info.json 파일의 trip_ids 목록도 업데이트
    User user = findUserById(userId); // user_info.json 읽기
    user.getTripIds().add(newTripId);
    saveUser(user); // 변경된 user_info.json 저장

    return finalTrip;
  }

  public User findUserById(int userId) {
    String path = usersBasePath + "/users_" + userId + ".json";
    return read(path, User.class);
  }

  public void saveUser(User user) {
    String path = usersBasePath + "/users_" + user.getId() + ".json";
    write(path, user);
  }

  public List<Trip> findAllTripsForUser(int userId) {
    User user = findUserById(userId);
    if (user == null || user.getTripIds().isEmpty()) {
      return new ArrayList<>();
    }
    return tripHandler.findAllByUserId(userId, user.getTripIds());
  }

  /**
   * 모든 사용자 폴더를 스캔하여 다음 tripId를 생성합니다.
   */
  private int generateNextGlobalTripId() {
    File usersDir = new File(usersBasePath);
    if (!usersDir.exists() || !usersDir.isDirectory()) {
      return 1;
    }

    return Arrays.stream(usersDir.listFiles(File::isDirectory)) // 모든 사용자 폴더 (1, 2, ...)
        .flatMap(userDir -> {
          File tripsDir = new File(userDir, "trips");
          if (tripsDir.exists() && tripsDir.isDirectory()) {
            return Arrays.stream(tripsDir.listFiles((dir, name) -> name.endsWith(".json")));
          }
          return null;
        })
        .filter(Objects::nonNull)
        .map(file -> file.getName().replace(".json", ""))
        .mapToInt(Integer::parseInt)
        .max()
        .orElse(0) + 1;
  }

  public <T> void write(String filePath, T object) {
    try {
      String json = serialize(object);
      File file = new File(filePath);
      file.getParentFile().mkdirs();
      java.nio.file.Files.write(file.toPath(), json.getBytes());
    } catch (IOException e) {
      throw new RuntimeException("Failed to write user data to file", e);
    }
  }

  public <T> T read(String filePath, Class<T> clazz) {
    try {
      String json = new String(
          java.nio.file.Files.readAllBytes(new File(filePath).toPath()));
      return deserialize(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read user data from file", e);
    }
  }
}
