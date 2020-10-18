package com.example.alihashem.connected3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow,1:red
    int active_player=0;
    int []game_state={2,2,2,2,2,2,2,2,2};
    int [][]winning_position={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Boolean game_active=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view) {

        ImageView img = (ImageView) view;
        int tapped = Integer.parseInt(img.getTag().toString());
        if (game_state[tapped] == 2&&game_active) {
            game_state[tapped] = active_player;

            img.setTranslationY(-1500);
            if (active_player == 1) {
                img.setImageResource(R.drawable.yellow);
                active_player = 0;
            } else {
                img.setImageResource(R.drawable.red);
                active_player = 1;
            }

            img.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winnig_position : winning_position) {
                if (game_state[winnig_position[0]] == game_state[winnig_position[1]] && game_state[winnig_position[1]] == game_state[winnig_position[2]] && game_state[winnig_position[0]] != 2) {
                    String winner = "";
                    game_active=false;
                    if (active_player == 1) {
                        winner = "red";

                    } else {
                        winner = "yellow";
                    }
                    TextView tv=(TextView)findViewById(R.id.textView_winner);
                    tv.setText(winner + "has won");
                    Button btn=(Button)findViewById(R.id.button_play_again);
                    tv.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void play_again(View view) {
        TextView tv=(TextView)findViewById(R.id.textView_winner);
        Button btn=(Button)findViewById(R.id.button_play_again);
        tv.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout layout= (android.support.v7.widget.GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<layout.getChildCount();i++)
        {
            ImageView child=(ImageView)layout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i=0;game_state.length>i;i++)
        {
            game_state[i]=2;
        }
        active_player=0;
        game_active=true;

    }


}
