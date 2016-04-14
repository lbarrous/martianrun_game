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

package com.gamestudio24.martianrun.actors.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamestudio24.martianrun.utils.AssetsManager;
import com.gamestudio24.martianrun.utils.Constants;
import com.gamestudio24.martianrun.utils.SaveScore;

import java.util.ArrayList;

public class LeaderBoardLabel extends Actor {

    private Rectangle bounds;
    private BitmapFont font;
    private SaveScore scores = new SaveScore();

    public LeaderBoardLabel(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        font = AssetsManager.getSmallFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        String s_scores = "";
        s_scores += "Mejor puntuacion: "+scores.getHighScore()+"\n";
        ArrayList<Integer> a_scores = scores.getScores();
        for(int i=0; i<a_scores.size(); i++) {
            s_scores += "Puntuacion "+(i+1)+": "+a_scores.get(i)+"\n";
        }
        font.drawWrapped(batch, s_scores, bounds.x, bounds.y, bounds.width, BitmapFont.HAlignment.CENTER);
    }

}