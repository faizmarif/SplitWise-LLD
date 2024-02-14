package services.impl;

import java.util.ArrayList;
import java.util.List;
import models.Application;
import models.User;
import services.enums.ExpenseType;
import services.enums.InputType;
import services.interfaces.ApplicationService;

public class SplitwiseApplication implements ApplicationService {

  private Application application;
  private ExpenseServiceImpl expenseService;

  public SplitwiseApplication() {
    this.application = new Application();
    this.expenseService = new ExpenseServiceImpl(this.application);
  }

  public SplitwiseApplication(User... users) {
    this.application = new Application(users);
    this.expenseService = new ExpenseServiceImpl(this.application);
  }


  @Override
  public void showBalanceForAllUser() {
    boolean noBalance = true;
    for (String userId : application.getUserIdMap().keySet()) {
      User user = application.getUserIdMap().get(userId);
      for (String userInDept : user.getUserBalanceMap().keySet()) {
        double amountOwes = user.getUserBalanceMap().get(userInDept);
        if(amountOwes > 0) {
          System.out.println(application.getUserIdMap().get(userInDept).getUserId() + " owes " + userId
              + " " + amountOwes);
          noBalance = false;
        }
      }
    }
    if (noBalance) {
      System.out.println("No balance");
    }
  }

  @Override
  public void showBalanceForSingleUser(String userId) {
    boolean noBalance = true;
    User user = application.getUserIdMap().get(userId);
    for (String userInDept : user.getUserBalanceMap().keySet()) {
      double amountOwes = user.getUserBalanceMap().get(userInDept);
      if(amountOwes < 0) {
        System.out.println(userId + " owes " + application.getUserIdMap().get(userInDept).getUserId()
            + " " + amountOwes*-1);
        noBalance = false;
      }
      else if(amountOwes > 0) {
        System.out.println(application.getUserIdMap().get(userInDept).getUserId() + " owes " + userId
            + " " + amountOwes);
        noBalance = false;
      }
    }
    if(noBalance) {
      System.out.println("No balance");
    }
  }

  @Override
  public void processInput(String input) {
    try {
      String[] params = input.split(" ");
      if (params.length == 0) {
        System.out.println("Invalid Input");
      } else if (params.length == 1 && params[0].equalsIgnoreCase(InputType.SHOW.name())) {
        showBalanceForAllUser();
      } else if (params.length > 1 && params[0].equalsIgnoreCase(InputType.SHOW.name())) {
        showBalanceForSingleUser(params[1]);
      } else if (params[0].equalsIgnoreCase(InputType.EXPENSE.name())) {
        int index = 1;
        String userId = params[index++];
        double amount = Double.parseDouble(params[index++]);
        int numUsers = Integer.parseInt(params[index++]);
        List<String> userIdsInDept = new ArrayList<>();
        for (int i = 0; i < numUsers; i++) {
          userIdsInDept.add(params[index++]);
        }
        String distributionType = params[index++];
        if (distributionType.equalsIgnoreCase(ExpenseType.EQUAL.name())) {
          expenseService.distributeAmountEqually(userId, userIdsInDept, amount);
        } else if(distributionType.equalsIgnoreCase(ExpenseType.EXACT.name())) {
          List<Double> exactAmountToSplit = new ArrayList<>();
          for(int i=0; i<numUsers; i++) {
            exactAmountToSplit.add(Double.parseDouble(params[index++]));
          }
          expenseService.distributeAmountExactly(userId, userIdsInDept, exactAmountToSplit, amount);
        } else if(distributionType.equalsIgnoreCase(ExpenseType.PERCENT.name())) {
          List<Double> percentages = new ArrayList<>();
          for(int i=0; i<numUsers; i++) {
            percentages.add(Double.parseDouble(params[index++]));
            expenseService.distributeAmountPercentWise(userId, userIdsInDept, percentages, amount);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Exception while processing input : ");
      e.printStackTrace();
    }
  }
}
