package com.benjameep.vcs_test_app;

public class Game {

    private Player[] _players;
    private Data[] _data;
    private double _totalProfits;
    // Intensity is from 0 to 1 based on the total amount of money
    // being passed around, which effects how long people will stay
    // in jail, which in turn effects the statistics slightly
    private double _intensity;

    public Game(int numPlayers,Data[] jsonData) {
        _players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            _players[i] = new Player();
        }
        _data = jsonData;
    }
    public int getNumPlayers(){
        return _players.length;
    }

    private int findOwner(int propID){
        // loop through all the _players to find who has the property
        for(int i = 0; i < _players.length; i++){
            if(_players[i].hasProp(propID)){
                return i;
            }
        }
        return -1; // return the playerID if we find it, else return -1
    }
    public void updateDev(int propID,boolean isUpgrade) throws Exception {
        // called when a property gets upgraded
        int owner = findOwner(propID);
        // if someone actually owns this property
        if(owner != -1){
            // pass on the request to the player
            if(isUpgrade){
                _players[owner].upgradeProp(propID);
            } else {
                _players[owner].downgradeProp(propID);
            }
        } else {
            // throw an exception
            throw new Exception("Tried to update a property no one owns");
        }
    }
    public void addProp(int playerID,int propID){
        // gives a property to a player

        int prevOwner = findOwner(propID);
        if(prevOwner == playerID){
            // do nothing, person already owns it
            return;
        } else if (prevOwner != -1){
            // take this property away from whoever used to own it
            this.removeProp(prevOwner,propID);
        }
        // pass on the request to the player with the corresponding jsonData
        _players[playerID].addProp(propID,_data[propID]);
    }
    public void removeProp(int playerID,int propID){
        // take a property away from a player
        _players[playerID].removeProp(propID);
    }
    public void updatePlayersBalance(){
        // each _players balance, is their value * numPlayer - total value
        this.updateTotalProfits();
        for(Player player: _players){
            player.setBalance((player.getValue() * _players.length) - _totalProfits);
        }
    }
    private void updateTotalProfits(){
        // 603 is when every single property has hotels
        _intensity = _totalProfits != 0 ? 603/_totalProfits : 0;
        _totalProfits = 0;
        // loop through all of the _players, and get their value
        for(Player player: _players){
            _totalProfits += player.updateValue(_intensity);
        }
    }
}
