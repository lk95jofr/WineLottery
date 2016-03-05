package com.toonsnet.tools.winelottery;

import android.os.Handler;
import android.util.Log;

import java.util.Random;

public class Lottery {
    private static final String TAG = "Lottery";
    
    Handler mHandler = new Handler();

    public void Lottery() {
    }

    public int getNumber(int numberOfContestant) {
        return getRandomNumber(numberOfContestant);
    }

    public boolean isWinner() {
        int first = getRandomNumber(1000);
        int second = getRandomNumber(1000);

        Log.d(TAG, "First: " + first);
        Log.d(TAG, "Second: " + second);

        return first == second;
    }

    private int getRandomNumber(int x) {
        Random random = new Random();
        return random.nextInt(x - 0);
    }

    public void runLotteryWheel() {
    }
}
