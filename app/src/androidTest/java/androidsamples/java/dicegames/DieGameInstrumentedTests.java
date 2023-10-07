package androidsamples.java.dicegames;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.widget.TextView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static androidx.test.runner.lifecycle.Stage.RESUMED;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Collection;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DieGameInstrumentedTests {
  private String currBalance;
  private int balance;

  public static Activity getCurrentActivity() {
    final Activity[] currentActivity = {null};
    getInstrumentation().runOnMainSync(new Runnable() {
      public void run() {
        Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(RESUMED);
        if (resumedActivities.iterator().hasNext()) {
          currentActivity[0] = (Activity) resumedActivities.iterator().next();
        }
      }
    });
    return currentActivity[0];
  }

  @Rule
  public ActivityScenarioRule<WalletActivity> walletActivityRule = new ActivityScenarioRule<>(WalletActivity.class);

  @Test
  public void checkSameBalanceWalletToTwoOrMore() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());

    walletActivityRule.getScenario().onActivity(activity -> {
      currBalance = ((TextView)activity.findViewById(R.id.txt_balance)).getText().toString();
    });

    onView(withId(R.id.btn_two_or_more)).perform(click());

    // Checks if balance is consistent in twoOrMoreActivity
    onView(withId(R.id.txt_balance)).check(matches(withText(currBalance)));
  }

  @Test
  public void checkPortraitLandscapeWalletActivity() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());

    WalletActivity walletActivity = (WalletActivity) getCurrentActivity();
    int initialBalance = walletActivity.vm.balance();

    walletActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    walletActivity = (WalletActivity) getCurrentActivity();

    assertThat(walletActivity.vm.balance(), is(initialBalance));
  }

  @Test
  public void checkSameBalanceTwoOrMoreToWallet() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.btn_two_or_more)).perform(click());

    TwoOrMoreActivity twoOrMoreActivity = (TwoOrMoreActivity) getCurrentActivity();

    onView(withId(R.id.rb_2_alike)).perform(click());
    onView(withId(R.id.txt_wager)).perform(clearText(), typeText("1"));
    onView(withId(R.id.btn_go)).perform(click());

    int resultBalance = twoOrMoreActivity.twoOrMoreViewModel.balance();

    onView(withId(R.id.btn_back)).perform(click());

    walletActivityRule.getScenario().onActivity(activity -> {
      balance = activity.vm.balance();
    });

    assertThat(resultBalance, is(balance));
  }

  @Test
  public void checkPortraitLandscapeTwoOrMoreActivity() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());

    onView(withId(R.id.btn_two_or_more)).perform(click());

    onView(withId(R.id.rb_2_alike)).perform(click());
    onView(withId(R.id.txt_wager)).perform(clearText(), typeText("1"));
    onView(withId(R.id.btn_go)).perform(click());

    TwoOrMoreActivity twoOrMoreActivity = (TwoOrMoreActivity) getCurrentActivity();

    int balance = twoOrMoreActivity.twoOrMoreViewModel.balance();

    twoOrMoreActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    twoOrMoreActivity = (TwoOrMoreActivity) getCurrentActivity();

    assertThat(twoOrMoreActivity.twoOrMoreViewModel.balance(), is(balance));
  }


  @Test
  public void checkInfoBalanceSame() {
    onView(withId(R.id.btn_two_or_more)).perform(click());

    TwoOrMoreActivity activity = (TwoOrMoreActivity) getCurrentActivity();
    int balance = activity.twoOrMoreViewModel.balance();

    onView(withId(R.id.btn_info)).perform(click());
    onView(withId(R.id.btn_back)).perform(click());

    activity = (TwoOrMoreActivity) getCurrentActivity();
    assertThat(activity.twoOrMoreViewModel.balance(), is(balance));
  }

  @Test
  public void checkPortraitLandscapeInfo() {
    onView(withId(R.id.btn_two_or_more)).perform(click());

    onView(withId(R.id.btn_info)).perform(click());

    InfoActivity infoActivity = (InfoActivity) getCurrentActivity();
    infoActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  }
}