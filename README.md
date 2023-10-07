[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/yBUeQ8Fo)
# A3 : Dice Games
The Dice Games app is a game where the user rolls a die and earns 5 coins everytime he rolls a 6.
The game can be extended to a gambling game called 'Two Or More' which goes as follows:

1. The user rolls 4 dice.
2. In each roll, the user bets on getting 2 alike dice, 3 alike, or 4 alike.
3. The user also sets a certain wager.
4. If the user bets on 2 alike dice and gets 2 or more alike dice, he wins 2 times wager number of coins. However, if less than 2 dice are alike, he will lose the same number of coins.
5. A similar logic holds for when the user chooses 3 alike or 4 alike dice.

At any point, the user can go back to the Dice Games game with the same number of coins.

For example:
A user starts playing Dice Games where he rolls a die certain number of times and earns some coins, say 15.
At this point, he decides to switch to the Two Or More game , in which he loses 4 coins.
He can then go back to Dice Games with 15-4=11 coins to earn more coins.

## Collaborators
Yash Yelmame (2020A7PS1224G), Email: f20201224@goa.bits-pilani.ac.in

Aaditya Raghavan (2020A3PS1251G), Email: f20201251@goa.bits-pilani.ac.in

## About the Assignment

### WalletActivity 
The following functions were used in the implementation of `WalletActivity`:
1. `updateUI`: This function displays the current die value and the updated number of coins.
2. `initWalletViewModel`: This function initializes a new die.
3. `onClickDie` : It is the function called when the button is pressed to roll the die. The die is rolled, the UI is updated, and if a six is rolled then a congratulatory message is displayed in the form of a toast.
4. `onClickTwoOrMore`: It is the function called when the button to switch to the Two Or More game is clicked. It creates an intent to execute `TwoOrMoreActivity`, adds the coins balance to the intent and executes `startActivityForResult` to execute the activity.
5. `onActivityResult`: This is the function called when `TwoOrMoreActivity` exits. It retrieves the new coins balance from the intent passed and updates the UI.

### TwoOrMoreViewModel
In addition to several getter and setter functions, the following functions were used in the implementation of `TwoOrMoreViewModel`:
1. `isValidWager`: This function checks if the wager entered by the user is valid or not. The wager is valid if the number of coins bet on (=wager*2,3 or 4) is less than or equal to the number of coins in balance.
2. `setGameType`: This function sets the game type(2 alike, 3 alike or 4 alike).
3. `play`: This function simulates the actual game. Before the game is played, we first check if the wager is valid or not using `isValidWager` and whether the game type is set. If either condition is not met, an `IllegalStateException` is thrown.

### TwoOrMoreActivity
The following functions were used in the implementation of `TwoOrMoreActivity`:
1. `goBack`: It is the function called to go back to `WalletActivity`. An intent is created, the coins balance is added to it, the result code is passed and the activity is closed using the `finish` function.
2. `onBackPressed`: It is the function called when the back button of the device is pressed. It calls `goBack`.
3. `backClicked`: It is the function called when the back button of the app is pressed. It calls `goBack`.
4. `updateUI`: This function updates the UI, displaying the values of the 4 dice and the updated value of the coins balance.
5. `goButtonClick`: This function is called when the button to play the game is clicked. It first sets the game type from the radio button selected by the user and then calls the `play` function of `TwoOrMoreViewModel`. An `IllegalStateException` is thrown if no radio button is chosen, and a `NumberFormatException` is thrown is the wager entered is invalid.

### Info 
The info is a third activity created to display information related to the game. It can be called from the `TwoOrMoreActivity`. A back button is provided to return to the same activity.

## Testing
A total of 5 Unit Tests and 5 Instrumented Tests were written in addition to the test cases provided. Initially, a few issues were faced while trying to access values inside an activity.
Thanks to Pranav Srinivas, we figured out how to access the current activity object which is being displayed on the screen.




### Pair Programming

Pair programming rating - 4/5
We did Pair Programming to quite a good extent, Aaditya reviewed and helped in brainstorming on how to proceed with the assignment meanwhile Yash coded the logic. Aaditya also made UIs for landscape orientation.
This helped us finish the assignment quite efficiently.

### Assignment Difficulty

Assignment difficulty: 8/10
This assignment took approximately 8-9 hours to complete. We found that there were a lot of things needed to be kept in mind while doing this assignment
for example, we had to take care of data persistance and create landscape and potrait for each and every activity we created. Along with intent handling all
this logic was a bit difficult. Also, writing instrumented tests was pretty difficult to write and make it useful.
