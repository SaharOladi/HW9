package com.example.hw9;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class FourInARowFragment extends Fragment {

    private TableLayout mTableLayout;

    private int mTurn = 1;
    private int mCount = 0;
    String[][] mButtonsMessage = new String[5][5];


    public FourInARowFragment() {
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
                .inflate(R.layout.fragment_four_in_a_row, container, false);

        createButtons(fragmentTicTacToeView);
        setListener(mTableLayout);

        return fragmentTicTacToeView;
    }


    private void createButtons(View view) {

        mTableLayout = view.findViewById(R.id.table_layout_four_in_row);

        TableLayout.LayoutParams rowLp = getTableLayoutParams();
        TableRow.LayoutParams cellLp = getTableRowLayoutParams();

        mTableLayout.setGravity(Gravity.CENTER);
        int counter = 10;

        for (int row = 0; row < 5; row++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for (int column = 0; column < 5; column++) {
                Button button = new Button(getActivity());
                setWidthHeightOrientation(button);
                button.setId(counter);
                button.setTag("button" + row + column);
                tableRow.addView(button, cellLp);
                counter++;
            }

            mTableLayout.addView(tableRow, rowLp);
        }

    }

    private void setWidthHeightOrientation(Button button) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            button.setWidth(50);
            button.setHeight(50);
        } else {
            button.setWidth(5);
            button.setHeight(5);
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
                        switch (button.getTag().toString()) {
                            case "button00":
                                mButtonsMessage[0][0] = button.getText().toString();
                                break;
                            case "button01":
                                mButtonsMessage[0][1] = button.getText().toString();
                                break;
                            case "button02":
                                mButtonsMessage[0][2] = button.getText().toString();
                                break;
                            case "button03":
                                mButtonsMessage[0][3] = button.getText().toString();
                                break;
                            case "button04":
                                mButtonsMessage[0][4] = button.getText().toString();
                                break;
                            case "button10":
                                mButtonsMessage[1][0] = button.getText().toString();
                                break;
                            case "button11":
                                mButtonsMessage[1][1] = button.getText().toString();
                                break;
                            case "button12":
                                mButtonsMessage[1][2] = button.getText().toString();
                                break;
                            case "button13":
                                mButtonsMessage[1][3] = button.getText().toString();
                                break;
                            case "button14":
                                mButtonsMessage[1][4] = button.getText().toString();
                                break;
                            case "button20":
                                mButtonsMessage[2][0] = button.getText().toString();
                                break;
                            case "button21":
                                mButtonsMessage[2][1] = button.getText().toString();
                                break;
                            case "button22":
                                mButtonsMessage[2][2] = button.getText().toString();
                                break;
                            case "button23":
                                mButtonsMessage[2][3] = button.getText().toString();
                                break;
                            case "button24":
                                mButtonsMessage[2][4] = button.getText().toString();
                                break;
                            case "button30":
                                mButtonsMessage[3][0] = button.getText().toString();
                                break;
                            case "button31":
                                mButtonsMessage[3][1] = button.getText().toString();
                                break;
                            case "button32":
                                mButtonsMessage[3][2] = button.getText().toString();
                                break;
                            case "button33":
                                mButtonsMessage[3][3] = button.getText().toString();
                                break;
                            case "button34":
                                mButtonsMessage[3][4] = button.getText().toString();
                                break;
                            case "button40":
                                mButtonsMessage[4][0] = button.getText().toString();
                                break;
                            case "button41":
                                mButtonsMessage[4][1] = button.getText().toString();
                                break;
                            case "button42":
                                mButtonsMessage[4][2] = button.getText().toString();
                                break;
                            case "button43":
                                mButtonsMessage[4][3] = button.getText().toString();
                                break;
                            case "button44":
                                mButtonsMessage[4][4] = button.getText().toString();
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + button.getId());
                        }
                        mCount += 1;
                        int value = checkWinner();
                        if (value == 0) {
                            if (mCount == 25)
                                showSnackBar(mTableLayout, value);
                        } else showSnackBar(mTableLayout, value);
                    }

                });
            }
        }
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
        textView.setText("click four in row button to play again!");
        FrameLayout frameLayout = getActivity().findViewById(R.id.fram_layout_four_in_row);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        frameLayout.addView(textView);
    }

    private void move(View view) {

        Button currentButton = (Button) view;
        String message = "";

        if (mTurn == 1) {
            currentButton.getBackground().setColorFilter(0xff0000ff, PorterDuff.Mode.MULTIPLY);
            mTurn = 2;
            message = "b";
        } else if (mTurn == 2) {
            currentButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
            mTurn = 1;
            message = "r";

        }

        setButtonsContext(currentButton, message);
        currentButton.setEnabled(false);

    }

    private void setButtonsContext(Button currentButton, String message) {
        if (message == "b") {
            currentButton.setTextColor(0xff0000ff);
            currentButton.setText(message);

        } else {
            currentButton.setTextColor(0xFFFF0000);
            currentButton.setText(message);
        }
    }

    private TableRow.LayoutParams getTableRowLayoutParams() {
        return new TableRow.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT,
                1.0f);
    }

    private TableLayout.LayoutParams getTableLayoutParams() {
        return new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);
    }

    private int checkWinner() {
        int output = 0;

        //check horizontally
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (mButtonsMessage[i][j] != null && mButtonsMessage[i + 1][j] != null &&
                        mButtonsMessage[i + 2][j] != null && mButtonsMessage[i + 3][j] != null) {
                    if (mButtonsMessage[i][j].equals("b") &&
                            mButtonsMessage[i + 1][j].equals("b") &&
                            mButtonsMessage[i + 2][j].equals("b") &&
                            mButtonsMessage[i + 3][j].equals("b")) {
                        output = 1;
                        break out;
                    }
                    if (mButtonsMessage[i][j].equals("r") &&
                            mButtonsMessage[i + 1][j].equals("r") &&
                            mButtonsMessage[i + 2][j].equals("r") &&
                            mButtonsMessage[i + 3][j].equals("r")) {
                        output = 2;
                        break out;
                    }
                }
            }
        }

        //checks vertically
        out:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5 - 3; j++) {
                if (mButtonsMessage[i][j] != null && mButtonsMessage[i][j + 1] != null &&
                        mButtonsMessage[i][j + 2] != null && mButtonsMessage[i][j + 3] != null) {
                    if (mButtonsMessage[i][j].equals("b") &&
                            mButtonsMessage[i][j + 1].equals("b") &&
                            mButtonsMessage[i][j + 2].equals("b") &&
                            mButtonsMessage[i][j + 3].equals("b")) {
                        output = 1;
                        break out;
                    }
                    if (mButtonsMessage[i][j].equals("r") &&
                            mButtonsMessage[i][j + 1].equals("r") &&
                            mButtonsMessage[i][j + 2].equals("r") &&
                            mButtonsMessage[i][j + 3].equals("r")) {
                        output = 2;
                        break out;
                    }
                }
            }
        }

        //checks the right-ascending diagonal
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 0; j < 5 - 3; j++) {
                if (mButtonsMessage[i][j] != null && mButtonsMessage[i + 1][j + 1] != null &&
                        mButtonsMessage[i + 2][j + 2] != null && mButtonsMessage[i + 3][j + 3] != null) {
                    if (mButtonsMessage[i][j].equals("b") &&
                            mButtonsMessage[i + 1][j + 1].equals("b") &&
                            mButtonsMessage[i + 2][j + 2].equals("b") &&
                            mButtonsMessage[i + 3][j + 3].equals("b")) {
                        output = 1;
                        break out;
                    }
                    if (mButtonsMessage[i][j].equals("r") &&
                            mButtonsMessage[i + 1][j + 1].equals("r") &&
                            mButtonsMessage[i + 2][j + 2].equals("r") &&
                            mButtonsMessage[i + 3][j + 3].equals("r")) {
                        output = 2;
                        break out;
                    }
                }
            }
        }

        //checks the left-ascending diagonal
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 3; j < 5; j++) {
                if (mButtonsMessage[i][j] != null && mButtonsMessage[i + 1][j - 1] != null &&
                        mButtonsMessage[i + 2][j - 2] != null && mButtonsMessage[i + 3][j - 3] != null) {
                    if (mButtonsMessage[i][j].equals("b") &&
                            mButtonsMessage[i + 1][j - 1].equals("b") &&
                            mButtonsMessage[i + 2][j - 2].equals("b") &&
                            mButtonsMessage[i + 3][j - 3].equals("b")) {
                        output = 1;
                        break out;
                    }
                    if (mButtonsMessage[i][j].equals("r") &&
                            mButtonsMessage[i + 1][j - 1].equals("r") &&
                            mButtonsMessage[i + 2][j - 2].equals("r") &&
                            mButtonsMessage[i + 3][j - 3].equals("r")) {
                        output = 2;
                        break out;
                    }
                }
            }
        }
        return output;
    }

}