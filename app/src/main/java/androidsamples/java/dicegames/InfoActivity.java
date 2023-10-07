package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;

import android.os.Bundle;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_info_potrait);
        }
        else {
            setContentView(R.layout.activity_info_landscape);
        }
    }

    public void backClicked(View view) {
        finish();
    }
}