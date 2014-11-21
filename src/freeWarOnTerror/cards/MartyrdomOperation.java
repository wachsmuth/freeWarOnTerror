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
import static freeWarOnTerror.Game.getTrack;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.helpers.CardLookup;
import static freeWarOnTerror.helpers.Governance.ISLAMISTRULE;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class MartyrdomOperation extends Card {

    public MartyrdomOperation() {
        super(CardLookup.MARTYRDOMOPERATION);
    }

    @Override
    public Boolean getPlayable() {
        for (Country c : getAllCountries()) {
            if (c.hasCells() && c.getGovernance() != ISLAMISTRULE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playEvent() {
        ArrayList<Cell> availableCells = new ArrayList<>();
        for (Country c : getAllCountries()) {
            if (c.hasCells() && c.getGovernance() != ISLAMISTRULE) {
                for (Cell cell : c.getCells()) {
                    availableCells.add(cell);
                }
            }
        }
        Cell targetCell = inputLoop(availableCells);
        Country targetCountry = (Country) targetCell.getLocation();
        targetCell.kill();
        targetCountry.placePlot(inputLoop(getTrack().getPlots()));
        targetCountry.placePlot(inputLoop(getTrack().getPlots()));
    }
}
