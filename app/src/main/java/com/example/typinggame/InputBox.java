package com.example.typinggame;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class InputBox {
    public String inputText = "";
    public Activity activity;
    public EditText inputBox;
    public Game game;

    public InputBox(Activity activity, Game game) {
        this.activity = activity;
        this.inputBox = (EditText) activity.findViewById(R.id.textInput);
        this.game = game;
        inputBox.requestFocus();
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                game.notifyTextChange(charSequence.toString());
            }
       });
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public void clearInputText() {
        this.inputText = "";
        this.inputBox.getText().clear();
    }
}
