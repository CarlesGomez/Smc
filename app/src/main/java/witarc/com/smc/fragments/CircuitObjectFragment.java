package witarc.com.smc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import witarc.com.smc.Circuit;
import witarc.com.smc.CircuitRow;
import witarc.com.smc.R;
import witarc.com.smc.adapters.CircuitListAdapter;

/**
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
public class CircuitObjectFragment extends Fragment {

    //CARREGAR UN LISTVIEW AMB LES DADES DEL CIRCUIT.

    public static final String ARG_OBJECT = "object";
    private ListView lvCircuit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circuit_list_fragment, container, false);
        Bundle args = getArguments();
        Serializable s = args.getSerializable("circuit");
        Circuit c = (Circuit)s;

        lvCircuit = (ListView)rootView.findViewById(R.id.circuitListView);
        CircuitListAdapter cla = new CircuitListAdapter(getActivity().getApplicationContext(), c);
        lvCircuit.setAdapter(cla);

        return rootView;
    }
}