package models;

import java.util.HashMap;
import java.util.Map;


public class Application {
  Map<String, User> userIdMap;


  public Application() {
    this.userIdMap = new HashMap<>();
  }

  public Application(User... users) {
    this.userIdMap = new HashMap<>();
    for(User user : users) {
      this.userIdMap.put(user.getUserId(), user);
    }
  }

  public Map<String, User> getUserIdMap() {
    return userIdMap;
  }

  public void setUserIdMap(Map<String, User> userIdMap) {
    this.userIdMap = userIdMap;
  }
}
