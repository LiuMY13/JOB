package edu.gatech.seclass.jobcompare6300;

import static android.service.autofill.Validators.not;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;
import static java.util.regex.Pattern.matches;
import static junit.framework.TestCase.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private JobDatabase database;
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.gatech.seclass.jobcompare6300", appContext.getPackageName());
    }

    private void replaceTextHelper(int viewId, String stringToBeSet) {
        // to reduce flaky test, https://stackoverflow.com/a/53430379/1326678
        onView(withId(viewId)).perform(clearText(), replaceText(stringToBeSet), closeSoftKeyboard());
    }

    @Test
    public void TestCase1() {
        //Check Main Menu Content display
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).check(matches(isDisplayed()));
        Espresso.onView(withText("Job Offers")).check(matches(isDisplayed()));
        Espresso.onView(withText("Comparison Settings")).check(matches(isDisplayed()));
        Espresso.onView(withText("Compare Offers")).check(matches(isDisplayed()));
        //Espresso.onView(withText("Wrong test")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase2() {
        //Check Current Job button and click on edit and save to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());

        // check that current job is saved
        Espresso.onView(withText("Current Job")).perform(click());
        assertEquals(database.getCurrentJob().getJob_title(), "Dead Beef");
        database.clearDatabase();
    }
    @Test
    public void TestCase3() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());

        // check that current job is not saved
        Espresso.onView(withText("Current Job")).perform(click());
        assertNull(database.getCurrentJob());
    }
    @Test
    public void TestCase4() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        replaceTextHelper(R.id.Title_value, "Cafe Babe");
        replaceTextHelper(R.id.Company_value, "ACME Co.");
        replaceTextHelper(R.id.Location_value, "Mumbai");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
//        Espresso.onView(withText("Edit")).perform(ViewActions.click());
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());

        // check only 1 job offer in database
        assertEquals(database.getAllJobs().size(), 1);
    }
    @Test
    public void TestCase5() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());

        // assert reached main menu
        Espresso.onView(withText("Current Job")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase6() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Ice Cream Cake");
        replaceTextHelper(R.id.Company_value, "Twice");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Compare Against Current")).perform(click());
        // Espresso.onView(withText("Back")).perform(ViewActions.click());

        // check details on table of comparison
        onView(withId(R.id.compare_job_table_company1)).check(matches(withText("ACME*")));
        onView(withId(R.id.compare_job_table_company2)).check(matches(withText("Twice")));
    }

    @Test
    public void TestCase7() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Comparison Settings")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.editTextYearlySalary, "3");
        replaceTextHelper(R.id.editTextYearlyBonus, "2");
        replaceTextHelper(R.id.editTextStockOptions, "1");
        replaceTextHelper(R.id.editTextHomeBuyingProgramFund, "1");
        replaceTextHelper(R.id.editTextPersonalChoiceHolidays, "3");
        replaceTextHelper(R.id.editTextMonthlyInternetStipend, "2");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Return to Main Menu")).perform(click());

        // check weights are saved
        assertEquals(database.getComparisonSettings().getPersonalChoiceHolidaysWeight(), 3);
    }
    @Test
    public void TestCase8() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Compare Offers")).perform(click());
        Espresso.onView(withText("Current Job")).check(matches(isDisplayed()));
        Espresso.onView(withText("Job Offers")).check(matches(isDisplayed()));
        Espresso.onView(withText("Comparison Settings")).check(matches(isDisplayed()));
        Espresso.onView(withText("Compare Offers")).check(matches(isDisplayed()));
    }
    @Test
    public void TestCase9() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "200");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Ice Cream Cake");
        replaceTextHelper(R.id.Company_value, "Twice");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "20000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "20");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "10");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "10");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());
        Espresso.onView(withText("Compare Offers")).perform(click());

        // check ranked by job score
        assertTrue(((Job) database.getAllJobs().get(1)).getJob_score() > ((Job) database.getAllJobs().get(0)).getJob_score());
    }
    @Test
    public void TestCase10() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "200");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Ice Cream Cake");
        replaceTextHelper(R.id.Company_value, "Twice");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "50000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "0");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "10");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());
        Espresso.onView(withText("Compare Offers")).perform(click());
        Espresso.// 找到 ListView 中的第一个 list_view_item
                onData(anything()).inAdapterView(withId(R.id.lv_text_view))
                .atPosition(0)
                // 通过子控件 id 找到 CheckBox 并选中
                .onChildView(withId(R.id.rb_check_button))
                .perform(click());
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.lv_text_view))
                .atPosition(1)
                .onChildView(withId(R.id.rb_check_button))
                .perform(click());
        Espresso.onView(withText("COMPARE")).perform(click());
        onView(withId(R.id.compare_job_table_title1)).check(matches(withText("Dead Beef")));
        onView(withId(R.id.compare_job_table_title2)).check(matches(withText("Ice Cream Cake")));

        // check ranked by job score
        //assertTrue(((Job) database.getAllJobs().get(0)).getJob_score() > ((Job) database.getAllJobs().get(1)).getJob_score());
    }
    @Test
    public void TestCase11() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "200");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());

        // check ranked by job score
        assertEquals(((Job) database.getAllJobs().get(0)).getJob_score(), 13.0  );
    }

    @Test
    public void TestCase12() {
        //Check Current Job button and click on edit and cancel/exit to return to main menu
        database = new JobDatabase(ApplicationProvider.getApplicationContext());
        database.clearDatabase();
        Espresso.onView(withText("Current Job")).perform(click());
        Espresso.onView(withText("Proceed")).perform(click());
        Espresso.onView(withText("Edit")).perform(click());
        replaceTextHelper(R.id.Title_value, "Dead Beef");
        replaceTextHelper(R.id.Company_value, "ACME");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "15000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "200");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "18");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "1");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Job Offers")).perform(click());
        replaceTextHelper(R.id.Title_value, "Ice Cream Cake");
        replaceTextHelper(R.id.Company_value, "Twice");
        replaceTextHelper(R.id.Location_value, "Delhi");
        replaceTextHelper(R.id.YearlySalary_value, "20000");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "12");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "10");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        replaceTextHelper(R.id.Title_value, "Security");
        replaceTextHelper(R.id.Company_value, "McDonalds");
        replaceTextHelper(R.id.Location_value, "New York");
        replaceTextHelper(R.id.YearlySalary_value, "100");
        replaceTextHelper(R.id.YearlyBonus_value, "3");
        replaceTextHelper(R.id.StockOptions_value, "2");
        replaceTextHelper(R.id.HomeBuyProgFund_value, "2");
        replaceTextHelper(R.id.PersonalChoiceHolidays_value, "6");
        replaceTextHelper(R.id.MonthlyInternetStipend_value, "5");
        Espresso.onView(withText("Save")).perform(click());
        Espresso.onView(withText("Edit Another Offer")).perform(click());
        Espresso.onView(withText("Cancel")).perform(click());
        Espresso.onView(withText("Yes, exit.")).perform(click());
        Espresso.onView(withText("Compare Offers")).perform(click());

        // check ranked by job score
        assertTrue((((Job) database.getAllJobs().get(1)).getJob_score() > ((Job) database.getAllJobs().get(0)).getJob_score()) &&
                (((Job) database.getAllJobs().get(1)).getJob_score() > ((Job) database.getAllJobs().get(2)).getJob_score()));
    }
}