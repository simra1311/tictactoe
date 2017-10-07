package com.example.android.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NO_PLAYER = 0;
    public static final int PLAYER_O = 1;
    public static final int PLAYER_X = 2;
    public static final int INCOMPLETE = 0;
    public static final int PLAYER_O_WON = 1;
    public static final int PLAYER_X_WON = 2;
    public static final int DRAW = 3;
    public int currentStatus = INCOMPLETE;
    public int size ;
    LinearLayout rootLayout;
    LinearLayout[] rows ;
    CustomButton[][] board ;
    SharedPreferences.Editor editor;

    public int currentPlayer = PLAYER_O;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        Bundle b = i.getExtras();

        final SharedPreferences sharedPreferences = getSharedPreferences("tictactoe", MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        editor.putString("Hello", "123");
//        editor.commit();
//       int n  =  sp.getInt("score", -1);
        String w = sharedPreferences.getString("winner", "new game");
       Toast.makeText(this,w,Toast.LENGTH_SHORT).show();
//
        size = i.getIntExtra("level",-1);

        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        setBoard(size);
    }

    public void  setBoard(int size){

        rows = new LinearLayout[size];
        board = new CustomButton[size][size];
        currentPlayer = PLAYER_O;
        currentStatus = INCOMPLETE;
        rootLayout.removeAllViews();

        for (int i = 0; i< size; ++i){

                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,0 ,1);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(params);
                rows[i] = linearLayout;
                rootLayout.addView(linearLayout);
              }

        for (int i = 0; i < size; ++i){
            for (int j = 0; j < size; ++j){
                CustomButton button = new CustomButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0 , ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(params);
                button.setOnClickListener(this);
                board[i][j] = button;
                rows[i].addView(button);
            }
        }
    }

    @Override
    public void onClick(View view) {

        if(currentStatus == INCOMPLETE){
            CustomButton customButton = (CustomButton)view;
            if(!customButton.isEmpty()){
                Toast.makeText(this,"Invalid move",Toast.LENGTH_SHORT).show();
                return;
            }
            customButton.setPlayer(currentPlayer);
            checkGameStatus();

            toggleCurrentPlayer();
        }
        else
            Toast.makeText(this,"Game Over! Reset to start a new one", Toast.LENGTH_LONG).show();
    }

    private void checkGameStatus() {

        for (int i = 0; i <size;++i){
            boolean rowSame = true;
            for (int j = 0;j< size;++j){
                if(board[i][j].isEmpty() || board[i][j].getPlayer() != board[i][0].getPlayer()){
                    rowSame = false;
                    break;
                }
            }
            if (rowSame == true){
                int winner = board[i][0].getPlayer();
                setWinner(winner);
                return;
            }
        }
        for (int i = 0; i<size; ++i){
            boolean curCol = true;
            for (int j = 0; j < size ;++j){
                if(board[j][i].isEmpty() || board[j][i].getPlayer() != board[0][i].getPlayer()){
                    curCol = false;
                    break;
                }
            }
            if (curCol == true){
                int winner = board[0][i].getPlayer();
                setWinner(winner);
                return;
            }
        }
        boolean d1same = true;
        for (int i = 0; i < size ;++i){
            if(board[i][i].isEmpty() || board[i][i].getPlayer() != board[0][0].getPlayer()){
                d1same = false;
                break;
            }
        }
        if (d1same == true){
            int winner = board[0][0].getPlayer();
            setWinner(winner);
            return;
        }

        boolean d2same = true;
        for (int i = 0; i<size; ++i){
            if (board[i][size-i-1].isEmpty() || board[i][size-i-1].getPlayer() != board[0][size-1].getPlayer()){
                d2same = false;
                break;
            }
        }
        if(d2same == true){
            int winner  = board[0][size-1].getPlayer();
            setWinner(winner);
            return;
        }

        for (int i= 0; i< size ;++i){
            for (int j = 0; j<size;++j){
                if (board[i][j].isEmpty()){
                  currentStatus = INCOMPLETE;
                    return;
                }
            }
        }

        currentStatus = DRAW;
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
    }

    private void setWinner(int winner) {
        if (winner == PLAYER_O){
            currentStatus = PLAYER_O_WON;
            Toast.makeText(this,"Player O won!",Toast.LENGTH_SHORT).show();
            editor.putString("winner","Last winner:Player_O");
            editor.commit();
        }
        else {
            currentStatus = PLAYER_X_WON;
            Toast.makeText(this,"Player X won!",Toast.LENGTH_SHORT).show();
            editor.putString("winner","Last winner:Player_X");
            editor.commit();
        }
    }

    private void toggleCurrentPlayer() {
        currentPlayer = currentPlayer == PLAYER_O?PLAYER_X:PLAYER_O;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.reset){
            size = 3;
            setBoard(size);
        }
        else if(id == R.id.demo){
            size = 4;
            setBoard(size);
        }
        return super.onOptionsItemSelected(item);
    }
}
