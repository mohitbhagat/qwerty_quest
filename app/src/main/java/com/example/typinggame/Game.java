package com.example.typinggame;

import android.app.Activity;
import android.content.Intent;

import java.util.List;
import java.util.Random;


public class Game extends Drawable {
    //GLOBAL GAME CONSTANTS
    private int demoPlayerAttack = 30;
    private int demoEnemyAttack = 0;
    private int demoHealthBonus = 30;

    private Activity activity;
    //public int highScore = 0;
    public int score = 0;
    public boolean win;
    public MainActivity.difficulty difficulty;
    public HealthBar eHealthBar;
    public HealthBar pHealthBar;
    public InputBox inputBox;
    public BubbleComposite bubbles;

    private static Game instance = null;


    public static Game getInstance() {
        return instance;
    }

    public static Game newGame(List<String> vocab, Activity activity, MainActivity.difficulty difficulty, int score) {
        instance = new Game(vocab, activity, difficulty, score);
        return instance;
    }

    private Game(List<String> vocab, Activity activity, MainActivity.difficulty difficulty, int score) {
        this.bubbles = new BubbleComposite(activity, vocab, this);
        this.eHealthBar = new EnemyHealthBar(activity);
        this.pHealthBar = new PlayerHealthBar(activity);
        this.Add(eHealthBar);
        this.Add(pHealthBar);
        this.inputBox = new InputBox(activity, this);
        this.activity = activity;
        this.difficulty = difficulty;
        this.score = score;
        //this.highScore = highScore;

        //Initialize the UI
        this.Draw();
    };

    // ms : Time elapsed in ms
    public void tick(int ms) {
        switch (ms) {
            case 1000:
                bubbles.tick();
                if(new Random().nextBoolean()) {
                   pHealthBar.takeDamage(Math.max(3, demoEnemyAttack));
                   if(pHealthBar.health <= 0) {
                       win = false;
                       this.endGame(false);
                   }
               }
               break;
            default:
                break;
        }
    }

    public void notifyTextChange(String inputText) {
        this.inputBox.setInputText(inputText);
        this.score += bubbles.notifyTextChange(inputText);
    }

    public void notifyEffect(Bubble bubble) {
        switch (bubble.effect) {
            case Attack:
                eHealthBar.takeDamage(bubble.effectMagnitude);
                if(eHealthBar.health <= 0) {
                    win = true;
                    this.endGame(true);
                }
                break;
            case Health:
                pHealthBar.giveHealth(bubble.effectMagnitude);
                break;
        }

    }

    public void endGame(boolean win){
        Intent intent = new Intent(activity, GameEnd.class);
        intent.putExtra("Difficulty", this.difficulty);
        intent.putExtra("Score", this.score);
        intent.putExtra("Win", win);
        activity.startActivity(intent);
        activity.finish();
    }

}
