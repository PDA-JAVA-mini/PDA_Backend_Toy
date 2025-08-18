package user;

import java.util.ArrayList;
import java.util.List;

public class User {
  private int id;
  private String loginId;
  private String password;
  private String nickname;
  private List<Integer> tripsIds;

  public User(){
    this.id = 0;
    this.loginId = null;
    this.password = null;
    this.nickname = null;
    this.tripsIds = new ArrayList<>();
  }

  public User(int id, String loginId, String password, String nickname) {
    this.id = id;
    this.loginId = loginId;
    this.password = password;
    this.nickname = nickname;
    this.tripsIds = new ArrayList<>();
  }


  public int getId() { return id; }
  public String getLoginId() { return loginId; }
  public String getPassword() { return password; }
  public String getNickname() { return nickname; }
  public List<Integer> getTripIds() { return tripsIds; }
}
