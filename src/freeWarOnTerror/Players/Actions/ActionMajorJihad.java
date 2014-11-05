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
public class ActionMajorJihad extends Action {

    public ActionMajorJihad() {
        super("Use ops for Major Jihad");
    }
    @Override
    public boolean canDoAction(Card c) {
        for (MuslimCountry country : getMuslimCountries()) {
            if (country.canMajorJihad(c.getOps())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void performAction(Card c) {
        List<MuslimCountry> targetCountries = new ArrayList<>();
            List<MuslimCountry> eligibles = new ArrayList<>();
            for (MuslimCountry country : getMuslimCountries()) {
                if (country.canMajorJihad(c.getOps())) {
                    eligibles.add(country);
                }
            }
            //Check if cell dies or no
            attemptMajorJihad((MuslimCountry) inputLoop("Choose a country for a Jihad attempt", eligibles), c.getOps());
    }

    private void attemptMajorJihad(MuslimCountry place, int ops) {
        //Choose cells
        int loopMax = Math.min(place.cellAmount(), ops);
        List<Cell> cells = new ArrayList<>();
        
        System.out.println("Add cells to the attempt:");
        for (int i = 0; i < loopMax; i++){
            Cell addCell = place.pickIdleCell();
            if (addCell == null) { 
                System.out.println("something went horribly wrong");
                return; }//Something went horribly wrong
            cells.add(place.pickIdleCell());
        }
        place.attemptMajorJihad(cells, ops);
    }
}
