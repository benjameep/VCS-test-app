package com.benjameep.vcs_test_app;

/**
 * Created by meep on 2/27/2017.
 */

public class Property {

    private int value;
    private int[] brothers;

    private int dev;

    public Property(int propID) {
        // read the Json file for it's data
    }
    public int getValue() { return value; }
    public int[] getBrothers() { return brothers; }
    public int getDev() { return dev; }
    public void setDev(int dev) { this.dev = dev; }
    public boolean isMaxed(){ return this.dev == 5; }
    public boolean isMinimum(){ return this.dev == -1; }
    public void upgrade(){
        if(!this.isMaxed()){
            this.dev++;
        }
    }
    public void downgrade(){
        if(!this.isMinimum()){
            this.dev--;
        }
    }
}
