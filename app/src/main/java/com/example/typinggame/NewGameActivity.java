package com.example.typinggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.IntFunction;

import android.widget.ProgressBar;

import org.w3c.dom.Text;


public class NewGameActivity extends AppCompatActivity {
    Game game;
    MediaPlayer medieval_theme;
    //SharedPreferences prefs;
    Handler handler = new Handler();
    final Random randImage = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Intent intent = getIntent();
        MainActivity.difficulty difficulty = (MainActivity.difficulty) intent.getSerializableExtra("Difficulty");
        int score = intent.getIntExtra("Score", 0);

        List<String> vocab = Vocab.level1;
        switch(difficulty){
            case MEDIUM:
                vocab = Vocab.level2;
                break;
            case HARD:
                vocab = Vocab.level3;
                break;
        }

        //medieval_theme = MediaPlayer.create(NewGameActivity.this, R.raw.medieval_theme);
        //medieval_theme.start();
        //medieval_theme.setLooping(true);

        ImageView enemy = (ImageView) findViewById(R.id.imageView3);
        final String imageName = "enemy" + randImage.nextInt(9);
        enemy.setImageDrawable(
                getResources().getDrawable(imageID(imageName, getApplicationContext())));


        ImageView background = (ImageView) findViewById(R.id.imageView2);
        final String bgName = "background" + randImage.nextInt(6);
        background.setImageDrawable(
                getResources().getDrawable(imageID(bgName, getApplicationContext())));

        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.clayout);
        layout.setBackground(
                getResources().getDrawable(imageID(bgName, getApplicationContext())));

        this.game = game.newGame(vocab, this, difficulty, score);

    }

    @Override
    public void onResume() {
        super.onResume();
        this.game = game.getInstance();
        //medieval_theme.start();
        int second = 1000; // 1000 Milliseconds = 1 second

        Runnable runnable = new Runnable() {
            public void run() {
                game.tick(second);
                handler.postDelayed(this, second);
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        //medieval_theme.release();
        handler.removeCallbacksAndMessages(null);
    }

    protected static int imageID(String resName, Context ctx) {

        final int ResourceID = ctx.getResources().getIdentifier(resName, "drawable",
                        ctx.getApplicationInfo().packageName);

        return ResourceID;
    }
}