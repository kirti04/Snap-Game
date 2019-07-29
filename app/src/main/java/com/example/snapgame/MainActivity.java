package com.example.snapgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> card_list =  new ArrayList<>();
    int pack = 4;
    RadioButton face;
    RadioButton suit;
    RadioButton both;
    Button start;
    public static int condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face = (RadioButton)findViewById(R.id.ch_face);
        suit = (RadioButton)findViewById(R.id.ch_suit);
        both = (RadioButton)findViewById(R.id.ch_both);
        start = (Button)findViewById(R.id.btn_start);
        int total_cards = pack*13;


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(face.isChecked()){
                    condition = 1;
                }else if(suit.isChecked()){
                    condition = 2;
                }else{
                    condition =3;
                }
                cardsShuffle();
                Intent intent= new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);

            }
        });




    }

    private void cardsShuffle() {

        //if the condition is both Fcae valaue and suit, the pack of cards should be more then 1.
        // I am hardcoding it to 2 instead of checking from the edit text value.
        if(condition == 3){
            pack = 27;
        }else{
            pack = 14;
        }

        for(int i =1; i <pack; i++){
            card_list.add(i+"A");//represents Black Club
        }
        for(int i =1; i <pack; i++){
            card_list.add(i+"B");// represents Black Spade
        }
        for(int i =1; i <pack; i++){
            card_list.add(i+"C");//represents Red Heart
        }
        for(int i =1; i <pack; i++){
            card_list.add(i+"D");//represents Red Diamond
        }

        System.out.println("show list"+  card_list.toString());
    }
}
