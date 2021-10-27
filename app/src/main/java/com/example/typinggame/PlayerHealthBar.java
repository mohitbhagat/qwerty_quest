package com.example.typinggame;

import android.app.Activity;
import android.widget.ProgressBar;

public class PlayerHealthBar extends HealthBar {
    ProgressBar progressBar;

    public PlayerHealthBar(Activity activity) {
        super(activity);
        this.progressBar = (ProgressBar) activity.findViewById(R.id.playerHealthBar);
    }

    public void Draw() {
        progressBar.setProgress(this.health);
    }
}
