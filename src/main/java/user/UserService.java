package user;

import static java.lang.Integer.valueOf;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

  private final UserHandler userHandler;
  private static final String BASE_DIR = "src/main/java/data/users";
  private static User loginUser;

  public UserService(UserHandler userHandler) {
    this.userHandler = userHandler;
  }

  public void signup(String loginId, String password, String nickname) {
    if (isLoginIdExists(loginId)) {
      throw new IllegalStateException("이미 존재하는 login_id입니다: " + loginId);
    }

    int newUserId = getNextUserId();
    User newUser = new User(newUserId, loginId, password, nickname);

    String filePath = buildUserFilePath(String.valueOf(newUserId));
    userHandler.write(filePath, newUser);
  }

  private int getNextUserId() {
    Path dir = Paths.get(BASE_DIR);
    if (!Files.exists(dir)) {
      return 1;
    }

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      int maxId = 0;
      for (Path p : stream) {
        if (Files.isDirectory(p)) {
          try {
            int id = Integer.parseInt(p.getFileName().toString());
            if (id > maxId) {
              maxId = id;
            }
          } catch (NumberFormatException e) {
            // Ignore directories with non-numeric names
          }
        }
      }
      return maxId + 1;
    } catch (IOException e) {
      throw new RuntimeException("Failed to scan user directory", e);
    }
  }

  public boolean isLoginIdExists(String loginId) {
    return getUserByLoginId(loginId) != null;
  }

  public User getUserByLoginId(String loginId) {
    Path dir = Paths.get(BASE_DIR);
    if (!Files.exists(dir)) {
      return null;
    }

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path p : stream) {
        if (Files.isDirectory(p)) {
          Path userFile = p.resolve("user_info.json");
          if (Files.exists(userFile)) {
            User u = userHandler.read(userFile.toString(), User.class);
            if (u != null && loginId.equals(u.getLoginId())) {
              return u;
            }
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to scan user directory", e);
    }
    return null;
  }

  public User login(String loginId, String password) {
    User user = getUserByLoginId(loginId);
    if (user == null) {
      throw new IllegalStateException("존재하지 않는 login_id입니다: " + loginId);
    }

    if (!user.getPassword().equals(password)) {
      throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
    }

    return user;
  }

  public User getLoginUser() {
    return loginUser;
  }

  private String buildUserFilePath(String userId) {
    return BASE_DIR + "/" + userId + "/user_info.json";
  }
}