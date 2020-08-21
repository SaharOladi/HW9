package com.example.hw9;

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
import android.widget.TableLayout;
import android.widget.TableRow;

public class FourInARowFragment extends Fragment {

    private TableLayout mTableLayout;

    private int mTurn = 1;
    private int count = 0;
    private String mStringButtonColor = "";
    private String[] mStringsButtonsColor = new String[25];

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
        int counter = 0;

        for (int row = 0; row < 5; row++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for (int column = 0; column < 5; column++) {
                Button button = new Button(getActivity());
//                button.setWidth(50);
//                button.setHeight(5);
                button.setId(counter);
                tableRow.addView(button, cellLp);
                counter++;
            }

            mTableLayout.addView(tableRow, rowLp);
        }

    }

    private void setListener(View view) {

        TableLayout tableLayout = (TableLayout) view;

        for (int i = 0; i < tableLayout.getChildCount(); i++) {

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {

                Button btn = (Button) tableRow.getChildAt(j);

                btn.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        count += 1;
                        move(v);
                        if (count == 25) {
                            String[] str = createStringsArray();
                            checkWinner(str);

                        }
                    }

                });
            }
        }
    }

    private String extractMessageTableLayout(View view) {

        TableLayout tableLayout = (TableLayout) view;
        String output = "";

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            //This will iterate through the table row.
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                output += btn.getText() + " ";
            }
        }
        return output;

    }

    private String[] createStringsArray() {

        String str = extractMessageTableLayout(mTableLayout);
        mStringsButtonsColor = str.split(" ");
        return mStringsButtonsColor;

    }

    private void move(View view) {

        Button currentButton = (Button) view;
        String message = "";

        if (mTurn == 1) {
            currentButton.getBackground().setColorFilter(0xff0000ff, PorterDuff.Mode.MULTIPLY);
            mTurn = 2;
            message = "blue";
        } else if (mTurn == 2) {
            currentButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
            mTurn = 1;
            message = "red";

        }

        setButtonsContext(currentButton, message);
        currentButton.setEnabled(false);

    }

    private void setButtonsContext(Button currentButton, String message) {
        if (message == "blue") {
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

    private int checkWinner(String[] strings) {
        int output = 0;
        String[][] strInp = convertOneArrayDimensional(strings);


        //check horizontally
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (strInp[i][j].equals("blue") &&
                        strInp[i + 1][j].equals("blue") &&
                        strInp[i + 2][j].equals("blue") &&
                        strInp[i + 3][j].equals("blue")) {
                    output = 1;
                    break out;
                }
            }
        }

        //checks vertically
        out:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5 - 3; j++) {
                if (strInp[i][j].equals("blue") &&
                        strInp[i][j + 1].equals("blue") &&
                        strInp[i][j + 2].equals("blue") &&
                        strInp[i][j + 3].equals("blue")) {
                    output = 1;
                    break out;
                }
            }
        }

        //checks the right-ascending diagonal
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 0; j < 5 - 3; j++) {
                if (strInp[i][j].equals("blue") &&
                        strInp[i + 1][j + 1].equals("blue") &&
                        strInp[i + 2][j + 2].equals("blue") &&
                        strInp[i + 3][j + 3].equals("blue")) {
                    output = 1;
                    break out;

                }
            }
        }

        //checks the left-ascending diagonal
        out:
        for (int i = 0; i < 5 - 3; i++) {
            for (int j = 3; j < 5; j++) {
                if (strInp[i][j].equals("blue") &&
                        strInp[i + 1][j - 1].equals("blue") &&
                        strInp[i + 2][j - 2].equals("blue") &&
                        strInp[i + 3][j - 3].equals("blue")) {
                    output = 1;
                    break out;
                }
            }
        }
        return output;

    }


    private String[][] convertOneArrayDimensional(String[] strings) {
        int counter = 0;
        String[][] inpStrings = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inpStrings[i][j] = strings[counter];
                counter++;
            }
        }
        return inpStrings;
    }
}