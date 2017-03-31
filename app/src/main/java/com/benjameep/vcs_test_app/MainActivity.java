package com.benjameep.vcs_test_app;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
        public static final String PREFS_NAME = "MyPrefsFile";
        public static final String NUM_PLAYERS_KEYWORD = "numPlayers";
        private Game game;
        private Data[] _jsonData;
        private int xDelta;
        private int yDelta;
        private ViewGroup mainLayout;

    private static final String TAG = MainActivity.class.getSimpleName();

      @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if (savedInstanceState != null) {
                Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
            } else {
                Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
            }


            // get data from savedPrefs
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            int numPlayers = settings.getInt(NUM_PLAYERS_KEYWORD,4);

            Log.v("Number of Players",Integer.toString(numPlayers));

            // Load json Data from 'numbers.json'
            Gson gson = new GsonBuilder().create();
            Data[] _jsonData = gson.fromJson(loadJSONFromAsset("numbers.json"), Data[].class);

            Log.v("Json Data loaded", String.valueOf(_jsonData.length));

            // Initalize Our game
            this.game = new Game(numPlayers,_jsonData);

            this.game.addProp(0,0);
            Log.d("Game",gson.toJson(this.game));
            //this.game.updatePlayersBalance();

            // DragNDrop
            mainLayout = (RelativeLayout) findViewById(R.id.activity_main);
            findViewById(R.id.prop_0).setOnTouchListener(onTouchListener());
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

    /**
     * Passed the file's name and read it into a string.
     * @return
     */
        public String loadJSONFromAsset(String File_name) {
            String json;
            try {
                InputStream is = getAssets().open(File_name);
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

        private View.OnTouchListener onTouchListener() {
            return new View.OnTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View view, MotionEvent event) {

                    final int x = (int) event.getRawX();
                    final int y = (int) event.getRawY();

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {

                        case MotionEvent.ACTION_DOWN:
                            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                    view.getLayoutParams();

                            xDelta = x - lParams.leftMargin;
                            yDelta = y - lParams.topMargin;
                            break;

                        case MotionEvent.ACTION_UP:
                            Toast.makeText(MainActivity.this,
                                    "thanks for new location!", Toast.LENGTH_SHORT)
                                    .show();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                    .getLayoutParams();
                            layoutParams.leftMargin = x - xDelta;
                            layoutParams.topMargin = y - yDelta;
                            layoutParams.rightMargin = 0;
                            layoutParams.bottomMargin = 0;
                            view.setLayoutParams(layoutParams);
                            break;
                    }
                    mainLayout.invalidate();
                    return true;
                }
            };
        }
}
