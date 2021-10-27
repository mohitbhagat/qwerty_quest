package com.example.typinggame;

import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {
    private List<Drawable> children = new ArrayList<Drawable>();
    public void Add(Drawable child) {
        this.children.add(child);
    }

    public void Draw() {
        for(Drawable d : children) {
            d.Draw();
        }
    }
}
