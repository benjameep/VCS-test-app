package com.benjameep.vcs_test_app;

public class Game {

    private Player[] players;

    public Game(int numPlayers) {
        this.players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            this.players[i] = new Player();
        }
    }

    public int getNumPlayers(){
        return this.players.length;
    }

    private int findOwner(int propID){
        // loop through all the players to find who has the property
        return -1; // return the playerID if we find it, else return -1
    }

    public void updateDev(int propID,boolean isUpgrade) throws Exception {
        // called when a property gets upgraded
        int owner = findOwner(propID);
        // if someone actually owns this property
        if(owner != -1){
            // pass on the request to the player
            if(isUpgrade){
                this.players[owner].upgradeProp(propID);
            } else {
                this.players[owner].downgradeProp(propID);
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
        // pass on the request to the player
        this.players[playerID].addProp(propID);
    }

    public void removeProp(int playerID,int propID){
        // take a property away from a player
        this.players[playerID].removeProp(propID);
    }

    public void updatePlayersValue(){
        // loop through all of the players, and get their value
        // each players balance, is their value * numPlayer - total value
    }
}
