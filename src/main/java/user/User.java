package user;

public class User {
  private String id;
  private String loginId;
  private String password;
  private String nickname;

  public User() {}

  public User(String id, String loginId, String password, String nickname) {
    this.id = id;
    this.loginId = loginId;
    this.password = password;
    this.nickname = nickname;
  }

  public String getId() { return id; }
  public String getLoginId() { return loginId; }
  public String getPassword() { return password; }
  public String getNickname() { return nickname; }

  public void setId(String id) { this.id = id; }
  public void setLoginId(String loginId) { this.loginId = loginId; }
  public void setPassword(String password) { this.password = password; }
  public void setNickname(String nickname) { this.nickname = nickname; }
}
