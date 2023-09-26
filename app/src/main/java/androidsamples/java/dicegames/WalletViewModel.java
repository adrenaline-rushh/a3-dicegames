package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {
  Die die;
  int balance, currDieValue, winDieValue, incrementAmount;
  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    currDieValue = 0;
    setBalance(0);
    setIncrement(5);
    setWinValue(6);
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    return balance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    balance = amount;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    try {
      die.roll();
    }
    catch (NullPointerException e) {
      throw new IllegalStateException("Die was not initialised");
    }
    currDieValue = die.value();

    if(currDieValue == winDieValue) {
      balance += incrementAmount;
    }
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    return currDieValue;
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    incrementAmount = increment;
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    winDieValue = winValue;
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    die = d;
  }
}
