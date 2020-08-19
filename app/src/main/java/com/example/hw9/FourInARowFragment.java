package com.example.hw9;

import android.os.Bundle;

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

        return fragmentTicTacToeView;
    }


    private void createButtons(View view) {

        mTableLayout = view.findViewById(R.id.table_layout_four_in_row);
        mTableLayout.setGravity(Gravity.CENTER);
        int counter = 0;

        for (int row = 0; row < 5; row++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for (int column = 0; column < 5; column++) {
                Button button = new Button(getActivity());
//                button.setWidth(25);
//                button.setHeight(25);
                button.setId(counter);
                tableRow.addView(button);
                counter++;
            }

            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }
}