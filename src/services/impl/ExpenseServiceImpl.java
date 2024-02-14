package services.impl;

import java.util.List;
import java.util.Map;
import models.Application;
import services.interfaces.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService {

  private Application application;

  public ExpenseServiceImpl(Application application) {
    this.application = application;
  }

  @Override
  public void distributeAmountEqually(String userIdWhoPaid, List<String> userIdsInDebt,
      double amount) {
    double splittedAmount = amount / userIdsInDebt.size();
    Map<String, Double> paidUserBalanceMap = application.getUserIdMap().get(userIdWhoPaid)
        .getUserBalanceMap();
    for (String id : userIdsInDebt) {
      paidUserBalanceMap.put(id, paidUserBalanceMap.getOrDefault(id, 0D) + splittedAmount);
      Map<String, Double> debtUserBalanceMap = application.getUserIdMap().get(id).getUserBalanceMap();
      debtUserBalanceMap.put(userIdWhoPaid, debtUserBalanceMap.getOrDefault(userIdWhoPaid, 0D) - splittedAmount);
    }
  }

  @Override
  public void distributeAmountExactly(String userIdWhoPaid, List<String> userIdsInDebt,
      List<Double> exactAmountsToSplit, double amount) {
    double totalSum = 0;
    for(double amt : exactAmountsToSplit) {
      totalSum += amt;
    }
    if(totalSum != amount) {
      return;
    }
    Map<String, Double> paidUserBalanceMap = application.getUserIdMap().get(userIdWhoPaid)
        .getUserBalanceMap();
    for (int i = 0; i < userIdsInDebt.size(); i++) {
      paidUserBalanceMap.put(userIdsInDebt.get(i), paidUserBalanceMap.getOrDefault(userIdsInDebt.get(i), 0D) + exactAmountsToSplit.get(i));
      Map<String, Double> debtUserBalanceMap = application.getUserIdMap().get(userIdsInDebt.get(i)).getUserBalanceMap();
      debtUserBalanceMap.put(userIdWhoPaid, debtUserBalanceMap.getOrDefault(userIdWhoPaid, 0D) - exactAmountsToSplit.get(i));
    }
  }

  @Override
  public void distributeAmountPercentWise(String userIdWhoPaid, List<String> userIdsInDebt,
      List<Double> amountSplitPercentage, double amount) {
    double totalPercent = 0;
    for(double per : amountSplitPercentage) {
      totalPercent += per;
    }
    if(totalPercent != 100D) {
      return;
    }
    Map<String, Double> paidUserBalanceMap = application.getUserIdMap().get(userIdWhoPaid)
        .getUserBalanceMap();
    for (int i = 0; i < userIdsInDebt.size(); i++) {
      paidUserBalanceMap.put(userIdsInDebt.get(i), paidUserBalanceMap.getOrDefault(userIdsInDebt.get(i), 0D) + amountSplitPercentage.get(i) * amount / 100);
      Map<String, Double> debtUserBalanceMap = application.getUserIdMap().get(userIdsInDebt.get(i)).getUserBalanceMap();
      debtUserBalanceMap.put(userIdWhoPaid, debtUserBalanceMap.getOrDefault(userIdWhoPaid, 0D) - amountSplitPercentage.get(i) * amount / 100);
    }
  }
}
