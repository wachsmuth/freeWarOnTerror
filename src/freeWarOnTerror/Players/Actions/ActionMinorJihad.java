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
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public class ActionMinorJihad extends Action {

    public ActionMinorJihad() {
        super("Use ops for Jihad");
    }

    @Override
    public boolean canDoAction(Card c) {
        for (MuslimCountry country : getMuslimCountries()) {
            if (country.canMinorJihad()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performAction(Card c) {
        for (int i = 0; i < c.getOps(); i++) {
            List<MuslimCountry> eligibles = new ArrayList<>();
            for (MuslimCountry country : getMuslimCountries()) {
                if (country.canMinorJihad()) {
                    eligibles.add(country);
                }
            }
            //can we still do it?
            if (!canDoAction(c)){
                return;
            }
            //Check if cell dies or no
            attemptJihad((MuslimCountry) inputLoop("Choose a country for a Jihad attempt", eligibles));
        }

    }

    private void attemptJihad(MuslimCountry place) {
        //Choose cell
        System.out.println("Which cell?");
        Cell c = place.pickIdleCell();
        if (c == null){
            System.out.println("No cells anymore");
            return;
        }
        place.attemptMinorJihad(c);
    }

}
