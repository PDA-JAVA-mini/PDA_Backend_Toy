package user;

import java.util.List;

public class User {
  private int id;
  private String loginId;
  private String password;
  private String nickname;
  private List<Integer> tripsIds;

  public User(){}

  public User(int id, String loginId, String password, String nickname) {
    this.id = id;
    this.loginId = loginId;
    this.password = password;
    this.nickname = nickname;
  }

  public User(int id, String loginId, String password, String nickname, List<Integer> tripsIds) {
    this.id = id;
    this.loginId = loginId;
    this.password = password;
    this.nickname = nickname;
    this.tripsIds = tripsIds;
  }

  public int getId() { return id; }
  public String getLoginId() { return loginId; }
  public String getPassword() { return password; }
  public String getNickname() { return nickname; }
  public List<Integer> getTripIds() { return tripsIds; }

  public void setId(int id) { this.id = id; }
  public void setLoginId(String login_id) { this.loginId = login_id; }
  public void setPassword(String password) { this.password = password; }
  public void setNickname(String nickname) { this.nickname = nickname; }
  public void setTripIds(List<Integer> trips_ids) { this.tripsIds = trips_ids; }
}
