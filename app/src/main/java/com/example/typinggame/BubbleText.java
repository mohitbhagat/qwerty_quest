package com.example.typinggame;

import android.app.Activity;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BubbleText extends Drawable {
    public int highlightIndex = 0;
    public String text;
    public int bubbleId;
    private Bubble bubble;
    private TextView textView;
    private Activity activity;

    public BubbleText(Activity activity, int bubbleId, Bubble bubble) {
        this.activity = activity;
        this.bubbleId = bubbleId;
        this.bubble = bubble;
        switch (bubbleId) {
            case 0:
                textView = (TextView) activity.findViewById(R.id.bubble1);
                break;
            case 1:
                textView = (TextView) activity.findViewById(R.id.bubble2);
                break;
            case 2:
                textView = (TextView) activity.findViewById(R.id.bubble3);
                break;
        }
    }

    public int getTextLen() {
        return (text == null) ? 0 : text.length();
    }

    public void setNewText(String text) {
        this.text = text;
        this.highlightIndex = 0;
    }

    public boolean isCompleted(String inputText) {
        return inputText.equals(text);
    }

    public void setHighlightIndex(String inputText) {
        if(this.text.startsWith(inputText)) {
            this.highlightIndex = inputText.length();
        } else {
            this.highlightIndex = 0;
        }
    }

    @Override
    public void Draw() {
        Spannable span = new SpannableString(text);
        if(highlightIndex > 0) {
            span.setSpan(new ForegroundColorSpan(Color.RED), 0, highlightIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(span, TextView.BufferType.SPANNABLE);
        switch (this.bubble.effect) {
            case Attack:
                textView.setBackground(
                        activity.getResources().getDrawable(R.drawable.attackborder)
                );
                break;
            case Health:
                textView.setBackground(
                        activity.getResources().getDrawable(R.drawable.healthborder)
                );
                break;
        }
    }
}
