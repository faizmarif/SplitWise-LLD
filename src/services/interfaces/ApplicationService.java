package services.interfaces;

import models.Application;

public interface ApplicationService {
  void showBalanceForAllUser();

  void showBalanceForSingleUser(String userId);

  void processInput(String input);
}
