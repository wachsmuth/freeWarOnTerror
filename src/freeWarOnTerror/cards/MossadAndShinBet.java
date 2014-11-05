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
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.ISRAEL;
import static freeWarOnTerror.helpers.CONSTANTS.JORDAN;
import static freeWarOnTerror.helpers.CONSTANTS.LEBANON;
import static freeWarOnTerror.helpers.CONSTANTS.MOSSADANDSHINBET;

/**
 *
 * @author Emil
 */
public class MossadAndShinBet extends Card {

    public MossadAndShinBet() {
        super("Mossad & Shin Bet", 2, 2, false, false, MOSSADANDSHINBET);
    }

    @Override
    public Boolean getPlayable() {
        return getCountry(ISRAEL).hasCells() || getCountry(JORDAN).hasCells() || getCountry(LEBANON).hasCells();
    }

    @Override
    public void playEvent() {
        getCountry(ISRAEL).killAllCells();
        getCountry(JORDAN).killAllCells();
        getCountry(LEBANON).killAllCells();
    }
}
