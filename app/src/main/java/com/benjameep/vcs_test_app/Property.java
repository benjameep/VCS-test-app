package com.benjameep.vcs_test_app;


/**
 * Manages its development and values
 */
public class Property {

    private Data _data;
    private int _dev;

    /**
     * Saves the properties data pass to it.
      * @param propData
     */
    public Property(Data propData) {
        _data = propData;
        this.setSingle();
    }
    public int[] getBrothers() { return _data._bros; }
    public int getDev() { return _dev; }
    public void setSingle() { _dev = -1; };
    public boolean isMaxed(){ return _dev >= 5; }
    public boolean isMinimum(){ return _dev <= -1; }

    /**
     * Upgrades the development if it can be.
      */
    public void upgrade(){
        if(!this.isMaxed()){
            _dev++;
        }
    }

    /**
     * Downgrades the development if it can be.
     */
    public void downgrade() {
        if (!this.isMinimum()) {
            _dev--;
        }
    }

    /**
     * Calculates the values based in the intensity
     * @param intensity
     * @return
     */
    public double getValue(double intensity) {
        // Intensity is a number from 0 to 1
        // 1 means that people stay in jail long
        // 0 means that people stay in jail short
        double weightedLong = _data._long[this.adjustedDev()] * intensity;
        double weightedShort = _data._short[this.adjustedDev()] * (1 - intensity);
        return weightedLong + weightedShort;
    }

    /**
     * Forces the development from 0 to 6.
     * @return
     */
    private int adjustedDev(){
        // -1 maps to 6 and everything else should stay the same
        // [0h,1h,2h,3h,4h,Hotel,Single]
        // [ 0, 1, 2, 3, 4,  5  ,  -1  ]
        return (7 + _dev)%7;
    }
}
