package com.benjameep.vcs_test_app;


/**
 * The player class holds all the properties and keeps track of them.
 */
public class Player {

    private Property[] _props;
    private double _value;
    private double _balance;

    /**
     * init with an array with the amount of propIDs there are
     */
    public Player() {
        _props = new Property[28];
        _value = 0;
    }

    public double getBalance(){ return _balance; }
    public void setBalance(double balance) { _balance = balance; }
    public double getValue(){ return _value; }
    public boolean hasProp(int propID){
        // look through our properties to see if we have the property
        return _props[propID] != null;
    }

    /**
     * look through all of the propIDs in the array, to see if we have all of them
     * @param propIDs
     * @return
     */
    public boolean hasProps(int[] propIDs){
        boolean hasProps = true;
        for(int propId : propIDs){
            if(!this.hasProp(propId)){
                hasProps = false;
            }
        }
        return hasProps;
    }

    /**
     * It creates properties with the passed data
     * @param propID
     * @param propData
     */
    public void addProp(int propID, Data propData) {
        // construct the property with the propID
        _props[propID] = new Property(propData);
        // if this completes a monopoly
        if(this.hasProps(_props[propID].getBrothers())){
            // then upgrade all of them
            _props[propID].upgrade();
            for(int broID : _props[propID].getBrothers()){
                _props[broID].upgrade();
            }
        }
    }

    /**
     * Deletes the properties from our array
     * @param propID
     */
    public void removeProp(int propID) {
        // if this breaks a monopoly
        if(this.hasProps(_props[propID].getBrothers())){
            // then set them all to single
            for(int broID : _props[propID].getBrothers()){
                _props[broID].setSingle();
            }
        }
        // remove from our list of properties
        _props[propID] = null;
    }

    /**
     * passes on the request to the properties
     * @param propID
     */
    public void upgradeProp(int propID){
        for(int broID : _props[propID].getBrothers()){
            // if this prop is already maxed out, or if it is already bigger than it's brother
            if(_props[propID].isMaxed() || _props[propID].getDev() > _props[broID].getDev()){
                // then upgrade the brother
                _props[broID].upgrade();
            }
        }
        // Then finally upgrade the actual property
        _props[propID].upgrade();
    }

    /**
     * passes on the request to the properties
     * @param propID
     */
    public void downgradeProp(int propID) {
        for(int broID : _props[propID].getBrothers()) {
            // if this prop is already it's minimum, or if it is already smaller than its brother
            if (_props[propID].isMinimum() || _props[propID].getDev() < _props[broID].getDev()) {
                // then downgrade the brother
                _props[broID].downgrade();
            }
        }
        // Then finally downgrade the actual property
        _props[propID].downgrade();
    }

    /**
     * Adds up it's properties values
     * @param intensity
     * @return
     */
    public double updateValue(double intensity){
        _value = 0;
        for(Property prop: _props){
            _value += prop.getValue(intensity);
        }
        return this.getValue();
    }
}
