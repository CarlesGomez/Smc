package witarc.com.smc;

/**
 * Created by rubengrafgarcia on 3/7/15.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import witarc.com.smc.adapters.CircuitPagerAdapter;

public class HistoricActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    CircuitPagerAdapter mDemoCollectionPagerAdapter;
    private ArrayList<Circuit> circuitsArray = new ArrayList<Circuit>();

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_collection);

        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");

        //Number of circuits.
        String selectNumberOfCircuits = "SELECT * FROM motos_state WHERE DATE(last_change) = '" + date + "' AND no_traction=1;";
        Cursor cNumberOfCircuits = Main.smcDB.rawQuery(selectNumberOfCircuits, null);

        //All data.
        String selectRows = "SELECT * FROM motos_state WHERE DATE(last_change) = '" + date + "' ORDER BY last_change ASC;";
        Cursor cSelectRows = Main.smcDB.rawQuery(selectRows, null);

        if(cNumberOfCircuits.moveToFirst())
        {
            ArrayList<CircuitRow> rowsCircuit = new ArrayList<CircuitRow>();
            while(cSelectRows.moveToNext())
            {
                String stateId = cSelectRows.getString(0);
                String motoId = cSelectRows.getString(1);
                Integer stateOn = cSelectRows.getInt(2);
                Integer stateOff = cSelectRows.getInt(3);
                Integer stateIntraction = cSelectRows.getInt(4);
                Integer stateNoTraction = cSelectRows.getInt(5);
                String vibracy = cSelectRows.getString(8);
                String battery = cSelectRows.getString(9);
                String velocity = cSelectRows.getString(10);
                String acceleration = cSelectRows.getString(11);
                String brake = cSelectRows.getString(12);
                String lastChange = cSelectRows.getString(13);
                String completeHour = lastChange.split(" ")[1];
                String finalLastChange = completeHour.split(":")[0] + ":" +  completeHour.split(":")[1];

                CircuitRow cr = new CircuitRow(stateId, motoId, stateOn, stateOff, stateIntraction, stateNoTraction,
                         vibracy, battery, velocity, acceleration, brake, finalLastChange);

                if(stateIntraction !=0 || stateNoTraction !=0)
                {
                    rowsCircuit.add(cr);
                    if(stateNoTraction != 0)
                    {
                        circuitsArray.add(new Circuit(rowsCircuit));
                        rowsCircuit = new ArrayList<CircuitRow>();
                    }
                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "There are no circuits for this day!", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        //
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.


        mDemoCollectionPagerAdapter = new CircuitPagerAdapter(getSupportFragmentManager(), circuitsArray);

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, Main.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
