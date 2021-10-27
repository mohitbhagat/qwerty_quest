package com.example.typinggame;

import android.app.Activity;

public abstract class HealthBar extends Drawable{
    private int max = 100;
    public int health = 100;
    private Activity activity;

    public HealthBar(Activity activity) { this.activity = activity; }

    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
        this.Draw();
    }

    public void giveHealth(int health) {
        this.health = Math.min(max, this.health + health);
        this.Draw();
    }

    public abstract void Draw();
}
