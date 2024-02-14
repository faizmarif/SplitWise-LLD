import java.util.Scanner;
import models.User;
import services.impl.SplitwiseApplication;
import services.interfaces.ApplicationService;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    User user1 = new User("kasjn", "dcdkv", "sknvjnskj", "u1");
    User user2 = new User("kasjn", "dcdkv", "sknvjnskj", "u2");
    User user3 = new User("kasjn", "dcdkv", "sknvjnskj", "u3");
    User user4 = new User("kasjn", "dcdkv", "sknvjnskj", "u4");
    ApplicationService app = new SplitwiseApplication(user1, user2, user3, user4);
    while(true) {
      String input = scan.nextLine();
      if(input.equalsIgnoreCase("EXIT")) break;
      app.processInput(input);
    }
  }

}
