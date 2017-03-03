package com.benjameep.vcs_test_app;

/**
 * Created by meep on 2/27/2017.
 */

public class Player {

    private Property[] props;
    private int value;

    public Player() {
        // init with an array with the amount of propIDs there are
        this.props = new Property[28];
        this.value = 0;
    }
    public boolean hasProp(int propID){
        // look through our properties to see if we have the property
        return false;
    }
    public boolean hasProps(int[] propIDs){
        // look through all of the propIDs in the array, to see if we have all of them
        return false;
    }
    public void addProp(int propID) {
        // construct the property with the propID
        this.props[propID] = new Property(propID);
        // if this completes a monopoly
        if(this.hasProps(this.props[propID].getBrothers())){
            // then upgrade all of them
            this.props[propID].upgrade();
            for(int broID : this.props[propID].getBrothers()){
                this.props[broID].upgrade();
            }
        }
    }

    public void removeProp(int propID) {
        // if this breaks a monopoly
        if(this.hasProps(props[propID].getBrothers())){
            // then set them all to single
            for(int broID : props[propID].getBrothers()){
                this.props[broID].setDev(-1);
            }
        }
        // remove from our list of properties
        props[propID] = null;
    }
    public void upgradeProp(int propID){
        for(int broID : props[propID].getBrothers()){
            // if this prop is already maxed out, or if it is already bigger than it's brother
            if(this.props[propID].isMaxed() || this.props[propID].getDev() > this.props[broID].getDev()){
                // then upgrade the brother
                this.props[broID].upgrade();
            }
        }
        // Then finally upgrade the actual property
        this.props[propID].upgrade();
    }
    public void downgradeProp(int propID) {
        for(int broID : props[propID].getBrothers()) {
            // if this prop is already it's minimum, or if it is already smaller than its brother
            if (this.props[propID].isMinimum() || this.props[propID].getDev() < this.props[broID].getDev()) {
                // then downgrade the brother
                this.props[broID].upgrade();
            }
        }
        // Then finally downgrade the actual property
        this.props[propID].downgrade();
    }
}
