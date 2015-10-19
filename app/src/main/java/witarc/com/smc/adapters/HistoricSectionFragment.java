package witarc.com.smc.adapters;

/**
 * Created by rubengrafgarcia on 3/7/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import witarc.com.smc.HistoricActivity;
import witarc.com.smc.R;

/**
 * A fragment that launches other parts of the demo application.
 */



public class HistoricSectionFragment extends Fragment {

    private SimpleDateFormat sdf = new SimpleDateFormat();
    DatePicker dp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_time, container, false);

        dp = (DatePicker)rootView.findViewById(R.id.datePicker);
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Demonstration of a collection-browsing activity.
        rootView.findViewById(R.id.search_circuit_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String calendarDate = sdf.format(new Date(dp.getYear() - 1900, dp.getMonth(), dp.getDayOfMonth()));
                        Intent intent = new Intent(getActivity(), HistoricActivity.class);
                        intent.putExtra("date", calendarDate);
                        startActivity(intent); //On change date picker.
                    }
                });

        return rootView;
    }
}

