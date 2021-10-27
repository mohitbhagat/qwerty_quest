package com.example.typinggame;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BubbleComposite extends Drawable {
    public List<Bubble> bubbles;
    public List<String> vocab;
    public Game game;
    public int vocabIndex = 0;
    private Random vocabnum = new Random();

    public BubbleComposite(Activity activity, List<String> vocab, Game game) {
       this.bubbles = new ArrayList<Bubble>();
       this.vocab = vocab;
       this.game = game;
        this.vocabIndex = vocabnum.nextInt(this.vocab.size());
       for (int i = 0; i < 3; i++) {
           Log.d("Vocab", vocab.get(this.vocabIndex));
           Bubble newBubble = new Bubble(activity, vocab.get(this.vocabIndex), i);
           this.vocabIndex = vocabnum.nextInt(this.vocab.size());
           bubbles.add(newBubble);
           this.Add(newBubble);
       }
    }

    public void tick() {
        for (Bubble curBubble : bubbles) {
            curBubble.tick();
            if (curBubble.expired()) {
                curBubble.setNewText(vocab.get(this.vocabIndex));
                this.vocabIndex = vocabnum.nextInt(this.vocab.size());
            }
            this.Draw();
        }
    }

    public int notifyTextChange(String inputText) {
        int score = 0;
        for (Bubble curBubble : bubbles) {
            curBubble.setHighlightIndex(inputText);
            if (curBubble.isCompleted(inputText)) {
                score = curBubble.completedScore();
                game.notifyEffect(curBubble);
                game.inputBox.clearInputText();
                curBubble.setNewText(vocab.get(this.vocabIndex));
                this.vocabIndex = vocabnum.nextInt(this.vocab.size());
            }
            this.Draw();
        }

        return score;
    }
}
