package androidsamples.java.dicegames;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {

  int balance, wager;
  GameType gameType;
  List<Integer> diceValues;
  Die die;
  GameResult gameResult;

  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    diceValues = new ArrayList<Integer>(4);
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
   * @param balance the given amount
   */
  public void setBalance(int balance) {
    this.balance = balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    return gameType;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    this.gameType = gameType;
  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    return wager;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
    this.wager = wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager() {
    if(wager <= 0) {
      return false;
    }

    switch(gameType) {
      case TWO_ALIKE:
        if(balance < 2 * wager)
          return false;
        break;
      case THREE_ALIKE:
        if(balance < 3 * wager)
          return false;
        break;
      case FOUR_ALIKE:
        if(balance < 4 * wager)
          return false;
        break;
      default:
        return false;
    }

    return true;
  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    return diceValues;
  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    die = d;
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {
    if(isValidWager()) {
      die.roll();
      diceValues.set(0, die.value());
      die.roll();
      diceValues.set(1, die.value());
      die.roll();
      diceValues.set(2, die.value());
      die.roll();
      diceValues.set(3, die.value());
    }
    else {
      if(gameType == GameType.NONE)
        throw new IllegalStateException("Game type is not set.");
      else
        throw new IllegalStateException("Wager set is invalid.");
    }
  }
}
