package com.example.hw9;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class TicTacToeFragment extends Fragment {

    private TableLayout mTableLayout;
    private int mTurn = 1;
    private String[] mButtonMessage = new String[9];


    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentTicTacToeView = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);

        createViews(fragmentTicTacToeView);


        return fragmentTicTacToeView;
    }


    @SuppressLint("ResourceType")
    private void createViews(View view) {
        mTableLayout = view.findViewById(R.id.table_layout);
        int counter = 1;

        for (int row = 0; row < 3; row++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int column = 0; column < 3; column++) {
                Button button = new Button(getActivity());
                button.setId(counter);
                button.setWidth(40);
                button.setHeight(40);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        move(v);
                    }
                });

                tableRow.addView(button);
                counter++;
            }
            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        showSnackBar(view);
    }

    @SuppressLint("ResourceType")
    private void move(View view) {

        Button currentButton = (Button) view;

        String message = "";
        String[] output = new String[9];

        for (int i = 0; i < 9; i++) {
            if (mTurn == 1) {
                message = "O";
                setButtons(currentButton, message);
                mTurn = 2;
            } else if (mTurn == 2) {
                message = "X";
                setButtons(currentButton, message);
                mTurn = 1;
            }
            output[i] = message;
        }

        mButtonMessage = output;
        gameFinish(mButtonMessage);
    }

    private void setButtons(Button currentButton, String message) {
        currentButton.setText(message);
        currentButton.setTextSize(16);
        currentButton.setTextColor(Color.BLACK);
        currentButton.setEnabled(false);
    }

    private void showSnackBar(View view) {
        int value = gameFinish(mButtonMessage);
        Snackbar snackbar;
        switch (value) {
            case 1:
                snackbar = Snackbar.make(view, "Player one wins!!!", Snackbar.LENGTH_LONG);
            case 2:
                snackbar = Snackbar.make(view, "Player two wins!!!", Snackbar.LENGTH_LONG);
            default:
                snackbar = Snackbar.make(view, "GameOver!!!", Snackbar.LENGTH_LONG);

        }
        snackbar.show();
    }


    private int gameFinish(String[] buttonsMessage) {

        boolean winnerOne = false;
        boolean winnerTwo = false;

        //Rows
        if (((buttonsMessage[0] == "O") && (buttonsMessage[1] == "O") && (buttonsMessage[2] == "O")) ||
                ((buttonsMessage[3] == "O") && (buttonsMessage[4] == "O") && (buttonsMessage[5] == "O")) ||
                ((buttonsMessage[6] == "O") && (buttonsMessage[7] == "O") && (buttonsMessage[8] == "O"))) {
            winnerOne = true;
        } else if (((buttonsMessage[0] == "X") && (buttonsMessage[1] == "X") && (buttonsMessage[2] == "X")) ||
                ((buttonsMessage[3] == "X") && (buttonsMessage[4] == "X") && (buttonsMessage[5] == "X")) ||
                ((buttonsMessage[6] == "X") && (buttonsMessage[7] == "X") && (buttonsMessage[8] == "X"))) {
            winnerTwo = true;
        }
        //Column
        else if (((buttonsMessage[0] == "O") && (buttonsMessage[3] == "O") && (buttonsMessage[6] == "O")) ||
                ((buttonsMessage[1] == "O") && (buttonsMessage[4] == "O") && (buttonsMessage[7] == "O")) ||
                ((buttonsMessage[2] == "O") && (buttonsMessage[5] == "O") && (buttonsMessage[8] == "O"))) {
            winnerOne = true;
        } else if (((buttonsMessage[0] == "X") && (buttonsMessage[3] == "X") && (buttonsMessage[6] == "X")) ||
                ((buttonsMessage[1] == "X") && (buttonsMessage[4] == "X") && (buttonsMessage[7] == "X")) ||
                ((buttonsMessage[2] == "X") && (buttonsMessage[5] == "X") && (buttonsMessage[8] == "X"))) {
            winnerTwo = true;
        }
        //Diagonal
        else if (((buttonsMessage[0] == "O") && (buttonsMessage[4] == "O") && (buttonsMessage[8] == "O")) ||
                ((buttonsMessage[2] == "O") && (buttonsMessage[4] == "O") && (buttonsMessage[6] == "O"))) {
            winnerOne = true;
        } else if (((buttonsMessage[0] == "X") && (buttonsMessage[4] == "X") && (buttonsMessage[8] == "X")) ||
                ((buttonsMessage[2] == "X") && (buttonsMessage[4] == "X") && (buttonsMessage[6] == "X"))) {
            winnerTwo = true;
        }


        if (winnerOne)
            return 1;
        else if (winnerTwo)
            return 2;
        else
            return 3;

    }


}