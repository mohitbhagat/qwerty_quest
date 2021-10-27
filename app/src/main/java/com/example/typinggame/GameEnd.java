package com.example.typinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity {
    private int score;
    private MainActivity.difficulty difficulty;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    MediaPlayer splash_outro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        //splash_outro = MediaPlayer.create(GameEnd.this, R.raw.splash_outro);
        //splash_outro.start();
        //splash_outro.setLooping(true);
        Intent intent = getIntent();
        prefs = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        int highScore = prefs.getInt("highScore", 0);
        score = intent.getIntExtra("Score", 0);

        if (score > highScore){
            editor = prefs.edit();
            editor.putInt("highScore", score);
            editor.commit();
            highScore = score;
        }

        boolean win = intent.getBooleanExtra("Win", true);
        difficulty = (MainActivity.difficulty) intent.getSerializableExtra("Difficulty");

        TextView scoreText = (TextView)findViewById(R.id.scoretextView);
        TextView results = (TextView)findViewById(R.id.resultsTextView);
        TextView highScoreText = (TextView)findViewById(R.id.highScoreTextView);
        Button continueButton = (Button)findViewById(R.id.continueButton);
        String displayScore = "Score: " + score;
        String displayHighScore = "High Score: " + highScore;
        scoreText.setText(displayScore);
        highScoreText.setText(displayHighScore);
        if(win){
            results.setText("You Won!");
        } else {
            results.setText("You Lost.");
            continueButton.setClickable(false);
            continueButton.setVisibility(View.GONE);
        }
    }


//    public void onResume() {
//        super.onResume();
//        splash_outro.start();
//    }
//
//    public void onPause() {
//        super.onPause();
//        splash_outro.release();
//    }

    public void quitGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void continueGame(View view){
        Intent intent = new Intent(this, NewGameActivity.class);
        intent.putExtra("Difficulty", difficulty);
        intent.putExtra("Score", score);
        startActivity(intent);
    }

    public int getScore (){
        return this.score;
    }
}