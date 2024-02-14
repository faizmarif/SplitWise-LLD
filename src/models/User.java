package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 11:25
public class User {
  private String name;
  private String email;
  private String phone;
  private final String userId;
  private Map<String, Double> userBalanceMap; // list of users who ows this user

  public User(String name, String email, String phone, String userId) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.userId = userId;
    this.userBalanceMap = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUserId() {
    return userId;
  }

  public Map<String, Double> getUserBalanceMap() {
    return userBalanceMap;
  }

  public void setUserBalanceMap(Map<String, Double> userBalanceMap) {
    this.userBalanceMap = userBalanceMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return userId.equals(user.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}
