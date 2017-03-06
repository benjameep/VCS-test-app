package com.benjameep.vcs_test_app;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
        public static final String PREFS_NAME = "MyPrefsFile";
        public static final String NUM_PLAYERS_KEYWORD = "numPlayers";
        private Game game;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // get data from savedPrefs
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            int numPlayers = settings.getInt(NUM_PLAYERS_KEYWORD,4);
            this.game = new Game(numPlayers);

            log.d()
        }

        @Override
        protected void onStop(){
            super.onStop();

            // save data to savedPrefs
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(NUM_PLAYERS_KEYWORD, this.game.getNumPlayers());

            // Commit the edits!
            editor.commit();
        }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("numbers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}