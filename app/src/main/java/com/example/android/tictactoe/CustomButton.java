package com.example.android.tictactoe;

import android.content.Context;
import android.widget.Button;

import static android.icu.text.Normalizer.NO;
import static com.example.android.tictactoe.MainActivity.NO_PLAYER;
import static com.example.android.tictactoe.MainActivity.PLAYER_O;
import static com.example.android.tictactoe.MainActivity.PLAYER_X;

/**
 * Created by Simra Afreen on 27-08-2017.
 */

public class CustomButton extends Button {

    int player;

    public CustomButton(Context context) {
        super(context);
       // this.setBackground(R.drawable.button_bg);
        setBackgroundResource(R.drawable.button_bg);
    }

    public boolean isEmpty() {
        return player == NO_PLAYER;
    }

    public void setPlayer(int currentPlayer) {
        this.player = currentPlayer;
        if(player == PLAYER_O)
            setText("O");
        else if(player == PLAYER_X)
            setText("X");
        else
            setText("");
    }

    public int getPlayer() {
        return player;
    }
}
