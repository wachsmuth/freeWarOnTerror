/*
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
import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.FAIR;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.POOR;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public class ActionJihad extends Action {

    public ActionJihad() {
        super("Use ops for Jihad");
    }

    @Override
    public boolean canDoAction(Card c) {
        for (MuslimCountry country : getMuslimCountries()) {
            if (country.getGovernance() == POOR || country.getGovernance() == GOOD || country.getGovernance() == FAIR || country.hasAid()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performAction(Card c) {
        List<MuslimCountry> targetCountries = new ArrayList<>();
        for (int i = 0; i < c.getOps(); i++) {
            List<MuslimCountry> eligibles = new ArrayList<>();
            for (MuslimCountry country : getMuslimCountries()) {
                if (country.canMinorJihad()) {
                    eligibles.add(country);
                }
            }
            //Check if cell dies or no
            attemptJihad((MuslimCountry) inputLoop("Choose a country for a Jihad attempt", eligibles));
        }

    }

    private void attemptJihad(MuslimCountry place) {
        //Choose cell
        System.out.println("Which cell?");
        int count = 0;
        List<Cell> cells = place.getCells();
        for (Cell c : cells){
            System.out.println(count++ + " " + c);
        }
        //place.attemptJihad(inputLoop)
    }

}
