package com.gamestudio24.martianrun.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

public class SaveScore {

    private Preferences preferences;
    private static final String PREFS_NAME = "martian_run";

    protected Preferences getPrefs() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences(PREFS_NAME);
        }
        return preferences;
    }

    public void setScore(int score) {
        Preferences scores = getPrefs();

        if (!scores.contains("highScore")) {
            scores.putInteger("highScore", score);
            scores.flush();
        } else {
            int currentHighScore = scores.getInteger("highScore", 0);
            if (currentHighScore < score) {
                scores.putInteger("highScore", score);
                int i = 1;
                while (scores.contains("score" + i)) {
                    i++;
                }
                scores.putInteger("score" + i, currentHighScore);
                scores.flush();
            }
            else {
                int i = 1;
                while (scores.contains("score" + i)) {
                    i++;
                }
                scores.putInteger("score" + i, score);
                scores.flush();
            }
        }
    }

    public int getHighScore() {
        Preferences scores = getPrefs();
        return scores.getInteger("highScore");
    }

    public ArrayList<Integer> getScores() {
        ArrayList<Integer> scores_array = new ArrayList<Integer>();
        Preferences scores = getPrefs();
        int i = 1;
        while (scores.contains("score" + i)) {
            i++;
            scores_array.add(scores.getInteger("score" + i));
        }
        return scores_array;
    }

    public void reach500() {
        Preferences scores = getPrefs();
        scores.putBoolean("reach500", true);
        scores.flush();
    }

    public boolean isrreach500() {
        Preferences scores = getPrefs();
        return scores.getBoolean("reach500");
    }

    public void reach1000() {
        Preferences scores = getPrefs();
        scores.putBoolean("reach1000", true);
        scores.flush();
    }

    public boolean isrreach1000() {
        Preferences scores = getPrefs();
        return scores.getBoolean("reach500");
    }

    public void reach2500() {
        Preferences scores = getPrefs();
        scores.putBoolean("reach2500", true);
        scores.flush();
    }

    public boolean isrreach2500() {
        Preferences scores = getPrefs();
        return scores.getBoolean("reach2500");
    }

    public void reach5000() {
        Preferences scores = getPrefs();
        scores.putBoolean("reach5000", true);
        scores.flush();
    }

    public boolean isrreach5000() {
        Preferences scores = getPrefs();
        return scores.getBoolean("reach5000");
    }
}
