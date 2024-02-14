package services.interfaces;

import java.util.List;
import services.enums.ExpenseType;

public interface ExpenseService {
  void distributeAmountEqually(String userIdWhoPaid, List<String> userIdsInDebt, double amount);

  void distributeAmountExactly(String userIdWhoPaid, List<String> userIdsInDebt, List<Double> exactAmountsToSplit, double amount);

  void distributeAmountPercentWise(String userIdWhoPaid, List<String> userIdsInDebt, List<Double> amountSplitPercentage, double amount);
}
