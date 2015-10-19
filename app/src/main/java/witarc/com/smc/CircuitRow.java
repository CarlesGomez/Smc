package witarc.com.smc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rubengrafgarcia on 9/7/15.
 */
public class CircuitRow {

    //region Atributs
    private String motoStateId = "";
    private String motoId = "";
    private int on = 0;
    private int off = 0;
    private int in_traction = 0;
    private int no_traction = 0;
    private String vibracy = "";
    private String battery = "";
    private String velocity = "";
    private String acceleration = "";
    private String brake = "";
    private String lastChange = "";
    //endregion Atributs

    //region Constructor
    public CircuitRow(String motoStateId, String motoId, int on, int off, int in_traction, int no_traction, String vibracy, String battery, String velocity, String acceleration, String brake, String lastChange) {
        this.motoStateId = motoStateId;
        this.motoId = motoId;
        this.on = on;
        this.off = off;
        this.in_traction = in_traction;
        this.no_traction = no_traction;
        this.vibracy = vibracy;
        this.battery = battery;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.brake = brake;
        this.lastChange = lastChange;
    }
    //endregion Constructor

    //region getters/setters
    public String getMotoStateId() {
        return motoStateId;
    }

    public void setMotoStateId(String motoStateId) {
        this.motoStateId = motoStateId;
    }

    public String getMotoId() {
        return motoId;
    }

    public void setMotoId(String motoId) {
        this.motoId = motoId;
    }

    public int getOn() {
        return on;
    }

    public void setOn(int on) {
        this.on = on;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }

    public int getIn_traction() {
        return in_traction;
    }

    public void setIn_traction(int in_traction) {
        this.in_traction = in_traction;
    }

    public int getNo_traction() {
        return no_traction;
    }

    public void setNo_traction(int no_traction) {
        this.no_traction = no_traction;
    }

    public String getVibracy() {
        return vibracy;
    }

    public void setVibracy(String vibracy) {
        this.vibracy = vibracy;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getBrake() {
        return brake;
    }

    public void setBrake(String brake) {
        this.brake = brake;
    }

    public String getLast_change() {
        return lastChange;
    }

    public void setLast_change(String lastChange) {
        this.lastChange = lastChange;
    }
    //endregion
}
