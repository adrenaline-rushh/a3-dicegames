package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

public class WalletActivity extends AppCompatActivity {
  Button btnDie;
  TextView txtBalance;
  WalletViewModel vm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      setContentView(R.layout.activity_wallet_potrait);
    }
    else {
      setContentView(R.layout.activity_wallet_landscape);
    }

    vm = new ViewModelProvider(this).get(WalletViewModel.class);

    btnDie = findViewById(R.id.btn_die);
    txtBalance = findViewById(R.id.txt_balance);

    initWalletViewModel();
    updateUI();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    vm.setBalance(data.getExtras().getInt("balance"));
    Log.d("testingg", String.format("%d", vm.balance()));
    updateUI();
  }

  void initWalletViewModel() {
    vm.setDie(new Die6());
  }

  void updateUI() {
    btnDie.setText(String.format(Locale.ENGLISH, "%d", vm.dieValue()));
    txtBalance.setText(String.format(Locale.ENGLISH, "%s %d", getString(R.string.coins), vm.balance()));
  }

  public void onClickDie(View view) {
    vm.rollDie();
    updateUI();
    if (vm.dieValue() == 6) Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
  }

  public void onClickTwoOrMore(View view) {
    Intent twoOrMoreIntent = new Intent(this, TwoOrMoreActivity.class);
    twoOrMoreIntent.putExtra(getString(R.string.balance_key), vm.balance());

    startActivityForResult(twoOrMoreIntent, 1);
  }
}