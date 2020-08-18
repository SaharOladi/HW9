package com.example.hw9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButtonTicTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTicTacToe = findViewById(R.id.btn_tic_tac_toe);

        mButtonTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, ticTacToeFragment)
                        .commit();
            }

        });

    }


}