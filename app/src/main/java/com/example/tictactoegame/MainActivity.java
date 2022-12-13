package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tictactoegame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    boolean gameActive =true;
    int activePlayer=0;
    int[] gameStates ={2,2,2,2,2,2,2,2,2};
    int[][] winPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());

        if(gameStates[tappedCounter]==2 && gameActive) {
            gameStates[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration((long) 300f);
            for(int[] winPos: winPositions) {

                if (gameStates[winPos[0]] == gameStates[winPos[1]] && gameStates[winPos[1]] == gameStates[winPos[2]]
                        && gameStates[winPos[0]] != 2)
                {
                    gameActive =false;
                    String winner = "Player Two";
                    if (gameStates[winPos[0]] == 0) {
                        winner = "Player One";
                    }

                    binding.winnerMessage.setText(winner + " has won!!");

                    binding.playAgainLayout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIsOver=true;
                    for(int counterState: gameStates)
                    {
                        if(counterState==2) gameIsOver=false;
                    }
                    if(gameIsOver)
                    {
                        binding.winnerMessage.setText( "Its a draw");

                        binding.playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void playAgain(View view)
    {
        gameActive =true;
        binding.playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer=0;
        for(int i = 0; i< gameStates.length; i++)
        {
            gameStates[i]=2;
        }

        for(int i=0;i<binding.gridLayout.getChildCount();i++){
           ImageView img = (ImageView) binding.gridLayout.getChildAt(i);
           img.setImageResource(0);
        }


    }
}
