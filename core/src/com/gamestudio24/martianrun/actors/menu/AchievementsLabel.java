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

public class AchievementsLabel extends Actor {

    private Rectangle bounds;
    private BitmapFont font;
    private SaveScore scores = new SaveScore();

    public AchievementsLabel(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        font = AssetsManager.getSmallFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        String achievements = "";
        if(!scores.isrreach500() && !scores.isrreach1000() && !scores.isrreach2500() && !scores.isrreach5000()) {
            achievements += "No hay logros desbloqueados.\n";
        }
        else {
            if (scores.isrreach500())
                achievements += "Logro '500' desbloqueado\n";
            if (scores.isrreach1000())
                achievements += "Logro '1000' desbloqueado\n";
            if (scores.isrreach2500())
                achievements += "Logro '2500' desbloqueado\n";
            if (scores.isrreach5000())
                achievements += "Logro '5000' desbloqueado\n";
        }
        font.drawWrapped(batch, achievements, bounds.x, bounds.y, bounds.width, BitmapFont.HAlignment.CENTER);
    }

}