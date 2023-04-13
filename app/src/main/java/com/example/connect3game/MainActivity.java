package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0:yellow, 1:red, 2:empty
    int activePlayer = 0;
    int[] boardState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameIsPositive = true;
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int imagePosition = Integer.parseInt(counter.getTag().toString());

        if(boardState[imagePosition] == 2 && gameIsPositive) {

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                boardState[imagePosition] = activePlayer;
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                boardState[imagePosition] = activePlayer;
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).setDuration(200);

            for (int[] WP : winPosition) {
                String winner;
                if (boardState[WP[0]] == boardState[WP[1]] && boardState[WP[1]] == boardState[WP[2]] && boardState[WP[0]] != 2) {
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameIsPositive = false;
                    TextView textView = (TextView) findViewById(R.id.textWinner);
                    textView.setText(winner + " has won!");
                    textView.setVisibility(View.VISIBLE);
                    Button playAgainButton = (Button) findViewById(R.id.playAgain);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    public void playAgain(View view) {

        TextView textView = (TextView) findViewById(R.id.textWinner);
        textView.setVisibility(View.INVISIBLE);
        Button playAgainButton = (Button) findViewById(R.id.playAgain);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout boardGrid = (GridLayout) findViewById(R.id.boardGridLayout);
        Log.i("info","ok");
        for(int i = 0; i < boardGrid.getChildCount(); i++) {
            ImageView position = (ImageView) boardGrid.getChildAt(i);
            position.setImageDrawable(null);
        }

        activePlayer = 0;
        for(int i = 0; i < boardState.length; i++) {
            boardState[i] = 2;
        }
        gameIsPositive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}