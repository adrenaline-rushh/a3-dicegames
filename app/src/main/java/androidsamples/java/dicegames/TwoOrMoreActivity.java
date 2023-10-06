package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TwoOrMoreActivity extends AppCompatActivity {

  TextView txtBalance;
  EditText wagerField;
  RadioGroup gameOptionsRB;
  Button dieBtn1, dieBtn2, dieBtn3, dieBtn4;
  TwoOrMoreViewModel twoOrMoreViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more_potrait);

    int balance = getIntent().getExtras().getInt(getString(R.string.balance_key));
    twoOrMoreViewModel = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);
    twoOrMoreViewModel.setBalance(balance);

    txtBalance = findViewById(R.id.txt_balance);
    wagerField = findViewById(R.id.txt_wager);
    gameOptionsRB = findViewById(R.id.rg_num_alikes);
    dieBtn1 = findViewById(R.id.btn_die_1);
    dieBtn2 = findViewById(R.id.btn_die_2);
    dieBtn3 = findViewById(R.id.btn_die_3);
    dieBtn4 = findViewById(R.id.btn_die_4);

    twoOrMoreViewModel.addDie(new Die6());
    twoOrMoreViewModel.addDie(new Die6());
    twoOrMoreViewModel.addDie(new Die6());
    twoOrMoreViewModel.addDie(new Die6());

    updateUI();
  }

  @Override
  public void onBackPressed() {
    goBack();
  }

  private void goBack() {
    Intent returnIntent = new Intent();
    returnIntent.putExtra("balance", twoOrMoreViewModel.balance());
    setResult(1, returnIntent);
    finish();
  }

  public void backClicked(View view) {
    goBack();
  }

  private void updateUI() {
    txtBalance.setText(String.format("%s %d", getString(R.string.coins), twoOrMoreViewModel.balance()));
    gameOptionsRB.clearCheck();
    wagerField.setText("");

    dieBtn1.setText(String.format("%d", twoOrMoreViewModel.diceValues().get(0)));
    dieBtn2.setText(String.format("%d", twoOrMoreViewModel.diceValues().get(1)));
    dieBtn3.setText(String.format("%d", twoOrMoreViewModel.diceValues().get(2)));
    dieBtn4.setText(String.format("%d", twoOrMoreViewModel.diceValues().get(3)));

    twoOrMoreViewModel.setWager(0);
    twoOrMoreViewModel.setGameType(GameType.NONE);
  }

  public void goButtonClick(View view) {
    try {
      int rbCheckedIndex = gameOptionsRB.getCheckedRadioButtonId();
      int multiplier = 0;
      switch (rbCheckedIndex) {
        case R.id.rb_2_alike: twoOrMoreViewModel.setGameType(GameType.TWO_ALIKE);
          multiplier = 2;
          break;
        case R.id.rb_3_alike: twoOrMoreViewModel.setGameType(GameType.THREE_ALIKE);
          multiplier = 3;
          break;
        case R.id.rb_4_alike: twoOrMoreViewModel.setGameType(GameType.FOUR_ALIKE);
          multiplier = 4;
          break;
        default:
          break;
      }

      int wager = Integer.parseInt(wagerField.getText().toString());
      twoOrMoreViewModel.setWager(wager);

      GameResult result = twoOrMoreViewModel.play();
      switch (result) {
        case WIN: twoOrMoreViewModel.setBalance(twoOrMoreViewModel.balance() + wager * multiplier);
          break;
        case LOSS: twoOrMoreViewModel.setBalance(twoOrMoreViewModel.balance() - wager * multiplier);
          break;
        default:
          break;
      }

      updateUI();
    }
    catch(IllegalStateException e) {
      Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    catch(NumberFormatException e) {
      Toast.makeText(this, R.string.illegal_wager, Toast.LENGTH_SHORT).show();
    }
  }
}