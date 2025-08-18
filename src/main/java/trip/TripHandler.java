package trip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import utils.JsonHandler;

public class TripHandler extends JsonHandler {
    private final String userBasePath;

    public TripHandler(String userBasePath){
        super();
        this.userBasePath = userBasePath;
    }

    public void save(int userId, Trip trip) {
        Path userTripPath = Paths.get(userBasePath, String.valueOf(userId), "trips");
        try {
            Files.createDirectories(userTripPath);
            String filePath = userTripPath.resolve("Trip_" + trip.getTripId() + ".json").toString();
            String jsonString = serialize(trip);
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기에 실패했습니다.", e);
        }
    }

    public Trip findById(int userId, int tripId){
        String filePath = Paths.get(userBasePath, String.valueOf(userId), "trips", tripId + ".json").toString();
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            return deserialize(jsonString, Trip.class);
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기에 실패했습니다: " + filePath, e);
        }
    }

    public List<Trip> findAllByUserId(int userId, List<Integer> tripIds){
        return tripIds.stream()
                .map(tripId -> findById(userId, tripId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public int generateNextId(){
        File directory = new File(userBasePath);
        File[] files = directory.listFiles((dir,name) -> name.toLowerCase().endsWith(".json"));

        if (files == null || files.length == 0){
            return 1;
        }

        return Arrays.stream(files)
                .map(file -> file.getName().replace(".json",""))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0)+1;
    }
}
