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

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.SPECIALFORCES;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class SpecialForces extends Card {

    public SpecialForces() {
        super("Special Forces", 1, 2, false, false, SPECIALFORCES);
    }

    @Override
    public Boolean getPlayable() {
        for (Country c : getAllCountries()) {
            if (c.hasCells()) {
                if (c.hasTroops()) {
                    return true;
                }
                for (Country x : c.getAdjacentCountries()) {
                    if (x.hasTroops()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void playEvent() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (Country c : getAllCountries()) {
            if (c.hasCells()) {
                if (c.hasTroops()) {
                    for (Cell cell : c.getCells()) {
                        cells.add(cell);
                    }
                } else {
                    for (Country x : c.getAdjacentCountries()) {
                        if (x.hasTroops()) {
                            for (Cell cell : c.getCells()) {
                                cells.add(cell);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (cells.size() > 0) {
            System.out.println("Choose a cell to kill");
            inputLoop(cells).kill();
        }
    }
}
