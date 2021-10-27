package com.example.typinggame;

import android.app.Activity;
import android.widget.TextView;

import java.util.Random;

public class BubbleTimer extends Drawable {
    public int timerVal;
    public int timerId;
    private TextView timerText;
    private Activity activity;

    public BubbleTimer(Activity activity, int timerId) {
        this.activity = activity;
        this.timerId = timerId;
        switch (timerId) {
            case 0:
                timerText = (TextView) activity.findViewById(R.id.timer1);
                break;
            case 1:
                timerText = (TextView) activity.findViewById(R.id.timer2);
                break;
            case 2:
                timerText = (TextView) activity.findViewById(R.id.timer3);
                break;
        }
    }

    public void tick() { this.timerVal -= 1; }
    public boolean expired() { return this.timerVal <= 0; }

    public void resetTimer(int textLen) {
        timerVal = getRandomTimerValue(textLen);
    }

    @Override
    public void Draw() {
        timerText.setText(String.valueOf(timerVal));
    }

    private int getRandomTimerValue(int textLen) {
        // The timer of the bubble is calculated based on the length of the bubble.
        final int minTimer = 4;
        final int extraTime = 5;
        final int extraRandTime = 5;
        return Math.max(minTimer, textLen) + extraTime + (new Random().nextInt(extraRandTime));
    }

}
