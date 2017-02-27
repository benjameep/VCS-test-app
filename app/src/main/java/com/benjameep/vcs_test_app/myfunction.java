package com.benjameep.vcs_test_app;

public class myfunction {

    public static int addUp(int [] numbers) {
        int sum = 0;
        for( int x: numbers){
            sum += x;
        }
        return sum;
    }
    public static int factorial(int x) {
        int result = 1;
        for( int i = 1; i <= x; i++){
            result *= i;
        }
        return result;
    }
    public static int powerUp(int x) {
        int result = 1;
        for( int i = 0; i < x; i++){
            result *= x;
        }
        return result;
    }

}
