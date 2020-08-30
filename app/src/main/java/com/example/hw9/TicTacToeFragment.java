package com.example.hw9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


public class TicTacToeFragment extends Fragment {


    public static final String BUNDLE_BUTTON_MESSAGE = "null";
    public static final String BUNDLE_BUTTON_TAG = "BUNDLE_BUTTON_TAG";
    public static final String BUNDLE_BUTTON_IDS = BUNDLE_BUTTON_TAG;
    public static final String BUNDLE_BUTTON_ENABLE = "BUNDLE_BUTTON_ENABLE";
    public static final String BUNDLE_WINNER_VALUE = "BUNDLE_WINNER_VALUE";
    public static final String BUNDLE_M_COUNT = "BUNDLE_M_COUNT";
    public static final String BUNDLE_M_BUTTON_MESSAGE = "BUNDLE_M_BUTTON_MESSAGE";
    private TableLayout mTableLayout;

    private int mTurn = 1;
    private int mCount = 0;
    int winnerValue = 0;
    String[] mButtonsMessage = new String[9];
    int[] ids = new int[9];
    String[] getButtonsMessage = new String[9];


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
        View fragmentTicTacToeView = inflater
                .inflate(R.layout.fragment_tic_tac_toe, container, false);

        createButtons(fragmentTicTacToeView);
        setListener(mTableLayout);

        return fragmentTicTacToeView;
    }


    @SuppressLint("ResourceType")
    private void createButtons(View view) {
        mTableLayout = view.findViewById(R.id.table_layout_tic_tac_toe);

        mTableLayout.setGravity(Gravity.CENTER);
        int counter = 0;
        Button button;
        // to do: weight
        for (int row = 0; row < 3; row++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setWeightSum(3);
            tableRow.setGravity(Gravity.CENTER);
            for (int column = 0; column < 3; column++) {
                button = new Button(getActivity());
                setWidthHeightOrientation(button);
                button.setId(counter + 1);
                tableRow.addView(button);
                counter++;
            }

            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    private void setWidthHeightOrientation(Button button) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            button.setWidth(150);
            button.setHeight(150);
        } else {
//            button.setWidth(50);
//            button.setHeight(50);
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

                        Button button = (Button) v;
                        move(button);
                        switch (button.getId()) {
                            case 1:
                                mButtonsMessage[0] = button.getText().toString();
                                break;
                            case 2:
                                mButtonsMessage[1] = button.getText().toString();
                                break;
                            case 3:
                                mButtonsMessage[2] = button.getText().toString();
                                break;
                            case 4:
                                mButtonsMessage[3] = button.getText().toString();
                                break;
                            case 5:
                                mButtonsMessage[4] = button.getText().toString();
                                break;
                            case 6:
                                mButtonsMessage[5] = button.getText().toString();
                                break;
                            case 7:
                                mButtonsMessage[6] = button.getText().toString();
                                break;
                            case 8:
                                mButtonsMessage[7] = button.getText().toString();
                                break;
                            case 9:
                                mButtonsMessage[8] = button.getText().toString();
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + button.getId());
                        }
                        mCount += 1;
                        winnerValue = checkWinner();
                        if (winnerValue == 0) {
                            if (mCount == 9)
                                showSnackBar(mTableLayout, winnerValue);
                        } else showSnackBar(mTableLayout, winnerValue);

                    }
                });
            }
        }
    }

    @SuppressLint("ResourceType")
    private void move(Button currentButton) {

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


    private void setButtons(Button currentButton, String message) {
        currentButton.setText(message);
        currentButton.setTextSize(20);
        currentButton.setTextColor(Color.BLACK);
        currentButton.setEnabled(false);
    }

    private void showSnackBar(View view, int value) {

        TextView textView = new TextView(getActivity());
        Snackbar snackbar;

        switch (value) {
            case 1: {
                snackbar = Snackbar.make(view, "Player one wins!!!", Snackbar.LENGTH_LONG);
                break;
            }
            case 2: {
                snackbar = Snackbar.make(view, "Player two wins!!!", Snackbar.LENGTH_LONG);
                break;
            }
            case 3: {
                snackbar = Snackbar.make(view, "Both of Player one and twe win!!!", Snackbar.LENGTH_LONG);
                break;
            }
            case 0: {
                snackbar = Snackbar.make(view, "GameOver!!!", Snackbar.LENGTH_LONG);
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + value);
            }
        }
        snackbar.show();
        resetGame(textView);
    }

    private void resetGame(TextView textView) {
        mTableLayout.setVisibility(View.INVISIBLE);
        textView.setText("click tic tac toe button to play again!");
        FrameLayout frameLayout = getActivity().findViewById(R.id.fram_layout_tic_tac_toe);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        frameLayout.addView(textView);
    }


    private int checkWinner() {
        int value = 0;
        boolean winnerOne = false;
        boolean winnerTwo = false;


        if (mButtonsMessage[0] != null && mButtonsMessage[1] != null && mButtonsMessage[2] != null) {
            //Rows
            if (mButtonsMessage[0].equals("O") && mButtonsMessage[1].equals("O") &&
                    mButtonsMessage[2].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[0].equals("X") && mButtonsMessage[1].equals("X") &&
                    mButtonsMessage[2].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[3] != null && mButtonsMessage[4] != null && mButtonsMessage[5] != null) {
            //Rows
            if (mButtonsMessage[3].equals("O") && mButtonsMessage[4].equals("O") &&
                    mButtonsMessage[5].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[3].equals("X") && mButtonsMessage[4].equals("X") &&
                    mButtonsMessage[5].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[6] != null && mButtonsMessage[7] != null && mButtonsMessage[8] != null) {
            //Rows
            if (mButtonsMessage[6].equals("O") && mButtonsMessage[7].equals("O") &&
                    mButtonsMessage[8].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[6].equals("X") && mButtonsMessage[7].equals("X") &&
                    mButtonsMessage[8].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[0] != null && mButtonsMessage[3] != null && mButtonsMessage[6] != null) {
            //Column
            if (mButtonsMessage[0].equals("O") && mButtonsMessage[3].equals("O") &&
                    mButtonsMessage[6].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[0].equals("X") && mButtonsMessage[3].equals("X") &&
                    mButtonsMessage[6].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[1] != null && mButtonsMessage[4] != null && mButtonsMessage[7] != null) {
            //Column
            if (mButtonsMessage[1].equals("O") && mButtonsMessage[4].equals("O") &&
                    mButtonsMessage[7].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[1].equals("X") && mButtonsMessage[4].equals("X") &&
                    mButtonsMessage[7].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[2] != null && mButtonsMessage[5] != null && mButtonsMessage[8] != null) {
            //Column
            if (mButtonsMessage[2].equals("O") && mButtonsMessage[5].equals("O") &&
                    mButtonsMessage[8].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[2].equals("X") && mButtonsMessage[8].equals("X") &&
                    mButtonsMessage[5].equals("X")) {
                winnerTwo = true;
            }
        }

        if (mButtonsMessage[0] != null && mButtonsMessage[4] != null && mButtonsMessage[8] != null) {
            //Diagonal
            if (mButtonsMessage[0].equals("O") && mButtonsMessage[4].equals("O") && mButtonsMessage[8].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[0].equals("X") && mButtonsMessage[4].equals("X") && mButtonsMessage[8].equals("X")) {
                winnerTwo = true;
            }
        }
        if (mButtonsMessage[2] != null && mButtonsMessage[4] != null && mButtonsMessage[6] != null) {
            //Diagonal
            if (mButtonsMessage[2].equals("O") && mButtonsMessage[4].equals("O") && mButtonsMessage[6].equals("O")) {
                winnerOne = true;
            }
            if (mButtonsMessage[2].equals("X") && mButtonsMessage[4].equals("X") && mButtonsMessage[6].equals("X")) {
                winnerTwo = true;
            }
        }


        if (winnerOne)
            value = 1;
        if (winnerTwo)
            value = 2;
        if (winnerOne && winnerTwo)
            value = 3;

        return value;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int count = 0;
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                ids[count] = btn.getId();
                getButtonsMessage[count] = btn.getText().toString();
                if (btn.isEnabled())
                    outState.putBoolean(BUNDLE_BUTTON_ENABLE, true);
                outState.putStringArray(BUNDLE_BUTTON_MESSAGE, getButtonsMessage);
                outState.putIntArray(BUNDLE_BUTTON_IDS, ids);
                count++;

            }
        }
        outState.putInt(BUNDLE_WINNER_VALUE, winnerValue);
        outState.putInt(BUNDLE_M_COUNT, mCount);
        outState.putStringArray(BUNDLE_M_BUTTON_MESSAGE, mButtonsMessage);


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            String[] strings = savedInstanceState.getStringArray(BUNDLE_BUTTON_MESSAGE);
            int[] ints = savedInstanceState.getIntArray(BUNDLE_BUTTON_IDS);
            int count = 0;
            for (int i = 0; i < mTableLayout.getChildCount(); i++) {
                TableRow tableRow = (TableRow) mTableLayout.getChildAt(i);
                for (int j = 0; j < tableRow.getChildCount(); j++) {
                    Button btn = (Button) tableRow.getChildAt(j);
                    if (btn.getId() == ints[count]) {
                        btn.setText(strings[count]);
                        if (!btn.getText().equals(""))
                            btn.setEnabled(false);
                    }
                    count++;
                }
            }
            winnerValue = savedInstanceState.getInt(BUNDLE_WINNER_VALUE);
            mButtonsMessage = savedInstanceState.getStringArray(BUNDLE_M_BUTTON_MESSAGE);
            mCount = savedInstanceState.getInt(BUNDLE_M_COUNT);


        }
    }
}