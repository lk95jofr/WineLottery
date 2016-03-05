package com.toonsnet.tools.winelottery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LotteryActivity extends Activity {
    private static final String TAG = "LotteryActivity";

    private ArrayList<String> contestantList = new ArrayList<String>();

    TextView contestantView;
    TextView resultView;

    String choosen = "";
    boolean hasWinner = false;

    Handler mHandler = new Handler();

    int nrOfSpin = 0;
    int onSpin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_lottery);

        Intent intent = getIntent();
        contestantList = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);

        contestantView = (TextView)findViewById(R.id.contestantView);
        resultView = (TextView)findViewById(R.id.resultView);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lottery, menu);

        Button buttonRun = (Button) findViewById(R.id.runLottery);
        buttonRun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                contestantView.setText("");
                resultView.setText("");

                chooseContestant();
                if (isWinner()) {
                    hasWinner = true;
                }

                nrOfSpin = (contestantList.size() > 1) ? contestantList.size() * 5 : 0;
                onSpin = 0;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        contestantView.setText(contestantList.get(onSpin++));
                        if (onSpin == contestantList.size()) {
                            onSpin = 0;
                        }

                        if (nrOfSpin-- > 0) {
                            mHandler.postDelayed(this, 300);
                        } else {
                            contestantView.setText(choosen);
                        }
                    }
                };
                mHandler.post(runnable);

                nrOfSpin = (contestantList.size() > 1) ? 100 : 0;
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        if (resultView.getText().equals("WINNER")) {
                            resultView.setText("Looser");
                        } else {
                            resultView.setText("WINNER");
                        }

                        if (nrOfSpin-- > 0) {
                            mHandler.postDelayed(this, 200);
                        } else {
                            if (hasWinner) {
                                resultView.setText("WINNER");
                            } else {
                                resultView.setText("Looser");
                            }
                        }
                    }
                };
                mHandler.post(runnable2);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chooseContestant() {
        contestantList.remove(choosen);

        int numberOfContestant = contestantList.size();
        Log.i(TAG, "numberOfContestant: " + numberOfContestant);

        if (numberOfContestant > 1) {
            int x = getRandomNumber(numberOfContestant);

            choosen = contestantList.get(x);
        } else {
            choosen = contestantList.get(0);
            hasWinner = true;
        }

        Log.i(TAG, "choosen: " + choosen);
    }

    private boolean isWinner() {
        int first = getRandomNumber(1000);
        int second = getRandomNumber(1000);

        return first == second;
    }

    private int getRandomNumber(int x) {
        Random random = new Random();
        return random.nextInt(x - 0);
    }

}
