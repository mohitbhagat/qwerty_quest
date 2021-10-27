package com.example.typinggame;


import android.app.Activity;
import android.app.Notification;
import android.util.Log;

import java.util.Random;

public class Bubble extends Drawable{
    enum Effect {
        Health,
        Attack
    }

    public Effect effect;
    public int effectMagnitude;
    private BubbleText bubbleText;
    private BubbleTimer bubbleTimer;
    private int bubbleId;
    private boolean complete;

    public Bubble(Activity activity, String text, int bubbleId) {
        this.bubbleText = new BubbleText(activity, bubbleId, this);
        this.bubbleTimer = new BubbleTimer(activity, bubbleId);
        this.Add(bubbleText);
        this.Add(bubbleTimer);
        this.setNewText(text);
        this.bubbleId = bubbleId;
    }

    public void setNewText(String text) {
        this.setEffect();
        this.bubbleText.setNewText(text);
        this.bubbleTimer.resetTimer(bubbleText.getTextLen());
    }

    private void setEffect() {
        this.effect = this.getRandomEffect();
        this.effectMagnitude = this.getEffectMagnitude();
    }

    public boolean isCompleted(String inputText) {
        this.complete = bubbleText.isCompleted(inputText);
        return this.complete;
    }

    public void tick() {
        this.bubbleTimer.tick();
    }

    public int completedScore(){
        if (this.complete) {
            return 5 * this.bubbleTimer.timerVal + 50;
        } else {
            return 0;
        }
    }

    public boolean expired() { return this.bubbleTimer.expired(); }

    private Effect getRandomEffect() {
        int randint = new Random().nextInt(100);
        if(0 <= randint && randint <= 40) {
            return Effect.Health;
        } else {
            return Effect.Attack;
        }
    }

    private int getEffectMagnitude() {
        return Math.min(5, bubbleText.getTextLen());
    }

    public void setHighlightIndex(String inputText) {
        bubbleText.setHighlightIndex(inputText);
    }
}
