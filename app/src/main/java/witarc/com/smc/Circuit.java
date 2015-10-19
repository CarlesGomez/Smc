package witarc.com.smc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rubengrafgarcia on 10/7/15.
 */
public class Circuit implements Serializable {

    private ArrayList<CircuitRow> circuitRows;

    public Circuit(ArrayList<CircuitRow> circuitRows)
    {
        this.circuitRows = circuitRows;
    }

    public ArrayList<CircuitRow> getCircuitRows() {
        return circuitRows;
    }

    public void setCircuitRows(ArrayList<CircuitRow> circuitRows) {
        this.circuitRows = circuitRows;
    }
}
