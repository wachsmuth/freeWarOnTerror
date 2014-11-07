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
package freeWarOnTerror.Players.Actions;

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.ISLAMISTRULE;
import static freeWarOnTerror.helpers.Die.rollDie;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionTravel extends Action {

    public ActionTravel() {
        super("Use ops for travel");
    }

    @Override
    public boolean canDoAction(Card c) {
        return true;
    }

    @Override
    public void performAction(Card c) {
        Cell[] chosenCells = new Cell[c.getOps()];
        Country[] chosenOrigins = new Country[c.getOps()];
        Country[] chosenDestinations = new Country[c.getOps()];
        for (int i = 0; i < c.getOps(); i++) {
            ArrayList<Cell> availableCells = new ArrayList<>();
            for (Country country : getAllCountries()) {
                if (country.hasCells()) {
                    for (Cell cell : country.getCells()) {
                        if (cell.isIdle()) {
                            availableCells.add(cell);
                        }
                    }
                }
            }
            System.out.println("Choose the cell to travel");
            chosenCells[i] = inputLoop(availableCells);
            chosenOrigins[i] = (Country) chosenCells[i].getLocation();
            System.out.println("Choose destination");
            chosenDestinations[i] = inputLoop(getAllCountries());
            chosenCells[i].setIdle(false);
        }
        for (int i = 0; i < c.getOps(); i++) {
            chosenDestinations[i].testCountry();
            boolean isTravelAutomatic = false;
            if (chosenOrigins[i] == chosenDestinations[i]) {
                isTravelAutomatic = true;
            } else if (chosenOrigins[i].isCountryAdjacent(chosenDestinations[i])) {
                isTravelAutomatic = true;
            } else if (chosenDestinations[i].getGovernance() == ISLAMISTRULE) {
                isTravelAutomatic = true;
            }
            if (isTravelAutomatic){
                chosenCells[i].move(chosenDestinations[i]);
            }
            else {
                if (chosenDestinations[i].getGovernance() >= rollDie()){
                    chosenCells[i].move(chosenDestinations[i]);
                }
                else {
                    chosenCells[i].kill();
                }
            }

        }
    }
}
