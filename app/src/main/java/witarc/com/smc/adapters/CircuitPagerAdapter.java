package witarc.com.smc.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import witarc.com.smc.Circuit;
import witarc.com.smc.fragments.CircuitObjectFragment;

/**
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
 * representing an object in the collection.
 */
public class CircuitPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Circuit> circuitsArray = new ArrayList<Circuit>();

    public CircuitPagerAdapter(FragmentManager fm, ArrayList<Circuit> circuits) {
        super(fm);
        circuitsArray = circuits;
    }

    @Override
    public Fragment getItem(int i) {
        //Passar paràmetre el circuit corresponent per emplenar el listview.
        Fragment fragment = new CircuitObjectFragment();
        Bundle args = new Bundle();
        args.putInt(CircuitObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
        args.putSerializable("circuit", circuitsArray.get(i));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return circuitsArray.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Circuit " + (position + 1);
    }
}