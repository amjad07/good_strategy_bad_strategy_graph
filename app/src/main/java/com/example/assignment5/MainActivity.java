package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    Button startgame_btn,stopgame_btn,showgrowth_btn;
    Boolean startgame=false;
    static Boolean stopgame=false;
    Boolean showgrowth=false;
    int totalamount=100;
    double good_strategy=100.0,bad_startegy=100.0;
    private Handler mHandler = new Handler();
    public static List<Double> good_list;
    public static List<Double> bad_list;
    public  TextView textView;
    int counter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        startgame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(startgame==false)
                {
                    startgame=true;
                    stopgame=false;
                    showgrowth=true;
                    counter=0;
                    textView.setText(""+100+"$");
                    Toast.makeText(MainActivity.this, "You start the Game", Toast.LENGTH_SHORT).show();
                    new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        while (bad_startegy>0 && stopgame==false)
                        {
                            try
                            {
                                Thread.sleep(1000);
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        bad_startegy = bad_startegy + (100 * getRandomInteger(1.0, -1.0));
                                        if(bad_startegy>0)
                                        {
                                           bad_list.add(bad_startegy);

                                        }



                                    }
                                });


                            }
                            catch (Exception e)
                            {

                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    try {
                                        Thread.sleep(1000);
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                good_strategy = good_strategy + (100 * getRandomInteger(01.0, 0.0));
                                                counter=counter+1;
                                                if(bad_startegy>0)
                                                {
                                                    good_list.add(good_strategy);

                                                }
                                                if(counter%10==0)
                                                {
                                                    textView.setText(""+bad_startegy+"$");
                                                }




                                            }
                                        });
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }).start();
                            if(bad_startegy<=0)
                            {
                                stopgametoast();
                            }
                        }
                        }
                    }).start();
                    if(bad_startegy<0)
                    {
                        Toast.makeText(MainActivity.this, "Your Balance Goes to Zero", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Your Game is already running", Toast.LENGTH_SHORT).show();
                }


            }
        });
        stopgame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(startgame==true)
                {
                    stopgame=true;
                    startgame=false;
                    Toast.makeText(MainActivity.this, "You Stopped The Game", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Start game First", Toast.LENGTH_SHORT).show();
                }

            }
        });
        showgrowth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(startgame==true || showgrowth==true)
                {
                    Intent intent=new Intent(MainActivity.this, GraphActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Start game First", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void stopgametoast()
    {
//        textView.setText(""+0);
        stopgame=true;
        startgame=false;

    }

    public void init()
    {
        startgame_btn=(Button)findViewById(R.id.startgame_btn);
        stopgame_btn=(Button)findViewById(R.id.stopgame_btn);
        showgrowth_btn=(Button)findViewById(R.id.showgrowth_btn);
        textView=(TextView)findViewById(R.id.text_view);
        good_list=new ArrayList<Double>();
        bad_list=new ArrayList<Double>();
        good_list.add(100.0);
        bad_list.add(100.0);

    }
    public  double getRandomInteger(double maximum, double minimum)
    {
        double value=((double) (Math.random()*(maximum - minimum))) + minimum;
        double round=Double.parseDouble(String.format("%.2f", value));
        return round; }


}