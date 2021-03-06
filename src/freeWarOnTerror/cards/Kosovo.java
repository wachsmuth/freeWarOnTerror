/*
 * Copyright (C) 2014 Emil Istrup Karlsmose
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror.cards;

import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Posture.HARD;
import static freeWarOnTerror.helpers.Posture.SOFT;

/**
 *
 * @author Emil
 */
public class Kosovo extends Card {

    public Kosovo() {
        super(CardLookup.KOSOVO);
    }

    @Override
    public void playEvent() {
        modifyPrestige(1);
        NonMuslimCountry serbia = (NonMuslimCountry) Game.getCountry(CountryLookup.SERBIA);
        if (isPostureHard()) {
            serbia.setPosture(SOFT);
        }
        else {
            serbia.setPosture(HARD);
        }
    }
}
