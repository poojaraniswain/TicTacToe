package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Boolean gameactive=true;

    //PLAYER REPRESENTATION
    //0=X
    //1=O
    int activePlayer=0;
    int count=0;
    //state meaning:0-X,1-O,2-NULL
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    //WINNING POSITION SUGGEST THE STATE IN WHICH A PLAYER CAN WIH
    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},
                              {0,3,6},{1,4,7},{2,5,8},
                               {0,4,8},{2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView status=findViewById(R.id.textView);
        status.setTextColor(Color.MAGENTA);

    }

    public void playertap(View view){
        ImageView img=(ImageView)view;
        int tappedimage=Integer.parseInt(img.getTag().toString());

        if((gamestate[tappedimage] == 2) && gameactive){
            gamestate[tappedimage]=activePlayer;
            img.setTranslationY(-1000f);
            count+=1;
            if(activePlayer==0){
                img.setImageResource(R.drawable.ximage);

                if(count==9){
                    TextView status=findViewById(R.id.status);
                    status.setText("Tap to Restart");
                    status.setTextColor(Color.BLACK);
                    TextView won=findViewById(R.id.won);
                    won.setText("No one wins");
                    gameactive=false;
                }
                else {
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("PLAYER 2 TURN");
                    status.setTextColor(Color.BLACK);
                }
            }
            else{
                img.setImageResource(R.drawable.oimage);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("PLAYER 1 TURN");
                status.setTextColor(Color.BLACK);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        else if(!gameactive)
        {
            reset(view);
        }

        // CHECK IF PLAYER HAS WON
        for (int[] position:winningposition){
            if(gamestate[position[0]]==gamestate[position[1]] &&
                    gamestate[position[1]]==gamestate[position[2]] &&
                    gamestate[position[0]]!=2){
                TextView won=findViewById(R.id.won);
                won.setTextColor(Color.RED);
                TextView status=findViewById(R.id.status);
                status.setText("Tap to Restart");
                status.setTextColor(Color.BLACK);
                gameactive=false;
                if (gamestate[position[0]]==0){

                    won.setText("player1 has won");

;                }
                else{
                    won.setText("player2 has won");

                }

            }
        }


    }
    public void reset(View view){
        activePlayer=0;
        count=0;
        for (int i=0;i<gamestate.length;i++) {
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageview0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageview8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("PLAYER 1 TURN");
        TextView won=findViewById(R.id.won);
        won.setText(" ");
        gameactive=true;


    }
    public void turns(View view){
        if(!gameactive){
            reset(view);
        }

    }
}
