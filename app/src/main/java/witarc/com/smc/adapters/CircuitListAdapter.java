package witarc.com.smc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import witarc.com.smc.Circuit;
import witarc.com.smc.CircuitRow;
import witarc.com.smc.R;

/**
 * Created by carles on 09/07/15.
 */
public class CircuitListAdapter extends BaseAdapter {

    //region ATRIBUTS
    private Context context;
    private LayoutInflater mInflater;
    private Circuit circuit;
    //endregion

    //CONSTRUCTOR:
    public CircuitListAdapter(Context context, Circuit circuit){
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
        this.circuit = circuit;
    }

    //region EVENTS
    @Override
    public int getCount() {
        return circuit.getCircuitRows().size();
    }

    @Override
    public Object getItem(int position) {
        return circuit.getCircuitRows().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Container con;

        if(convertView==null)
        {
            con=new Container();
            convertView = mInflater.inflate(R.layout.circuit_list_item, parent, false);
            con.tvTime = (TextView)convertView.findViewById(R.id.tvTime);
            con.tvState = (TextView)convertView.findViewById(R.id.tvState);
            con.tvVibration = (TextView)convertView.findViewById(R.id.tvVibration);
            con.tvBattery = (TextView)convertView.findViewById(R.id.tvBattery);
            con.tvVelocity = (TextView)convertView.findViewById(R.id.tvVelocity);
            con.tvAcceleration = (TextView)convertView.findViewById(R.id.tvAcceleration);
            con.tvBrake = (TextView)convertView.findViewById(R.id.tvBrake);
            assert convertView != null;
            convertView.setTag(con);
        }
        else
        {
            con = (Container) convertView.getTag();
        }

        CircuitRow cr = circuit.getCircuitRows().get(position);

        con.tvTime.setText(cr.getLast_change());
        con.tvState.setText(cr.getNo_traction() + "");
        con.tvVibration.setText(cr.getVibracy());
        con.tvBattery.setText(cr.getBattery());
        con.tvVelocity.setText(cr.getVelocity());
        con.tvAcceleration.setText(cr.getAcceleration());
        con.tvBrake.setText(cr.getBrake());

        return convertView;
    }

    //Private class:
    private class Container {
        TextView tvTime, tvState, tvVibration, tvBattery, tvVelocity, tvAcceleration, tvBrake;
    }
    //endregion

}

