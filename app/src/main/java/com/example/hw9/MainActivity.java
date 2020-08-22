package com.example.hw9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButtonTicTacToe;
    private Button mButtonFourInRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTicTacToe = findViewById(R.id.btn_tic_tac_toe);
        mButtonFourInRow = findViewById(R.id.btn_four_in_row);

        mButtonTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ticTacToeFragment)
                        .commit();
            }

        });

        mButtonFourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FourInARowFragment fourInARowFragment = new FourInARowFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fourInARowFragment)
                        .commit();
            }
        });

    }


}