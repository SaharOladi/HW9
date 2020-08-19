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


public class TicTacToeFragment extends Fragment {

    private TableLayout mTableLayout;

    int count = 0;
    private int mTurn = 1;
    private String mButtonsMessageAll = "";
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

        createButtons(fragmentTicTacToeView);
        setListener(mTableLayout);

        return fragmentTicTacToeView;
    }


    @SuppressLint("ResourceType")
    private void createButtons(View view) {
        mTableLayout = view.findViewById(R.id.table_layout);
        int counter = 0;
        for (int row = 0; row < 3; row++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int column = 0; column < 3; column++) {
                Button button = new Button(getActivity());
                button.setId(counter);
                button.setWidth(50);
                button.setHeight(50);
                tableRow.addView(button);
                counter++;
            }
            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    private void setListener(View view) {
        TableLayout tableLayout = (TableLayout) view;
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        move(v);
                        count += 1;
                        if (count == 9) {
                            String str = extractMessage(mTableLayout);
                            System.out.println(str);
                            int value = gameFinish(str);
                            showSnackBar(value);
                        }
                    }
                });
            }
        }

    }

    @SuppressLint("ResourceType")
    private void move(View view) {

        Button currentButton = (Button) view;
        String message = "";

        if (mTurn == 1) {
            message = "O";
            setButtons(currentButton, message);
            mTurn = 2;
        } else if (mTurn == 2) {
            message = "X";
            setButtons(currentButton, message);
            mTurn = 1;
        }

    }

    private String extractMessage(View view) {
        TableLayout tableLayout = (TableLayout) view;
        String output = "";
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            //This will iterate through the table row.
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                output += btn.getText();
            }
        }
        return output;

    }

    private void setButtons(Button currentButton, String message) {
        currentButton.setText(message);
        currentButton.setTextSize(16);
        currentButton.setTextColor(Color.BLACK);
        currentButton.setEnabled(false);
    }

    private void showSnackBar(int value) {
        switch (value) {
            case 1:
//                snackbar = Snackbar.make(view, "Player one wins!!!", Snackbar.LENGTH_LONG);
                Toast.makeText(getActivity(), "winner one", Toast.LENGTH_SHORT).show();
            case 2:
//                snackbar = Snackbar.make(view, "Player two wins!!!", Snackbar.LENGTH_LONG);
                Toast.makeText(getActivity(), "winner two", Toast.LENGTH_SHORT).show();

            case 3:
//                snackbar = Snackbar.make(view, "GameOver!!!", Snackbar.LENGTH_LONG);
                Toast.makeText(getActivity(), "not", Toast.LENGTH_SHORT).show();


        }
//        snackbar.show();
    }


    private int gameFinish(String string) {
        String[] buttonsMessage = string.split("");


        boolean winnerOne = false;
        boolean winnerTwo = false;

        //Rows
        if (((buttonsMessage[0].equals("O")) && (buttonsMessage[1].equals("O")) && (buttonsMessage[2].equals("O"))) ||
                ((buttonsMessage[3].equals("O")) && (buttonsMessage[4].equals("O")) && (buttonsMessage[5].equals("O"))) ||
                ((buttonsMessage[6].equals("O")) && (buttonsMessage[7].equals("O")) && (buttonsMessage[8].equals("O")))){
            winnerOne = true;
        } else if (((buttonsMessage[0].equals("X")) && (buttonsMessage[1].equals("X")) && (buttonsMessage[2].equals("X"))) ||
                ((buttonsMessage[3].equals("X")) && (buttonsMessage[4].equals("X")) && (buttonsMessage[5].equals("X"))) ||
                ((buttonsMessage[6].equals("X")) && (buttonsMessage[7].equals("X")) && (buttonsMessage[8].equals("X")))) {
            winnerTwo = true;
        }
        //Column
        else if (((buttonsMessage[0].equals("O")) && (buttonsMessage[3].equals("O")) && (buttonsMessage[6].equals("O"))) ||
                ((buttonsMessage[1].equals("O")) && (buttonsMessage[4].equals("O")) && (buttonsMessage[7].equals("O"))) ||
                ((buttonsMessage[2].equals("O")) && (buttonsMessage[5].equals("O")) && (buttonsMessage[8].equals("O")))) {
            winnerOne = true;
        } else if (((buttonsMessage[0].equals("X")) && (buttonsMessage[3].equals("X")) && (buttonsMessage[6].equals("X"))) ||
                ((buttonsMessage[1].equals("X")) && (buttonsMessage[4].equals("X")) && (buttonsMessage[7].equals("X"))) ||
                ((buttonsMessage[2].equals("X")) && (buttonsMessage[5].equals("X")) && (buttonsMessage[8].equals("X")))) {
            winnerTwo = true;
        }
        //Diagonal
        else if (((buttonsMessage[0].equals("O")) && (buttonsMessage[4].equals("O")) && (buttonsMessage[8].equals("O"))) ||
                ((buttonsMessage[2].equals("O")) && (buttonsMessage[4].equals("O")) && (buttonsMessage[6].equals("O")))) {
            winnerOne = true;
        } else if (((buttonsMessage[0].equals("X"))) && (buttonsMessage[4].equals("X")) && (buttonsMessage[8].equals("X")) ||
                ((buttonsMessage[2].equals("X"))) && (buttonsMessage[4].equals("X")) && (buttonsMessage[6].equals("X"))) {
            winnerTwo = true;
        }


        if (winnerOne == true)
            return 1;
        else if (winnerTwo == true)
            return 2;
        else
            return 3;

    }


}