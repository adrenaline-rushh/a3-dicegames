package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class TwoOrMoreActivity extends AppCompatActivity {

  TextView txtBalance;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more_potrait);

    int balance = getIntent().getExtras().getInt(getString(R.string.balance_key));

    txtBalance = findViewById(R.id.txt_balance);
    txtBalance.setText(String.format(Locale.ENGLISH, "%s %d", getString(R.string.coins), balance));
  }
}