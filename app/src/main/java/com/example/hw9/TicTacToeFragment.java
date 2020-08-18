package com.example.hw9;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


public class TicTacToeFragment extends Fragment {

    private TableLayout mTableLayout;


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

//        mTableLayout.setStretchAllColumns(true);

        int counter = 1;
        for (int row = 0; row < 3; row++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int column = 0; column < 3; column++) {
                Button button = new Button(getActivity());
                button.setId(counter);
                button.setWidth(10);
                button.setHeight(10);

                tableRow.addView(button);

                counter++;
            }
            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }


}