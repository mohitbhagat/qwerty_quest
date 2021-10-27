package com.example.typinggame;

import android.app.Activity;
import android.widget.ProgressBar;

public class EnemyHealthBar extends HealthBar{
    ProgressBar progressBar;

    public EnemyHealthBar(Activity activity) {
        super(activity);
        this.progressBar = (ProgressBar) activity.findViewById(R.id.enemyHealthBar);
    }

    public void Draw() {
        progressBar.setProgress(this.health);
    }
}
