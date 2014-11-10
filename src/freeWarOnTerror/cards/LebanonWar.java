/*
 * Copyright (C) 2014 Emil
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

import static freeWarOnTerror.Game.canPlaceCell;
import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getUS;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.LEBANONWAR;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class LebanonWar extends Card {

    public LebanonWar() {
        super("Lebanon War", 3, 3, false, false, LEBANONWAR);
    }

    @Override
    public void playEvent() {
        getCurrentPlayer().discard(getUS().getRandomCard());
        modifyPrestige(-1);
        if (canPlaceCell()) {
            ArrayList<Country> shiaCountries = new ArrayList<>();
            for (MuslimCountry c : getMuslimCountries()) {
                if (c.getShiaMix()) {
                    shiaCountries.add(c);
                }
            }
            System.out.println("Choose a country to place a cell in");
            placeCell(inputLoop(shiaCountries));
        }
    }
}
