package com.example.snapgame;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    ImageView player1_img;
    ImageView player2_img;
    TextView player1_txt;
    TextView player2_txt;
    TextView score_one;
    TextView score_two;
    LinearLayout win_layout;
    LinearLayout game_layout;
    ImageView snap;
    static int face_value1;
    static  int face_value2;
    String suit_value1;
    String suit_value2;
    String number_value1;
    String number_value2;
    boolean player_turn = false;

    String value1;
    String value2;
    int min = 0;
    int max ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        setLayout();
        final Random r = new Random();
        snap.setClickable(true);
        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max = MainActivity.card_list.size()-1;
                int random_number = r.nextInt(max - min + 1) + min;
                if(player_turn){
                    player_turn = false;
                    value2 = MainActivity.card_list.get(random_number);
                    MainActivity.card_list.remove(random_number);
                    if(value2.length()>2){
                        number_value2=value2.substring(0,2);
                        player2_txt.setText(number_value2);
                        suit_value2 = value2.substring(2,3);
                        face_value2= face_value2 + Integer.parseInt(number_value2);

                    }else{
                        number_value2=value2.substring(0,1);
                        player2_txt.setText(number_value2);
                        suit_value2 = value2.substring(1,2);
                        face_value2= face_value2 + Integer.parseInt(number_value2);

                    }
                    score_two.setText("Player 2 : "+String.valueOf(face_value2));
                    if(suit_value2.equals("A")){
                        player2_img.setBackgroundResource(R.drawable.club);
                    }else if (suit_value2.equals("B")){
                        player2_img.setBackgroundResource(R.drawable.spade);
                    }else if (suit_value2.equals("C")){
                        player2_img.setBackgroundResource(R.drawable.dimond);
                    } else{
                        player2_img.setBackgroundResource(R.drawable.heart);
                    }
                    System.out.println("player2 card"+ value2);
                    check_snap();


                }else{
                    player_turn = true;
                    value1 = MainActivity.card_list.get(random_number);
                    MainActivity.card_list.remove(random_number);
                    System.out.println("player1 card"+ value1);
                    if(value1.length()>2){
                        number_value1 = value1.substring(0,2);
                        player1_txt.setText(number_value1);
                        face_value1= face_value1 + Integer.parseInt(number_value1);
                        suit_value1 = value1.substring(2,3);
                    }else{
                        number_value1 = value1.substring(0,1);
                        player1_txt.setText(number_value1);
                        face_value1= face_value1 + Integer.parseInt(number_value1);
                        suit_value1 = value1.substring(1,2);
                    }
                    score_one.setText("Player 1 :"+String.valueOf(face_value1));
                    if(suit_value1.equals("A")){
                        player1_img.setBackgroundResource(R.drawable.club);
                    }else if (suit_value1.equals("B")){
                        player1_img.setBackgroundResource(R.drawable.spade);
                    }else if (suit_value1.equals("C")){
                        player1_img.setBackgroundResource(R.drawable.dimond);
                    } else{
                        player1_img.setBackgroundResource(R.drawable.heart);
                    }
                }


            }
        });



    }

    private void setLayout() {
        snap =  (ImageView)findViewById(R.id.snap_click);
        player1_img = (ImageView)findViewById(R.id.player1_img);
        player2_img = (ImageView)findViewById(R.id.player2_img);
        player1_txt = (TextView) findViewById(R.id.player1_val);
        player2_txt = (TextView)findViewById(R.id.player2_val);
        score_one= (TextView) findViewById(R.id.txt_score_1);
        score_two = (TextView)findViewById(R.id.txt_score_2);
        win_layout= (LinearLayout)findViewById(R.id.win_layout);
        game_layout= (LinearLayout)findViewById(R.id.win_layout);

    }

    public void check_snap() {
        System.out.println("size"+MainActivity.card_list.size());
        if(MainActivity.card_list.size() == 0){
            Toast.makeText(PlayActivity.this,"No cards Matched!! Make selection and start playing again...",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(PlayActivity.this, MainActivity.class);
            startActivity(intent);
            PlayActivity.this.finish();

        }else{
            switch (MainActivity.condition){
                case 1:
                    checkFaceValue();
                    break;
                case 2:
                    checkSuitValue();
                    break;
                case 3:
                    checkBoth();
                    break;
                default:

                    break;

            }

        }


    }

    private void checkBoth() {

        if(value1.equals(value2)){
            game_layout.setVisibility(View.GONE);
            win_layout.setVisibility(View.VISIBLE);

            if(face_value1 > face_value2 )
                Toast.makeText(this,"Player1 wins the Game with greater Face Value",Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(this,"Player2 wins the Game with greater Face Value",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void checkSuitValue() {
        if(suit_value1.equals(suit_value2)){
            game_layout.setVisibility(View.GONE);
            win_layout.setVisibility(View.VISIBLE);

            if(face_value1 > face_value2 ) {
                Toast.makeText(this, "Player1 wins the Game with greater Face Value", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Player2 wins the Game with greater Face Value",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void checkFaceValue() {
        if(number_value1.equals(number_value2)){
            game_layout.setVisibility(View.GONE);
            win_layout.setVisibility(View.VISIBLE);

            if(face_value1 > face_value2 )
                Toast.makeText(this,"Player1 wins the Game with greater Face Value",Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(this,"Player2 wins the Game with greater Face Value",Toast.LENGTH_SHORT).show();
            }


        }
    }


}
