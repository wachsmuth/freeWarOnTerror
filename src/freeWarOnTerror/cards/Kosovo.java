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

import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.Game;
import freeWarOnTerror.helpers.CONSTANTS;
import freeWarOnTerror.NonMuslimCountry;

/**
 *
 * @author Emil
 */
public class Kosovo extends Card {

    public Kosovo(Game game) {
        super("Kosovo", 1, 1, false);
    }

    @Override
    public void play() {
        Game.modifyPrestige(1);
        NonMuslimCountry serbia = (NonMuslimCountry) Game.getCountry(CONSTANTS.SERBIA);
        if (Game.isPostureHard()) {
            serbia.setPosture(1);
        }
        else {
            serbia.setPosture(2);
        }
    }
}
