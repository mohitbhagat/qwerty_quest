package com.example.typinggame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.typinggame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public enum difficulty { EASY, MEDIUM, HARD}
    private difficulty current_difficulty;
    private SharedPreferences prefs;
    private int highScore = 0;

    MediaPlayer splash_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //splash_intro = MediaPlayer.create(MainActivity.this, R.raw.splash_intro);
        //splash_intro.start();
        //splash_intro.setLooping(true);

        this.prefs = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        this.highScore = prefs.getInt("highScore", 0);

        TextView highScoreText = (TextView)findViewById(R.id.highScoreText);
        highScoreText.setText("High Score: " + highScore);
        current_difficulty = difficulty.EASY;

    }

    public void startGame(View view){
        //splash_intro.stop();

        Intent intent = new Intent(this, NewGameActivity.class);
        intent.putExtra("Difficulty", current_difficulty);
        intent.putExtra("Score", 0);
        //intent.putExtra("High Score", highScore);
        startActivity(intent);

    }

    public void viewTutorial(View view){
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("Difficulty", current_difficulty);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 1) && (resultCode == 5)){
                current_difficulty = (difficulty) data.getSerializableExtra("Difficulty");
        }
    }
}