package com.benjameep.vcs_test_app;

/**
 * Created by meep on 2/27/2017.
 */

public class Player {

    private Property[] props;
    private int value;

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
        props[propID] = new Property(propID);
        // if this completes a monopoly
        if(this.hasProps(props[propID].getBrothers())){
            // then upgrade all of them
            props[propID].upgrade();
            for(int broID : props[propID].getBrothers()){
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

    public void updateDev(int propID, boolean isUpgrade) {
        if(isUpgrade)
            this.props[propID].upgrade();
        else
            this.props[propID].downgrade();
        // adjust other properties in monopoly accordingly
    }
}
