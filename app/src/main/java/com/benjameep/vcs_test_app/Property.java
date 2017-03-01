package com.benjameep.vcs_test_app;

/**
 * Created by meep on 2/27/2017.
 */

public class Property {

    private int values;


    private int[] brothers;

    private int dev;

    public Property(int propID) {
    }

    public int[] getBrothers() {
        return brothers;
    }

    public int getDev() {
        return dev;
    }

    public void setDev(int dev) {
        this.dev = dev;
    }


    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }


    public void upgrade(){
        dev++;
    }
    public void downgrade(){
        dev--;
    }

}
