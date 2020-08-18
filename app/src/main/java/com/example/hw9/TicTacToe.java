package com.example.hw9;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class TicTacToe extends Fragment {



    public TicTacToe() {
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

        createButton();


        return fragmentTicTacToeView;
    }


    private Button[] createButton() {
        LinearLayout[] linearLayouts = createLayoutForButtons();
        Button[] button = new Button[9];

        for (int i = 0; i < 9; i++) {
            button[i].setId(i + 1);
        }

        linearLayouts[0].addView(button[0]);
        linearLayouts[0].addView(button[1]);
        linearLayouts[0].addView(button[2]);

        linearLayouts[1].addView(button[0]);
        linearLayouts[1].addView(button[1]);
        linearLayouts[1].addView(button[2]);

        linearLayouts[2].addView(button[0]);
        linearLayouts[2].addView(button[1]);
        linearLayouts[2].addView(button[2]);

        return button;
    }

    @SuppressLint("ResourceType")
    private LinearLayout[] createLayoutForButtons() {
        LinearLayout linearLayout1 = new LinearLayout(getActivity());
        LinearLayout linearLayout2 = new LinearLayout(getActivity());
        LinearLayout linearLayout3 = new LinearLayout(getActivity());

        linearLayout1.setId(1);
        linearLayout2.setId(2);
        linearLayout3.setId(3);

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout1.setWeightSum(3);
        linearLayout2.setWeightSum(3);
        linearLayout3.setWeightSum(3);

        layoutParams1.setMargins(20, 50, 20, 50);
        linearLayout1.setLayoutParams(layoutParams1);

        layoutParams2.setMargins(20, 60, 20, 60);
        linearLayout2.setLayoutParams(layoutParams1);

        layoutParams3.setMargins(20, 70, 20, 70);
        linearLayout3.setLayoutParams(layoutParams1);

        return new LinearLayout[]{linearLayout1, linearLayout2, linearLayout3};
    }

    private void findViews(){
        Button[] buttons = createButton();
        LinearLayout[] linearLayouts = createLayoutForButtons();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].getId();
        }
    }
}