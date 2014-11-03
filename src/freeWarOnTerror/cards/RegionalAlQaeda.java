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

import static freeWarOnTerror.Game.anyIslamistRule;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.REGIONALALQAEDA;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class RegionalAlQaeda extends Card {

    public RegionalAlQaeda() {
        super("Regional al-Qaeda", 3, 3, false, false, REGIONALALQAEDA);
    }

    @Override
    public Boolean getPlayable() {
        int untested = 0;
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.needsTesting()) {
                untested++;
            }
        }
        return untested > 1;
    }

    @Override
    public void playEvent() {
        for (int i = 0; i < 2; i++) {
            ArrayList<Country> untestedCountries = new ArrayList<>();
            for (MuslimCountry c : getMuslimCountries()) {
                if (c.needsTesting()) {
                    untestedCountries.add(c);
                }
            }
            Country targetCountry = inputLoop("Choose a untested country to place cells in", untestedCountries);
            if (anyIslamistRule()) {
                placeCell(targetCountry);
                placeCell(targetCountry);
            } else {
                placeCell(targetCountry);
            }
        }
    }
}
