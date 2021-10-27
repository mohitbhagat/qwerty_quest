package com.example.typinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private MainActivity.difficulty new_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        int selection = 0;
        new_difficulty = (MainActivity.difficulty) intent.getSerializableExtra("Difficulty");

        switch (new_difficulty){
            case MEDIUM:
                selection = 1;
                break;
            case HARD:
                selection = 2;
                break;
        }
        spinner.setSelection(selection);
    }


    public void saveSettings(View view){
        Intent intent = new Intent();
        intent.putExtra("Difficulty", new_difficulty);
        setResult(5, intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String difficulty = parent.getItemAtPosition(position).toString();
        switch (position){
            case 0:
                new_difficulty = MainActivity.difficulty.EASY;
                break;
            case 1:
                new_difficulty = MainActivity.difficulty.MEDIUM;
                break;
            case 2:
                new_difficulty = MainActivity.difficulty.HARD;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}