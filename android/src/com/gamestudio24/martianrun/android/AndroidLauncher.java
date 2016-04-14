/*
 * Copyright (c) 2014. William Mora
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gamestudio24.martianrun.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamestudio24.martianrun.MartianRun;
import com.gamestudio24.martianrun.utils.Constants;
import com.gamestudio24.martianrun.utils.GameEventListener;
import com.gamestudio24.martianrun.utils.GameManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.games.Games;

public class AndroidLauncher extends AndroidApplication implements GameEventListener {

    private static String SAVED_LEADERBOARD_REQUESTED = "SAVED_LEADERBOARD_REQUESTED";
    private static String SAVED_ACHIEVEMENTS_REQUESTED = "SAVED_ACHIEVEMENTS_REQUESTED";

    private AdView mAdView;
    private boolean mLeaderboardRequested;
    private boolean mAchievementsRequested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        // Game view
        View gameView = initializeForView(new MartianRun(this), config);
        layout.addView(gameView);


        mAdView = createAdView();
        mAdView.loadAd(createAdRequest());

        layout.addView(mAdView, getAdParams());

        setContentView(layout);

        //gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        //gameHelper.setup(this);
        //gameHelper.setMaxAutoSignInAttempts(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_LEADERBOARD_REQUESTED, mLeaderboardRequested);
        outState.putBoolean(SAVED_ACHIEVEMENTS_REQUESTED, mAchievementsRequested);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLeaderboardRequested = savedInstanceState.getBoolean(SAVED_LEADERBOARD_REQUESTED, false);
        mAchievementsRequested = savedInstanceState.getBoolean(SAVED_ACHIEVEMENTS_REQUESTED, false);
    }

    private AdRequest createAdRequest() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }

    private AdView createAdView() {
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getAdMobUnitId());

        return adView;
    }

    private RelativeLayout.LayoutParams getAdParams() {
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        return adParams;
    }

    /*@Override
    public void onSignInFailed() {
        // handle sign-in failure (e.g. show Sign In button)
        mLeaderboardRequested = false;
        mAchievementsRequested = false;
    }*/

    /*@Override
    public void onSignInSucceeded() {
        // handle sign-in success
        if (GameManager.getInstance().hasSavedMaxScore()) {
            GameManager.getInstance().submitSavedMaxScore();
        }

        if (mLeaderboardRequested) {
            displayLeaderboard();
            mLeaderboardRequested = false;
        }

        if (mAchievementsRequested) {
            displayAchievements();
            mAchievementsRequested = false;
        }
    }*/

    @Override
    public void displayAd() {
        mAdView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAd() {
        mAdView.setVisibility(View.GONE);
    }

    @Override
    public void submitScore(int score) {
            GameManager.getInstance().saveScore(score);
    }

    @Override
    public void displayLeaderboard() {
            mLeaderboardRequested = true;
    }

    @Override
    public void displayAchievements() {
    }

    @Override
    public void share() {/*
        String url = String.format("http://play.google.com/store/apps/details?id=%s",
                BuildConfig.APPLICATION_ID);
        String message = String.format(Constants.SHARE_MESSAGE_PREFIX, url);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, Constants.SHARE_TITLE));*/
    }

    @Override
    public void unlockAchievement(String id) {
    }

    @Override
    public void incrementAchievement(String id, int steps) {
    }

    @Override
    public String getGettingStartedAchievementId() {
        return "";
    }

    @Override
    public String getLikeARoverAchievementId() {
        return "";
    }

    @Override
    public String getSpiritAchievementId() {
        return "";
    }

    @Override
    public String getCuriosityAchievementId() {
        return "";
    }

    @Override
    public String get5kClubAchievementId() {
        return "";
    }

    @Override
    public String get10kClubAchievementId() {
        return "";
    }

    @Override
    public String get25kClubAchievementId() {
        return "";
    }

    @Override
    public String get50kClubAchievementId() {
        return "";
    }

    @Override
    public String get10JumpStreetAchievementId() {
        return "";
    }

    @Override
    public String get100JumpStreetAchievementId() {
        return "";
    }

    @Override
    public String get500JumpStreetAchievementId() {
        return "";
    }

    private String getAdMobUnitId() {
        return "";
    }

}
