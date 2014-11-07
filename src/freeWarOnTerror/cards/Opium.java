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

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AFGHANISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.OPIUM;

/**
 *
 * @author Emil
 */
public class Opium extends Card {

    public Opium() {
        super("Opium", 2, 3, false, false, OPIUM);
    }

    @Override
    public Boolean getPlayable() {
        return getCountry(AFGHANISTAN).hasCells();
    }

    @Override
    public void playEvent() {
        placeCell(getCountry(AFGHANISTAN));
        placeCell(getCountry(AFGHANISTAN));
        placeCell(getCountry(AFGHANISTAN));
        if (getCountry(AFGHANISTAN).getGovernance() == 4) {
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
            placeCell(getCountry(AFGHANISTAN));
        }
    }
}